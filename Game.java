///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            (Game.java)
// Files:            (p3.jar)
// Semester:         (Introduction to Computer Programming) Fall 2015
//
// Author:           (Nhialee Yang)
// Email:            (nyang5@wisc.edu)
// CS Login:         (nhialee)
// Lecturer's Name:  (Deb Deppeler)
// Lab Section:      (302)
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
//
//                   CHECK ASSIGNMENT PAGE TO see IF PAIR-PROGRAMMING IS ALLOWED
//                   If pair programming is allowed:
//                   1. Read PAIR-PROGRAMMING policy (in cs302 policy) 
//                   2. choose a partner wisely
//                   3. REGISTER THE TEAM BEFORE YOU WORK TOGETHER 
//                      a. one partner creates the team
//                      b. the other partner must join the team
//                   4. complete this section for each program file.
//
// Pair Partner:     (name of your pair programming partner)
// Email:            (email address of your programming partner)
// CS Login:         (partner's login name)
// Lecturer's Name:  (name of your partner's lecturer)
// Lab Section:      (your partner's lab section number)
//
//////////////////// STUDENTS WHO GET HELP FROM OTHER THAN THEIR PARTNER //////
//                   must fully acknowledge and credit those sources of help.
//                   Instructors and TAs do not have to be credited here,
//                   but tutors, roommates, relatives, strangers, etc do.
//
// Persons:          Identify persons by name, relationship to you, and email.
//                   Describe in detail the the ideas and help they provided.
//
// Online sources:   avoid web searches to solve your problems, but if you do
//                   search, be sure to include Web URLs and description of 
//                   of any information you find.
//////////////////////////// 80 columns wide //////////////////////////////////

import java.util.*;

/**
 * The Game class represents a running instance of the PortalSnake game. It
 * keeps track of the Snake object, lists of Apple, Rock, and PortalPair
 * objects, the current score, and whether the player has won.
 * 
 * The game engine (which we've written for you) will create a new instance of
 * this class when the player chooses a level to play. 
 * 
 * At each iteration of the game loop, the game engine calls the update() method
 * in the Game class. The update() method tells each of the objects in the game
 * to update itself based on the rules of the game. It then checks if the game
 * is over or not.
 */
public class Game {	

	// Store the instances of the game objects that you create for your game
	// in these member variables: 

	private Snake snake;
	private ArrayList<Apple> apples;		
	private ArrayList<Rock> rocks;			
	private ArrayList<PortalPair> portals;
	private int snakeControlType;
	private int countScore;

	// Create member variables to track the controlType, score (ie number
	// of apples eaten by the snake), and whether the game has been won
	// or lost here.

	/**
	 * @param level - "RANDOM" or descriptions of the object to load
	 * @param controlType - 1: classic, 2: analog, 3: slither
	 */
	public Game(String level, int controlType) {
		
		this.snakeControlType = controlType;

		// Initialize all member variables
		this.apples = new ArrayList<Apple>();
		this.rocks = new ArrayList<Rock>();
		this.portals = new ArrayList<PortalPair>();

		// Create a random level when level contains: RANDOM
		if(level.equalsIgnoreCase("random")) {
			createRandomLevel();
		}

		// Otherwise load the objects described in the string level
		else {
			loadLevel(level);
		}		
	}

	/**
	 * create a new level with randomly positioned:
	 * 
	 * Snake(1), Rocks(20), Apples(8), and PortalPairs(3)
	 */
	public void createRandomLevel() {

		// create a snake: positioned as specified in the write-up
		snake = new Snake((float)(Engine.getWidth()/2), + 
				(float)(Engine.getHeight()/2));

		// create 20 randomly positioned rocks
		for (int i = 0; i < 20; i++) {
			float x = (float)(Engine.getWidth() * Math.random());
			float y = (float)(Engine.getHeight() * Math.random());
			rocks.add(new Rock(x,y));
		}

		// create 8 randomly positioned apples
		for (int i = 0; i < 8; i++) {
			float x = (float)(Engine.getWidth() * Math.random());
			float y = (float)(Engine.getHeight() * Math.random());
			apples.add(new Apple(x,y));
		}

		// create 3 randomly positioned portal pairs
		for (int i = 0; i < 3; i++) {
			float x1 = (float)(Engine.getWidth()*Math.random());
			float y1 = (float)(Engine.getHeight()*Math.random());
			float x2 = (float)(Engine.getWidth()*Math.random());
			float y2 = (float)(Engine.getHeight()*Math.random());
			this.portals.add(new PortalPair(i + "",x1,y1,x2,y2));
		}
	}

