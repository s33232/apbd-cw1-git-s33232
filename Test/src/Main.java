import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wprowadź liczby oddzielone przecinkiem:");
        String input = scanner.nextLine();

        if (input == null || input.trim().isEmpty()) {
            System.out.println("Błąd: Wejście nie może być puste.");
            return;
        }
    }
}