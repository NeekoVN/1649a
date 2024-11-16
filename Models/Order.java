package Models;

import java.util.ArrayList;
import java.util.List;
import MyAlgo.Sort.MyMergeSort;

public class Order {
    private Integer orderId;
    private ArrayList<Integer> bookIds;
    private Integer customerId;

    public Order(Integer orderId, List<Integer> bookIds, Integer customerId) {
        this.orderId = orderId;
        this.bookIds = new ArrayList<>(bookIds);
        this.customerId = customerId;
    }

    public Order() {

    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public ArrayList<Integer> getBookIds() {
        return bookIds;
    }

    public void setBookIds(ArrayList<Integer> bookIds) {
        this.bookIds = bookIds;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String toString() {
        return "Order ID: " + orderId + "\nBook IDs: " + bookIds + "\nCustomer ID: " + customerId;
    }

    public void display() {
        System.out.println(toString());
    }

    public void sortBookIds(ArrayList<Integer> bookIds) {
        long startTime = System.nanoTime();
        MyMergeSort sortedBookIds = new MyMergeSort();
        sortedBookIds.mergeSort(bookIds, 0, bookIds.size() - 1);
        this.bookIds = bookIds;
        long endTime = System.nanoTime();
        System.out.println("Time taken to sort book IDs using Merge Sort: " + (endTime - startTime) + " ns");
    }

}
