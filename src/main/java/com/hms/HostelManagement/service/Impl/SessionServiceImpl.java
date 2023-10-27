package com.hms.HostelManagement.service.Impl;

import com.hms.HostelManagement.model.Session;
import com.hms.HostelManagement.repository.SessionRepository;
import com.hms.HostelManagement.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionServiceImpl implements SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    public SessionServiceImpl(SessionRepository sessionRepository) {
        super();
        this.sessionRepository = sessionRepository;
    }

    @Override
    public void createSession(Session s) {
        sessionRepository.createSession(s);
    }

    @Override
    public List<Session> getAllSession() {
        return sessionRepository.getAllSession();
    }

    @Override
    public Session getSessionFromId(int id) {
        return sessionRepository.getSessionFromId(id);
    }
}
