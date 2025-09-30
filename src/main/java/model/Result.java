package model;

public class Result {
    private boolean flag;
    private String message;

    public Result(boolean flag, String message) {
        this.flag = flag;
        this.message = message;
    }

    public boolean getFlag() { return flag; }
    public String getMessage() { return message; }
}
