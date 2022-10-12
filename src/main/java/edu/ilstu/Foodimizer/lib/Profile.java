package edu.ilstu.Foodimizer.lib;

public class Profile {
    private String name;
    private int uniqueId;

    public Profile(int uniqueId, String name) {
        this.uniqueId = uniqueId;
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getId() {
        return this.uniqueId;
    }
}
