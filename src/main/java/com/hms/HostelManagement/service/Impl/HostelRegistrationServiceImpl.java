package com.hms.HostelManagement.service.Impl;

import com.hms.HostelManagement.model.HostelRegistration;
import com.hms.HostelManagement.repository.HostelRegistrationRepository;
import com.hms.HostelManagement.service.HostelRegistrationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HostelRegistrationServiceImpl implements HostelRegistrationService {

    private HostelRegistrationRepository hostelRegistrationRepository;

    public HostelRegistrationServiceImpl(HostelRegistrationRepository hostelRegistrationRepository) {
        super();
        this.hostelRegistrationRepository = hostelRegistrationRepository;
    }

    @Override
    public List<HostelRegistration> getAllRegisteredHostel() {
        return hostelRegistrationRepository.getAllRegisteredHostels();
    }

    @Override
    public void createHostelRegistration(HostelRegistration hr) {
        hostelRegistrationRepository.createHostelRegistration(hr);
    }
}
