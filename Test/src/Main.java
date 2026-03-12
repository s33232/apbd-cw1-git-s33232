import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wprowadź liczby oddzielone przecinkiem:");
        String input = scanner.nextLine();
        if (input == null || input.trim().isEmpty()) {
            System.out.println("Błąd: Wejście nie może być puste.");
        }
            try {
                String[] stringNumbers = input.split(",");
                int[] numbers = new int[stringNumbers.length];
                for (int i = 0; i < stringNumbers.length; i++) {
                    numbers[i] = Integer.parseInt(stringNumbers[i].trim());
                }
                if (numbers.length > 100) {
                    System.out.println("Błąd: Można podać maksymalnie 100 liczb.");
                    return;
                }
                System.out.println("Wprowadzono " + numbers.length + " liczb.");
            } catch (NumberFormatException e) {
                System.out.println("Błąd: Wprowadzono niepoprawne dane. Użyj tylko cyfr i przecinków.");
            }
        }
    }