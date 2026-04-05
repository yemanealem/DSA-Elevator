import java.util.*;

public class MajorityElementII {

    // 🔹 Solution 1: HashMap approach
    public static List<Integer> majorityElementHashMap(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> result = new ArrayList<>();

        // Count frequency
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        int threshold = nums.length / 3;

        // Collect result
        for (int key : map.keySet()) {
            if (map.get(key) > threshold) {
                result.add(key);
            }
        }

        return result;
    }

    // 🔹 Solution 2: Boyer-Moore Voting Algorithm (Optimal)
    public static List<Integer> majorityElementOptimal(int[] nums) {
        int count1 = 0, count2 = 0;
        Integer candidate1 = null, candidate2 = null;

        // Step 1: Find candidates
        for (int num : nums) {
            if (candidate1 != null && num == candidate1) {
                count1++;
            } else if (candidate2 != null && num == candidate2) {
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

        // Step 2: Verify candidates
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

    // 🔹 Main method to test
    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 3, 3, 2, 2, 2};

        System.out.println("Input: " + Arrays.toString(nums));

        List<Integer> result1 = majorityElementHashMap(nums);
        System.out.println("HashMap Result: " + result1);

        List<Integer> result2 = majorityElementOptimal(nums);
        System.out.println("Optimal (Boyer-Moore) Result: " + result2);
    }
}
