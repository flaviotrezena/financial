package br.com.trader.me.engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EngineRunner {

    private final Engine engine;

    @Autowired
    public EngineRunner(Engine engine) {
        this.engine = engine;
        engine.start();
    }
}