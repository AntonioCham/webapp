package antoniocham;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HomeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String username = (String) session.getAttribute("username");
        List<String[]> records = new ArrayList<>();

        try (Connection conn = DBUtil.getConnection()) {
            String sql = "SELECT title, description FROM Record WHERE creator = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                records.add(new String[]{rs.getString("title"), rs.getString("description")});
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Internal server error.");
        }

        request.setAttribute("records", records);
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }
}
