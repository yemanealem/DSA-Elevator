public class MaximumProductSubarray {

    public static int maxProduct(int[] nums) {
        int maxProd = nums[0];
        int minProd = nums[0];
        int result = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int curr = nums[i];

            // If current number is negative, swap max and min
            if (curr < 0) {
                int temp = maxProd;
                maxProd = minProd;
                minProd = temp;
            }

            maxProd = Math.max(curr, curr * maxProd);
            minProd = Math.min(curr, curr * minProd);

            result = Math.max(result, maxProd);
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, -2, 4};
        System.out.println(maxProduct(nums)); // Output: 6
    }
}
