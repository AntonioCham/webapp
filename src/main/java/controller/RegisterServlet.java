package controller;

import model.Result;
import service.UserService;
import javax.servlet.*;
import javax.servlet.http.*;

import javax.servlet.annotation.WebServlet;
import java.io.*;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Result rs = userService.registerUser(username, password);

        if(rs.getFlag()){
            request.setAttribute("successMessage", rs.getMessage());
        }else {
            request.setAttribute("errorMessage", rs.getMessage());
        }
        
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }
}