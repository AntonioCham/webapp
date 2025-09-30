package service;

import util.SecurityUtil;

import java.sql.ResultSet;

import dao.UserDAO;
import model.Result;
import model.User;

public class UserService {
    private UserDAO userDAO = new UserDAO();

    public Result userLogin(String username, String password){
        Result validationResult = SecurityUtil.vlidation(username, password);
        if(!validationResult.getFlag()){
            return validationResult;
        }

        try{
            User u = userDAO.getUser(username);

            if (u == null) {
                return new Result(false, "Invalid username or password.");
            }

            if (!"Y".equalsIgnoreCase(u.getisValid())) {
                return new Result(false, "Account is invalid. Please contact the administrator.");
            }

            String hashedPassword = SecurityUtil.hashPassword(password, u.getSlat());
            if (u.getPassword().equals(hashedPassword)) {
                return new Result(true, "Login Successfully");
            }else{
                return new Result(false, "Invalid username or password.");
            }
        }catch(Exception e){
            e.printStackTrace();
            return new Result(false, "Login failed. Please try again.");
        }
    }

    public Result registerUser(String username, String password){
        Result validationResult = SecurityUtil.vlidation(username, password);
        if(!validationResult.getFlag()){
            return validationResult;
        }

        try{
            if(userDAO.checkUserExist(username)){
                return new Result(false, "Username already exists. Please choose another.");
            }
        }catch(Exception e){
            e.printStackTrace();
            return new Result(false, "Registration failed. Please try again.");
        }

        try{
            Boolean flag = userDAO.registerUser(username, password);
            if(flag){
                return new Result(true, "Registration successful! You may now log in.");
            }
            return new Result(false, "Registration failed. Please try again.");
        }catch(Exception e){
            e.printStackTrace();
            return new Result(false, "Registration failed. Please try again.");
        }
    }
}
