package MyAlgo.Sort;


import MyADT.ArrayList.MyArrayListADT;

public class MyMergeSort {
    public static void mergeSort(MyArrayListADT<Integer> arr, int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }

    private static void merge(MyArrayListADT<Integer> arr, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;

        MyArrayListADT<Integer> L = new MyArrayListADT<>(n1);
        MyArrayListADT<Integer> R = new MyArrayListADT<>(n2);

        for (int i = 0; i < n1; i++) {
            L.add(arr.get(l + i));
        }
        for (int i = 0; i < n2; i++) {
            R.add(arr.get(m + 1 + i));
        }

        int i = 0, j = 0;
        int k = l;
        while (i < n1 && j < n2) {
            if (L.get(i) <= R.get(j)) {
                arr.set(k, L.get(i));
                i++;
            } else {
                arr.set(k, R.get(j));
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr.set(k, L.get(i));
            i++;
            k++;
        }

        while (j < n2) {
            arr.set(k, R.get(j));
            j++;
            k++;
        }
    }
}
