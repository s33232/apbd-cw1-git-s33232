public class Main {
    public static void main(String[] args) {
        int number = 305419896;

        System.out.println(number + Integer.toHexString(number));

        int last5Bits = number & 0b11111;
        System.out.println(last5Bits);


        int seventhBit = (number >> 6) & 1;
        System.out.println(seventhBit);

        int byte4 = (number >> 24) & 0xFF;
        int byte3 = (number >> 16) & 0xFF;
        int byte2 = (number >> 8) & 0xFF;
        int byte1 = number & 0xFF;

        System.out.println("byte4" + byte4);
        System.out.println("byte3" + byte3);
        System.out.println("byte2" + byte2);
        System.out.println("byte1" + byte1);


        int middleBytes = (number >> 8) & 0xFFFF;
        System.out.println(middleBytes);
    }
}