package bookstore_management;

import java.util.Scanner;

import data.BookCabinet;
import data.PublisherCabinet;
import ui.Menu;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu("Book Store Management");
        menu.addNewOption("1. Publishers Management");
        menu.addNewOption("2. Books Management");
        menu.addNewOption("3. Quit");

        Scanner sc = new Scanner(System.in);
        int choice, caseChoice01, caseChoice02;
        PublisherCabinet cb1 = new PublisherCabinet();
        BookCabinet cb2 = new BookCabinet();
        boolean check = false;

        do {
            menu.printMenu();
            choice = menu.getUserChoice();
            switch (choice) {
                case 1:
                    Menu subMenu01 = new Menu("Publishers Management");
                    subMenu01.addNewOption("1. Create a Publisher");
                    subMenu01.addNewOption("2. Delete a Publisher");
                    subMenu01.addNewOption("3. Display the publisher list");
                    subMenu01.addNewOption("4. Save the Publisher list to file");
                    subMenu01.addNewOption("5. Print the Publisher list to file");
                    subMenu01.addNewOption("6. Return to the main menu");

                    do {
                        subMenu01.printMenu();
                        caseChoice01 = subMenu01.getUserChoice();
                        switch (caseChoice01) {
                            case 1:
                                System.out.println("You are preparing to add a new publisher");
                                cb1.addAPublisher();
                                break;
                            case 2:
                                System.out.println("You are preparing to remove a publisher");
                                cb1.removePublisher();
                                break;
                            case 3:
                                System.out.println("Here is the publisher list");
                                System.out.println("-----------------------------------------------------------------------------------------");
                                cb1.displayPublisherList();
                                break;
                            case 4:
                                check = true;
                                if (check) {
                                    System.out.print("Save changes [Y/N]: ");
                                    String response = sc.nextLine().toUpperCase();
                                    if (response.startsWith("Y")) {
                                        cb1.saveToFile();
                                        System.out.println("Saved successfully!");
                                    } else
                                        System.out.println("Failed to save!");
                                }
                                break;
                            case 5:
                                cb1.printPublisherListAscendingByName();
                                break;
                            default:
                                break;
                        }
                    } while (caseChoice01 > 0 && caseChoice01 < 6);
                    break;
                case 2:
                    Menu subMenu02 = new Menu("Books Management");
                    subMenu02.addNewOption("1. Create a book");
                    subMenu02.addNewOption("2. Search a book");
                    subMenu02.addNewOption("3. Update a book");
                    subMenu02.addNewOption("4. Delete a book");
                    subMenu02.addNewOption("5. Display the book list");
                    subMenu02.addNewOption("6. Save the book list to file");
                    subMenu02.addNewOption("7. Print the book list from the file");
                    subMenu02.addNewOption("8. Return to the main menu");

                    do {
                        subMenu02.printMenu();
                        caseChoice02 = subMenu02.getUserChoice();
                        switch (caseChoice02) {
                            case 1:
                                System.out.println("You are preparing to add a new book");
                                cb2.addABook();
                                break;
                            case 2:
                                System.out.println("You are preparing to search a book");
                                cb2.searchBook();
                                break;
                            case 3:
                                System.out.println("You are preparing to update a book");
                                cb2.updateABook();
                                break;
                            case 4:
                                System.out.println("You are preparing to update a book");
                                cb2.removeABook();
                                break;
                            case 5:
                                System.out.println("Here is the book list");
                                System.out.println("-----------------------------------------------------------------------------------------");
                                cb2.displayBookList();
                                break;
                            case 6:
                                check = true;
                                if (check) {
                                    System.out.print("Save changes [Y/N]: ");
                                    String respone = sc.nextLine().toUpperCase();
                                    if (respone.startsWith("Y")) {
                                        cb2.saveToFile();
                                        System.out.println("Saved successfully!");
                                    } else
                                        System.out.println("Failed to save!");
                                }
                                break;
                            case 7:
                                cb2.printBookListAscendingByName();
                                break;
                            default:
                                break;
                        }
                    } while (caseChoice02 > 0 && caseChoice02 < 8);
                    break;
                default:
                    System.out.println("Bye bye, see you next time!");
                    break;
            }
        } while (choice > 0 && choice < 3);

    }
}
