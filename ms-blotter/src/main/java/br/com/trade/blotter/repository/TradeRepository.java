package br.com.trade.blotter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.trader.blotter.model.Trade;


@Repository
public interface TradeRepository extends JpaRepository<Trade, Long>{

	 List<Trade> findByTicket(String ticket);
	 
}
