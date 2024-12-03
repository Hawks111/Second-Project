import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите выражение:");
        String enter = scanner.nextLine();

        String[] parts = enter.split(" "); // разбиваем строку на части пробелом
        try {
            if (parts.length != 3) {
                throw new IllegalArgumentException("throws Exception");
            }
            String number1Str = parts[0];
            String operation = parts[1];
            String number2Str = parts[2];

            boolean isRoman = isRomanNumber(number1Str) && isRomanNumber(number2Str);
            if (isRomanNumber(number1Str) ^ isRomanNumber(number2Str)) {
                throw new IllegalArgumentException("throws Exception");
            }

            int number1 = isRoman ? romanToArabic(number1Str) : Integer.parseInt(number1Str);
            int number2 = isRoman ? romanToArabic(number2Str) : Integer.parseInt(number2Str);

            if (number1 <= 0 || number1 > 10 || number2 <= 0 || number2 > 10) {
                throw new IllegalArgumentException("throws Exception");
            }

            int result = calculate(number1, number2, operation);

            if (isRoman) {
                if (result <= 0) {
                    throw new IllegalArgumentException("throws Exception");
                }
                System.out.println(arabicToRoman(result));
            } else {
                System.out.println(result);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("throws Exception");
        }
    }


    //создаём метод, который обрабатывает наличие операторов и указывает как поступить с выражением
    private static int calculate(int number1, int number2, String operation) throws IllegalArgumentException {
        switch (operation) {
            case "+":
                return number1 + number2;
            case "-":
                return number1 - number2;
            case "*":
                return number1 * number2;
            case "/":
                return number1 / number2;
            default:
                throw new IllegalArgumentException("throws Exception");
        }
    }


    // задаём строгую проверку римского ввода
    private static boolean isRomanNumber(String input) {
        return input.matches("^(X|IX|IV|V?I{0,3})$");
    }


    // пользуясь HashMap присваиваем римским цифрам арабские значения и задаём формулу для подсчёта
    private static int romanToArabic(String roman) {
        Map<Character, Integer> romanMap = new HashMap<>();
        romanMap.put('I', 1);
        romanMap.put('V', 5);
        romanMap.put('X', 10);
        romanMap.put('L', 50);
        romanMap.put('C', 100);
        int result = 0;
        int prevValue = 0;

        for (int i = roman.length() - 1; i >= 0; i--) {
            int currentValue = romanMap.get(roman.charAt(i));
            if (currentValue < prevValue) {
                result -= currentValue;
            } else {
                result += currentValue;
            }
            prevValue = currentValue;
        }
        return result;
    }


    // создаём метод для преобразования арабских цифр обратно в римские
    private static String arabicToRoman(int number) {
        StringBuilder roman = new StringBuilder();
        int[] arabicNumbers = {100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romanNumbers = {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        for (int i = 0; i < arabicNumbers.length; i++) {
            while (number >= arabicNumbers[i]) {
                roman.append(romanNumbers[i]);
                number -= arabicNumbers[i];
            }
        }
        return roman.toString();
    }
}