package com.hms.HostelManagement.service;

import com.hms.HostelManagement.model.HostelRegistration;

public interface HostelRegistrationService {
    public int  getHostelIDFromHostelRegistrationId(Integer HostelRegistrationId);
    public int getSessionFromHostelRegistrationId(Integer HostelRegistrationId);
}
