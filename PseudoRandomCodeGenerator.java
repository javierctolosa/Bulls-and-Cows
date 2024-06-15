package bullscows;

import java.util.Random;

public class PseudoRandomCodeGenerator {
    final static int LOWER_ASCII_BOUND = 48;

    private int upperAsciiBound;
    private String code;
    private final int codeLength;

    public PseudoRandomCodeGenerator(int codeLength, int numSymbols) {
        this.valCodeInput(codeLength, numSymbols);
        this.setUpperAsciiBound(numSymbols);
        this.generatePseudoRandomCode(codeLength);
        this.codeLength = codeLength;
    }

    private void generatePseudoRandomCode(int codeLength) {

        StringBuilder pseudoRandomNumber = new StringBuilder();
        Random random = new Random();
        do {
            char symbol = (char) (random.nextInt(this.getUpperAsciiBound()) + LOWER_ASCII_BOUND);
            if (pseudoRandomNumber.indexOf(String.valueOf(symbol)) == -1 &&
                    String.valueOf(symbol).matches("[a-z]|[0-9]")) {
                pseudoRandomNumber.append(symbol);
            }
        }
        while (pseudoRandomNumber.length() < codeLength);
        this.code = pseudoRandomNumber.toString();
    }

    private void valCodeInput(int codeLength, int numSymbols) {
        if (codeLength > 10 || codeLength <= 0) {
            System.out.println("Error: can only generate codes with a length between 1 and 10.");
            System.exit(1);
        } else if (numSymbols <= 0 || numSymbols > 36) {
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            System.exit(1);
        } else if (numSymbols < codeLength) {
            System.out.printf(
                    "Error: it's not possible to generate a code with a length of %1$s with %2$s unique symbols.%n",
                    codeLength, numSymbols);
            System.exit(1);
        }
    }

    private void setUpperAsciiBound(int numSymbols) {
        this.upperAsciiBound = numSymbols <= 10 ? numSymbols : numSymbols + 39;
    }

    public int getUpperAsciiBound() {
        return this.upperAsciiBound;
    }

    public char getUpperAsciiBoundChar() {
        return (char) (this.getUpperAsciiBound() + LOWER_ASCII_BOUND - 1);
    }

    public String getCode() {
        return code;
    }

    public int getCodeLength() {
        return codeLength;
    }
}
