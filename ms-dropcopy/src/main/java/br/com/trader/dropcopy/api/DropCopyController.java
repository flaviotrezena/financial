package br.com.trader.dropcopy.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.trader.dropcopy.model.Trade;

@RestController
public class DropCopyController {

	@GetMapping("/trade/{id}")
	Trade getTrade(@PathVariable Long id) {
		return null;
	}
	
	@GetMapping("/trades")
	Trade getTrade() {
		return null;
	}

}
