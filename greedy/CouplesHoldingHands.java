public class CouplesHoldingHands {

    public int minSwapsCouples(int[] row) {

        int n = row.length;

        // position[value] = index
        int[] pos = new int[n];

        for (int i = 0; i < n; i++) {
            pos[row[i]] = i;
        }

        int swaps = 0;

        for (int i = 0; i < n; i += 2) {

            int first = row[i];

            // expected partner
            int partner = (first % 2 == 0) ? first + 1 : first - 1;

            if (row[i + 1] == partner) continue;

            swaps++;

            int partnerIndex = pos[partner];

            // swap row[i+1] and row[partnerIndex]
            int temp = row[i + 1];

            row[i + 1] = partner;
            row[partnerIndex] = temp;

            // update positions
            pos[temp] = partnerIndex;
            pos[partner] = i + 1;
        }

        return swaps;
    }
}
