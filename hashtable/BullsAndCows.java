int bulls = 0, cows = 0;
        int[] count = new int[10];

        for (int i = 0; i < secret.length(); i++) {
            int s = secret.charAt(i) - '0';
            int g = guess.charAt(i) - '0';

            if (s == g) {
                bulls++;
            } else {
                // If previously unmatched guess digit matches current secret → cow
                if (count[s] < 0) cows++;
                
                // If previously unmatched secret digit matches current guess → cow
                if (count[g] > 0) cows++;

                count[s]++;
                count[g]--;
            }
        }

        return bulls + "A" + cows + "B";