///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            (PortalPair.java)
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

/**
 * The PortalPair class represents a pair of portals.
 * 
 * The Game class instantiates this class once for each pair of portals present
 * when a new level is loaded.
 */
public class PortalPair {

	// Create private field to hold the GraphicObject associated with the blue portal
	// Create private field to hold the GraphicObject associated with the orange portal

	private GraphicObject portal1;
	private GraphicObject portal2;

	/**
	 * @param name		name displayed on each end of the portal pair
	 * @param blueX		the x position of the blue portal
	 * @param blueY		the y position of the blue portal
	 * @param orangeX	the x position of the orange portal
	 * @param orangeY	the y position of the orange portal
	 */
	public PortalPair(String name, float blueX, float blueY, 
			float orangeX, float orangeY) {

		// Initialize the GraphicObjects associated with the blue and orange
		// portals, setting the type to "BLUE_PORTAL_name" or
		// "ORANGE_PORTAL_name", respectively, and setting their x and y
		// coordinates appropriately
		portal1 = new GraphicObject("BLUE_PORTAL_" + name, blueX, blueY);
		portal2 = new GraphicObject("ORANGE_PORTAL_" + name, orangeX, orangeY);
	}

	/**
	 * Checks if either end of this portal pair is colliding with the specified
	 * snake.
	 * 
	 * If either end of this portal pair is colliding with the snake, moves the
	 * snake past the other end of the portal.
	 * 
	 * @param snake		the snake to check for collisions with
	 */
	public void teleportSnakeIfColliding(Snake snake) {

		// If the blue portal is colliding with the snake's head's GraphicObject,
		// move the snake's head's GraphicObject past the orange portal
		if (portal1.isCollidingWith(snake.getGraphicObject())) {
			snake.getGraphicObject().movePast(portal2);
		}

		// If the orange portal is colliding with the snake's head's 
		// GraphicObject, move the snake's head's GraphicObject past the 
		// blue portal
		if (portal2.isCollidingWith(snake.getGraphicObject())) {
			snake.getGraphicObject().movePast(portal1);
		}
	}
}