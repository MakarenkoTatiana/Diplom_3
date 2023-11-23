package model;

public class User extends Auth {
    private String name;

    public User() {}

    public User(String email, String name, String password) {
        super(email, password);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
