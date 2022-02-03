# **Q2 Base Project: Underwater Adventure with Peppa Pig!**
#### ***Objective: Help Peppa swim away from octopus obstacles and collect seashell coins to increase score***


## **Project Intro:**
In Quarter 2, we were assigned to create a game which was either a recreation of an already existing game, or a brand new idea. 
We learned JAVA basics in Quarter 1 like data type use, `for loops`, `while loops`, `if statements`, writing classes, and methods to create a basic idea and start coding.
However, as the quarter progressed, we continued to add to our games, using new concepts, such as `arrays`, `ArrayLists`, and sorting methods like `Bubble Sort`, `Selection Sort`, and `Insertion Sort`. 

## **Game Overview:**
For my game, I decided to channel my inner Peppa Pig fan to center the theme around her. I wanted to create an underwater Peppa Pig Adventure. The goal is to avoid losing points by ***evading octopus*** and ***collect coins*** to get more points. We all know Peppa is super versatile and her tenacity is to die for so of course this game is easy for her, but the question is can you catch up to her level. In this game, Peppa has lost her mobility and you control her now. It's up to you to save her from the octopus and help her get rich off shell currency.  

![Game GIF recording](https://user-images.githubusercontent.com/70664950/148616276-9d9905af-cb97-4c10-8976-aa47a80b6436.gif)

## **Meet the Characters:**
![peppa1](https://user-images.githubusercontent.com/70664950/148616878-faf66f43-fbda-4332-a775-78d6a30b992b.png)
![octopus obstacle](https://user-images.githubusercontent.com/70664950/148616357-8a09070e-b1a3-4277-bf4a-500ebc1958da.png)
![coinshell](https://user-images.githubusercontent.com/70664950/148616405-5bdb14cf-3ddc-4f44-a8b0-0def50d7bfb2.png)

## **Code Run-Down:**
#### After deciding on a theme, finding my backgrounds, drawing my characters, and importing them into Eclipse, I started coding. 

#### First, I had to make classes for each of my characters, one for my background, and a frame to put the game together on. 
```
OceanPeppa.java
Octopus.java
Background.java
Coin.java
Frame.java
```

#### Before putting the game together in the `Frame.java` class, I had to set up each of my characters
- Upload Image of Character
  ```
  img = getImage("/imgs/coinshell.png");
  ```
- Set position, acceleration, and velocity variables
  ```
  public int x1 , y1;
  private Image img;
  public int bvy1 = 4;
  private AffineTransform tx;
  public Octopus(int x, int y) {
  this.x1 = x;
  this.y1 = y;
  img = getImage("/imgs/octopus obstacle.png"); //load the image
  tx = AffineTransform.getTranslateInstance(x, y );
  init(x, y); //initialize the location of the image
  ```
- Define movement of objects through update method and create methods to move object through user interface if neccessary
  - *example of user interface method*
    ```
     public void fly () {
		 vy -= 15;
		 vx += 10;
	 }
	
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(.5, .5);
	}
	
	public void flap () {
		vy -= 75;
	}
    ```
  - *example of update method*
    ```
    private void update() {
	x2+= bvx1;		 
	if (x2 > 800) {
		x2 = 0;
	}		
	tx.setToTranslation(x2, y2);
	tx.scale(.9, .9);
    }
    ```
 
 #### After setting up my characters, I started building the game in the `Frame.java` class
- First, I utilized a boolean to create an intro screen. The idea is, that the boolean is always set to false, unless the enter key is pressed. 
  ```
  //outside of paint method
  boolean gameStart = false;
  
  //in paint method
  bg.paint(g);
  intro.paint(g);
  introTop.paint(g);
  if(!gameStart) {
	g.setColor(Color.blue);
	g.setFont(f2);
	g.drawString("welcome. help peppa finness octopus and make bank", 70, 100);
	g.drawString("press enter to commence your ocean adventure", 100, 200);
	g.drawString("with the ultimate G, Peppa", 220, 300);
	return;
  } 
  
  // in keyPressed method
  if (arg0.getKeyCode() == 10) {
	gameStart=true;
  }
 
  ```
  ![game](https://user-images.githubusercontent.com/70664950/148460073-3febfd30-9c2b-4514-9756-9609bbd90761.PNG)

- Then I had to create all my objects. I decided to have shell currency, three octopus obstacles, and of course, my main character Peppa.
  ```
  Background bg = new Background(0, 0);
	OceanPeppa peppa = new OceanPeppa(10, 300);
	Octopus octo1 = new Octopus (10,0);
	Octopus octo2 = new Octopus (300,0);
	Octopus octo3 = new Octopus (590,0);
	Coin intro = new Coin(30, 490);
	Coin introTop = new Coin(30, 0);
	
  ```
  
- Then I had to create some additional variables for the game, like the scoring aspect.
- After that, I had to create a `paint` method to build my characters and set different methods in place for functionality.
  - Make the objects paint themselves in the `paint` method
    ```
    bg.paint(g);
    peppa.paint(g);
    octo1.paint(g);
    octo2.paint(g);
    octo3.paint(g);
    ```
  - For the coins, I utilized an array to build many coins at once. In order to paint the array, I had to create it within the `paint` method itself. 
    ```
    Coin myShells [] = new Coin[18];
    int x2 = 200;
    int y2 = 100;
    int bvx = 50;
    for (int i = 0; i < myShells.length; i ++) {
	      Coin temp = new Coin(x2, y2);
	      myShells[i] = temp;
	      myShells[i].paint(g);
    }
    ```
  - Then I had to add text onto the background for basic instructions and the score count
  - I also used the paint method to create rectangles around the objects so that I could utilize collision to change the score. 
    ```
    Rectangle rocto1 = new Rectangle (octo1.x1, octo1.y1, 50, 50);
    Rectangle rocto2 = new Rectangle (octo2.x1, octo2.y1, 50, 50);
    Rectangle rocto3 = new Rectangle (octo3.x1, octo3.y1, 50, 50);
    Rectangle rpeppa = new Rectangle (peppa.x, peppa.y, 50, 50);
    ```
  - After creating the rectangles, I used `if statements` to make the program check if the Peppa character collided with the obstacles or the coins using the `intersect method`. The code within the if statements sets changes in score based on what the object collides with.
    ```
    if (rpeppa.intersects(rcoin)) {
	      score += 1;
    }
    ```
 - The last thing to do in the `Frame.java` class was call the methods I made in the `OceanPeppa.java` class so that she could move with user interface.
   ```
   public void keyPressed(KeyEvent arg0) {
	      // TODO Auto-generated method stub
	      System.out.println(arg0.getKeyCode());
	      peppa.flap();
	          if (arg0.getKeyCode() == 10) {
		            gameStart=true;
	          }
   }
   
#### In this code, I incorporated knowledge of classes, arrays, primitive data types, methods, loops, and conditional statements.  
 
## **What I Learned:**
As I have been working on this project for the whole quarter, I learned a lot. I learned how to apply the basic skills we were taught in Quarter 1 in a setting 
other than ***AP style FRQs and MCQs***. I also learned how to create multiple classes and ***effectively*** create objects and use them in a runner class. Though 
loops and conditional statements were something we practiced a lot, working on the game was a chance for me to practice programming in a more ***efficient*** way. 
The game was also a way to apply concepts that were taught this quarter. Not only did I get to increase my understanding of them through practicing questions, but 
I also got to see how they worked in a runnable program which helped me understand the reasoning behind the code, and not just how it works.

## **Final Thoughts:**
This game was super fun to build. It was really cool to build a game that others could actually play with my own characters and my own theme. It also gave me an 
opportunity to see a little bit of the behind-the-scenes programming that went into building some of the games I used to play like ***Jetpack Joyride*** and 
***Fruit Ninja*** even though my game is not nearly as complex. I really liked being able to apply what I learned in **JAVA** and see the results through a 
runnable program.
