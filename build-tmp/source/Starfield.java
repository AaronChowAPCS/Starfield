import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Starfield extends PApplet {

Particle [] meteor = new Particle[50];
Particle ufo  = new OddballParticle();
Boundary walls = new Boundary();
public void setup()
{
	size(500,500);
	background(0);
	for(int index = 0; index < meteor.length; index++)
	{
		meteor[index] = new NormalParticle();
	}
}
public void draw()
{
	background(0);
	for(int index = 0; index < meteor.length; index++)
	{
		meteor[index].show();
		meteor[index].move();
		meteor[index].collide();
	}
	ufo.show();
	ufo.move();
	ufo.collide();
	walls.show();
	((OddballParticle)ufo).swag();
}
interface Particle
{
	public void show();
	public void move();
	public void collide();
}
class NormalParticle implements Particle
{
	float myX;
	float myY;
	int changeX;
	int changeY;
	int directionX;
	int directionY; 
	int normalColor;
	NormalParticle()
		{
		myX = (int)(Math.random()*400) + 50;
		myY = (int)(Math.random()*400) + 50;
		changeX = (int)(Math.random()*7) - 3;
		changeY = (int)(Math.random()*7) - 3;
		directionX = 1;
		directionY = 1;
		normalColor = color((int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256));
		if(changeX == 0)
			changeX = 1;
	}
	public void show()
	{
		noStroke();
		normalColor = color((int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256));
		fill(normalColor);
		ellipse(myX,myY,10,10);
	}
	public void move()
	{
		myX = myX + (changeX * directionX);
		myY = myY + (changeY * directionY);
	}
	public void collide()
	{
		if(myX < 0 || myX > 500)
			directionX = directionX * -1;
		if(myY < 0 || myY > 500)
			directionY = directionY * -1;
	}
}
class OddballParticle implements Particle 
{
	int myX;
	int myY;
	int changeX;
	int changeY;
	int directionX;
	int directionY;
	float distance;
	int dotColor;
	boolean bouncy;
	OddballParticle()
	{
		myX = 200;
		myY = 200;
		changeX = (int)(Math.random()*8) - 4;
		changeY = (int)(Math.random()*8) - 4;
		directionX = 1;
		directionY = 1;
		dotColor = color(255,255,0);
	}
	public void show()
	{
		noStroke();
		fill(dotColor);
		ellipse(myX,myY,20,20);
	}
	public void move()
	{
		myX = myX + (changeX * directionX);
		myY = myY + (changeY * directionX);
	}
	public void collide()
	{
		bouncy = true;
		if(get(myX + 10, myY + 10)!= color(0,0,0) && get(myX + 10, myY + 10)!= color(100))
		{
			directionX = directionX * 1;
			directionY = directionY * -1;
			changeX = (int)(Math.random()*10) - 5;
			changeY = (int)(Math.random()*10) - 5;
	
		}
		else if(get(myX - 10, myY + 10)!= color(0,0,0) && get(myX - 10, myY + 10)!= color(100))
		{
			directionX = directionX * -1;
			directionY = directionY * 1;
			changeX = (int)(Math.random()*10) - 5;
			changeY = (int)(Math.random()*10) - 5;
	
		}
		else if(get(myX + 10, myY - 10)!= color(0,0,0) && get(myX + 10, myY - 10)!= color(100))
		{
			directionX = directionX * -1;
			directionY = directionY * -1;
			changeX = (int)(Math.random()*10) - 5;
			changeY = (int)(Math.random()*10) - 5;
		}
		else if(get(myX - 10, myY - 10)!= color(0,0,0) && get(myX - 10, myY - 10)!= color(100))
		{
			directionX = directionX * -1;
			directionY = directionY * 1;
			changeX = (int)(Math.random()*10) - 5;
			changeY = (int)(Math.random()*10) - 5;
	
		}
		if(myX < 0 || myX > 500)
			directionX = directionX * -1;
		if(myY < 0 || myY > 500)
			directionY = directionY * -1;
	}
	public void swag()
	{
		println("swag");
	}
}
class Boundary
{
	public void show()
	{
	fill(100);
	rect(0,0, width, 5);
	rect(0,0, 5, height);
	rect(0, height - 5, width, 5);
	rect(width - 5, 5, 5, height);
	}
}

  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Starfield" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
