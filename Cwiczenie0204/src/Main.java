public class Main {
    public static void main(String[] args) {
        int a1 = 0x6D, a2 = 13, a = a1 & a2,
                b1 = 0xA,  b2 = 5,  b = b1 | b2,
                c1 = 11 << 2, c2 = 6,  c = c1 ^ c2;

        System.out.println(a + " " + b + " " + c);
    }
}
