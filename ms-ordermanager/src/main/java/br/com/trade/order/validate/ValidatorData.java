package br.com.trade.order.validate;

import org.apache.commons.lang3.EnumUtils;

import br.com.trade.order.enums.Side;
import br.com.trade.order.model.Order;

public class ValidatorData {

	public static boolean isValidOrder(Order newOrder) {
		
		boolean isValid = true;
		
		if (newOrder == null) {
			isValid = false;
		}
		
		if (newOrder.getQuantity() == 0) {
			isValid = false;
		}

		if (newOrder.getTicker() == null || newOrder.getTicker().length()==0) {
			isValid = false;
		}
		
		if (newOrder.getSide() == null) {
			isValid = false;
		}
		
		EnumUtils.isValidEnum(Side.class, newOrder.getSide());

		return isValid;
	}
	
	
}
