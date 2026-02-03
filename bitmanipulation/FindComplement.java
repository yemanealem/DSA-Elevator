package bitmanipulation;

public class FindComplement {
     public int findComplement(int num) {
          int mask = 1;
        while (mask < num) {
            mask = (mask << 1) | 1; // create mask with 1s in all bits
        }
        return num ^ mask; // XOR flips all bits
    }
    
}
