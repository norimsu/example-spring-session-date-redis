package com.example.demosessionapp;

import org.springframework.context.ApplicationListener;
import org.springframework.session.events.SessionDeletedEvent;
import org.springframework.session.events.SessionDestroyedEvent;
import org.springframework.session.events.SessionExpiredEvent;
import org.springframework.stereotype.Component;

@Component
public class RedisSessionDestroyListener implements ApplicationListener<SessionDestroyedEvent> {

    @Override
    public void onApplicationEvent(SessionDestroyedEvent event) {
        if (event instanceof SessionDeletedEvent) {
            System.out.println("Session Destroyed: LogOut");
        }
        if (event instanceof SessionExpiredEvent) {
            System.out.println("Session Destroyed: TimeOut");
        }
    }
}