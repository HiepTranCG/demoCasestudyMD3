package com.example.democasestudy3.controller;

import com.example.democasestudy3.model.Clazz;
import com.example.democasestudy3.model.Student;
import com.example.democasestudy3.service.ClassService;
import com.example.democasestudy3.service.ClassServiceImpl;
import com.example.democasestudy3.service.StudentService;
import com.example.democasestudy3.service.StudentServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "HomeServlet", urlPatterns = "/home")
public class HomeServlet extends HttpServlet {
    ClassService classService = new ClassServiceImpl();
    StudentService studentService = new StudentServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String classId = request.getParameter("classId");
        String findName = request.getParameter("findName");
        List<Clazz> classes = classService.findAll();
        request.setAttribute("classes", classes);
        List<Student> students = studentService.findAll();
        if(classId != null) {
            students = studentService.findAllByClass(Integer.parseInt(classId));
        }
        if(findName != null) {
            students = studentService.findAllByNameContains(findName);
        }
        request.setAttribute("students", students);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
