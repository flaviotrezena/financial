package br.com.trader.dropcopy.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.trader.dropcopy.FixApplication;
import quickfix.Application;
import quickfix.ConfigError;
import quickfix.DefaultMessageFactory;
import quickfix.Initiator;
import quickfix.LogFactory;
import quickfix.MemoryStoreFactory;
import quickfix.MessageFactory;
import quickfix.MessageStoreFactory;
import quickfix.SLF4JLogFactory;
import quickfix.SessionSettings;
import quickfix.SocketInitiator;

@Configuration
public class FixConfiguration {

    @Bean
    public SessionSettings sessionSettings() throws ConfigError {
        return new SessionSettings("fix-session.cfg");
    }

    @Bean
    public Application fixApplication() {
        return new FixApplication();
    }

    @Bean
    public MessageStoreFactory messageStoreFactory() {
        return new MemoryStoreFactory();
    }

    @Bean
    public LogFactory logFactory() {
        try {
			return new SLF4JLogFactory(sessionSettings());
		} catch (ConfigError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }

    @Bean
    public MessageFactory messageFactory() {
        return new DefaultMessageFactory();
    }

    @Bean
    public Initiator initiator() throws ConfigError {
        return new SocketInitiator(
            fixApplication(),
            messageStoreFactory(),
            sessionSettings(),
            logFactory(),
            messageFactory()
        );
    }
}

