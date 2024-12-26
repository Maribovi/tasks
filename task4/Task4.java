import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Task4 {
    public static void main(String[] args) {

    int[] nums;
    Scanner scanner = new Scanner(System.in);
    System.out.print("Введите путь к файлу: "); //Task4.txt
    String filepath = scanner.nextLine();


   try {
       BufferedReader reader = new BufferedReader(new FileReader(filepath));
       String newline = reader.readLine();
       nums = Arrays.stream(newline.split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        reader.close();
    } catch (IOException e) {
        System.err.println("Ошибка при чтении файла: " + e.getMessage());
        return;
    }

    int minMoves = calculateMinMoves(nums);
    System.out.println("Минимальное количество ходов: " + minMoves);
}

    private static int calculateMinMoves(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;

        int median = nums[n / 2];

        int moves = 0;
        for (int num : nums) {
            moves += Math.abs(num - median);
        }
        return moves;
    }
}



