package com.hms.HostelManagement.service;

import com.hms.HostelManagement.model.Student;

public interface StudentService {
    public Student getStudentByRollNo(String rollNo);

    public void deleteStudent(Student student);

    public void updateStudent(Student student) ;
}
