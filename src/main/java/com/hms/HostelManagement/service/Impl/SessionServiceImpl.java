package com.hms.HostelManagement.service.Impl;

import com.hms.HostelManagement.model.Session;
import com.hms.HostelManagement.repository.SessionRepository;
import com.hms.HostelManagement.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionServiceImpl implements SessionService{
    @Autowired
    SessionRepository sessionRepository;

    public SessionServiceImpl(SessionRepository sessionRepository) {
        super();
        this.sessionRepository=sessionRepository;
    }

    @Override
    public List<Session> getAllSession() {
        return sessionRepository.getAllSession();
    }

//    @Override
//    public Session getSessionById(Integer sessionId) {
//        return sessionService.getSessionById(sessionId);
//    }

    @Override
    public void createSession(Session session) {
        sessionRepository.createSession(session);
    }
//    @Override
//    public Session updateSession(Session session) {
//        return sessionService.updateSession(session);
//    }
//    @Override
//    public void deleteSession(Integer sessionId) {
//        sessionService.deleteSession(sessionId);
//    }

}
