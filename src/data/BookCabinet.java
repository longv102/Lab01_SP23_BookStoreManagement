package data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import ui.Menu;
import util.Tools;

public class BookCabinet {
    // Declaration
    private Scanner sc = new Scanner(System.in);
    private List<Book> list = new ArrayList<>();
    private String fileName = "Book.txt";
    private List<Publisher> tmpList = getPublihsersFromFile();

    private List<Publisher> getPublihsersFromFile() {
        String fName = "Publisher.txt";
        List<Publisher> pubList = new ArrayList<>();
        List<String> tmp = Tools.readLineFromFile(fName);

        for (String x : tmp) {
            StringTokenizer stk = new StringTokenizer(x, ",");
            String id = stk.nextToken();
            String name = stk.nextToken();
            String phoneNumber = stk.nextToken();

            pubList.add(new Publisher(id, name, phoneNumber));
        }
        return pubList;
    }

    public BookCabinet() {
        list = (ArrayList<Book>) getBooksFromFile(fileName);
    }

    // Function 01
    public void addABook() {
        String id, name;
        String status, display;
        String publisherID;
        double price;
        int quantity;
        int pos;

        do {
            id = Tools.getStringFormat("Enter the id(BXXXXX): ",
                    "The format of the id is BXXXXX (X stands for a digit)", "^B\\d{5}$");
            pos = searchABook(id);
            if (pos >= 0)
                System.out.println("The book has already existed! Please enter the id again.");
        } while (pos != -1);

        do {
            name = Tools.getString("Enter the name: ", "The name is required!");
        } while (name.length() < 5 || name.length() > 30);

        do {
            price = Tools.getADouble("Enter the price: ", "The price is required!");
        } while (price <= 0);

        do {
            quantity = Tools.getAnInteger("Enter the quantity: ", "The quantity is required!");
        } while (quantity <= 0);

        do {
            status = Tools.getString("Enter the status [A/NA]: ", "This field is required!");
            if (status.matches("A"))
                display = "Available";
            else
                display = "Not available";
        } while (!status.matches("^(A|NA)$"));

        publisherID = Tools.getString("Enter the publisher's id: ", "The publisher's id is required!");
        // Check publisher's id is existed in the Publisher.txt
        for (int i = 0; i < tmpList.size(); i++) {
            String tmp = tmpList.get(i).getId();
            if (tmp.equalsIgnoreCase(publisherID)) {
                publisherID = tmpList.get(i).getId();
                list.add(new Book(id, name, price, quantity, display, publisherID));
                System.out.println("A new book is added successfully!");
                return;
            }
        }
        System.out.println("Publisher's id not found. A book is not added!");
    }

    // Function 02
    public void searchBook() {
        List<Book> x;
        int choice;
        Menu subMenu = new Menu("Books Search System");
        subMenu.addNewOption("1. Search a book by publisher's id");
        subMenu.addNewOption("2. Search a book by book's name");
        subMenu.addNewOption("3. Quit");
        do {
            subMenu.printMenu();
            choice = subMenu.getUserChoice();
            switch (choice) {
                case 1:
                    String pubID = Tools.getString("Enter the publisher's id: ", "The id is required!");
                    x = searchBooksByPublishersID(pubID);
                    System.out.println(
                            "------------------------------------------------------------------------------------------------------------");
                    if (x == null)
                        System.out.println("Not found!!!");
                    else {
                        System.out.println("Here is the book(s) that you want to search");
                        for (Book tmp : x) {
                            tmp.showProfile_V1();
                        }
                    }
                    break;
                case 2:
                    String name = Tools.getString("Enter the book's name: ", "The name is required!");
                    x = searchBooksList(name);
                    System.out.println(
                            "------------------------------------------------------------------------------------------------------------");
                    if (x == null)
                        System.out.println("Not found!!!");
                    else {
                        System.out.println("Here is the book(s) that you want to search");
                        for (Book tmp : x) {
                            tmp.showProfile_V1();
                        }
                    }
                    break;
                // case 3:
                // break;
            }
        } while (choice != 3);

    }

