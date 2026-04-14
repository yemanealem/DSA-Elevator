public class WaterJugProblem {

    public static boolean canMeasureWater(int x, int y, int z) {
        if (z == 0) return true;
        if (x + y < z) return false;

        return z % gcd(x, y) == 0;
    }

    private static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static void main(String[] args) {
        int x = 3, y = 5, z = 4;
        System.out.println(canMeasureWater(x, y, z)); 
    }
}
