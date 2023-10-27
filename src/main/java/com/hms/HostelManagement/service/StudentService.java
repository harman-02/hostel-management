package com.hms.HostelManagement.service;

import com.hms.HostelManagement.model.Student;

public interface StudentService {
    public boolean checkRollNoExists(Integer roll);

    public void createStudent(Student s);

    public Student getStudentFromRoll(Integer roll);

    public void updateStudentByRoll(Student s,Integer roll);

}
