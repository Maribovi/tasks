import java.util.Scanner;

public class Task1 {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Введите значение n (количество элементов массива: ");
            int n = scanner.nextInt();
            System.out.print("Введите значение m (интервал): ");
            int m = scanner.nextInt();

          int j = 1;
            int[] myArray = new int[n];
               for (int i = 0; i<n; i++) {
                   myArray[i] = j++;
                   }

            StringBuilder path = new StringBuilder();
            int x = 0;
            do {
                path.append(myArray[x]);
                x = (x + m-1) % n;
            } while (x != 0);

            System.out.println("Полученный путь: " + path);
        }
}





