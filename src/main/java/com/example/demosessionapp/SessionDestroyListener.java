package com.example.demosessionapp;

import org.springframework.context.ApplicationListener;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.session.SessionDestroyedEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.List;

@Component
public class SessionDestroyListener implements ApplicationListener<SessionDestroyedEvent> {
    @Override
    public void onApplicationEvent(SessionDestroyedEvent event) {
        System.out.println("Session Destroyed: " + event.getId() + " : " + event);
        final List<SecurityContext> contexts = event.getSecurityContexts();
        if (!contexts.isEmpty()) {
            System.out.println("Session Destroyed: " + contexts.size());
            for (SecurityContext ctx : contexts) {
                final UserDetails ud = (UserDetails) ctx.getAuthentication().getPrincipal();
                System.out.println("Session Destroyed: " + ud.getUsername());
                if (RequestContextHolder.getRequestAttributes() == null) {
                    System.out.println("Session Destroyed: TimeOut");
                } else {
                    System.out.println("Session Destroyed: LogOut");
                }
            }
        }
    }
}
