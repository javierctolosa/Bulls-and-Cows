package bullscows;

public class BullsCowsGrader {
    private boolean answerFound;
    private int bulls;
    private int cows;

    public void gradeBullsCows(String code, String inputCode) {
        for (int i = 0; i < inputCode.length(); i++) {
            if (inputCode.charAt(i) == code.charAt(i)) {
                ++this.bulls;
            } else if (code.contains(String.valueOf(inputCode.charAt(i)))) {
                ++this.cows;
            }
        }
        if (code.equals(inputCode)) {
            answerFound = true;
        }
    }

    public boolean isAnswerFound() {
        return answerFound;
    }

    public Integer getBulls() {
        return bulls;
    }

    public Integer getCows() {
        return cows;
    }
}
