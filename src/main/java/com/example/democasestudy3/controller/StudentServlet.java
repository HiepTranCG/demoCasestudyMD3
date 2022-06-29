package com.example.democasestudy3.controller;

import com.example.democasestudy3.model.Clazz;
import com.example.democasestudy3.model.Student;
import com.example.democasestudy3.service.ClassService;
import com.example.democasestudy3.service.ClassServiceImpl;
import com.example.democasestudy3.service.StudentService;
import com.example.democasestudy3.service.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "StudentServlet", urlPatterns = "/students")
public class StudentServlet extends HttpServlet {
    StudentService studentService = new StudentServiceImpl();
    ClassService classService = new ClassServiceImpl();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String act = request.getParameter("act");
        if(act == null) {
            act = "";
        }
        switch (act) {
            case "create":
                try {
                    create(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            default:
                showList(request, response);
        }
    }

    private void create(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        int classId = Integer.parseInt(request.getParameter("classId"));
        Clazz clazz = classService.findById(classId);
        studentService.add(new Student(0, name, age, clazz));
        response.sendRedirect("/home");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String act = request.getParameter("act");
        if(act == null) {
            act = "";
        }
        switch (act) {
            case "create":
                showCreateFrom(request, response);
                break;
            case "view":
                view(request, response);
                break;
            default:
                showList(request, response);
        }
    }

    private void view(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Student student = studentService.findById(id);
        request.setAttribute("student", student);
        List<Clazz> classes = classService.findAll();
        request.setAttribute("classes", classes);
        request.getRequestDispatcher("student/view.jsp").forward(request, response);
    }

    private void showCreateFrom(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Clazz> classes = classService.findAll();
        request.setAttribute("classes", classes);
        request.getRequestDispatcher("student/create.jsp").forward(request, response);
    }

    private void showList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Student> students = studentService.findAll();
        request.setAttribute("ds", students);
        request.getRequestDispatcher("student/list.jsp").forward(request, response);
    }
}
