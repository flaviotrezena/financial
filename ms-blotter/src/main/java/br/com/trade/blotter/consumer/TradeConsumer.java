package br.com.trade.blotter.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import br.com.trade.blotter.repository.TradeRepository;
import br.com.trader.blotter.model.Trade;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TradeConsumer {

	private final TradeRepository tradeRepository;

    public TradeConsumer(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    @KafkaListener(topics = "trade.out", groupId = "blotter_consumer_group")
    public void consume(Trade trade) {
        tradeRepository.save(trade);
        log.info("Trade data saved: " + trade.getTradeId());
    }
}
