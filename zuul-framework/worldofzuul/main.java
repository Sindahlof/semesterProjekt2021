package worldofzuul; //den package vi bruger

import java.util.Scanner;

public class main {
    public static void main(String[] args) {

        // Game newGame = new Game(); //laver et nyt objekt der herdder game
        // newGame.play(); //kalder en construcotr som hedder play fra klassen Game


        /*
        Hvis man vil se alle keys i HashMap'et
        CommandWords obj1 = new CommandWords();
        obj1.showAll();
        */


        String question = "asdasdasdasda";
        String[] questions = {"A) asdasdas","B) asdasdasd","C) asdasdasda"};
        String answerKey = "A";

        Quiz room1 = new Quiz(question,questions,answerKey);

        room1.doQuiz();

        Item test = new Information("Cake","Fuck dig sindahl");
        System.out.println(test.getItemName());


    }
}
