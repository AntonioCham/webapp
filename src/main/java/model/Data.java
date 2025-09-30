package model;

public class Data {
    private int id;
    private String title;
    private String description;

    //Constructors
    public Data() {}
    public Data(String title, String description, int id){
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public String getTitle() { return title; }
    public String getDescription() { return description; }
}
