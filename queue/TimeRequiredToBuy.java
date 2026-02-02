public class TimeRequiredToBuy {

    // Method to calculate time needed
    public static int timeRequiredToBuy(int[] tickets, int k) {
        int totalTime = 0;
        int target = tickets[k];

        for (int i = 0; i < tickets.length; i++) {
            if (i <= k) {
                totalTime += Math.min(tickets[i], target);
            } else {
                totalTime += Math.min(tickets[i], target - 1);
            }
        }
        return totalTime;
    }

    // Main method
    public static void main(String[] args) {

        int[] tickets = {2, 3, 2};
        int k = 2;

        int result = timeRequiredToBuy(tickets, k);

        System.out.println("Time needed to buy tickets: " + result);
    }
}
