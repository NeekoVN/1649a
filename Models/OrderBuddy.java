package Models;

import Data.BookMockupData;
import MyADT.Queue.MyQueueADT;
import Data.OrderMockupData;
import MyAlgo.Search.MyBookBinarySearch;
import MyAlgo.Sort.MyBookMergeSort;

public class OrderBuddy {
    private MyQueueADT<Order> pendingOrders;
    private MyQueueADT<Order> processedOrders;
    private Integer orderId;

    public OrderBuddy() {
        this.pendingOrders = new MyQueueADT<>();
        this.processedOrders = new MyQueueADT<>();
        this.orderId = 1;

        //TODO: hardcoded data
        for (Order order : OrderMockupData.orderData) {
            addOrder(order);
        }
    }

    public boolean addOrder(Order order) {
        long startTime = System.nanoTime();
        for (Integer bookId : order.getBookIds()) {
            Book book = getBook(bookId);
            if (book != null) {
                if (book.getQuantity() <= 0) {
                    System.out.println("Book with ID " + bookId + " is out of stock, order cancelled.");
                    return false;
                }
                book.setQuantity(book.getQuantity() - 1);
            }
        }
        order.setOrderId(this.orderId);
        this.pendingOrders.enqueue(order);
        this.orderId++;
        long endTime = System.nanoTime();
        System.out.println("Time taken to add order: " + (endTime - startTime) + " ns");
        return true;
    }

    public void processOrder() {
        long startTime = System.nanoTime();
        Order order = this.pendingOrders.dequeue();
        this.processedOrders.enqueue(order);
        long endTime = System.nanoTime();
        System.out.println("Time taken to process order: " + (endTime - startTime) + " ns");
    }

    public MyQueueADT<Order> getPendingOrders() {
        return pendingOrders;
    }

    public MyQueueADT<Order> getProcessedOrders() {
        return processedOrders;
    }

    public void peekPendingOrder() {
        Order order = this.pendingOrders.peek();
        System.out.println("Order ID: " + order.getOrderId());
        System.out.println("Book IDs: " + order.getBookIds());
        System.out.println("Customer ID: " + order.getCustomerId());
    }

    public void displayPendingOrders() {
        long startTime = System.nanoTime();
        if (this.pendingOrders.isEmpty()) {
            System.out.println("No pending orders.");
        } else {
            System.out.println("Pending Orders:");
            for (int i = 0; i < this.pendingOrders.size(); i++) {
                Order order = this.pendingOrders.dequeue();
                System.out.println("Order ID: " + order.getOrderId());
                System.out.println("Book IDs: " + order.getBookIds());
                System.out.println("Customer ID: " + order.getCustomerId());
                this.pendingOrders.enqueue(order);
            }
        }
        long endTime = System.nanoTime();
        System.out.println("Time taken to display pending orders: " + (endTime - startTime) + " ns");
    }

    public void displayProcessedOrders() {
        long startTime = System.nanoTime();
        if (this.processedOrders.isEmpty()) {
            System.out.println("No processed orders.");
        } else {
            System.out.println("Processed Orders:");
            for (int i = 0; i < this.processedOrders.size(); i++) {
                Order order = this.processedOrders.dequeue();
                System.out.println("Order ID: " + order.getOrderId());
                System.out.println("Book IDs: " + order.getBookIds());
                System.out.println("Customer ID: " + order.getCustomerId());
                this.processedOrders.enqueue(order);
            }
        }
        long endTime = System.nanoTime();
        System.out.println("Time taken to display processed orders: " + (endTime - startTime) + " ns");
    }

    public static Book getBook(Integer id) {
        long startTime = System.nanoTime();
        Book[] sortedBooks = BookMockupData.bookData;
        MyBookMergeSort.mergeSort(sortedBooks, 0, sortedBooks.length - 1);
        // Binary search
        Book res = MyBookBinarySearch.binarySearch(sortedBooks, id);
        long endTime = System.nanoTime();
        System.out.println("Time taken to get book using Merge Sort and Binary Search: " + (endTime - startTime) + " ns");
        return res;
    }

    public void viewAllBooks() {
        long startTime = System.nanoTime();
        Book[] sortedBooks = BookMockupData.bookData;
        MyBookMergeSort.mergeSort(sortedBooks, 0, sortedBooks.length - 1);
        for (Book book : sortedBooks) {
            System.out.println(book.toString());
            System.out.println();
        }
        long endTime = System.nanoTime();
        System.out.println("Time taken to view all books using Merge Sort: " + (endTime - startTime) + " ns");
    }

    public boolean checkValidBookId(Integer bookId) {
        for (Book book : BookMockupData.bookData) {
            if (book.getId().equals(bookId)) {
                return true;
            }
        }
        return false;
    }

    public void searchOrder(Integer orderId) {
        long startTime = System.nanoTime();
        boolean found = false;

        for (int i = 0; i < this.pendingOrders.size(); i++) {
            Order order = this.pendingOrders.dequeue();
            if (order.getOrderId().equals(orderId)) {
                System.out.println("Order found in pending orders.");
                System.out.println(order.toString());
                found = true;
            }
            this.pendingOrders.enqueue(order);
        }

        if (!found) {
            for (int i = 0; i < this.processedOrders.size(); i++) {
                Order order = this.processedOrders.dequeue();
                if (order.getOrderId().equals(orderId)) {
                    System.out.println("Order found in processed orders.");
                    System.out.println(order.toString());
                    found = true;
                }
                this.processedOrders.enqueue(order);
            }
        }

        if (!found) {
            System.out.println("Order not found in both pending and processed orders.");
        }
        long endTime = System.nanoTime();
        System.out.println("Time taken to search order: " + (endTime - startTime) + " ns");
    }
}
