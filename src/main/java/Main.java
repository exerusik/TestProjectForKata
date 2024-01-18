import java.util.Arrays;
import java.util.Scanner;
import java.util.spi.AbstractResourceBundleProvider;

public class Main {
    public static void main(String[] args) {
        System.out.print("Please type expression: ");
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        System.out.println(calc(line));
    }

    public static String calc(String input) {
        String str = input.replaceAll("\\s", "");
        String [] operators = {"\\+", "-", "\\*", "/"};
        String [] actions = {"+", "-", "*", "/"};
        int operation = -1;
        for (int i = 0; i < actions.length; i++) {
            if (str.contains(actions[i])) {
                operation = i;
                break;
            }
        }
        if (operation == -1) {
            throw new RuntimeException("expression is not correct");
        }
        String[] operands = str.split(operators[operation]);
        String oneOperand = operands[0];
        String twoOperand = operands[1];

        if (operands.length > 2){
            throw new RuntimeException("expression is not correct");
        }


        if (isArabNumeric(oneOperand) && isArabNumeric(twoOperand)){
            int parseOneOperand = Integer.parseInt(operands[0]);
            int parseTwoOperand = Integer.parseInt(operands[1]);

            if (parseOneOperand > 10 || parseTwoOperand > 10){
                throw new RuntimeException("numbers should be less 10");
            }

            String result = calculated(parseOneOperand, parseTwoOperand, actions[operation]);
            return result;
        } else {
            int oneRomainOperand = convertToRomainNumber(oneOperand);
            int twoRomainOperand = convertToRomainNumber(twoOperand);
            String result = calculated(oneRomainOperand, twoRomainOperand, actions[operation]);
            String convertToRomainResult = convertToRomainResult(result);
            return convertToRomainResult;

        }

    }

    private static String convertToRomainResult(String result) {

        for (RomanNumerals value : RomanNumerals.values()) {
            if (result.equals(String.valueOf(value.getTranslation()))) {
                return String.valueOf(value);
            }
        }
        throw new RuntimeException("Roman number is not negative number");
    }

    private static String calculated(int first, int second, String operator) {
        int sum;
            switch (operator) {
                case "+" :
                    sum = first + second;
                    break;
                case "-" :
                    sum = first - second;
                    break;
                case "*" :
                    sum = first * second;
                    break;
                case "/" :
                    sum = first / second;
                    break;
                default:
                    throw new RuntimeException("this digit no exist");
            }


            return String.valueOf(sum);
        }

        private static boolean isArabNumeric(String number) {
            char [] digitsFirst = number.toCharArray();

            for (char c : digitsFirst) {
                if (Character.isDigit(c)) {
                    return true;
                }
            }
            return false;
        }

        private static int convertToRomainNumber(String number) {
            for (RomanNumerals value : RomanNumerals.values()) {
                if (value.getTranslation() > 10) {
                    throw new RuntimeException("this expression number is not correct or numbers should be less X");
                } else if (number.equals(String.valueOf(value))) {
                    return value.getTranslation();
                }
            }
            return 0;

//        switch (number) {
//            case "I":
//                return 1;
//            case "II":
//                return 2;
//            case "III":
//                return 3;
//            case "IV":
//                return 4;
//            case "V":
//                return 5;
//            case "VI":
//                return 6;
//            case "VII":
//                return 7;
//            case "VIII":
//                return 8;
//            case "XI":
//                return 9;
//            case "X":
//                return 10;
//            default:
//                throw new RuntimeException("this romain number not exist");
//        }

        }
}
