package controller;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import service.DataService;

import java.io.*;
import java.util.List;
import model.Data;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    private DataService dataService = new DataService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String username = (String) session.getAttribute("username");
        List<Data> records = dataService.getData(username);

        if(records != null){
            request.setAttribute("records", records);
        }else{
            request.setAttribute("errorMessage", "Internal server error.");
        }

        request.getRequestDispatcher("home.jsp").forward(request, response);
    }
}
