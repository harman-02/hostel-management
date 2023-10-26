package com.hms.HostelManagement.service;

import com.hms.HostelManagement.model.Student;
import com.hms.HostelManagement.model.StudentUserMapping;

public interface StudentUserMappingService {

    public void createStudentUserMapping(StudentUserMapping s);
    public int getRollNoFromUsername(String username);

    Student getStudentFromUsername(String Username);
}
