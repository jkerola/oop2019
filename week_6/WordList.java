import java.io.IOException;
import java.io.FileNotFoundException;
import java.lang.IndexOutOfBoundsException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.PrintWriter;

class WordList{
    //variables
    private Scanner readFile;
    //needed for bonus methods
    private PrintWriter printFile;
    //constructor
    public WordList(String filename){
        try{
            this.readFile = new Scanner(new File(filename));
        }catch(FileNotFoundException e){
            System.out.println("Sanalistaa ei löydetty...");
        }
    }
    //methods
    public List<String> returnWords(){
        List<String> words = new ArrayList<String>();
        while(readFile.hasNextLine()){
            String line = readFile.nextLine();
            words.add(line);
            }
        readFile.close();
        return words;
        }

    //bonus methods, unused but work ok.
    //creates a new file and copies over lines that match
    //the given length.
    //creates a new object with the new file
    //file exists as filteredwords.txt in buffered memory
    //can create a new file on disk if need be
    public WordList filteredWords(int length){
        String file = "FilteredWords.txt";
        try{
            printFile = new PrintWriter(file);
        }catch(FileNotFoundException e){
            System.out.println("tätä virhettä ei pitäisi näkyä.");
        }
        while(readFile.hasNextLine()){
            String line = readFile.nextLine();
            if(line.length() == length){
                printFile.println(line);
            }
        }
        readFile.close();
        printFile.close(); 
        WordList filteredWords = new WordList(file);
        return filteredWords;
    }
    
    //same as previous one, with a few small changes
    //counts number of correct chars
    //compares it to words in list
    //only adds words that match
    //same in every other aspect as previous one.
    public WordList wordsWithChars(String letters) {
        String file = "FilteredWords.txt";
        try {
            printFile = new PrintWriter(file);
        } catch (FileNotFoundException e) {
            System.out.println("tätä virhettä ei pitäisi näkyä.");
        }
        while (readFile.hasNextLine()) {
            int correctCount = 0;
            int compareCount = 0;
            char dash = '_';
            String line = readFile.nextLine();
            try{
                for(int i=0;i<line.length();i++){
                    if(letters.charAt(i) != dash){
                        correctCount += 1;
                    }
                    if(letters.charAt(i) == line.charAt(i)){
                        compareCount += 1;
                    }    
                }
                if(correctCount == compareCount){
                    printFile.println(line);
                }
            }catch(IndexOutOfBoundsException e){
                //nothing
            }
        }
        readFile.close();
        printFile.close();
        WordList filteredWords = new WordList(file);
        return filteredWords;
    }
}