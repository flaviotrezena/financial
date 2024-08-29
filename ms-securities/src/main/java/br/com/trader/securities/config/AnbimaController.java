package br.com.trader.securities.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnbimaController {
    @Autowired
    private AnbimaService anbimaService;

    @GetMapping("/get-data")
    public String getData(@RequestParam("param") String param) {
        return anbimaService.getData(param);
    }
}