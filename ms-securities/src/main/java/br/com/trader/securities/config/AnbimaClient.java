package br.com.trader.securities.config;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "anbimaClient", url = "https://api-sandbox.anbima.com.br", configuration = br.com.trader.securities.config.FeignConfig.class)
public interface AnbimaClient {
    @GetMapping("/feed/precos-indices/v1/titulos-publicos/curva-intradiaria") // Replace with the actual endpoint
    String getData(@RequestParam("param") String param);
}
