package Lesson;

import java.util.Scanner;

public class Calc {
    public static void main(String[] args) throws Exception{
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите два числа: арабских или римских");
        String expression = scanner.nextLine();
        System.out.println(parse(expression));
    }
    static String parse (String expression) throws Exception{
        int num1;
        int num2;
        String oper;
        String result;
        boolean rim;
        String[] operands = expression.split("[+\\-*/]");
        if (operands.length != 2) throw new Exception("Должно быть два операнда");
        oper = detectOperation(expression);
        if (oper == null) throw new Exception("Неподдерживаемая математическая операция");
        if (Rim.isRoman(operands[0]) && Rim.isRoman(operands[1])) {
            num1 = Rim.convertToArabian(operands[0]);
            num2 = Rim.convertToArabian(operands[1]);
            rim = true;
        }
        else if (!Rim.isRoman(operands[0]) && !Rim.isRoman(operands[1])) {
            num1 = Integer.parseInt(operands[0]);
            num2 = Integer.parseInt(operands[1]);
            rim = false;
        }
        else {
            throw new Exception("Числа должны быть в одном формате");
        }
        if (num1 > 10 || num2 > 10) {
            throw new Exception("Числа должны быть от 1 до 10");
        }
        int arabian = calc(num1, num2, oper);
        if (rim) {
            if (arabian <= 0) {
                throw new Exception("Римское число должно быть больше нуля");
            }
            result = Rim.convertToRoman(arabian);
        } else {
            result = String.valueOf(arabian);
        }
        return result;
    }
    static String detectOperation(String expression) {
        if (expression.contains("+")) return "+";
        else if (expression.contains("-")) return "-";
        else if (expression.contains("*")) return "*";
        else if (expression.contains("/")) return "/";
        else return null;
    }
    static int calc(int a, int b, String oper) {

        if (oper.equals("+")) return a + b;
        else if (oper.equals("-")) return a - b;
        else if (oper.equals("*")) return a * b;
        else return a / b;
    }
}
class Rim {
    static String[] rimArray = new String[]{"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI",
            "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV",
            "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI",
            "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII",
            "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII",
            "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV",
            "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV",
            "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII",
            "XCVIII", "XCIX", "C"};


    static boolean isRoman(String val) {
        for (int i = 0; i < rimArray.length; i++) {
            if (val.equals(rimArray[i])) {
                return true;
            }
        }
        return false;
    }

    static int convertToArabian(String roman) {
        for (int i = 0; i < rimArray.length; i++) {
            if (roman.equals(rimArray[i])) {
                return i;
            }
        }
        return -1;
    }

    static String convertToRoman(int arabian) {
        return rimArray[arabian];
    }
}

