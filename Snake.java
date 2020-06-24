///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            (Snake.java)
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

import java.util.ArrayList;

/**
 * The Snake class represents the player-controlled snake. 
 *
 * The Game class instantiates this class exactly once, when a new level is
 * loaded.
 */
public class Snake {

	// Private variables to hold the GraphicObject associated with the snake's head
	// and an ArrayList of GraphicObject associated with the snake's body

	private GraphicObject head;
	private ArrayList<GraphicObject> body;

	/**
	 * Initializes a new Snake object at the specified (x,y) position.
	 * 
	 * @param x		the initial x position of the snake
	 * @param y		the initial y position of the snake
	 */
	public Snake(float x, float y) {

		// Initialize new ArrayList to hold body segments
		// Initialize the head
		head = new GraphicObject("HEAD", x, y);
		body = new ArrayList<>();

		// Set the speed of the head
		this.head.setSpeed(2);
		
		// Set the direction of the head
		this.head.setDirection(90);

		// Add the head to the list of body segments
		// Add four body segments (grow the snake four times)
		this.body.add(head);

		for (int i = 0; i < 4; i++) {
			grow();
		}
	}

	/**
	 * Returns the GraphicObject associated with the head of this snake.
	 * 
	 * @return the GraphicObject associated with the head of this snake
	 */
	public GraphicObject getGraphicObject() {
		return head;
	}

	/**
	 * Grows the snake by one body segment.
	 */
	public void grow() {

		// Create a new GraphicObject with type "BODY"		
		// Find the last body segment in the list of body segments
		// Set the leader of the new body segment to be the last body segment
		// Add the new body segment to the end of the list of body segments
		GraphicObject newSegment = new GraphicObject("BODY",0,0);
		int position = body.size()-1;	//
		GraphicObject last = body.get(position); //
		newSegment.setLeader(last);
		this.body.add(newSegment);
	}

	/**
	 * Reads keyboard input and changes the snake's direction as necessary.
	 * 
	 * @param controlType - 1: classic, 2: analog, 3: slither
	 */
	public void updateMoveDirection(int controlType) {

		// if controlType is one
		if (controlType == 1) {

			// implementation for controlType one
			if (Engine.isKeyPressed("RIGHT")) {
				this.head.setDirection(this.head.getDirection() - 90);
			}
			else if (Engine.isKeyPressed("LEFT")) {
				this.head.setDirection(this.head.getDirection() + 90);
			}

		}

		// if controlType is two
		else if (controlType == 2) {

			// implementation for controlType two
			if (Engine.isKeyHeld("RIGHT")) {
				this.head.setDirection(this.head.getDirection() - 6);
			}
			else if (Engine.isKeyHeld("LEFT")) {
				this.head.setDirection(this.head.getDirection() + 6);
			}
		}

		// if controlType is three
		else if (controlType == 3) {
			
			//implementation for controlType three
			if (Engine.isKeyHeld("SPACE")) {
				this.head.setDirection(this.head.getDirection() + 6);
			}
			else {
				this.head.setDirection(this.head.getDirection() - 6);
			}
		}
	}

	/**
	 * Kills the snake if the head is colliding with any of the body segments.
	 */
	public void dieIfCollidingWithOwnBody() {

		// For each game object in the body...
		for (int i = 0; i < body.size(); i++) {
			if (head.isCollidingWith(body.get(i))) {
				this.die();
			}
		}	
	}

	/**
	 * Kills the snake.
	 */
	public void die() {

		// Set the head's type to "DEAD"
		this.head.setType("DEAD");

		// For each GraphicObject in the snake's body, set its type to "DEAD"
		for (int i = 0; i < body.size(); i++) {
			this.body.get(i).setType("DEAD");
		}
	}

	/**
	 * Returns true if the snake is dead.
	 * 
	 * @return true if the snake is dead, false otherwise
	 */
	public boolean isDead() {

		if (this.head.getType().equalsIgnoreCase("DEAD")) {
			return true;
		}
		return false;
	}
}
