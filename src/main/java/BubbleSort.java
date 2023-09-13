public class BubbleSort {
    public static void main(String[] args) {
        int n = 300000;
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = i;
        }
        sort(array);
        System.out.println("Sortowanie zakończone");
    }

    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] < arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}