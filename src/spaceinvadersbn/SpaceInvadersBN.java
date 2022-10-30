package spaceinvadersbn;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PFont;

public class SpaceInvadersBN extends PApplet {
	
	
	public static void main(String[] args) {
        PApplet.main("spaceinvadersbn.SpaceInvadersBN");
    }
	
	PImage img;
	PImage backgroundImg;
	PImage eimg;
	PImage eimg2;
	PImage eimg3;
	PImage eimg4;
	PImage eimg5;
	PImage eimg6;
	PImage eimg7;



	
	PFont scoreFont;

	public float speed = 10;
	public float x = 438;
	public float y = 600;
	public float bulletX = 512;
	public float bulletY = 610; 
	public float enemy1X = 30;
	public float enemy2X = 175;
	public float enemy3X = 320;
	public float enemy4X = 465;
	public float enemy5X = 610;
	public float enemy6X = 755;
	public float enemy7X = 900;
	
	public float enemy1Y = 50;
	public float enemy2Y = 50;
	public float enemy3Y = 50;
	public float enemy4Y = 50;
	public float enemy5Y = 50;
	public float enemy6Y = 50;
	public float enemy7Y = 50;
	float bulletSpeed = 10;
	ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	
	public int score = 0;
  
    public int enemySpeed = 0;
    
    enum GameState {
    	START, OVER, RUNNING
    }
    
    static GameState currentState;
    
	public boolean moveForward = false;
	public boolean moveBackward = false;
	public boolean moving = false;
	public boolean gameStart = false; // keeps track of whether game has started
	public boolean gameOver1 = false; // keeps track of whether gameOver condition has been reached
	public boolean gameOver2 = false; // keeps track of whether gameOver condition has been reached
	
	
	

    public void settings() {
        size (1024 , 768);
    }

    public void setup() {
    	img = loadImage("Spaceship.png");
    	backgroundImg = loadImage("Background.jpg");
    	eimg = loadImage("Enemy1.png");
    	eimg2 = loadImage("Enemy2.png");
    	eimg3 = loadImage("Enemy1.png");
    	eimg4 = loadImage("Enemy2.png");
    	eimg5 = loadImage("Enemy1.png");
    	eimg6 = loadImage("Enemy2.png");
    	eimg7 = loadImage("Enemy1.png");
    	currentState = GameState.START;
    	reset();
    }

