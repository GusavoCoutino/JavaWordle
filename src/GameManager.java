/**
 * Returns values that indicate the background of the letter box JLabels
 */
public class GameManager {
    /**
     * Checks if the sent word is the same as the created answer
     * @param guess the word that will be compared to the answer
     * @param WordList the object to access the answer
     * @return an int value indicating whether the word is equal to the answer
     */
    public int checkGuess(String guess, wordList WordList){
        //Because the word list is not curated, the answer is printed out to win the game in case the created answers
        //are unusual
        System.out.println(WordList.getAnswer());
        if(guess.equals(WordList.getAnswer())){
            //The word has been guessed
            return 1;
        }
        else{
            //The word is not equal to the answer
            return 2;
        }
    }

    /**
     * Checks which letters exist in the answer word and places the corresponding color in an array
     * @param guess the word that will be compared to see which letters exist in the answer
     * @param WordList the object to access the answer
     * @return the String array containing the respective color of the background of each letter
     */
    public String[] letterPosition(String guess, wordList WordList){
        String[] arr = new String[5];
        for(int i = 0; i<5; i++){
            String letter = guess.charAt(i)+"";
            if(guess.charAt(i)==(WordList.getAnswer().charAt(i))){
                arr[i] = "Green";
            }
            else if(WordList.getAnswer().contains(letter)){
                arr[i] = "Yellow";
            }
            else{
                arr[i] = "None";
            }
        }
        return arr;
    }
}
