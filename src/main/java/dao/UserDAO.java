package dao;

import util.SecurityUtil;
import util.DBUtil;

import model.User;
import java.sql.*;

public class UserDAO {
    public boolean checkUserExist(String username) throws SQLException {
        String sql = "SELECT id FROM Account WHERE username = ?";
        try (Connection conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {    
                return true;
            }
            return false;
        }
    }

    public User getUser(String username) throws SQLException{
        String sql = "SELECT username, password, salt, is_valid FROM Account WHERE username = ?";
        User user = null;
        try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                String retrievedUsername = rs.getString("username");
                String retrievedPassword = rs.getString("password");
                String retrievedIsValid = rs.getString("is_valid");
                String retrievedSalt = rs.getString("salt");
                user = new User(retrievedUsername, retrievedPassword, retrievedIsValid, retrievedSalt);
            }
        }
        return user;
    }

    public boolean registerUser(String username, String password) throws SQLException, Exception{
            String salt = SecurityUtil.generateSalt();
            String hashedPassword = SecurityUtil.hashPassword(password, salt);
            String insertSQL = "INSERT INTO Account (username, password, salt, create_datetime, last_modified_datetime, is_valid) VALUES (?, ?, ?, NOW(), NOW(), 'Y')";
            try (Connection conn = DBUtil.getConnection(); PreparedStatement insertStmt = conn.prepareStatement(insertSQL)){
                insertStmt.setString(1, username);
                insertStmt.setString(2, hashedPassword);
                insertStmt.setString(3, salt);
                int rows = insertStmt.executeUpdate();
                if(rows > 0){
                    return true;
                }
                return false;
            }
    }
}