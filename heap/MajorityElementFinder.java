import java.util.*;

public class MajorityElementFinder {

    public List<Integer> majorityElement(int[] nums) {
        int candidate1 = 0, candidate2 = 0;
        int count1 = 0, count2 = 0;

        // Step 1: Find candidates
        for (int num : nums) {
            if (num == candidate1) {
                count1++;
            } else if (num == candidate2) {
                count2++;
            } else if (count1 == 0) {
                candidate1 = num;
                count1 = 1;
            } else if (count2 == 0) {
                candidate2 = num;
                count2 = 1;
            } else {
                count1--;
                count2--;
            }
        }

        // Step 2: Validate candidates
        count1 = 0;
        count2 = 0;

        for (int num : nums) {
            if (num == candidate1) count1++;
            else if (num == candidate2) count2++;
        }

        List<Integer> result = new ArrayList<>();
        int threshold = nums.length / 3;

        if (count1 > threshold) result.add(candidate1);
        if (count2 > threshold) result.add(candidate2);

        return result;
    }

    // 🔹 Main method
    public static void main(String[] args) {
        MajorityElementFinder finder = new MajorityElementFinder();

        int[] nums = {1, 1, 1, 3, 3, 2, 2, 2};

        System.out.println("Input: " + Arrays.toString(nums));

        List<Integer> result = finder.majorityElement(nums);

        System.out.println("Majority Elements (> n/3): " + result);
    }
}
