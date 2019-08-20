/*
 * File: ParaKarel.java
 * ------------------
 * This program will eventually play the ParaKarel game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ParaKarel extends ConsoleProgram {

	/***********************************************************
	 *              CONSTANTS                                  *
	 ***********************************************************/
	
	/* The number of guesses in one game of ParaKarel */
	private static final int N_GUESSES = 7;
	/* The width and the height to make the karel image */
	private static final int KAREL_SIZE = 150;
	/* The y-location to display karel */
	private static final int KAREL_Y = 230;
	/* The width and the height to make the parachute image */
	private static final int PARACHUTE_WIDTH = 300;
	private static final int PARACHUTE_HEIGHT = 130;
	/* The y-location to display the parachute */
	private static final int PARACHUTE_Y = 50;
	/* The y-location to display the partially guessed string */
	private static final int PARTIALLY_GUESSED_Y = 430;
	/* The y-location to display the incorrectly guessed letters */
	private static final int INCORRECT_GUESSES_Y = 460;
	/* The fonts of both labels */
	private static final String PARTIALLY_GUESSED_FONT = "Courier-36";
	private static final String INCORRECT_GUESSES_FONT = "Courier-26";
	
	/***********************************************************
	 *              Instance Variables                         *
	 ***********************************************************/
	
	/* An object that can produce pseudo random numbers */
	private RandomGenerator rg = new RandomGenerator();
	
	private GCanvas canvas = new GCanvas();
	
	/***********************************************************
	 *                    Methods                              *
	 ***********************************************************/
	private int missedCount = 0;
	private String secretWord = "";
	private String partiallyGuessedString = "";
	private String incorrectGuessesString = "";
	private int len;

	public void init(){
	    add(canvas);
	    initGameGraphicalInterface();
    }

    double cordGap;
	GImage karel;
	GLabel partiallyGuessedLabel;
	GLabel incorrectGuessesLabel;

    private void initGameGraphicalInterface() {
        //draw background
        GImage bg = new GImage("background.jpg");
        bg.setSize(canvas.getWidth(),canvas.getHeight());
        canvas.add(bg,0,0);

        //draw parachute
        GImage parachute = new GImage("parachute.png");
        parachute.setSize(PARACHUTE_WIDTH,PARACHUTE_HEIGHT);
        parachute.setLocation((canvas.getWidth()-PARACHUTE_WIDTH)/2,PARACHUTE_Y);
        canvas.add(parachute);

        //draw Karel
        karel = new GImage("karel.png");
        karel.setSize(KAREL_SIZE, KAREL_SIZE);
        karel.setLocation((canvas.getWidth()-KAREL_SIZE)/2, KAREL_Y);
        canvas.add(karel);

        //draw cords
        cordGap = (double) PARACHUTE_WIDTH/(N_GUESSES-1);
        for (int i=0; i< N_GUESSES; i++){
            GLine cord = new GLine((canvas.getWidth()-PARACHUTE_WIDTH)/2+i*cordGap,PARACHUTE_Y+PARACHUTE_HEIGHT,canvas.getWidth()/2,KAREL_Y);
            canvas.add(cord);
        }

        partiallyGuessedLabel = new GLabel(partiallyGuessedString);
        partiallyGuessedLabel.setFont(PARTIALLY_GUESSED_FONT);
        partiallyGuessedLabel.setLocation((canvas.getWidth()-partiallyGuessedLabel.getWidth())/2, PARTIALLY_GUESSED_Y);
        canvas.add(partiallyGuessedLabel);

        incorrectGuessesLabel = new GLabel(incorrectGuessesString);
        incorrectGuessesLabel.setFont(INCORRECT_GUESSES_FONT);
        incorrectGuessesLabel.setLocation((canvas.getWidth()-incorrectGuessesLabel.getWidth())/2, INCORRECT_GUESSES_Y);
        canvas.add(incorrectGuessesLabel);
    }

    public void run() {
		println("Welcome to ParaKarel");
		// Let's Code It!
		do {
			initGame();
			println("Your word now looks like this: "+ partiallyGuessedString);
			println("you have "+ N_GUESSES + " guesses left.");
			guessWord();
		}while (readBoolean("restart the game? ","y","n"));
        println("game over");
        pause(2000);
        exit();
	}

    private void initGame() {
	    missedCount = 0;
        partiallyGuessedString = "";
        incorrectGuessesString = "";
        secretWord = getRandomWord();
        len = secretWord.length();
        for (int i=0; i<len; i++){
            partiallyGuessedString += "-";
        }
        canvas.removeAll();
        initGameGraphicalInterface();
    }

    private void guessWord() {
		while (true){
			String input;
			do {
			    input = readLine("Your guess: ");
            }while (!isInputValid(input));

			char guessChar = input.charAt(0);
			boolean isGuessed = false;
			for (int i=0; i< len; i++){
				char c = secretWord.charAt(i);
				if (compareCharsIgnoreCase(c,guessChar)){
					updateGuessString(c,i);
					isGuessed = true;
				}
			}
			if (isGuessed){
				println("Your guess is correct.");
			}else {
				// no this letter in the secret word
				println("There is no " + Character.toUpperCase(guessChar) + "\'s in the word.");
				missedCount++;
				removeCord(missedCount);
			}
			incorrectGuessesString += guessChar;
			println("Your word now looks like this: "+ partiallyGuessedString);
			println("You hava "+(N_GUESSES-missedCount)+" guesses left.");

			updatePartialGuessedAndIncorrectGuessesLabel();

			if (missedCount >= N_GUESSES){
				// failed, game over, you lose.
				println("You are completely hung.");
				println("The word was: "+secretWord);
				dropKarel();
				break;
			}

			if (partiallyGuessedString.equalsIgnoreCase(secretWord)){
				// succeeded, game over, you Win.
				println("You win!");
				println("The word was: "+ partiallyGuessedString.toUpperCase());
				break;
			}
		}
	}

    private void updatePartialGuessedAndIncorrectGuessesLabel() {
        canvas.remove(partiallyGuessedLabel);
        canvas.remove(incorrectGuessesLabel);

        partiallyGuessedLabel = new GLabel(partiallyGuessedString);
        partiallyGuessedLabel.setFont(PARTIALLY_GUESSED_FONT);
        partiallyGuessedLabel.setLocation((canvas.getWidth()-partiallyGuessedLabel.getWidth())/2, PARTIALLY_GUESSED_Y);
        canvas.add(partiallyGuessedLabel);

        incorrectGuessesLabel = new GLabel(incorrectGuessesString);
        incorrectGuessesLabel.setFont(INCORRECT_GUESSES_FONT);
        incorrectGuessesLabel.setLocation((canvas.getWidth()-incorrectGuessesLabel.getWidth())/2, INCORRECT_GUESSES_Y);
        canvas.add(incorrectGuessesLabel);
    }

    private void dropKarel() {
        canvas.remove(karel);
        karel = new GImage("karelFlipped.png");
        karel.setSize(KAREL_SIZE, KAREL_SIZE);
        karel.setLocation((canvas.getWidth()-KAREL_SIZE)/2, KAREL_Y);
        canvas.add(karel);
    }

    private void removeCord(int missedCount) {
        double gaps = 0;
        switch (missedCount){
            case 1:
                gaps = 0;
                break;
            case 2:
                gaps = 6 * cordGap;
                break;
            case 3:
                gaps = 1 * cordGap;
                break;
            case 4:
                gaps = 5 * cordGap;
                break;
            case 5:
                gaps = 2 * cordGap;
                break;
            case 6:
                gaps = 4 * cordGap;
                break;
            case 7:
                gaps = 3 * cordGap;
                break;
        }

	    double x = (canvas.getWidth()-PARACHUTE_WIDTH)/2+ gaps;
	    double y = PARACHUTE_Y + PARACHUTE_HEIGHT;
	    GObject gObject = canvas.getElementAt(x,y);
	    if (gObject != null){
	        canvas.remove(gObject);
        }
    }

    private boolean isInputValid(String input) {
	    return input != null && input.length() == 1 && Character.isLetter(input.charAt(0));
    }

    private void updateGuessString(char c, int i) {
		partiallyGuessedString = partiallyGuessedString.substring(0,i) + c + partiallyGuessedString.substring(i+1);
	}

	private boolean compareCharsIgnoreCase(char c1, char c2){
		String str1 = ""+c1;
		String str2 = ""+c2;
		return str1.equalsIgnoreCase(str2);
	}

	/**
	 * Method: Get Random Word
	 * -------------------------
	 * This method returns a word to use in the ParaKarel game. It randomly 
	 * selects from among 10 choices.
	 */
	private String getRandomWord() {
		int index = rg.nextInt(10);
		if(index == 0) return "BUOY";
		if(index == 1) return "COMPUTER";
		if(index == 2) return "CONNOISSEUR";
		if(index == 3) return "DEHYDRATE";
		if(index == 4) return "FUZZY";
		if(index == 5) return "HUBBUB";
		if(index == 6) return "KEYHOLE";
		if(index == 7) return "QUAGMIRE";
		if(index == 8) return "SLITHER";
		if(index == 9) return "ZIRCON";
		throw new ErrorException("getWord: Illegal index");
//		return getRandomWordFromFile("ShorterLexicon.txt");
	}

	private String getRandomWordFromFile(String  name) {
		ArrayList<String> list = new ArrayList<String>();
		try {
			Scanner input = new Scanner(new File(name));
			while (input.hasNextLine()){
				String line = input.nextLine();
				list.add(line);
			}
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		int size = list.size();

		if (size>0){
			int index = rg.nextInt(size);
			return list.get(index);
		} else {
			throw new ErrorException("there is no word in the file");
		}
	}
}
