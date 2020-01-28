import java.util.Scanner;
import java.util.InputMismatchException;


//Janne Kerola, opisknro 2311849
class Main{
    //required variables
    String filename = "words.txt";
    int errors = 8;
    char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    //game init
    WordList wordList = new WordList(filename);
    Hangman hangman = new Hangman(errors, wordList);
    
    //input
    private static Scanner sc = new Scanner(System.in);
    
    public static void main(String [] args){
        Main game = new Main();
        System.out.println("Hirsipuu!");
        String input = "";
        //main game loop
        while(!game.hangman.gameOver()){
            game.drawGame();
            input = sc.nextLine();
            input.toLowerCase();
            if(game.checkInput(input)){
                char letter = input.charAt(0);
                if(game.hangman.checkIfUsed(letter)){
                    System.out.println("Olet jo arvannut kirjaimella " + letter);
                }
                else{
                    game.hangman.guess(letter);
                }
            }else{
                System.out.println("\nKäytä aakkosia a-z!");
            }
        }
    }

    //methods
    //checks if user inputs allowed chars
    public boolean checkInput(String input){
        boolean result = false;
        char[] letters = input.toCharArray();
        if(letters.length == 1){
            for(int i=0; i<alphabet.length; i++){
                if(letters[0] == alphabet[i]){
                    result = true;
                }
            }
        }else{
            result = false;
            }
        return result;
    }
    //updates game info on screen
    public void drawGame(){
        System.out.println("\nArvauksia jäljellä: " + hangman.triesLeft());
        System.out.println("Käytetyt kirjaimet: " + hangman.getUsedLetters());
        System.out.println(hangman.getScrambledWord());
    }
}