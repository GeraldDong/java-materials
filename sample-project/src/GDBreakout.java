/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class GDBreakout extends GraphicsProgram {

	// Dimensions of the canvas, in pixels
	// These should be used when setting up the initial size of the game,
	// but in later calculations you should use getWidth() and getHeight()
	// rather than these constants for accurate size information.
	public static final double CANVAS_WIDTH = 420;
	public static final double CANVAS_HEIGHT = 600;

	// Number of bricks in each row
	public static final int NBRICK_COLUMNS = 10;

	// Number of rows of bricks
	public static final int NBRICK_ROWS = 10;

	// Separation between neighboring bricks, in pixels
	public static final double BRICK_SEP = 4;

	// Width of each brick, in pixels
	public static final double BRICK_WIDTH = Math.floor(
			(CANVAS_WIDTH - (NBRICK_COLUMNS + 1.0) * BRICK_SEP) / NBRICK_COLUMNS);

	// Height of each brick, in pixels
	public static final double BRICK_HEIGHT = 8;

	// Offset of the top brick row from the top, in pixels
	public static final double BRICK_Y_OFFSET = 70;

	// Dimensions of the paddle
	public static final double PADDLE_WIDTH = 100;
	public static final double PADDLE_HEIGHT = 10;

	// Offset of the paddle up from the bottom 
	public static final double PADDLE_Y_OFFSET = 30;

	// Radius of the ball in pixels
	public static final double BALL_RADIUS = 10;

	// The ball's vertical velocity.
	public static final double VELOCITY_Y = 3.0;

	// The ball's minimum and maximum horizontal velocity; the bounds of the
	// initial random velocity that you should choose (randomly +/-).
	public static final double VELOCITY_X_MIN = 1.0;
	public static final double VELOCITY_X_MAX = 3.0;

	// Animation delay or pause time between ball moves (ms)
	public static final double DELAY = 1000.0 / 60.0;
//    public static final double DELAY = 5.0;

	// Number of turns 
	public static final int NTURNS = 3;

	public int brickNumber = 100;

	private double vx = 0;
	private double vy = 3.0;

	private RandomGenerator rgen = RandomGenerator.getInstance();

	public void run() {
		// Set the window's title bar text
		setTitle("CS 106A Breakout");

		// Set the canvas size.  In your code, remember to ALWAYS use getWidth()
		// and getHeight() to get the screen dimensions, not these constants!
		setCanvasSize(CANVAS_WIDTH, CANVAS_HEIGHT);

		/* You fill this in, along with any subsidiary methods */

		for(int i=0; i<NTURNS; i++){
			setUpGame();
			playGame();

			if (brickNumber == 0){
				printGameResult("Win");
				break;
			}

			if (brickNumber > 0){
			    removeAll();
			    brickNumber = NBRICK_COLUMNS * NBRICK_ROWS;
            }
		}
		if (brickNumber > 0){
            printGameResult("lose");
        }

	}

    private void printGameResult(String result){
		GLabel label = new GLabel(result, getWidth()/2, getHeight()/2);
		label.move(-label.getWidth()/2, -label.getHeight()/2);
		label.setColor(Color.RED);
		add(label);
	}

    private void setUpGame() {
		//build bricks
		buildBricks();
		//build paddle
		buildPaddle();
		//build ball
		buildBall();

	}

	private void buildBricks() {
		GRect brick;
		for (int i=0; i< NBRICK_ROWS; i++){
			for (int j=0; j<NBRICK_COLUMNS; j++){
				double startX = BRICK_SEP + (BRICK_WIDTH + BRICK_SEP) * j;
				double startY = BRICK_Y_OFFSET + (BRICK_HEIGHT+BRICK_SEP) * i;
				brick = new GRect(startX,startY,BRICK_WIDTH,BRICK_HEIGHT);
				brick.setFilled(true);
				add(brick);

				switch (i/2){
					case 0:
						brick.setColor(Color.RED);
						break;
					case 1:
						brick.setColor(Color.ORANGE);
						break;
					case 2:
						brick.setColor(Color.YELLOW);
						break;
					case 3:
						brick.setColor(Color.GREEN);
						break;
					case 4:
						brick.setColor(Color.CYAN);
						break;
				}
			}
		}
	}

	private GRect paddle;
	private void buildPaddle(){
		double startX = (CANVAS_WIDTH - PADDLE_WIDTH)/2;
		double startY = CANVAS_HEIGHT - PADDLE_Y_OFFSET - PADDLE_HEIGHT;
		paddle = new GRect(startX,startY,PADDLE_WIDTH,PADDLE_HEIGHT);
		paddle.setFilled(true);
		paddle.setColor(Color.BLACK);
		add(paddle);
		addMouseListeners();
	}

    @Override
    public void mouseMoved(MouseEvent e) {

        double coordinateY = paddle.getY();

        int x = e.getX();
        if (x<paddle.getWidth()/2){
            paddle.setLocation(0, coordinateY);
        }else if (x>getWidth()-paddle.getWidth()/2){
            paddle.setLocation(getWidth()-paddle.getWidth(),coordinateY);
        }else {
            paddle.setLocation(x-paddle.getWidth()/2,coordinateY);
        }

    }

    private GOval ball;
    private void buildBall(){

	    double startX = CANVAS_WIDTH/2 - BALL_RADIUS;
	    double startY = CANVAS_HEIGHT/2 - BALL_RADIUS;

	    ball = new GOval(startX, startY, 2 * BALL_RADIUS, 2 * BALL_RADIUS);
        ball.setFilled(true);
        ball.setColor(Color.BLACK);
        add(ball);
    }

    private void playGame(){
        waitForClick();
        initBallVelocity();
        while (true){
            moveBall();

            if (ball.getY()>=getHeight()){
                break;
            }

            if (brickNumber == 0){
                break;
            }

        }
    }

    private void moveBall(){
        ball.move(vx,vy);

        double coordinateX = ball.getX();
        double coordinateY = ball.getY();

        //hit left wall
        if (coordinateX <= 0){
            vx = -vx;
        }

        //hit right wall
        if (coordinateX >= getWidth()){
            vx = -vx;
        }

        //hit top wall
        if (coordinateY <= 0){
            vy = -vy;
        }

        //hit bottom wall
        if (coordinateY >= getHeight()){
            vy = -vy;
        }

        //get Colliding object
        GObject collidingObject = getCollidingObject();
        if (collidingObject != null){
            vy = -vy;
            if (collidingObject != paddle){
                //hit a brick
                remove(collidingObject);
                brickNumber--;
            }
        }
        pause(DELAY);
    }

    private GObject getCollidingObject(){
        double x = ball.getX();
        double y = ball.getY();
        GObject gObject = null;

        for (int i=0; i<2; i++){
            for (int j=0; j<2; j++){
                gObject = getElementAt(x+i*2*BALL_RADIUS, y+j*2*BALL_RADIUS);
                if (gObject != null){
                    return gObject;
                }
            }
        }

        return gObject;
    }

    private void initBallVelocity(){
        vy = VELOCITY_Y;
        vx = rgen.nextDouble(VELOCITY_X_MIN, VELOCITY_X_MAX);
        if (rgen.nextBoolean(0.5)) vx = -vx;
    }

}
