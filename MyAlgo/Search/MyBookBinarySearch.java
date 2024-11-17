package MyAlgo.Search;

import Models.Book;

import java.util.Arrays;
import java.util.stream.Collectors;

public class MyBookBinarySearch {
    public static Book binarySearch(Book[] arr, int id) {
        int left = 0;
        int right = arr.length - 1;

        System.out.println("Starting Binary Search...");
        while (left <= right) {
            int mid = left + (right - left) / 2;
            Book midBook = arr[mid];

            // Constructing the visualization using Book IDs
            String leftHalf = Arrays.stream(Arrays.copyOfRange(arr, left, mid))
                    .map(book -> String.valueOf(book.getId()))
                    .collect(Collectors.joining(", ", "[", "]"));
            String midElement = "[" + midBook.getId() + "]";
            String rightHalf = Arrays.stream(Arrays.copyOfRange(arr, mid + 1, right + 1))
                    .map(book -> String.valueOf(book.getId()))
                    .collect(Collectors.joining(", ", "[", "]"));

            // Print the current state of the search
            System.out.println(leftHalf + midElement + rightHalf);

            if (midBook.getId() == id) {
                System.out.println("Book found at index " + mid + ": " + midBook);
                return midBook; // Book found
            } else if (midBook.getId() < id) {
                left = mid + 1; // Search right half
            } else {
                right = mid - 1; // Search left half
            }
        }

        System.out.println("Book with ID " + id + " not found.");
        return null; // Book not found
    }
}

//package MyAlgo.Search;
//
//import Models.Book;
//
//public class MyBookBinarySearch {
//    public static Book binarySearch(Book[] arr, int id) {
//        int left = 0;
//        int right = arr.length - 1;
//
//        while (left <= right) {
//            int mid = left + (right - left) / 2;
//            Book midBook = arr[mid];
//
//            if (midBook.getId() == id) {
//                return midBook; // Book found
//            } else if (midBook.getId() < id) {
//                left = mid + 1; // Search right half
//            } else {
//                right = mid - 1; // Search left half
//            }
//        }
//
//        return null; // Book not found
//    }
//}