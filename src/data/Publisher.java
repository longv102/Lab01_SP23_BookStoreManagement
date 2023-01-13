package data;

public class Publisher implements Comparable<Publisher> {
    private String id;
    private String name;
    private String phoneNumber;

    public Publisher() {
    }

    public Publisher(String id, String name, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getId() {
        return id;
    }
    // Not given permission to set the ID, because the ID cannot be changed
    // public void setId(String id) {
    //     this.id = id;
    // }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return id + ", " + name + ", " + phoneNumber;
    }

    public void showProfile_V1() {
        System.out.printf("|%-8s|%-25s|%-12s|%n", id, name, phoneNumber);
    }

    public void showProfile_V2() {
        System.out.printf("|%-25s|", name);
    }

    @Override
    public int compareTo(Publisher that) {
        return this.name.compareToIgnoreCase(that.name);
    }
}
