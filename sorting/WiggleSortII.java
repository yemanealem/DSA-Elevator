import java.util.Arrays;

public class WiggleSortII {

    public static void wiggleSort(int[] nums) {
        int n = nums.length;

        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        int left = (n + 1) / 2 - 1;
        int right = n - 1;

        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                nums[i] = sorted[left--];
            } else {
                nums[i] = sorted[right--];
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 5, 1, 1, 6, 4};

        wiggleSort(nums);

        for (int num : nums) {
            System.out.print(num + " ");
        }
    }
}
