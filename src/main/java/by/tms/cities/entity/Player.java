package by.tms.cities.entity;

public class Player {
    private String name;

    public Player(String name) {
        this.name = name;
    }

    public Player() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
