class Solution {
    public int totalFruit(int[] fruits) {
        if (fruits.length == 0) return 0;

        int maxFruits = 0;
        int lastFruit = -1, secondLastFruit = -1;
        int lastFruitCount = 0;
        int currentMax = 0;

        for (int fruit : fruits) {
            if (fruit == lastFruit || fruit == secondLastFruit) {
                currentMax += 1; 
            } else {
                currentMax = lastFruitCount + 1; 
            }

            if (fruit == lastFruit) {
                lastFruitCount += 1;
            } else {
                lastFruitCount = 1; 
                secondLastFruit = lastFruit;
                lastFruit = fruit;
            }

            maxFruits = Math.max(maxFruits, currentMax);
        }

        return maxFruits; 
    }
}