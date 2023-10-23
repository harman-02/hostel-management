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
    public Student getStudentByRollNo(String rollNo) {
        Student student = studentRepository.getByRollNo(rollNo);
        return student;
    }
    @Override
    public void deleteStudent(Student student) {
        studentRepository.delete(student.getRoll());
    }
    @Override
    public void updateStudent(Student student) {
        studentRepository.update(student);
    }

    public void addStudent(Student student)
    {
        studentRepository.createStudent(student);
    }
}
