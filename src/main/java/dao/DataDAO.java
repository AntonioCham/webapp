package dao;

import util.DBUtil;
import model.Data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataDAO {
    public List<Data> getDataByUsername(String username) throws SQLException{
        List<Data> records = new ArrayList<>();
        String sql = "SELECT recordID, title, description FROM Record WHERE creator = ?";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                    String title = rs.getString("title");
                    String description = rs.getString("description");
                    int recordID = rs.getInt("recordID");
                    records.add(new Data(title, description, recordID));
            }
        }
        return records;
    }
}