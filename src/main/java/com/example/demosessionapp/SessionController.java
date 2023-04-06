package com.example.demosessionapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
public class SessionController {

    @GetMapping("/session/create")
    public void createSession(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final HttpSession session = request.getSession(false);
        session.setAttribute("test", 0);
        response.sendRedirect("/session");
    }

    @GetMapping("/session")
    public String getSession(HttpSession session) {
        if (session == null) {
            return "Session is not created";
        }
        if (session.getAttribute("test") == null) {
            return "Session Data is not created";
        }
        int data = (int) session.getAttribute("test");
        session.setAttribute("test", ++data);
        return "Current Data in Session: " + data;
    }

    @GetMapping("/session/destroy")
    public void destroySession(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        response.sendRedirect("/login");
    }
}
