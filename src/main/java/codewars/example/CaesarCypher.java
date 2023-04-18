package codewars.example;

public class CaesarCypher {

    public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    public static void main(String[] args) {
        // смещение 10
        System.out.println(cypher("helloworld", "rovvygybvn"));
    }

    /**
     * Check if string encrypted byCaesarCypher
      * @param text original string
     * @param cypher encrypted string
     * @return - true if used CaesarCypher for encrypt
     */
    public static boolean cypher(String text, String cypher) {
        if (text.isEmpty() || cypher.isEmpty() || text.length() != cypher.length()) {
            return false;
        }
        int indexTextSymbol = ALPHABET.indexOf(text.charAt(0));
        int indexCypherSymbol = ALPHABET.indexOf(cypher.charAt(0));
        int diff = indexCypherSymbol - indexTextSymbol;
        int offset = diff > 0 ? diff : diff + ALPHABET.length();
        printData(indexTextSymbol, indexCypherSymbol, offset);

        for (int i = 1; i < text.length(); i++) {
            indexTextSymbol = ALPHABET.indexOf(text.charAt(i));
            indexCypherSymbol = ALPHABET.indexOf(cypher.charAt(i));
            int tempOffset = indexTextSymbol > indexCypherSymbol
                    ? offset - ALPHABET.length()
                    : offset;
            printData(indexTextSymbol, indexCypherSymbol, tempOffset);
            if ((indexTextSymbol + tempOffset) != indexCypherSymbol) {
                return false;
            }
        }
        return true;
    }

    private static void printData(int indexTextSymbol, int indexCypherSymbol, int offset) {
        System.out.println("indexTextSymbol: " + indexTextSymbol);
        System.out.println("indexCypherSymbol: " + indexCypherSymbol);
        System.out.println("offset: " + offset);
    }
}
