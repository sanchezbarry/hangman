import java.util.Arrays;
import java.util.Scanner;

public class Hangman {

    //list of words
    public static String[] listofwords = {"ant", "baboon", "badger", "bat", "bear", "beaver", "camel",
    "cat", "clam", "cobra", "cougar", "coyote", "crow", "deer",
    "dog", "donkey", "duck", "eagle", "ferret", "fox", "frog", "goat",
    "goose", "hawk", "lion", "lizard", "llama", "mole", "monkey", "moose",
    "mouse", "mule", "newt", "otter", "owl", "panda", "parrot", "pigeon", 
    "python", "rabbit", "ram", "rat", "raven","rhino", "salmon", "seal",
    "shark", "sheep", "skunk", "sloth", "snake", "spider", "stork", "swan",
    "tiger", "toad", "trout", "turkey", "turtle", "weasel", "whale", "wolf",
    "wombat", "zebra"};

    //status of gallows
    public static String[] gallows = 
    {"+---+\n" +
    "|   |\n" +
    "    |\n" +
    "    |\n" +
    "    |\n" +
    "    |\n" +
    "=========\n",

    "+---+\n" +
    "|   |\n" +
    "O   |\n" +
    "    |\n" +
    "    |\n" +
    "    |\n" +
    "=========\n",

    "+---+\n" +
    "|   |\n" +
    "O   |\n" +
    "|   |\n" +
    "    |\n" +
    "    |\n" +
    "=========\n",

    " +---+\n" +
    " |   |\n" +
    " O   |\n" +
    "/|   |\n" +
    "     |\n" +
    "     |\n" +
    " =========\n",

    " +---+\n" +
    " |   |\n" +
    " O   |\n" +
    "/|\\  |\n" + //print '\' 
    "     |\n" +
    "     |\n" +
    " =========\n",

    " +---+\n" +
    " |   |\n" +
    " O   |\n" +
    "/|\\  |\n" +
    "/    |\n" +
    "     |\n" +
    " =========\n",

    " +---+\n" +
    " |   |\n" +
    " O   |\n" +
    "/|\\  |\n" + 
    "/ \\  |\n" +
    "     |\n" +
    " =========\n"};

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        //set the word to be guess from the randomly generated word
        String word = randomWord();
        
        // create the blanks based on the chosen word's length.
        char[] placeholders = new char[word.length()];
        for (int i = 0; i < placeholders.length; i++) {
            placeholders[i] = '_';
        }

        //set the misses to 0
        int misses = 0;    

        char[] missedGuesses =  new char[6];  

        //to print the game each time a character is guess, as long as misses <6
        while (misses < 6) {
            System.out.print(gallows[misses]);
            
            System.out.print("Word:   ");
            printPlaceholders(placeholders);
            System.out.print("\n");

            System.out.print("Misses:   ");
            printMissedGuesses(missedGuesses);
            System.out.print("\n\n");

            //define the user's guess, and only take the 1st character
            System.out.print("Guess:   ");
            char guess = scan.nextLine().charAt(0);
            System.out.print("\n");

            if (checkGuess(word, guess)) {
                updatePlaceholders(word, placeholders, guess);
            } else {
                missedGuesses[misses] = guess;
                misses++;
            }
            
            if (Arrays.equals(placeholders, word.toCharArray())) {
                System.out.print(gallows[misses]);
                System.out.print("\nWord:   ");
                printPlaceholders(placeholders);
                System.out.println("\nGreat job! That's correct.");                
                break;
            }
        }
        //loss event & message
        if (misses == 6) {
            System.out.print(gallows[6]);
            System.out.println("\nYou Lost!");
            System.out.println("\nThe word was: '" + word + "'");
        }
        scan.close();
    }

    //choose random word from list of words.
    public static String randomWord() {
        int numWords = listofwords.length;
        double randomDouble = Math.random();
        int randomIndex = (int) (randomDouble * numWords);
        return listofwords[randomIndex];
    }

    //loop through the set word, and check if guessed charcater exists.
    public static boolean checkGuess(String word, char guess) {

        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == guess) {
                return true;
            } 
        }
        return false;

    }

    //replace placeholders with correct guess
    public static void updatePlaceholders(String word, char[] placeholders, char guess) {

            for (int j = 0; j < word.length(); j++) {

                if (word.charAt(j) == guess) {
                    placeholders[j] = guess;
                }
            }
        }
    

    public static void printPlaceholders(char[] placeholders) {
        for (int i = 0; i < placeholders.length; i++) {
                System.out.print(" " + placeholders[i]);
            }
        System.out.print("\n");
    }

    //show all wrong guesses
    public static void printMissedGuesses(char[] misses) {
        for (int i = 0; i < misses.length; i++) {
            System.out.print(misses[i]);
        }
    }

}