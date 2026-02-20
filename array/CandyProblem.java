class Solution {
    public int candy(int[] ratings) {
         int n = ratings.length;
        if (n == 0) return 0;

        int total = 1;    
        int up = 0;      
        int down = 0;     
        int peak = 1;     

        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {         
                up++;
                peak = up + 1;
                total += peak;
                down = 0;                              
            } else if (ratings[i] < ratings[i - 1]) {  
                down++;
                total += down;
                if (down >= peak) total++;              
                up = 0;                                
            } else {                                    
                up = 0;
                down = 0;
                peak = 1;
                total += 1;
            }
        }

        return total;
    }
}