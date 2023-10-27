package com.hms.HostelManagement.service.Impl;


import com.hms.HostelManagement.model.Student;
import com.hms.HostelManagement.repository.StudentRepository;
import com.hms.HostelManagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        super();
        this.studentRepository = studentRepository;
    }

    @Override
    public boolean checkRollNoExists(Integer roll) {
        Student s=studentRepository.getStudentFromRoll(roll);
        if(s==null)
            return false;
        else
            return true;
    }

    @Override
    public void createStudent(Student s) {
        studentRepository.createStudent(s);
    }

    @Override
    public Student getStudentFromRoll(Integer roll) {
        return studentRepository.getStudentFromRoll(roll);
    }

    @Override
    public void updateStudentByRoll(Student s, Integer roll) {
        studentRepository.updateStudentFromRoll(s,roll);
    }
}
