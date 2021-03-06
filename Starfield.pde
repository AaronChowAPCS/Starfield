Particle [] meteor = new Particle[50];
Particle ufo  = new OddballParticle();
Boundary walls = new Boundary();
void setup()
{
	size(500,500);
	background(0);
	for(int index = 0; index < meteor.length; index++)
	{
		meteor[index] = new NormalParticle();
	}
}
void draw()
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
}
interface Particle
{
	void show();
	void move();
	void collide();
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
	void show()
	{
		noStroke();
		normalColor = color((int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256));
		fill(normalColor);
		ellipse(myX,myY,10,10);
	}
	void move()
	{
		myX = myX + (changeX * directionX);
		myY = myY + (changeY * directionY);
	}
	void collide()
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
	void show()
	{
		noStroke();
		fill(dotColor);
		ellipse(myX,myY,20,20);
	}
	void move()
	{
		myX = myX + (changeX * directionX);
		myY = myY + (changeY * directionX);
	}
	void collide()
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
}
class Boundary
{
	void show()
	{
	fill(100);
	rect(0,0, width, 5);
	rect(0,0, 5, height);
	rect(0, height - 5, width, 5);
	rect(width - 5, 5, 5, height);
	}
}

