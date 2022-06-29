package com.example.democasestudy3.service;

import com.example.democasestudy3.model.Student;
import java.util.List;

public interface StudentService extends GeneralService<Student> {
    List<Student> findAllByClass(int classId);
    List<Student> findAllByNameContains(String name);
}
