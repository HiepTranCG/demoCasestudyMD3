package com.example.democasestudy3.controller;

import com.example.democasestudy3.model.Clazz;
import com.example.democasestudy3.service.ClassService;
import com.example.democasestudy3.service.ClassServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ClassServlet", urlPatterns = "/classes")
public class ClassServlet extends HttpServlet {
    ClassService classService = new ClassServiceImpl();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String act = request.getParameter("act");
        if(act == null) {
            act = "";
        }
        switch (act) {
            default:
                showList(request, response);
        }
    }

    private void showList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Clazz> classes = classService.findAll();
        request.setAttribute("ds", classes);
        request.getRequestDispatcher("class/list.jsp").forward(request, response);
    }
}
