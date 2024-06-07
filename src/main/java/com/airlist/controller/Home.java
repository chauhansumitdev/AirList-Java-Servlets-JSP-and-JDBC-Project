package com.airlist.controller;

import com.airlist.model.Database;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.io.IOException;

@WebServlet("/home")
public class Home extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        String checkUser = (String) session.getAttribute("isValidUser");

        if(checkUser != null && checkUser.equals("true")){
            List<String[]> data = Database.retrieveRecords();

            resp.setContentType("text/html");
            StringBuilder html = new StringBuilder();
            html.append("<!DOCTYPE html>");
            html.append("<html>");
            html.append("<head>");
            html.append("<title>Data Cards</title>");
            html.append("<style>");
            html.append(".card { border: 1px solid #ccc; border-radius: 5px; padding: 16px; margin: 16px; width: 300px; box-shadow: 2px 2px 12px #aaa; }");
            html.append(".card-container { display: flex; flex-wrap: wrap; }");
            html.append(".status-tag-1 { background-color: yellow; color: black; padding: 5px; border-radius: 5px; }");
            html.append(".status-tag-2 { background-color: green; color: white; padding: 5px; border-radius: 5px; }");
            html.append(".status-tag-3 { background-color: grey; color: white; padding: 5px; border-radius: 5px; }");
            html.append("</style>");
            html.append("</head>");
            html.append("<body>");
            html.append("<div class='card-container'>");

            for (String[] row : data) {
                html.append("<div class='card'>");
                html.append("<h3>Company Name: ").append(row[2]).append("</h3>");
                html.append("<p>ID: ").append(row[0]).append("</p>");
                html.append("<p>Arrival Date: ").append(row[1]).append("</p>");
                html.append("<p>Next Date: ").append(row[3]).append("</p>");

                String statusTagClass;
                switch (row[5]) {
                    case "1":
                        statusTagClass = "status-tag-1";
                        break;
                    case "2":
                        statusTagClass = "status-tag-2";
                        break;
                    case "3":
                        statusTagClass = "status-tag-3";
                        break;
                    default:
                        statusTagClass = "status-tag-2"; // Default to green if no match
                        break;
                }
                html.append("<p class='").append(statusTagClass).append("'>Status: ").append(row[4]).append("</p>");

                html.append("<p>Tag: ").append(row[5]).append("</p>");
                html.append("<p>Role: ").append(row[6]).append("</p>");
                html.append("<p>Description: ").append(row[7]).append("</p>");
                html.append("</div>");
            }

            html.append("</div>");
            html.append("</body>");
            html.append("</html>");

            resp.getWriter().write(html.toString());
        } else{
            resp.sendRedirect("unauthenticated.jsp");
        }
    }
}
