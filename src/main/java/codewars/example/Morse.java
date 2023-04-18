package codewars.example;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @see <a href="https://www.codewars.com/kata/54b72c16cd7f5154e9000457">
 * Decode the Morse code, advanced
 * </a>
 */
public class Morse {
    public static Map<String, String> MorseCode =
            Map.of(
                    "....", "H",
                    ".", "E",
                    "-.--", "Y",
                    ".---", "J",
                    "..-", "U",
                    "-..", "D",
                    "..", "I",
                    "-", "T",
                    "--", "M"
            );
    public static final int WORD_PAUSE = 7;
    public static final int CHARACTER_PAUSE = 3;

    public static void main(String[] args) {
        System.out.println(decodeBits("1000111000"));
    }

    /**
     * Decodes bits string to morse character string
     *
     * @param bits input message there every character being transmitted
     *             as a sequence of "dots" (short presses on the key)
     *             and "dashes" (long presses on the key).
     * @return decoded string
     */
    public static String decodeBits(String bits) {
        bits = bits.replaceAll("^0+|0+$", "");
        if (bits.isEmpty()) throw new IllegalArgumentException("only zeros in input");
        var zerosArr = bits.replaceAll("1+", "/").split("/");
        var onesArr = bits.replaceAll("0+", "/").split("/");
        var zeroStats =
                Arrays.stream(zerosArr).mapToInt(String::length).filter(c -> c != 0).summaryStatistics();
        var maxByZero = zeroStats.getMax();
        var minByZero = zeroStats.getMin();
        var minByOne =
                Arrays.stream(onesArr).mapToInt(String::length).filter(c -> c != 0).min().orElse(1);
        int timeUnit;
        if (maxByZero % WORD_PAUSE == 0) {
            timeUnit = maxByZero / WORD_PAUSE;
        } else if (minByZero % CHARACTER_PAUSE == 0 && minByZero != minByOne) {
            timeUnit = minByZero / CHARACTER_PAUSE;
        } else {
            timeUnit = Math.min(minByOne, minByZero);
        }
        var morseCode =
                bits.replaceAll("111".repeat(timeUnit), "-")
                        .replaceAll("1".repeat(timeUnit), ".")
                        .replaceAll("0000000".repeat(timeUnit), "   ")
                        .replaceAll("000".repeat(timeUnit), " ")
                        .replaceAll("0".repeat(timeUnit), "");
        return decode(morseCode);
    }

    public static String decode(String morseCode) {

        return Arrays.stream(morseCode.trim().split("\\s{3}"))
                .map(
                        word ->
                                Arrays.stream(word.split(" "))
                                        .map(s -> MorseCode.get(s))
                                        .collect(Collectors.joining()))
                .collect(Collectors.joining(" "));
    }
}
