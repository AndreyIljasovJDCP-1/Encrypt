package codewars.example;

import java.util.Map;

public class RomanNumerals {
    public static final Map<Integer, String> ROMAN_NUMERALS =
            Map.of(
                    1, "I",
                    5, "V",
                    10, "X",
                    50, "L",
                    100, "C",
                    500, "D",
                    1000, "M");
    public static final int RADIX = 10;

    public static void main(String[] args) {
        System.out.println(code(2023));
    }

    /**
     * Convert arabic numerals to Roman numerals
     *
     * @param num arabic number
     * @return Roman number
     */
    public static String code(int num) {
        StringBuilder digits = new StringBuilder();
        int order = 1;
        while (num > 0) {
            int remain = num % RADIX;
            if (remain == 9) {
                digits
                        .append(ROMAN_NUMERALS.get(order * RADIX))
                        .append(ROMAN_NUMERALS.get(order));
            } else if (remain != 0) {
                int fiveRemain = remain % 5;
                if (fiveRemain == 4) {
                    digits
                            .append(ROMAN_NUMERALS.get(5 * order))
                            .append(ROMAN_NUMERALS.get(order));
                } else if (remain >= 5) {
                    digits
                            .append(ROMAN_NUMERALS.get(order).repeat(fiveRemain))
                            .append(ROMAN_NUMERALS.get(5 * order));
                } else {
                    digits.append(ROMAN_NUMERALS.get(order).repeat(fiveRemain));
                }
            }
            order *= RADIX;
            num /= RADIX;
        }
        return digits.reverse().toString();
    }
}
