package com.hms.HostelManagement.service;

import com.hms.HostelManagement.model.Student;
import com.hms.HostelManagement.model.StudentUserMapping;

import java.util.List;

public interface StudentUserMappingService {

    public void createStudentUserMapping(StudentUserMapping s);
    public int getRollNoFromUsername(String username);

    public Student getStudentFromUsername(String Username);

    public int getHostelRegIdFromUsername(String username);

    public List<StudentUserMapping> getAllStudentUserMapping();
}
