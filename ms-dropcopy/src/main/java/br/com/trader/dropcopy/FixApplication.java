package br.com.trader.dropcopy;

import quickfix.*;

public class FixApplication extends quickfix.MessageCracker implements quickfix.Application {

    @Override
    public void onCreate(SessionID sessionId) {
        System.out.println("onCreate: " + sessionId);
    }

    @Override
    public void onLogon(SessionID sessionId) {
        System.out.println("onLogon: " + sessionId);
    }

    @Override
    public void onLogout(SessionID sessionId) {
        System.out.println("onLogout: " + sessionId);
    }

    @Override
    public void toAdmin(Message message, SessionID sessionId) {
        System.out.println("toAdmin: " + message);
    }

    @Override
    public void fromAdmin(Message message, SessionID sessionId) {
        System.out.println("fromAdmin: " + message);
    }

    @Override
    public void toApp(Message message, SessionID sessionId) {
        System.out.println("toApp: " + message);
    }

    @Override
    public void fromApp(Message message, SessionID sessionId) throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, UnsupportedMessageType {
        crack(message, sessionId);
        System.out.println("fromApp: " + message);
    }
}

