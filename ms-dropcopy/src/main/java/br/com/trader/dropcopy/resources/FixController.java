package br.com.trader.dropcopy.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import quickfix.ConfigError;
import quickfix.Initiator;

@RestController
@RequestMapping("/fix")
public class FixController {

    @Autowired
    private Initiator initiator;

    @GetMapping("/start")
    public String startSession() {
        try {
            initiator.start();
            return "FIX session started.";
        } catch (ConfigError configError) {
            configError.printStackTrace();
            return "Error starting FIX session.";
        }
    }

    @GetMapping("/stop")
    public String stopSession() {
        initiator.stop();
        return "FIX session stopped.";
    }
}
