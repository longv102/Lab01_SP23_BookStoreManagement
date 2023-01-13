package data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import util.Tools;

public class PublisherCabinet {
    // Declaration
    private ArrayList<Publisher> list = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);
    private String fileName = "Publisher.txt";
    // load data from the file to operate functions 
    public PublisherCabinet() {
        list = (ArrayList<Publisher>) getPublihsersFromFile(fileName);
    }

    // Function 01
    public void addAPublisher() {
        String id, name, phoneNumber;
        int pos;
        do {
            id = Tools.getStringFormat("Enter the id(PXXXXX): ", "The format of id is PXXXXX (X stands for a digit)", "^P\\d{5}$");
            pos = searchPublisher(id);
            if (pos >= 0)
                System.out.println("The id has already existed! Please enter the id again");
        } while (pos != -1);
        
        do {
            name = Tools.getString("Enter the name: ", "The name is required!");
        } while (name.length() < 5 || name.length() > 30);

        phoneNumber = Tools.getStringFormat("Enter the phone: ", "The phone is required!", "\\d{10,12}$");

        list.add(new Publisher(id, name, phoneNumber));
        System.out.println("A new publisher is added successfully!");
    }
    // Find the position of a publisher based on publisher's id
    public int searchPublisher(String id) {
        if (list.isEmpty()) 
            return -1;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().equalsIgnoreCase(id))
                return i;
        }
        return -1;
    }

    public Publisher searchPublisherObject(String id) {
        if (list.isEmpty())
            return null;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().equalsIgnoreCase(id))
                return list.get(i);
        }
        return null;
    }

    public void displayPublisherList() {
        if (list.isEmpty())
            System.out.println("The list is empty!");
        for (Publisher x : list) {
            x.showProfile_V1();    
        }
    }
    // Function 03
    public void removePublisher() {
        String id;
        int pos;
        Publisher x;
        id = Tools.getString("Enter the id: ", "The id is required!");
        pos = searchPublisher(id);
        x = searchPublisherObject(id);

        if (pos == -1) {
            System.out.println("Publisher's Id does not exist!");
            return;
        } else {
            System.out.println("Here is the publisher before removed");
            x.showProfile_V1();
            boolean flag = true;
            if (flag) {
                System.out.print("Do you want to remove [Y/N]: ");
                String respone = sc.nextLine().toUpperCase();
                if (respone.startsWith("Y")) {
                    list.remove(pos);
                    System.out.println("The publisher is removed successfully!");
                } else 
                    System.out.println("Failed to be removed!!!");
            }
        }
    }

    // Function 04
    public void saveToFile() {
        List<String> tmp = new ArrayList<>();

        if (list.isEmpty()) {
            System.out.println("The list is empty!");
            return;
        }

        for (Publisher x : list) {
            tmp.add(x.getId() + "," + x.getName() + "," + x.getPhoneNumber());
        }
        Tools.writeListToFile(fileName, tmp);
    }
    // Get Publishers from file
    private List<Publisher> getPublihsersFromFile(String fName) {
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
    // Function 05
    public void printPublisherListAscendingByName() {
        List<Publisher> pubList = getPublihsersFromFile(fileName);
        Collections.sort(pubList);
        
        System.out.println("------------------------------------------------------------------------------------------------------------");
        System.out.println("Here is the publisher list after sorted");
        String header = String.format("|%-8s|%-25s|%-12s|", "ID", "Name", "PhoneNumber");
        System.out.println(header);
        for (int i = 0; i < pubList.size(); i++) {
            pubList.get(i).showProfile_V1();
        }
    } 
}
