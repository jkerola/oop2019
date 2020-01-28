import java.util.List;
import java.util.ArrayList;
import java.util.Random;


class Hangman{
    //needed variables
    private int errors;
    private int errorsMade;
    private int turn;
    private int correct;
    //variables for words
    private List<String> wordList =  new ArrayList<String>();
    private List<Character> usedLetters = new ArrayList<Character>();
    private char[] correctChars;

    private String word;
    private Random rng;

    //constructor
    public Hangman(int errors, WordList words){
        this.errors = errors;
        this.turn = 0;
        this.correct = 0;
        this.wordList = words.returnWords();
        rng = new Random();
        this.word = wordList.get(rng.nextInt(wordList.size()));
        this.correctChars = this.word.toCharArray();
    }

    //methods

    //checks if letter has already been used
    public boolean checkIfUsed(Character letter){
        boolean result = false;
        for (int i = 0; i < usedLetters.size(); i++) {
            if (letter == usedLetters.get(i)) {
                result = true;
            }
        }
        return result;
    }
    //get method for testing
    public char[] getCorrectChars(){
        return this.correctChars;
    }

    //checks if letter is in the word
    //if true, advances the game
    //if false, docks a point
    //always adds the letter into used letters
    public boolean guess(Character letter){
        boolean result = false;
        int noneRight = 0;
        for(int i=0; i<word.length(); i++){
            if(letter == word.charAt(i)){
                result = true;
                noneRight += 1;
                correct += 1;
            }
        }
        if(noneRight == 0){
            result = false;
            this.errorsMade += 1;
        }
            usedLetters.add(letter);
            this.turn += 1;
            return result;
        }
        
    //get method
    public List<Character> getUsedLetters(){
        return  usedLetters;
    }
    //get tries let
    public int triesLeft(){
        return (errors - errorsMade);
    }
    //creates the visually scrambled word for gameplay
    public String getScrambledWord(){
        String scrambled = "";
        for(int i=0; i<word.length(); i++){
            scrambled += "_";
        }
        char[] scrambledChars = scrambled.toCharArray();
        for(int i=0; i<word.length(); i++){
            if(usedLetters.contains(word.charAt(i))){
                scrambledChars[i] = word.charAt(i);
            }
        }
        scrambled = String.valueOf(scrambledChars);
        return scrambled;
    }
    //get method for testing
    public String getWord(){
        return word;
    }
    //checks win/lose conditions, game ends when true;
    public boolean gameOver(){
        boolean result = false;
        if(triesLeft() == 0){
            result = true;
            System.out.println("Arvaukset loppu, hävisit pelin!");
            System.out.println("Sana oli: " + getWord());
        }else if(correct == word.length()){
            result = true;
            System.out.println("Sana arvattu, voitit pelin!");
            System.out.println("Sana oli: " + getWord());
        }
        return result;
    }

}