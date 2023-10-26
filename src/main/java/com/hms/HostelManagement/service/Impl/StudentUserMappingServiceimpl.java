package com.hms.HostelManagement.service.Impl;

import com.hms.HostelManagement.model.Student;
import com.hms.HostelManagement.model.StudentUserMapping;
import com.hms.HostelManagement.repository.StudentUserMappingRepository;
import com.hms.HostelManagement.service.StudentUserMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentUserMappingServiceimpl implements StudentUserMappingService {

    @Autowired
    private StudentUserMappingRepository studentUserMappingRepository;

    @Override
    public void createStudentUserMapping(StudentUserMapping s) {
        studentUserMappingRepository.createStudentUserMapping(s);
    }

    @Override
    public int getRollNoFromUsername(String username) {
        return studentUserMappingRepository.getStudentUserMappingFromUsername(username).getRoll();
    }

    @Override
    public Student getStudentFromUsername(String username) {
        return studentUserMappingRepository.getStudentFromUsername(username);
    }


}
