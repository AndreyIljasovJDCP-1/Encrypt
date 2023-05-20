package codewars.example;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @see <a href="https://www.codewars.com/kata/58c5577d61aefcf3ff000081">Rail Fence Cipher: Encoding
 *     and Decoding</a>
 */
public class RailFenceCipher {
    public static void main(String[] args) {
        // System.out.println(encode("WEAREDISCOVEREDFLEEATONCE", 6));

        System.out.println(decode("WVTEOEAOACRENRSEECEIDLEDF", 6));
    }

    static String decode(String s, int n) {
        String[][] arr = new String[n][s.length()];
        fill(arr);
        int index = 0;
        int step = 2 * (n - 1);
        for (int i = 0; i < n; i++) {
            boolean odd = true;
            int subIndex = i;
            while (subIndex < s.length()) {
                arr[i][subIndex] = String.valueOf(s.charAt(index));
                index++;
                if (i == 0 || i == n - 1) {
                    subIndex += step;
                } else {
                    subIndex += odd ? step - 2 * i : 2 * i;
                    odd = !odd;
                }
            }
        }
        print(arr);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < n; j++) {
                // if (Objects.nonNull(arr[j][i])) result.append(arr[j][i]);
                if (!Objects.equals(arr[j][i], "_")) result.append(arr[j][i]);
            }
        }

        return result.toString();
    }

    static String encode(String s, int n) {
        String[][] rail = new String[n][s.length()];
        fill(rail);
        int i = 0;
        int offset = 1;
        for (int j = 0; j < s.length(); j++) {
            rail[i][j] = String.valueOf(s.charAt(j));
            if (i == n - 1) {
                offset *= -1;
            }
            i += offset;
            if (i == 0) {
                offset *= -1;
            }
        }
        print(rail);
        System.out.println();

        return Arrays.stream(rail)
                .flatMap(Arrays::stream)
                .filter(x -> !x.equals("_"))
                // .filter(Objects::nonNull)
                .collect(Collectors.joining());
    }

    private static void print(String[][] arr) {
        for (String[] lines : arr) {
            System.out.println();
            for (String letter : lines) {
                System.out.print(letter);
            }
        }
        System.out.println();
    }

    private static void fill(String[][] arr) {
        for (String[] lines : arr) {
            Arrays.fill(lines, "_");
        }
    }

    public static String encodeBest(String s, int n) {
        return process(s, n, true);
    }

    public static String decodeBest(String s, int n) {
        return process(s, n, false);
    }

    private static String process(String s, int n, boolean enc) {
        int len = s.length();
        int d = n * 2 - 2;
        StringBuilder sb = new StringBuilder(s);
        int counter = 0;
        for (int i = 0; i < n; i++) {
            int next = i == n - 1 ? d : d - i * 2;
            int index = i;

            while (index < len) {
                sb.setCharAt((enc ? counter++ : index), s.charAt(enc ? index : counter++));
                index += next;
                next = (next == d ? d : d - next);
            }
        }

        return sb.toString();
    }
}