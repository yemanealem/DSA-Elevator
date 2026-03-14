public class KokoEatingBananas {

    /*
    ---------------------------------------------------------
    Koko Eating Bananas - Binary Search
    ---------------------------------------------------------

    We search for the minimum eating speed k.

    Search range:
        low = 1
        high = max(piles)

    For each mid (speed k):
        Calculate total hours needed.

    If hours <= h:
        Try smaller k (move left)
    Else:
        Need bigger k (move right)

    ---------------------------------------------------------
    */

    public static int minEatingSpeed(int[] piles, int h) {

        int low = 1;
        int high = 0;

        // Find maximum pile
        for (int pile : piles) {
            high = Math.max(high, pile);
        }

        int result = high;

        while (low <= high) {

            int mid = low + (high - low) / 2;

            if (canFinish(piles, h, mid)) {
                result = mid;      // valid speed
                high = mid - 1;    // try smaller
            } else {
                low = mid + 1;     // need faster speed
            }
        }

        return result;
    }

    // Helper function to check if Koko can finish with speed k
    private static boolean canFinish(int[] piles, int h, int k) {

        long hours = 0;

        for (int pile : piles) {
            hours += (pile + k - 1) / k;  // ceiling division
        }

        return hours <= h;
    }

    // Main method
    public static void main(String[] args) {

        int[] piles = {3, 6, 7, 11};
        int h = 8;

        int result = minEatingSpeed(piles, h);

        System.out.println("Minimum Eating Speed: " + result);
    }
}
