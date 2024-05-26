import java.net.URL;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;

public class Alien extends MovingThing
{
	private int speed;
	private Image image;
	private boolean touchedByShip = false;

	public void setTouch(boolean state) {
		touchedByShip = state;
	}

	public boolean getTouch() {
		return touchedByShip;
	}


	public Alien()
	{
		this(0,0,30,30,0);
	}

	public Alien(int x, int y)
	{
		//add code here
		this(x, y,30, 30, 1 );
	}

	public Alien(int x, int y, int s)
	{
		//add code here
		this(x, y, 30, 30, 1);
	}

	public Alien(int x, int y, int w, int h, int s)
	{
		//add code here
		super(x, y, w, h);
		speed = s;
      try
		{
			 URL url = getClass().getResource("alien.jpeg");
			image = ImageIO.read(url);

		}
		catch(Exception e)
		{
			System.out.println("Failed to load alien.jpg" + e.getMessage());
		}
	}

	public void setSpeed(int s)
	{
	   //add code
		speed = s;
	}

	public int getSpeed()
	{
	   //add code
		return speed;
	}

   public void move(String direction) { //direction here because Move is an abstract method inherited from moving thing that takes in a string for the parameter
	   //add code here
	   //check that the alien is within the bounds of the screen (see Starfighter.java)
	   //if alien is out of bounds change speed direction
	   //and move the alien down a  row (40 pixels)
	   //constantly change the x position of the alien by the speed
	   if (getX() > 800 - getWidth() || getX() <= 0) {
		   speed = -speed;
		   setY(getY() + 40);
	   }
	   setX(getX() + speed);
   }

   
   /* The draw method is done for  you.
   This method will move the alien and update it's location on screen by
   constantly redrawing it. 
   */
	public void draw( Graphics window )
	{
		//move("DOWN");
        window.drawImage(image,getX(),getY(),getWidth(),getHeight(),null);
	}

	public String toString()
	{
		return super.toString() + getSpeed();
   }
}