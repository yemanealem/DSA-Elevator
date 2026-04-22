import java.util.*;

/*
 * Sliding Window Optimized Version
 * Time: O(N log N)
 * Faster due to reduced overhead
 */

public class SmallestRangeKLists {

    public static int[] smallestRange(List<List<Integer>> nums) {

        int k = nums.size();
        List<int[]> list = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            for (int val : nums.get(i)) {
                list.add(new int[]{val, i});
            }
        }

        list.sort((a, b) -> a[0] - b[0]);

        int[] freq = new int[k];  
        int count = 0;

        int left = 0;
        int start = 0, end = Integer.MAX_VALUE;

        for (int right = 0; right < list.size(); right++) {
            int listIdx = list.get(right)[1];

            if (freq[listIdx] == 0) count++;
            freq[listIdx]++;

            // shrink window
            while (count == k) {
                int leftVal = list.get(left)[0];
                int rightVal = list.get(right)[0];

                if (rightVal - leftVal < end - start) {
                    start = leftVal;
                    end = rightVal;
                }

                int leftIdx = list.get(left)[1];
                freq[leftIdx]--;

                if (freq[leftIdx] == 0) count--;

                left++;
            }
        }

        return new int[]{start, end};
    }

    public static void main(String[] args) {
        List<List<Integer>> nums = new ArrayList<>();
        nums.add(Arrays.asList(4, 10, 15, 24, 26));
        nums.add(Arrays.asList(0, 9, 12, 20));
        nums.add(Arrays.asList(5, 18, 22, 30));

        int[] result = smallestRange(nums);

        System.out.println("Smallest Range: [" + result[0] + ", " + result[1] + "]");
    }
}
