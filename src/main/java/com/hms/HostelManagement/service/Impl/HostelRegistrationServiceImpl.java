package com.hms.HostelManagement.service.Impl;

import com.hms.HostelManagement.model.HostelRegistration;
import com.hms.HostelManagement.repository.HostelRegistrationRepository;
import com.hms.HostelManagement.service.HostelRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HostelRegistrationServiceImpl implements HostelRegistrationService {
    @Autowired
    private HostelRegistrationRepository hostelRegistrationRepository;

    public void registerHostel(HostelRegistration hostelRegistration){
        hostelRegistrationRepository.addRegistration(hostelRegistration);
    }

}
