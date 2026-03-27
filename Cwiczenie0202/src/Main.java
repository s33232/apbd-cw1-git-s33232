public class Main {
    public static void main(String[] args) {

        int initialValue = 14;
        System.out.println(initialValue);

        int step1 = initialValue >> 0;
        System.out.println(step1);

        int step2 = step1 << 2;
        System.out.println(step2);

        int step3 = step2 >> 4;
        System.out.println(step3);
    }
}