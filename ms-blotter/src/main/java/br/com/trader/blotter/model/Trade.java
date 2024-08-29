package br.com.trader.blotter.model;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "trades")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Trade {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long tradeId;

 @Column(name = "side", nullable = false)
 private String side; // Could be "BUY" or "SELL"

 @Column(name = "ticker", nullable = false)
 private String ticker;

 @Column(name = "quantity", nullable = false)
 private double quantity;

 @Column(name = "price", nullable = false)
 private double price;

 @Column(name = "pu", nullable = false)
 private double pu;

 @Column(name = "transact_time")
 private LocalDateTime transactTime;

 @Column(name = "status")
 private String status; // Optional: To track the status of the trade
}
