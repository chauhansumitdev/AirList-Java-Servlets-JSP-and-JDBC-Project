package com.airlist.controller;

import com.airlist.model.Database;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/update")
public class UpdateRecord extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String verifyUser = (String) session.getAttribute("isValidUser");

        if(verifyUser != null && verifyUser.equals("true")){
            req.getRequestDispatcher("updaterecord.jsp").forward(req,resp);
        }else{
            req.getRequestDispatcher("unauthenticated.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String arrivalDate = req.getParameter("arrivalDate");
        String companyName = req.getParameter("companyName");
        String nextDate = req.getParameter("nextDate");
        String status = req.getParameter("status");
        int tag = Integer.parseInt(req.getParameter("tag"));
        String role = req.getParameter("role");
        String description = req.getParameter("description");

        System.out.println(id + " "+ arrivalDate+ " "+nextDate);

        boolean success = Database.updateRecord(id, arrivalDate, companyName, nextDate, status, tag, role, description);
        if (success) {
            resp.getWriter().write("Record updated successfully");
        } else {
            resp.getWriter().write("Failed to update record");
        }
    }

}
