package com.hms.HostelManagement.service;

import com.hms.HostelManagement.model.HostelRegistration;

import java.util.List;

public interface HostelRegistrationService {
    List<HostelRegistration>getAllRegisteredHostel();

    public void createHostelRegistration(HostelRegistration hr);
}
