public class RemoveAdjacentDuplicates {

    public static String removeDuplicates(String s) {

        char[] arr = s.toCharArray();
        int j = 0; // stack pointer

        for (int i = 0; i < arr.length; i++) {

            if (j > 0 && arr[j - 1] == arr[i]) {
                j--; // pop duplicate
            } else {
                arr[j] = arr[i]; // push
                j++;
            }
        }

        return new String(arr, 0, j);
    }

    public static void main(String[] args) {

        String s = "abbaca";

        String result = removeDuplicates(s);

        System.out.println("Result: " + result);
    }
}
