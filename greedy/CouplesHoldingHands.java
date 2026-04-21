import java.util.*;

public class CouplesHoldingHands {

    public int minSwapsCouples(int[] row) {

        int n = row.length;
        Map<Integer, Integer> pos = new HashMap<>();

        // Step 1: Store current positions
        for (int i = 0; i < n; i++) {
            pos.put(row[i], i);
        }

        int swaps = 0;

        // Step 2: Fix each pair greedily
        for (int i = 0; i < n; i += 2) {

            int first = row[i];

            // expected partner:
            int expectedPartner = (first % 2 == 0) ? first + 1 : first - 1;

            // if already correct, continue
            if (row[i + 1] == expectedPartner) {
                continue;
            }

            // otherwise swap needed
            swaps++;

            int partnerIndex = pos.get(expectedPartner);

            // swap row[i+1] and partnerIndex
            int temp = row[i + 1];
            row[i + 1] = expectedPartner;
            row[partnerIndex] = temp;

            // update map
            pos.put(temp, partnerIndex);
            pos.put(expectedPartner, i + 1);
        }

        return swaps;
    }
}
