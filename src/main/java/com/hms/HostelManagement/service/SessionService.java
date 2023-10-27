package com.hms.HostelManagement.service;

import com.hms.HostelManagement.model.Session;

import java.util.List;

public interface SessionService {

    public void createSession(Session s);
    List<Session> getAllSession();

    public Session getSessionFromId(int id);

}
