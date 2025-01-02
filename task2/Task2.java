import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Task2 {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Введите путь к файлу с координатами центра окружности и радиусом: "); //file1.txt
            String circleFile = scanner.nextLine();
            System.out.print("Введите путь к файлу с координатами точек: "); //file2.txt
            String pointsFile = scanner.nextLine();

            try {
                BufferedReader circleReader = new BufferedReader(new FileReader(circleFile));
                int x = Integer.parseInt(circleReader.readLine().trim());
                int y = Integer.parseInt(circleReader.readLine().trim());
                int radius = Integer.parseInt(circleReader.readLine().trim());
                circleReader.close();

                BufferedReader reader = new BufferedReader(new FileReader(pointsFile));
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] coordinates = line.trim().split(" ");
                    int xPoint = Integer.parseInt(coordinates[0]);
                    int yPoint = Integer.parseInt(coordinates[1]);

                    if (xPoint < (radius + x) & yPoint < (radius + y)) {
                        System.out.print("1. Точка (" + xPoint + ", " + yPoint + ") находится внутри окружности.\n");
                    } else if ((xPoint == (radius + x)) & (yPoint == (radius + y))) {
                        System.out.print("0. Точка (" + xPoint + ", " + yPoint + ") находится на окружности.\n");
                    } else {
                        System.out.print("2. Точка (" + xPoint + ", " + yPoint + ") находится снаружи окружности.\n");
                    }

                }
                reader.close();

            } catch (IOException e) {
                System.out.println("Ошибка при чтении файла: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка формата числа: " + e.getMessage());
            }
        }
    }
