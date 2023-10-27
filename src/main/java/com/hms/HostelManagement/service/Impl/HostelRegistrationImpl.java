package com.hms.HostelManagement.service.Impl;
import com.hms.HostelManagement.model.HostelRegistration;
import com.hms.HostelManagement.repository.HostelRegistrationRepository;
import com.hms.HostelManagement.service.HostelRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class HostelRegistrationImpl implements HostelRegistrationService{
    @Autowired
    HostelRegistrationRepository hostelRegistrationRepository;
    public int  getHostelIDFromHostelRegistrationId(Integer HostelRegistrationId){
        return hostelRegistrationRepository. getHostelIDFromHostelRegistrationId(HostelRegistrationId).getHostelId();
    }
    public int getSessionFromHostelRegistrationId(Integer HostelRegistrationId){
        return hostelRegistrationRepository. getSessionFromHostelRegistrationId(HostelRegistrationId).getSession();
    }
}
