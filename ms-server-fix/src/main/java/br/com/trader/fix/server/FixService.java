package br.com.trader.fix.server;

import java.io.InputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import quickfix.DefaultMessageFactory;
import quickfix.FileLogFactory;
import quickfix.FileStoreFactory;
import quickfix.LogFactory;
import quickfix.MessageFactory;
import quickfix.MessageStoreFactory;
import quickfix.SessionSettings;
import quickfix.ThreadedSocketAcceptor;


@Service
public class FixService {

    private ThreadedSocketAcceptor acceptor;

    @Autowired
    private FixApplication fixApplication;

    @PostConstruct
    public void start() throws Exception {
    	try (InputStream inputStream = getClass().getResourceAsStream("/quickfix.cfg")) {
            if (inputStream == null) {
                throw new RuntimeException("quickfix.cfg not found in the classpath");
            }

            SessionSettings settings = new SessionSettings(inputStream);
            MessageStoreFactory storeFactory = new FileStoreFactory(settings);
            LogFactory logFactory = new FileLogFactory(settings);
            MessageFactory messageFactory = new DefaultMessageFactory();

            acceptor = new ThreadedSocketAcceptor(fixApplication, storeFactory, settings, logFactory, messageFactory);
            acceptor.start();
            System.out.println("FIX Acceptor started successfully");
        } catch (Exception e) {
            System.err.println("Error starting FIX Acceptor: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to start FIX Acceptor", e);
        }
    }

    @PreDestroy
    public void stop() {
        if (acceptor != null) {
            acceptor.stop();
            System.out.println("FIX Acceptor stopped successfully");
        }
    }
}
