package antoniocham;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.*;
import java.sql.*;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try (Connection conn = DBUtil.getConnection()) {
            // Check if username already exists
            String checkSql = "SELECT id FROM Account WHERE username = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setString(1, username);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                request.setAttribute("errorMessage", "Username already exists. Please choose another.");
                request.getRequestDispatcher("register.jsp").forward(request, response);
                return;
            }

            // Generate salt + hash
            String salt = DBUtil.generateSalt();
            String hashedPassword = DBUtil.hashPassword(password, salt);

            // Insert user into account table
            String insertSql = "INSERT INTO Account (username, password, salt, create_datetime, last_modified_datetime, is_valid) VALUES (?, ?, ?, NOW(), NOW(), 'Y')";
            PreparedStatement insertStmt = conn.prepareStatement(insertSql);
            insertStmt.setString(1, username);
            insertStmt.setString(2, hashedPassword);
            insertStmt.setString(3, salt);

            int rows = insertStmt.executeUpdate();

            if (rows > 0) {
                request.setAttribute("successMessage", "Registration successful! You may now log in.");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            } else {
                request.setAttribute("errorMessage", "Registration failed. Please try again.");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Internal server error.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}