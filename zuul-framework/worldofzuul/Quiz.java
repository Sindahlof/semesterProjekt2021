package worldofzuul;

import java.util.Scanner;

public class Quiz {

    private String description;
    private String question;

    //husk i filen hvor du bruger denne attribute at oprette arrayed
    // inden så du sætter det ind på answers plads i constructor
    private String[] answers;

    //husk at sætte svar muligheder op med A,B,C og D før svar muligheden
    private String answerKey;


    //denne boolean bruges til at holde styr på om quizzen er blevet klaret
    private boolean completion;

    //en String som der bruges .equals på længere nede
    private String quit;

    Quiz(String question, String[] answers, String answerKey, String description) {
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

    private Player doQuiz(Player player) { //Method used to do the quiz it returns the players health
        printQuestion();
        printAnswers();

        while (true) {
            System.out.print("Type A, B or C for your answer or type quit: \n>");
            Scanner input = new Scanner(System.in); //Making a Scanner
            String answer = input.next(); //chekker hvad der er blevet skrevet på input og læse
            // dette som en String

            if (answer.equalsIgnoreCase(this.answerKey)) { //true hvis input passer med answerKey
                System.out.println("Your answer was correct!");
                this.completion = true; //Sætter completion til at være true så vi ved at quizen er klaret
                return player; //Returner player
            } else if (answer.equalsIgnoreCase(this.quit)) { //chekker om man skriver quit og vil så stoppe quizzen
                System.out.println("remember you can always return to this quiz later");
                return player;
            }

            System.out.println("Your answer was wrong.");
            player.setHealth(player.getHealth() - 1); //Everytime you answer wrong the player losses 1 health
            System.out.println("You have " + player.getHealth() + " left.");
            if (player.getHealth() == 0) { //Checks if the player has lost all his health. If true return player and stop the while loop
                return player;
            }
        }
    }

    public Player getQuiz(Player player) { //A method to execute the quiz
        return doQuiz(player);
    }

    public boolean isCompletion() {
        return this.completion;
    }

    public String getDescription() {
        return this.description;
    }
}
