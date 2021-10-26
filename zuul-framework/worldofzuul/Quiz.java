package worldofzuul;

import java.util.Scanner;

public class Quiz {

    private String question;

    //husk i filen hvor du bruger denne atribut at oprette arrayed
    // inden så du sætter det ind på answers plads i constructoren
    private String[] answers;

    // denne int bestemmet hvor i arrayed det korekte svar er
    //husk at sætte svar mulighedder op med A,B,C og D før svar muligheden eller til svarende
    private String answerKey;


    //denne int bruges til at holde styr på om quizzen er blevet klaret
    private boolean completion;

    //en String som der bruges .equals på længere nede
    private String quit;

    Quiz(String question, String[] answers, String answerKey) {
        this.question = question;
        this.answers = answers;
        this.answerKey = answerKey;
        this.completion = false;
        this.quit = "QUIT";
    }

    private void printQuestion() {
        System.out.println(this.question);
    }

    private void printAnswers() {
        for (int i = 0; i < this.answers.length; i++)
            System.out.println(this.answers[i]);

    }

    private boolean doQuiz() {
        printQuestion();
        printAnswers();
        boolean check = true; //der bruges en boolean til at kører while loopet

        while (check) {
            Scanner input = new Scanner(System.in); //laver en ny scanner
            String answer = input.next().toUpperCase(); //chekker hvad der er blevet skrevet på input og læse
            // dette som en String

            if (answer.equals(this.answerKey)) { //hvis in put passer med answerkey
                // array pladsen vil dette være true
                check = false;
                System.out.println("Your answer was correct!");
                this.completion = true; //tæller memoryen en op så der kan holdes styr på om quizen er klaret
                break;
            } else if (this.quit.equals(answer)) { //chekkero m man skriver quit og vil så stoppe quizen
                System.out.println("remember you can always return to this quiz later");
                return false;
            }

            System.out.println("Your answer was wrong");
        }
        return true;
    }

    public boolean getQuiz() { //en metode man kan kalde for at chekke om quizzen er klaret
        doQuiz();
        return this.completion;
    }

    public String getAnswer() { //kalder det man har sat som det rigtige svar i answers arrayed
        return this.answerKey;
    }
}
