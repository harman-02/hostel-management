package com.hms.HostelManagement.service;

import com.hms.HostelManagement.model.Session;

import java.util.List;

public interface SessionService {
    List<Session> getAllSession();
//
//    Session getSessionById(Integer sessionId);
    void createSession(Session session);
//    Session updateSession(Session session);
//    void deleteSession(Integer sessionId);
}