    // Find the position of a book based on book's id
    public int searchABook(String id) {
        if (list.isEmpty())
            return -1;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().equalsIgnoreCase(id))
                return i;
        }
        return -1;
    }

    // Search a book based on book's id
    public Book searchABookByID(String id) {
        if (list.isEmpty())
            return null;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().equalsIgnoreCase(id))
                return list.get(i);
        }
        return null;
    }

    //
    public List<Book> searchBooksByPublishersID(String publisherId) {
        List<Publisher> checkList = getPublihsersFromFile();
        List<Book> tmp = new ArrayList<>();

        if (list.isEmpty())
            return null;

        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < checkList.size(); j++) {
                if (list.get(i).getPublisherID().equalsIgnoreCase(publisherId)) {
                    if (checkList.get(j).getId().equalsIgnoreCase(publisherId))
                        tmp.add(list.get(i));
                }
            }
        }
        if (tmp.isEmpty())
            return null;

        return tmp;
    }

    // Find the list of the books by book's name
    public List<Book> searchBooksList(String bookName) {
        List<Book> tmp = new ArrayList<>();
        if (list.isEmpty())
            return null;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().contains(bookName))
                tmp.add(list.get(i));
        }
        if (tmp.isEmpty())
            return null;
        return tmp;
    }

    // Function 03
    public void updateABook() {
        String id, newName;
        int pos;
        Book x;

        id = Tools.getString("Enter the id: ", "The id is required!");
        pos = searchABook(id);
        x = searchABookByID(id);

        if (pos == -1)
            System.out.println("Book's name doesn't exist!");
        else {
            System.out.println("Here is the book before updated the name");
            x.showProfile_V1();
            boolean check = true;
            if (check) {
                System.out.print("Do you want to update [Y/N]: ");
                String respone = sc.nextLine().toUpperCase();
                if (respone.startsWith("Y")) {
                    newName = Tools.getString("Enter the new name: ", "The name is required!");
                    x.setName(newName);
                    System.out.println("The book's name is updated successfully!");
                } else
                    System.out.println("The book's name is not updated!");
            }
        }
    }

    // Function 04
    public void removeABook() {
        String id;
        int pos;
        Book x;

        id = Tools.getString("Enter the id: ", "The id is required!");
        pos = searchABook(id);
        x = searchABookByID(id);

        if (pos == -1) {
            System.out.println("Book's name doesn't exist!");
        } else {
            System.out.println("Here is the book before removed");
            x.showProfile_V1();
            boolean check = true;
            if (check) {
                System.out.print("Do you want to remove [Y/N]: ");
                String respone = sc.nextLine().toUpperCase();
                if (respone.startsWith("Y")) {
                    list.remove(pos);
                    System.out.println("The book is removed successfully!");
                } else
                    System.out.println("Failed to be removed!");
            }
        }
    }

    // Function 05
    public void saveToFile() {
        List<String> tmp = new ArrayList<>();

        if (list.isEmpty()) {
            System.out.println("The list is empty");
            return;
        }

        for (Book x : list) {
            tmp.add(x.getId() + "," + x.getName() + "," + x.getPrice() + "," + x.getQuantity() + "," + x.getStatus()
                    + "," + x.getPublisherID());
        }

        Tools.writeListToFile(fileName, tmp);
    }

    private List<Book> getBooksFromFile(String fName) {
        List<Book> bookList = new ArrayList<>();
        List<String> tmp = Tools.readLineFromFile(fName);

        for (String x : tmp) {
            StringTokenizer stk = new StringTokenizer(x, ",");
            String id = stk.nextToken();
            String name = stk.nextToken();
            double price = Double.parseDouble(stk.nextToken());
            int quantity = Integer.parseInt(stk.nextToken());
            String status = stk.nextToken();
            String pubID = stk.nextToken();

            bookList.add(new Book(id, name, price, quantity, status, pubID));
        }
        return bookList;
    }

    // Function 06
    public void printBookListAscendingByName() {
        List<Book> bookList = getBooksFromFile(fileName);
        Collections.sort(bookList);

        System.out.println(
                "------------------------------------------------------------------------------------------------------------");
        System.out.println("Here is the book list after sorted");
        String header = String.format("|%-8s|%-25s|%-6s|%-8s|%-7s|%-25s|%-15s|", "ID", "Name", "Price", "Quantity",
                "SubTotal", "Publisher's name", "Status");
        System.out.println(header);

        for (int i = 0; i < bookList.size(); i++) {
            for (int j = 0; j < tmpList.size(); j++) {
                if (tmpList.get(j).getId().equalsIgnoreCase(bookList.get(i).getPublisherID())) {
                    bookList.get(i).showProfile_V2();
                    tmpList.get(j).showProfile_V2();
                    bookList.get(i).showProfile_V3();

                }
            }
        }
    }

    // Function 03
    public void displayBookList() {
        if (list.isEmpty())
            System.out.println("The list is empty!");
        for (Book x : list) {
            if (x.getPrice() > 100)
                x.showProfile_V1();
        }
    }
    //Find the list of books based on publisherId and bookName
    //User are required to input these two fields to find the book(s)
    public List<Book> searchBooksByPublisherIdAndName(String publisherId, String bookName) {
        List<Book> bookList = new ArrayList<>();
        List<Publisher> checkList = getPublihsersFromFile();
        if (list.isEmpty())
            return null;

        if (publisherId.isEmpty() && bookName.isEmpty())
            return null;
        else if (bookName.isEmpty())
            bookList = searchBooksByPublishersID(publisherId);
        else if (publisherId.isEmpty()) 
            bookList = searchBooksList(bookName);
        else {
            for (int i = 0; i < list.size(); i++) {
                for (int j = 0; j < checkList.size(); j++) {
                    // Check wheter the inputted publisherId is match with the publisherId in Publisher.txt
                    if ((list.get(i).getPublisherID().equalsIgnoreCase(publisherId)))
                        if (checkList.get(j).getId().equalsIgnoreCase(publisherId))
                        //Check whether the inputted bookName is match with bookName in BookCabinet list
                            if (list.get(i).getName().contains(bookName))
                                bookList.add(list.get(i));
                }
            }
        }
        if (bookList.isEmpty())
            return null;
        return bookList;
    }
    //Another function to find the list of book(s)
    public void searchBooks() {
        String publisherId = null;
        String bookName = null;
        List<Book> searchedList;

        System.out.print("Enter the publisher's id(Pxxxxx): ");
        publisherId = sc.nextLine();
        System.out.print("Enter the book's name: ");
        bookName = sc.nextLine();

        searchedList = searchBooksByPublisherIdAndName(publisherId, bookName);
        if (searchedList == null) {
            System.out.println("Not found!");
            return;
        }
        System.out.println("=====================================================================================");
        System.out.println("Here is the list of book(s) that you want to search");
        for (Book x : searchedList) {
            x.showProfile_V1();
        }
        
    }

    public static void main(String[] args) {
        BookCabinet tc = new BookCabinet();
        tc.searchBooks();

    }
}
