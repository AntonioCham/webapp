package controller;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import model.Result;
import service.UserService;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{

    private UserService userService = new UserService();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Result rs = userService.userLogin(username, password);

        if(rs.getFlag()){
                    HttpSession session = request.getSession();
                    session.setAttribute("username", username);
                    response.sendRedirect("home");
        }else {
            request.setAttribute("errorMessage", rs.getMessage());
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
