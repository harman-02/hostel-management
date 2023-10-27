package com.hms.HostelManagement.service.Impl;

import com.hms.HostelManagement.repository.StudentUserMappingRepository;
import com.hms.HostelManagement.service.StudentUserMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentUserMappingServiceImpl implements StudentUserMappingService {

    @Autowired
    private StudentUserMappingRepository studentUserMappingRepository;
    @Override
    public int getRollNofromUsername(String username) {
        return studentUserMappingRepository.getRollNoFromUsername(username).getRollNo();
    }

    @Override
    public int getHostelRegistrationIdFromUsername(String username){
        return studentUserMappingRepository.getRollNoFromUsername(username).getHostelRegistrationId();
    }




}
