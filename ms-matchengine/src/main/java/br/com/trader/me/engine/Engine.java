package br.com.trader.me.engine;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

import br.com.trader.me.engine.model.Order;
import br.com.trader.me.engine.model.Security;
import br.com.trader.me.engine.model.Trade;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Engine {

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	static final String TRADE_OUT_TOPIC = "trade.out";

	String type;

	List<Security> listOfSecurities = new ArrayList<>();

	LinkedList<Order> bookOfOrders = new LinkedList<>();

	LinkedList<Trade> bookOfTrades = new LinkedList<>();

	public boolean start() {
		try {
			// Load securities at the start of the engine TODO refactor this part
			listOfSecurities.add(new Security(1, "ALGA12", 2.1, "CORP"));
			listOfSecurities.add(new Security(2, "PETRO11", 5.1, "CORP"));
			listOfSecurities.add(new Security(3, "BNDP21", 19.1, "CORP"));

			// Load more securities TODO refactor this part
			listOfSecurities.add(new Security(4, "NTNB12", 100.5, "GOV"));
			listOfSecurities.add(new Security(5, "NTNB13", 9232.12, "GOV"));
			listOfSecurities.add(new Security(6, "NTNB14", 1345243.40, "GOV"));
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	public Engine(String typeEngine) {
		this.type = typeEngine;
	}

	public void process(Order order) {
		bookOfOrders.add(order);
		process(bookOfOrders);
	}

	private void process(LinkedList<Order> book) {
		MatchOrdersByPriceAndQuantityWithOpositeSide(book);
	}


	/* return matches that have the same quantity and price, but opposite side */
	private void MatchOrdersByPriceAndQuantityWithOpositeSide(LinkedList<Order> processBookForTicker) {
		LinkedList<Order> matchOrders = processBookForTicker.stream()
        .filter(order -> processBookForTicker.stream()
                               .filter(o -> o.getPrice() == order.getPrice() 
                               				&& o.getQuantity() == order.getQuantity() 
                               				&& (o.getSide() != order.getSide()))
                               .count() > 0)
        .collect(Collectors.toCollection(LinkedList::new));
		
		//found matches! Let's trade!
		if (!matchOrders.isEmpty()) {
			Trade newTrade = new Trade();
			Order sellOrder = (Order) matchOrders.stream().filter(sello -> "SELL".equals(sello.getSide())).findFirst().get();
			Order buyOrder = (Order) matchOrders.stream().filter(buyo -> "BUY".equals(buyo.getSide())).findFirst().get();
			
			
			newTrade.setTakerOrderId(buyOrder.getOwner());
			newTrade.setMakerOrderId(sellOrder.getOwner());
			newTrade.setAmount(sellOrder.getPu());
			newTrade.setPrice(sellOrder.getPrice());
			newTrade.setTransactTime(LocalDateTime.now());
			newTrade.setQuantity(buyOrder.getQuantity());
			newTrade.setId(getTotalOrders());
			if (buyOrder.getTransactTime().isBefore(sellOrder.getTransactTime())) {
				newTrade.setSide("BUY");
			}else {
				newTrade.setSide("SELL");
			}
			newTrade.setId(new Random().nextLong());

			log.info("Generating a Trade for " + buyOrder.getOwner() + " x " + sellOrder.getOwner());

			//storing new Trade
			bookOfTrades.add(newTrade);
			
			sendTrade(newTrade);
			
			//remove Orders processed
			log.info("Removing Buyer order processed ? " + bookOfOrders.remove(buyOrder));
			log.info("Removing Seller order processed ? " + bookOfOrders.remove(sellOrder));
			
			/*Iterator<Order> iterator = bookOfOrders.iterator();
	        while (iterator.hasNext()) {
	            Order order = iterator.next();
	            if (order.getClOrdId() == sellOrder.getClOrdId()) {
	                iterator.remove();
	                System.out.println("Removing SELL order processed!");
	            }else {
	                iterator.remove();
	                System.out.println("Removing BUY order processed!");
	            }
	        }*/
		}else {
			log.warn("without matches...");
		}
	}


	public boolean sendTrade(Trade newTrade) {
		try {
			
            CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(TRADE_OUT_TOPIC, newTrade);

            future.whenComplete((result, ex) -> {
                if (ex == null) {
                    log.info("Trade sent to Kafka: "+ newTrade);
                } else {
                    log.error("Error sending newOrder to Kafka: " + ex.getMessage());
                }
            });

            // Block until the future is completed to ensure the result
            future.get();
            return future.isDone() && !future.isCompletedExceptionally();
        } catch (Exception e) {
            log.error("Error sending Order to Kafka: {}", e.getMessage());
            return false;
        }
	}

	
	private LinkedList<Order> getListOfOrdersByTicker(String ticker) {
		log.info("Getting orders for Ticker " + ticker);
		
		LinkedList<Order> result = bookOfOrders.stream().filter(order -> order.getTicker().equals(ticker))
				.collect(Collectors.toCollection(LinkedList::new));
		
		log.info("Got " + result.size() + " orders.");
		
		return result;
	}

	private LinkedList<Order> getListOfOrdersByClOrdId(String clOrdId) {
		return bookOfOrders.stream().filter(order -> order.getClOrdId().equals(clOrdId))
				.collect(Collectors.toCollection(LinkedList::new));
	}

	private Security getSecurity(String ticker) {
		return listOfSecurities.stream().filter(security -> security.getTicker().equals(ticker)).findFirst()
				.orElse(null);
	}

	public int getTotalOrders() {
		return bookOfOrders.size();
	}
	
	public int getTotalTrades() {
		return bookOfTrades.size();
	}
	
	private static class OrderKey {
	    private final double price;
	    private final double quantity;

	    public OrderKey(double price, double quantity) {
	        this.price = price;
	        this.quantity = quantity;
	    }

	    @Override
	    public boolean equals(Object obj) {
	        if (this == obj) return true;
	        if (obj == null || getClass() != obj.getClass()) return false;
	        OrderKey orderKey = (OrderKey) obj;
	        return Double.compare(orderKey.price, price) == 0 &&
	               quantity == orderKey.quantity;
	    }

	    @Override
	    public int hashCode() {
	        return Objects.hash(price, quantity);
	    }
	}
}

