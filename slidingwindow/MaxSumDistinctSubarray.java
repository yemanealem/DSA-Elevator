int n = nums.length;
        Map<Integer, Integer> freqMap = new HashMap<>();
        long currentSum = 0;
        long maxSum = 0;

        // Initialize first window
        for (int i = 0; i < k; i++) {
            freqMap.put(nums[i], freqMap.getOrDefault(nums[i], 0) + 1);
            currentSum += nums[i];
        }
        if (freqMap.size() == k) maxSum = currentSum;

        // Slide the window
        for (int i = k; i < n; i++) {
            int newNum = nums[i];
            int leftNum = nums[i - k];

            // Add new number
            freqMap.put(newNum, freqMap.getOrDefault(newNum, 0) + 1);
            currentSum += newNum;

            // Remove left number
            freqMap.put(leftNum, freqMap.get(leftNum) - 1);
            if (freqMap.get(leftNum) == 0) freqMap.remove(leftNum);
            currentSum -= leftNum;

            // Update maxSum if all elements are distinct
            if (freqMap.size() == k) maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;