    public void draw() {     
    	
        
    	image(backgroundImg, 0, 0);
    	move();
    	
    	
    	if(!gameStart) {
    		currentState = GameState.RUNNING;
    		fill(190, 190, 255);
            noStroke();
            rect(width / 2 - 125, height / 2 - 100, 250, 200); 
            fill(0, 0, 235); // 255
            textAlign(CENTER);
            text("Welcome!\nPress B to Begin.\n\nControls:\nAD to Move\nSpacebar to Shoot", width / 2, height / 2 - 75);
            textSize(20);            
    	}

        if(gameStart) {
        	drawScore();
        	image(img, x, y, 150, 90);
        	image(eimg , enemy1X, enemy1Y += enemySpeed, 75, 75);
        	image(eimg2, enemy2X, enemy2Y += enemySpeed, 75, 75);
        	image(eimg3, enemy3X, enemy3Y += enemySpeed, 75, 75);
        	image(eimg4, enemy4X, enemy4Y += enemySpeed, 75, 75);
        	image(eimg5, enemy5X, enemy5Y += enemySpeed, 75, 75);
        	image(eimg6, enemy6X, enemy6Y += enemySpeed, 75, 75);
        	image(eimg7, enemy7X, enemy7Y += enemySpeed, 75, 75);
        	
        	for (int b = 0; b < bullets.size(); b++) { 
                Bullet bull = bullets.get(b);
                bull.move();
                bull.drawBullet();  
                
	            if((bull.x + 3 >= enemy1X) && (bull.x + 3 <= enemy1X + 70) && (bull.y >= enemy1Y) && (bull.y <= enemy1Y + 75)) {
	        		enemy1Y = 10000;	
	        	}
	            if((bull.x + 3 >= enemy2X) && (bull.x + 3 <= enemy2X + 70) && (bull.y >= enemy2Y) && (bull.y <= enemy2Y + 75)) {
	        		enemy2Y = 10000;	
	        	}
	            if((bull.x + 3 >= enemy3X) && (bull.x + 3 <= enemy3X + 70) && (bull.y >= enemy3Y) && (bull.y <= enemy3Y + 75)) {
	        		enemy3Y = 10000;	
	        	}
	            if((bull.x+ 3  >= enemy4X) && (bull.x + 3 <= enemy4X + 70) && (bull.y >= enemy4Y) && (bull.y <= enemy4Y + 75)) {
	        		enemy4Y = 10000;	
	        	}
	            if((bull.x + 3 >= enemy5X) && (bull.x + 3 <= enemy5X + 70) && (bull.y >= enemy5Y) && (bull.y <= enemy5Y + 75)) {
	        		enemy5Y = 10000;	
	        	}
	            if((bull.x + 3 >= enemy6X) && (bull.x + 3 <= enemy6X + 70) && (bull.y >= enemy6Y) && (bull.y <= enemy6Y + 75)) {
	        		enemy6Y = 10000;	
	        	}
	            if((bull.x+ 3  >= enemy7X) && (bull.x+ 3  <= enemy7X + 70) && (bull.y >= enemy7Y) && (bull.y <= enemy7Y + 75)) {
	        		enemy7Y = 10000;	
	        	}
        	}
        }
        
        if(score == 7) {
    		gameOver1 = true;
    	}

    	if(gameOver1) {
    		currentState = GameState.OVER;
    		enemySpeed = 0;
    		speed = 0;
    		fill(190, 255, 190);
            noStroke();
            rect(width / 2 - 125, height / 2 - 80, 250, 160); 
            fill(0, 255, 0); // 255
            textAlign(CENTER);
            text("You Win!", width / 2, height / 2 - 10);
            text("Press R to Restart", width / 2, height / 2 + 30);
            
    	}
    	if(gameOver2) {
    		currentState = GameState.OVER;
    		enemySpeed = 0;
    		speed = 0;
    		fill(255, 190, 190);
            noStroke();
            rect(width / 2 - 125, height / 2 - 80, 250, 160); 
            fill(255, 0, 0); // 255
            textAlign(CENTER);
            text("You Lose.", width / 2, height / 2 - 10);
            text("Press R to Restart", width / 2, height / 2 + 30);
            
    	}
        
        

        if(enemy1Y == 10000 || enemy2Y == 10000 || enemy3Y == 10000 || enemy4Y == 10000 || enemy5Y == 10000 || enemy6Y == 10000 || enemy7Y == 10000) {
    		score++;
    	}
    	
    	if((x+75 >= enemy1X) && (x + 75 <= enemy1X + 150) && (y + 20 >= enemy1Y) && (y + 20 <= enemy1Y + 75) || (enemy1Y == 650)) {
    		gameOver2 = true;
    	}
    	if((x+75 >= enemy2X) && (x + 75 <= enemy2X + 150) && (y + 20 >= enemy2Y) && (y + 20 <= enemy2Y + 75) || (enemy2Y == 650)) {
    		gameOver2 = true;
    	}
    	if((x+75 >= enemy3X) && (x + 75 <= enemy3X + 150) && (y + 20 >= enemy3Y) && (y + 20 <= enemy3Y + 75) || (enemy3Y == 650)) {
    		gameOver2 = true;
    	}
    	if((x+75 >= enemy4X) && (x + 75 <= enemy4X + 150) && (y + 20 >= enemy4Y) && (y + 20 <= enemy4Y + 75) || (enemy4Y == 650)) {
    		gameOver2 = true;
    	}
    	if((x+75 >= enemy5X) && (x + 75 <= enemy5X + 150) && (y + 20 >= enemy5Y) && (y + 20 <= enemy5Y + 75) || (enemy5Y == 650)) {
    		gameOver2 = true;
    	}
    	if((x+75 >= enemy6X) && (x + 75 <= enemy6X + 150) && (y + 20 >= enemy6Y) && (y + 20 <= enemy6Y + 75) || (enemy6Y == 650)) {
    		gameOver2 = true;
    	}
    	if((x+75 >= enemy7X) && (x + 75 <= enemy7X + 150) && (y + 20 >= enemy7Y) && (y + 20 <= enemy7Y + 75) || (enemy7Y == 650)) {
    		gameOver2 = true;
    	}
    	
    }
   
