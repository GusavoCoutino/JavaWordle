import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;




public class App extends JFrame implements KeyListener{
    private JLabel[][] boxes = new JLabel[6][5];
    private final static int totalGuesses = 0;
    private static int guesses = totalGuesses;
    private static int currentLetter = 0;
    private static String word = "";
    private GameManager game = null;
    private wordList WordList = new wordList();
    private PopupWindow window = null;
    private String message = "";
    private Color yellow = new Color(200,182,83);
    private Color green = new Color(108,169,101);
    private Color gray = new Color(120,124,127);
    //Main Page
    public App(){

        final int width = 550;
        final int height = 800;

        //Letter Box Panels
        JPanel letterBox = buildLetterBoxes();
        letterBox.setBackground(Color.BLACK);

        //Title Container
        Container contentpane = getContentPane();

        //Title Label
        JLabel titleLabel = new JLabel("WORDLE");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 90));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setVerticalAlignment(JLabel.TOP);
        titleLabel.setBorder(new EmptyBorder(50, 0, 0, 0));

        //Title Panel
        JPanel titlePanel = new JPanel();
        titlePanel.add(titleLabel);
        contentpane.add(titlePanel, BorderLayout.PAGE_START);

        //LetterBox Panel
        JPanel letterJPanel = buildLetterBoxes();
        letterJPanel.setBorder(new EmptyBorder(50, 70, 50, 70));
        contentpane.add(letterJPanel, BorderLayout.CENTER);

        //JFrame declaration
        this.setSize(width, height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.addKeyListener(this);
        this.setResizable(false);
        this.setVisible(true);

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Gets key pressed by user, adds the key to the letter box in case it is an alphabetic character
     * Creates word based on keys input, evaluates the guess when enter is pressed
     * @param e the event to be processed, the key press
     */
    @Override
    public void keyPressed(KeyEvent e) {
        //If the key press is an alphabetic character, it will be added to the letter box labels
        if(e.getKeyChar()>=97 && e.getKeyChar() <= 122 || e.getKeyChar() >= 65 && e.getKeyChar() <= 90){
            if(currentLetter < 5 && guesses < 6 ){
                String letter = e.getKeyChar()+"";
                word += letter.toUpperCase();
                boxes[guesses][currentLetter].setText(e.getKeyChar()+"");
                currentLetter++;
            }
        }
        //If the key press is the enter key, the built word is evaluated to see if it is the same as the answer created
        else if(e.getKeyCode() == KeyEvent.VK_ENTER){
            if(currentLetter == 5){
                game = new GameManager();
                if(WordList.wordsExists(word)){
                    int answer = game.checkGuess(word, WordList);
                    if(answer == 1){
                        for(int i = 0; i<5; i++){
                            boxes[guesses][i].setBackground(green);
                        }
                        message = "<HTML>Congratulations! You guessed the word " + WordList.getAnswer() + " in " + (int)(guesses+1) + " attempts.<HTML>";
                        window = new PopupWindow(message);
                    }
                    else{
                        String[] arr = game.letterPosition(word, WordList);
                        for(int i = 0; i<5; i++){
                            if(arr[i].equals("Green")){
                                boxes[guesses][i].setBackground(green);
                            }
                            else if(arr[i].equals("Yellow")){
                                boxes[guesses][i].setBackground(yellow);
                            }
                            else{
                                boxes[guesses][i].setBackground(gray);
                            }
                        }
                        guesses++;
                        currentLetter = 0;
                        word = "";
                        if(guesses == 6){
                            message = "<HTML>The correct answer was " +  WordList.getAnswer()  + "<HTML>";
                            window = new PopupWindow(message);
                        }
                    }
                }
                else{
                    message = "<HTML>Not a real word<HTML>";
                    window = new PopupWindow(message);
                }
            }

        }
        //If the backspace key is pressed, a letter is deleted from the built words and from the current letter box label
        else if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE){
            if(currentLetter>0){
                boxes[guesses][currentLetter-1].setText("");
                currentLetter--;
                word = word.substring(0,word.length()-1);
            }
        }
    }



    @Override
    public void keyReleased(KeyEvent e) {

    }

    /**
     * Creates the panel contaning the boxes where the letters will be displayed
     * @return the JPanel containing the letter box labels
     */
    public JPanel buildLetterBoxes(){
        JPanel letterJPanel = new JPanel(new GridLayout(6,5,4,4));
        for (int i = 0; i < boxes.length; i++){ //initialize the array
            for (int j = 0; j < boxes[i].length; j++){
                boxes[i][j] = singleLetterBox();
                letterJPanel.add(boxes[i][j]);
            }

        }
        return letterJPanel;
    }

    /**
     * Creates and designs the letter box that will contain a single letter
     * @return the JLabel
     */
    public JLabel singleLetterBox(){
        JLabel letterBox = new JLabel("",JLabel.CENTER);
        Border border = BorderFactory.createLineBorder(Color.GRAY, 5);
        letterBox.setText("");
        letterBox.setBorder(border);
        letterBox.setSize(500, 300);
        letterBox.setForeground(Color.BLACK);
        letterBox.setFont(new Font("Arial",Font.BOLD, 60));
        letterBox.setOpaque(true);
        return letterBox;
    }

    public static void main(String[] args) {
        new App();
    }



}