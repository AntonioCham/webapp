package service;

import java.sql.SQLException;
import java.util.List;

import dao.DataDAO;
import model.Data;

public class DataService {
    private DataDAO dataDAO = new DataDAO();

    public List<Data> getData(String username){
        try{
            List<Data> records = dataDAO.getDataByUsername(username);
            return records;
        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
