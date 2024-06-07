package com.airlist.controller;

import com.airlist.model.Database;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class Login extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        boolean isValidUser = Database.login(username,password);

        HttpSession session = req.getSession();

        session.setAttribute("isValidUser","false");

        if(isValidUser){
            session.setAttribute("isValidUser", "true");

            resp.sendRedirect("home");

        }else{
            resp.sendRedirect("unauthenticated.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("unauthenticated.jsp");
    }
}
