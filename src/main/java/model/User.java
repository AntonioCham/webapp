package model;

public class User {
    //Properties
    private String username;
    private String password;
    private String is_valid;
    private String slat;

    //Constructors
    public User() {}
    public User(String username, String password, String is_valid, String slat){
        this.username = username;
        this.password = password;
        this.is_valid = is_valid;
        this.slat = slat;
    }

    // Getters
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getisValid() { return is_valid; }
    public String getSlat() { return slat; }
    
    // Setters
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setisValid(String is_valid) { this.is_valid = is_valid; }
    public void setSlat(String slat) { this.slat = slat; }
}