    public void drawScore() {
        scoreFont = createFont("Bauhaus 93", 26, true);
        textFont(scoreFont);
        fill(255, 255, 0);
        textAlign(CENTER);
        text("Score: " + score, width - 90, 40);
    }
    
   
    public void keyPressed() {
    	if(key == 'd' && gameStart && !gameOver1 && !gameOver2) //added to check if gameStart is true
    	{
    		moveForward = true;
    		moving = true;
    	} 
    	if(key == 'a' && gameStart && !gameOver1 && !gameOver2) 
    	{
    		moveBackward = true;
    		moving = true;
    	} 
//    	if(key == CODED) {
//    		if(key == ENTER) {
//    			gameStart = true;
//    			enemySpeed = 2;
//    		}
//    	}
    	if(key == 'b') { // press y to begin, can change to any key
    		gameStart = true;
			enemySpeed = 2;
    	}
    }
   
    public void keyReleased() {
    	if(key == 'd' && gameStart && !gameOver1 && !gameOver2) 
    	{
    		moveForward = false;
    		moving = false;
    	} 
    	if(key == 'a' && gameStart && !gameOver1 && !gameOver2) 
    	{
    		moveBackward = false;
    		moving = false;
    	}
    	if(key == ' ') {
    	    float vx = bulletSpeed;
    	    float vy = bulletSpeed;
    	    bullets.add(new Bullet(x+71, y+6, vx, vy));
    	    
    	}
    	if(key == 'r' && (gameOver1 || gameOver2)) {
    	    reset();
    	}
    }
    
    public void move() {
    	
    	if(x <= -32) {
    		moveBackward = false;
    		moving = false;
    	}
    	else if(moveBackward) {
    		x -= speed;
    	}
    	
    	if(x >= 900) {
    		moveForward = false;
    		moving = false;
    	}
    	else if(moveForward) {
    		x += speed;
    	}
    }
    
    public static void sleep (int time) {
		try {
			Thread.sleep(time);
		}
		catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}
    
    public void reset() { //copy all global variables here set to what they are at the beginning of the program
    	for(int i = 0; i < bullets.size(); i ++) {
    		Bullet bull = bullets.get(i);
    		bull.x = 10000;
    	}
    	gameOver1 = false;
	    gameOver2 = false;
	    gameStart = false;
    	speed = 10;
    	x = 438;
    	y = 600;
    	bulletX = 512;
    	bulletY = 610; 
    	enemy1X = 30;
    	enemy2X = 175;
    	enemy3X = 320;
    	enemy4X = 465;
    	enemy5X = 610;
    	enemy6X = 755;
    	enemy7X = 900;
    	
    	enemy1Y = 50;
    	enemy2Y = 50;
    	enemy3Y = 50;
    	enemy4Y = 50;
    	enemy5Y = 50;
    	enemy6Y = 50;
    	enemy7Y = 50;
    	bulletSpeed = 10;
    	score = 0;
    	
    }

	class Bullet {
		float x, y, vx, vy;

		Bullet(float x, float y, float vx, float vy) {
			this.x = x;
			this.y = y;
			this.vx = vx;
			this.vy = vy;
		}

		void drawBullet() {
			fill(0, 255, 0);

			rect(x, y, 6, 15);
		}
		void move() {
			y -= vy;
		}
	}
}
