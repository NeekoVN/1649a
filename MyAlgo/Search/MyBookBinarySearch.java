package MyAlgo.Search;

import Models.Book;

public class MyBookBinarySearch {
    public static Book binarySearch(Book[] arr, int id) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            Book midBook = arr[mid];

            if (midBook.getId() == id) {
                return midBook; // Book found
            } else if (midBook.getId() < id) {
                left = mid + 1; // Search right half
            } else {
                right = mid - 1; // Search left half
            }
        }

        return null; // Book not found
    }
}
