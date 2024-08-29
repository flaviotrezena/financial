package br.com.trader.securities.config;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnbimaService {
    @Autowired
    private AnbimaClient anbimaClient;

    public String getData(String param) {
        return anbimaClient.getData(param);
    }
}