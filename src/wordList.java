import java.io.BufferedReader;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Creates the word list from a txt file and the methods to create and get the answer
 * Also creates the method to check if the guess made by the user is a real word
 */
public class wordList {
    private BufferedReader read = null;
    public ArrayList<String> list = null;
    private static String answer = "BASIC";

    /**
     * Creates a String ArrayList from validWords.txt, if the file is not found the exception is caught
     * and the stack trace is printed
     */

    public wordList(){
        list = new ArrayList<String>();
        try {
            read = new BufferedReader(new FileReader("/Users/gustavocoutino/IdeaProjects/JavaWordle/resources/validWords.txt"));
            String line = read.readLine();
            while(line != null){
                list.add(line.toUpperCase());
                line = read.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File was not found");
            e.printStackTrace();

        } catch(IOException e){
            e.printStackTrace();
        } finally {
            try {
                if(read != null){
                    read.close();
                }
            } catch (Exception e) {
                System.out.println("Exception when closing Buffered Reader");
                e.printStackTrace();
            }
        }
        this.createAnswer();
    }

    /**
     * Checks if the guess made by the user is a real word
     * @param word the guess made by the user
     * @return a boolean
     */
    public boolean wordsExists(String word){
        return list.contains(word);
    }

    /**
     * Creates the answer that will be guessed in the game
     */
    public void createAnswer(){
        Random random = new Random();
        wordList.answer = list.get(random.nextInt(list.size()));
    }

    /**
     * Returns the word that will be guessed
     * @return the answer to be guessed
     */
    public String getAnswer(){
        return wordList.answer;
    }

}