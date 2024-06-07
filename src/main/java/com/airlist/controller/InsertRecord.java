package com.airlist.controller;

import com.airlist.model.Database;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


@WebServlet("/insert")
public class InsertRecord extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String validUser = (String)session.getAttribute("isValidUser");
        if(validUser!=null && validUser.equals("true") ){
            req.getRequestDispatcher("insertrecord.jsp").forward(req,resp);
        }else{
            req.getRequestDispatcher("unauthenticated.jsp").forward(req,resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String arrivalDate = req.getParameter("arrivalDate");
        String companyName = req.getParameter("companyName");
        String nextDate = req.getParameter("nextDate");
        String status = req.getParameter("status");
        int tag = Integer.parseInt(req.getParameter("tag"));
        String role = req.getParameter("role");
        String description = req.getParameter("description");

        boolean success = Database.insertRecord(arrivalDate, companyName, nextDate, status, tag, role, description);
        if (success) {
            resp.getWriter().write("Record inserted successfully");
        } else {
            resp.getWriter().write("Failed to insert record");
        }
    }

}
