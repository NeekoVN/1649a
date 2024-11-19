import Models.Book;
import Models.Order;
import Models.OrderBuddy;
import MyADT.ArrayList.MyArrayListADT;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MyApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        OrderBuddy orderBuddy = new OrderBuddy();
        Integer customerId = null;
        boolean exit = false;

        while (!exit) {
            System.out.println("Menu:");
            System.out.println("1. Add Order");
            System.out.println("2. Process Order");
            System.out.println("3. Search For Books");
            System.out.println("4. View All Books");
            System.out.println("5. Search For Orders");
            System.out.println("6. View All Orders");
            System.out.println("7. Add a New Book");
            System.out.println("8. Exit");

            int choice = -1;
            while (choice == -1) {
                System.out.print("Choose an option: ");
                try {
                    choice = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number between 1 and 7.");
                    scanner.nextLine(); // clear invalid input
                }
            }

            switch (choice) {
                case 1:
                    Order order = new Order();
                    MyArrayListADT<Integer> bookIds = new MyArrayListADT<>();

                    while (customerId == null) {
                        System.out.print("Enter customer ID: ");
                        try {
                            customerId = Integer.valueOf(scanner.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter a valid customer ID.");
                        }
                    }

                    boolean addMoreBooks = true;
                    while (addMoreBooks) {
                        while (true) { // Loop until a valid book ID is entered
                            System.out.print("Enter book ID: ");
                            try {
                                Integer bookId = Integer.valueOf(scanner.nextLine());
                                if (orderBuddy.checkValidBookId(bookId)) {
                                    bookIds.add(bookId);
                                    break; // Exit the loop if the book ID is valid
                                } else {
                                    System.out.println("Invalid book ID. Please try again.");
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid input. Please enter a valid book ID.");
                            }
                        }

                        System.out.print("Finish adding books? (y/n): ");
                        String finish = scanner.nextLine();
                        switch (finish.toLowerCase()) {
                            case "y":
                                addMoreBooks = false;
                                break;
                            case "n":
                                break;
                            default:
                                System.out.println("Invalid choice. Please try again.");
                        }
                    }

                    order.sortBookIds(bookIds);
                    order.setBookIds(bookIds);
                    order.setCustomerId(customerId);

                    System.out.println("----------Order details----------");
                    order.display();
                    System.out.println("---------------------------------");

                    while (true) {
                        System.out.print("Confirm order? (y/n): ");
                        String confirm = scanner.nextLine();
                        switch (confirm.toLowerCase()) {
                            case "y":
                                boolean orderRes = orderBuddy.addOrder(order);
                                if (orderRes) {
                                    System.out.println("Order added successfully.");
                                }
                                break;
                            case "n":
                                break;
                            default:
                                System.out.println("Invalid choice. Order cancelled.");
                        }
                        break;
                    }
                    break;

                case 2:
                    System.out.println("----------Order details----------");
                    orderBuddy.peekPendingOrder();
                    System.out.println("---------------------------------");
                    System.out.print("Do you want to process this order? (y/n): ");
                    String process = scanner.nextLine();
                    switch (process.toLowerCase()) {
                        case "y":
                            orderBuddy.processOrder();
                            System.out.println("Order processed successfully.");
                            break;
                        case "n":
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                    break;

                case 3:
                    System.out.print("Enter book ID to search: ");
                    try {
                        Integer bookId = Integer.valueOf(scanner.nextLine());
                        Book bookSearchRes = orderBuddy.getBook(bookId);
                        if (bookSearchRes != null) {
                            System.out.println("Book found!");
                            System.out.println(bookSearchRes.toString());
                        } else {
                            System.out.println("Book not found.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid book ID.");
                    }
                    System.out.println("Press enter to continue...");
                    scanner.nextLine();
                    break;

                case 4:
                    System.out.println("----------All Books----------");
                    orderBuddy.viewAllBooks();
                    System.out.println("-----------------------------");
                    break;

                case 5:
                    boolean searchOrder = true;
                    while (searchOrder) {
                        System.out.print("Enter order ID to search: ");
                        try {
                            Integer orderId = Integer.valueOf(scanner.nextLine());
                            orderBuddy.searchOrder(orderId);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter a valid order ID.");
                        }

                        System.out.print("Search for another order? (y/n): ");
                        String search = scanner.nextLine();
                        switch (search.toLowerCase()) {
                            case "y":
                                break;
                            case "n":
                                searchOrder = false;
                                break;
                            default:
                                System.out.println("Invalid choice. Please try again.");
                        }
                    }
                    break;

                case 6:
                    System.out.println("----------Pending Orders----------");
                    orderBuddy.displayPendingOrders();
                    System.out.println("----------------------------------");
                    System.out.println();
                    System.out.println("----------Processed Orders----------");
                    orderBuddy.displayProcessedOrders();
                    System.out.println("------------------------------------");
                    break;

                case 7:
                    //Add new book
                    Book book = new Book();
                    try {
                        System.out.print("Enter book title: ");
                        book.setTitle(scanner.nextLine());
                    } catch (Exception e) {
                        System.out.println("Invalid input. Please enter a valid book title.");
                    }
                    try {
                        System.out.print("Enter book author: ");
                        book.setAuthor(scanner.nextLine());
                    } catch (Exception e) {
                        System.out.println("Invalid input. Please enter a valid book author.");
                    }
                    try {
                        System.out.print("Enter book quantity: ");
                        book.setQuantity(Integer.valueOf(scanner.nextLine()));
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid book quantity.");
                    }
                    boolean addBook = orderBuddy.addBook(book);
                    if (addBook) {
                        System.out.println("Book added successfully.");
                    }
                    break;

                case 8:
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }
}

