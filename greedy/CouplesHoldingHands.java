import java.util.*;

/*
===========================================================
LeetCode 765 - Couples Holding Hands

Problem:
---------
You are given an array row[] where row[i] represents a person sitting in seat i.
People are paired as couples:
    (0,1), (2,3), (4,5), ...

You can swap any two people.
Return the minimum number of swaps so that each couple sits together.

-----------------------------------------------------------

How it works (Greedy Idea):
----------------------------
We scan the row in pairs (i, i+1).

For each pair:
- Determine the correct partner of row[i]
    partner = (even -> +1, odd -> -1)

- If row[i+1] is already the partner → continue
- Otherwise:
    → Find partner's position
    → Swap partner into correct position
    → Update positions array
    → Count 1 swap

Key Insight:
- Each swap fixes exactly one couple immediately
- We never revisit fixed pairs → greedy optimal

-----------------------------------------------------------

Running Time:
--------------
Time Complexity:  O(N)
    - Each person is visited at most once or swapped once

Space Complexity: O(N)
    - Position array stores index of each person

===========================================================
*/

public class CouplesHoldingHands {

    public int minSwapsCouples(int[] row) {

        int n = row.length;

        // position[value] = index of person in row
        int[] pos = new int[n];

        // Build initial positions
        for (int i = 0; i < n; i++) {
            pos[row[i]] = i;
        }

        int swaps = 0;

        // Process each pair
        for (int i = 0; i < n; i += 2) {

            int first = row[i];

            // Find expected partner
            int partner = (first % 2 == 0) ? first + 1 : first - 1;

            // Already correct couple
            if (row[i + 1] == partner) continue;

            swaps++;

            // index of correct partner
            int partnerIndex = pos[partner];

            // swap row[i+1] with partner
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