	/**
	 * Loads a level from a String description.
	 * 
	 * Initializes all of the class private fields which hold the Snake object
	 * and the lists of Apple, Rock, and PortalPair objects from the provided
	 * String which contains  
	 * 
	 * @param level - a string containing the names and locations of objects
	 */
	public void loadLevel(String level) {

		// Initialize Rock, Apple, and PortalPair ArrayLists
		this.apples = new ArrayList<Apple>();
		this.rocks = new ArrayList<Rock>();
		this.portals = new ArrayList<PortalPair>();
		String line;

		// Create a new scanner to read the level description	
		Scanner scnr = new Scanner(level);
	
		// Loop through lines in the level description
		while (scnr.hasNextLine()) {

			// Get the next line
			line = scnr.nextLine();

			// Split the line into tokens
			String[] token = line.split(",");

			// Determine the type of object to add to the level
			for (int i = 0; i < token.length; i++) {

				// If it's a snake, create a new snake at the x and y
				// coordinates specified by the second and third tokens
				token[i] = token[i].trim();

				if (token[i].equalsIgnoreCase("Snake")) {
					this.snake = new Snake(new Integer(token[i+1].trim()), + 
							new Integer(token[i + 2].trim()));
				}

				// If it's an apple, create a new apple at the x and y
				// coordinates specified by the second and third tokens, and add
				// it to the list of apples
				else if (token[i].equalsIgnoreCase("Apple")) {
					this.apples.add(new Apple(new Integer(token[i+1].trim()), + 
							new Integer(token[i+2].trim())));
				}

				// If it's a rock, create a new rock at the x and y coordinates
				// specified by the second and third tokens and add it to the
				// list of rocks
				else if (token[i].equalsIgnoreCase("Rock")) {
					this.rocks.add(new Rock(new Integer(token[i+1].trim()), + 
							new Integer(token[i+2].trim())));
				}
				
				// If it's a portal pair, create a new PortalPair with the
				// name equal to the second token, with the first portal at the
				// x and y coordinates specified by the third and fourth
				// tokens, and the second portal at the x and y coordinates
				// specified by the fifth and sixth tokens	
				else if (token[i].equalsIgnoreCase("PortalPair")) {
					this.portals.add(new PortalPair(token[i+1].trim(), +
							new Integer(token[i+2].trim()), +
							new Integer(token[i+3].trim()), + 
							new Integer(token[i+4].trim()), + 
							new Integer(token[i+5].trim())));
				}
			}
		}

		// Close the scanner	
		scnr.close();
	}

	/**
	 * Updates the game objects.
	 * 
	 * Goes through each of the objects--the snake, rocks, apples, and portals--
	 * and tells them to behave according to the rules of the game. This method
	 * returns true if the game should continue, or false if the game is over.
	 * 
	 * @return - false if the game is over, otherwise true
	 */
	public boolean update() {
		
		// Tell the snake to update itself
		this.snake.updateMoveDirection(this.snakeControlType);

		// Tell the snake to die if it's colliding with itself
		snake.dieIfCollidingWithOwnBody();

		// For each rock, tell the rock to kill the snake if the two are
		// colliding
		for (int i = 0; i < rocks.size(); i++) {
			rocks.get(i).killSnakeIfColliding(snake);
		}

		// For each apple, tell the apple to be eaten by the snake if the two
		// are colliding, and if so update the score
		for (int i = 0; i < apples.size(); i++) {
			if (apples.get(i).getEatenBySnakeIfColliding(this.snake)) {
				countScore++;
			}
		}

		// For each portal pair, tell the pair to teleport the snake if the two
		// are colliding
		for (int i = 0; i < portals.size(); i++) {
			portals.get(i).teleportSnakeIfColliding(this.snake);
		}

		// If the score is equal to the number of apples, make sure playerHasWon()
		// will return true and then return false 
		if (playerHasWon()) {
			return false;
		}
		
		// Else, if the snake is dead, make sure playerHasWon() will return false
		// and then return false
		if (snake.isDead()) {
			return false;
		}
		return true;
	}

	/**
	 * Returns true if the player has won
	 * 
	 * @return true when the player has won, and false when they have lost or
	 * the game is not over
	 */

	public boolean playerHasWon() {
		
		return this.countScore == apples.size();
	}

	/**
	 * Returns the player's score.
	 * 
	 * @return the current score (number of apples eaten)
	 */
	public int getScore() {
		
		return countScore;
	}	

	/**
	 * There is nothing left to implement in this method, it simply calls
	 * Engine.startEngineAndGame(), which in turn starts the Engine and creates
	 * an instance of this Game class.  The engine will then repeatedly call
	 * the update() method on this Game until it returns false.
	 * 
	 * If you want to turn off the logging you can change the parameter being
	 * passed to startEngineAndGame to false.  
	 *  
	 * @param args - command line arguments
	 */
	public static void main(String[] args) {

		Application.startEngineAndGame(true);
	}
}
