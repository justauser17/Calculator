import java.util.Scanner;

public class Main2 {

    private static final String[] romanNum = {"C", "XC", "L", "XL", "X", "IX","VIII", "VII", "VI", "V", "IV", "III", "II", "I"};
    private static final int[] num = {100, 90, 50, 40, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println(calc(input));
    }

    public static int romanToArab(String romanNumber) {

        int arabNum = 0;
        for (int i = 0; i < num.length; i++) {
            while (romanNumber.startsWith(romanNum[i])) {
                arabNum += num[i];
                romanNumber = romanNumber.substring(romanNum[i].length());
            }
        }
        return arabNum;
    }

    public static String arabToRoman(int arabNum) {

        StringBuilder roman = new StringBuilder();
        for (int i = 0; i < num.length; i++) {
            while (arabNum >= num[i]) {
                roman.append(romanNum[i]);
                arabNum -= num[i];
            }
        }
        return roman.toString();
    }

    private static boolean isRoman(String s) {
        for (String romanNumeral : romanNum) {
            if (s.equals(romanNumeral)) {
                return true;
            }
        }
        return false;
    }

    public static String calc(String input) throws Exception {
        String[] array = input.split(" ");
        if (array.length > 3){
            throw new Exception("операндов не может быть больше двух");
        }
        int num1, num2;
        String operator = array[1];

        if (isRoman(array[0]) && isRoman(array[2])) {
            num1 = romanToArab(array[0]);
            num2 = romanToArab(array[2]);

            int result;
            if (num1 > 0 && num1 <= 10 && num2 > 0 && num2 <= 10) {
                result = switch (operator) {
                    case "+" -> num1 + num2;
                    case "-" -> num1 - num2;
                    case "*" -> num1 * num2;
                    case "/" -> num1 / num2;
                    default -> throw new Exception("неверный знак");
                };
            } else throw new Exception("неверный диапазон чисел");

            if (result < 1) {
                throw new Exception("результат не может быть меньше 1");
            }
            return arabToRoman(result);

        } else {
            num1 = Integer.parseInt(array[0]);
            num2 = Integer.parseInt(array[2]);

            int result;
            if (num1 > 0 && num1 <= 10 && num2 > 0 && num2 <= 10) {
                result = switch (operator) {
                    case "+" -> num1 + num2;
                    case "-" -> num1 - num2;
                    case "*" -> num1 * num2;
                    case "/" -> num1 / num2;
                    default -> throw new IllegalArgumentException("неверный знак");
                };
            } else throw new NumberFormatException("неверный диапазон чисел");
            return Integer.toString(result);
        }
    }
}
