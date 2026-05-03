/*
LeetCode Problem: Wiggle Sort II

Problem:
Given an integer array nums, reorder it such that:
nums[0] < nums[1] > nums[2] < nums[3] > nums[4] ...

You may assume the input array always has a valid answer.

-------------------------------------------------------

Approach:
1. Find the median using QuickSelect (O(n))
2. Use 3-way partition (Dutch National Flag)
   - Elements > median → left
   - Elements < median → right
   - Elements = median → middle
3. Use Virtual Index Mapping to rearrange positions:
   newIndex = (1 + 2*i) % (n | 1)

This ensures:
- Larger numbers go to odd indices
- Smaller numbers go to even indices

-------------------------------------------------------

Time Complexity: O(n)
Space Complexity: O(1)
*/

import java.util.Random;

public class WiggleSortII {

    public static void wiggleSort(int[] nums) {
        int n = nums.length;

        // Step 1: Find median
        int median = findKthLargest(nums, (n + 1) / 2);

        // Step 2: 3-way partition with virtual indexing
        int left = 0, i = 0, right = n - 1;

        while (i <= right) {
            int mappedIndex = newIndex(i, n);

            if (nums[mappedIndex] > median) {
                swap(nums, newIndex(left++, n), mappedIndex);
                i++;
            } else if (nums[mappedIndex] < median) {
                swap(nums, newIndex(right--, n), mappedIndex);
            } else {
                i++;
            }
        }
    }

    // Virtual index mapping
    private static int newIndex(int i, int n) {
        return (1 + 2 * i) % (n | 1);
    }

    // QuickSelect to find kth largest element
    private static int findKthLargest(int[] nums, int k) {
        int left = 0, right = nums.length - 1;
        Random rand = new Random();

        while (true) {
            int pivotIndex = partition(nums, left, right, rand);

            if (pivotIndex == nums.length - k) {
                return nums[pivotIndex];
            } else if (pivotIndex < nums.length - k) {
                left = pivotIndex + 1;
            } else {
                right = pivotIndex - 1;
            }
        }
    }

    private static int partition(int[] nums, int left, int right, Random rand) {
        int pivotIndex = left + rand.nextInt(right - left + 1);
        int pivot = nums[pivotIndex];

        swap(nums, pivotIndex, right);

        int storeIndex = left;

        for (int i = left; i < right; i++) {
            if (nums[i] < pivot) {
                swap(nums, storeIndex++, i);
            }
        }

        swap(nums, storeIndex, right);
        return storeIndex;
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // Main method for testing
    public static void main(String[] args) {
        int[] nums = {1, 5, 1, 1, 6, 4};

        System.out.println("Before Wiggle Sort:");
        for (int num : nums) {
            System.out.print(num + " ");
        }

        wiggleSort(nums);

        System.out.println("\nAfter Wiggle Sort:");
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }
}
