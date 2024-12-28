import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите значение n (количество элементов массива): ");
        int n = scanner.nextInt();
        System.out.print("Введите значение m (интервал): ");
        int m = scanner.nextInt();

       int j = 1;
        int[] myArray = new int[n];
        for (int i = 0; i < n; i++) {
            myArray[i] = j++;
        }

        int current = 0;
        System.out.print("Путь: ");
        do {
            System.out.print(myArray[current]);
            current = (current + m-1) % n;
        } while (current != 0);
        scanner.close();
    }
}






