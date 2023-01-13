package data;

public class Book implements Comparable<Book>{
    private String id;
    private String name;
    private double price;
    private int quantity;
    private String status;
    private String publisherID;

    public Book() {
    }

    public Book(String id, String name, double price, int quantity, String status, String publisherID) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
        this.publisherID = publisherID;
    }

    public String getId() {
        return id;
    }

    public String getPublisherID() {
        return publisherID;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double calcSubTotal() {
        return (price * quantity);
    }

    
    @Override
    public String toString() {
        return id + ", " + name + ", " + price + ", " + quantity + ", " + status + "," + publisherID;
    }

    public void showProfile_V1() {
        System.out.printf("|%-8s|%-25s|%-6.1f|%-5d|%-15s|%-8s|%n", id, name, price, quantity, status, publisherID);
    }

    public void showProfile_V2() {
        System.out.printf("|%-8s|%-25s|%-6.1f|%-8d|%-8.1f", id, name, price, quantity, calcSubTotal());
    }

    public void showProfile_V3() {
        System.out.printf("%-15s|%n", status);
    }

    @Override
    public int compareTo(Book that) {
        if (this.quantity > that.getQuantity()) {
            return -1;
        } else if (this.quantity < that.getQuantity()) {
            return 1;
        } else {
            if (this.price > that.getPrice()) {
                return -1;
            } else if (this.price < that.getPrice()) {
                return 1;
            } else 
                return this.name.compareTo(that.getName());
        }
    }

}
