package bullscows;

import java.util.Scanner;

public class BullsCowsGame {
    private final Scanner SCANNER = new Scanner(System.in);

    private int turn;
    private PseudoRandomCodeGenerator codeGenerator;

    public void startGame() {
        this.inputSecretCode();
        this.printSecretSentence();
        System.out.println("Okay, let's start a game!");

        BullsCowsGrader grader;
        do {
            grader = new BullsCowsGrader();
            takeTurn(grader);
        } while (!grader.isAnswerFound());
    }

    private void inputSecretCode() {
        System.out.println("Please, enter the secret code's length:");
        String codeLengthInput = SCANNER.nextLine();
        int codeLength = this.valCodeInput(codeLengthInput);

        System.out.println("Input the number of possible symbols in the code:");
        String numSymbolsInput = SCANNER.nextLine();
        int numSymbols = this.valCodeInput(numSymbolsInput);

        this.codeGenerator = new PseudoRandomCodeGenerator(codeLength, numSymbols);
    }

    private int valCodeInput(String codeInput) throws NumberFormatException {
        try { return Integer.parseInt(codeInput); } catch (NumberFormatException e) {
            System.out.printf("Error: \"%1$s\" isn't a valid number.", codeInput);
            System.exit(1);
        }
        return 0;
    }

    private void takeTurn(BullsCowsGrader grader) {
        System.out.printf("Turn %1$d:\n", ++this.turn);
        String inputCode = SCANNER.next();
        grader.gradeBullsCows(this.codeGenerator.getCode(), inputCode);
        printGrade(grader);
    }

    private void printGrade(BullsCowsGrader grader) {
        StringBuilder gradeStatement = new StringBuilder("Grade: ");
        if (grader.isAnswerFound()){
            gradeStatement.append(String.format("%1$d bulls\nCongratulations! You guessed the secret code", grader.getBulls()));
        } else if (grader.getBulls() != 1 && grader.getCows() != 1) {
            gradeStatement.append(String.format("%1$d bulls and %2$d cows", grader.getBulls(), grader.getCows()));
        } else if (grader.getBulls() != 1){
            gradeStatement.append(String.format("%1$d bulls and %2$d cow", grader.getBulls(), grader.getCows()));
        } else if (grader.getCows() != 1) {
            gradeStatement.append(String.format("%1$d bull and %2$d cows", grader.getBulls(), grader.getCows()));
        } else {
            gradeStatement.append(String.format("%1$d bull and %2$d cow", grader.getBulls(), grader.getCows()));
        }
        System.out.println(gradeStatement);
    }

    private void printSecretSentence() {
        StringBuilder sentence = new StringBuilder("The secret is prepared: **********");
        sentence.delete(sentence.lastIndexOf(" ") + this.codeGenerator.getCodeLength(), sentence.lastIndexOf("*"));
        if (Character.isDigit(this.codeGenerator.getUpperAsciiBoundChar())) {
            sentence.append(String.format(" (0-%1$c).", this.codeGenerator.getUpperAsciiBoundChar()));
        } else {
            sentence.append(String.format(" (0-9, a-%1$c).", this.codeGenerator.getUpperAsciiBoundChar()));
        }
        System.out.println(sentence);
    }
}
