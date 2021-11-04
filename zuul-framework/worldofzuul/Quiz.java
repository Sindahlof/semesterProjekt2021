package worldofzuul;

import java.util.Scanner;

public class Quiz {

    private String description;
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

    Quiz(String question, String[] answers, String answerKey,String description) {
        this.question = question;
        this.answers = answers;
        this.answerKey = answerKey;
        this.completion = false;
        this.description = description;
        this.quit = "QUIT";

    }

    private void printQuestion() {
        System.out.println(this.question);
    }

    private void printAnswers() {
        for (String answer : this.answers) System.out.println(answer);

    }

    private void doQuiz(Player player) {
        printQuestion();
        printAnswers();

        while (true) {
            System.out.print("Your answer: \n>");
            Scanner input = new Scanner(System.in); //Making a Scanner
            String answer = input.next(); //chekker hvad der er blevet skrevet på input og læse
            // dette som en String

            if (answer.equalsIgnoreCase(this.answerKey)) { //hvis in put passer med answerkey
                // array pladsen vil dette være true
                System.out.println("Your answer was correct!");
                this.completion = true; //tæller memoryen en op så der kan holdes styr på om quizen er klaret
                break;
            } else if (this.quit.equalsIgnoreCase(answer)) { //chekkero m man skriver quit og vil så stoppe quizen
                System.out.println("remember you can always return to this quiz later");
                break;
            }

            System.out.println("Your answer was wrong");
            player.setHealth(player.getHealth()-1);
            if (player.getHealth()==0){
                break;
            }
        }
    }

    public boolean getQuiz(Player player) { //A metod to execute the quiz
        doQuiz(player);
        return this.completion;
    }

    public String getAnswer() { //kalder det man har sat som det rigtige svar i answers arrayed
        return this.answerKey;
    }

    public boolean isCompletion() {
        return this.completion;
    }

    public String getDescription() {
        return this.description;
    }
}
