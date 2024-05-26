import java.awt.*;
import java.net.URL;
import java.util.List;
import javax.imageio.ImageIO;

public class Ship extends MovingThing
{
	private int speed;
	private Image image;
	private int MaxLives = 10;
	private int lives  = MaxLives;
	private int LiveRectWidth = 150;
	private boolean readyToBlink = false;
	int MaxBlinks = 4;
	private int blinkCounter = MaxBlinks;

	public Ship()
	{
		this(0,0,50,50,0);
	}

	public Ship(int x, int y)
	{
		//add code here
		this(x,y,50,50,0);
	}

	public Ship(int x, int y, int s)
	{
		//add code here
		this(x,y,50,50,s);
	}

	public Ship(int x, int y, int w, int h, int s)
	{
		//add code here
		super(x,y,w,h); //this needs to be first line cuz its super
		speed = s;
		try
		{
			//this sets ship.jpg as the image for your ship
			//for this to work ship.jpg needs to be in the same folder as this .java file
			URL url = getClass().getResource("ship.jpg");
			image = ImageIO.read(url);
		}
		catch(Exception e)
		{
			//feel free to do something here or not
			System.out.println("ship image failed to load");
		}
	}


	public boolean isALive() {
		return (lives > 0);
	}

	public void setSpeed(int s)
	{
		//add more code
		speed = s;

	}

	public int getSpeed()
	{
		//continue coding
		return speed;
	}

	public void recomputeLives(List<Alien> alList) {
		if(alList.size() > 0) {
            for (Alien al : alList) {
                boolean priorTouchState = al.getTouch();
                if ((al.getX() >= getX() && al.getX() <= getX() + getWidth()) ||
                        (al.getX() + al.getWidth() >= getX() && al.getX() + al.getWidth() <= getX() + getWidth())) {

					if ((al.getY() >= getY() && al.getY() <= getY() + getHeight()) ||
							(al.getY() + al.getHeight() >= getY() && al.getY() + al.getHeight() <= getY() + getHeight())) {

						al.setTouch(true);
						if (!priorTouchState) { // UnTouched to Touched - decrement life
							if(lives!=0) {
								lives--;
								readyToBlink = true;
							}
							break;
						}

					} else {
						al.setTouch(false);
					}
				} else {
					al.setTouch(false);
				}
            }
		}
	}

	public void move(String direction)
	{
		//add code here
		//think about ALL your global variables and how you can use them to "move"
		//keep your parameter in mind as well
		if(direction.equals("LEFT")) {
			if((getX()-speed) >= 0) {
				setX(getX() - speed);
			}
		}
		if(direction.equals("RIGHT")) {
			if((getX()+getWidth()+speed) <= 800) {
				setX(getX() + speed);
			}
		}
		if(direction.equals("UP")) {
			if ((getY() - speed) >= 0) {
				setY(getY() - speed);
			}
		}

		if(direction.equals("DOWN")) {
			if ((getY() + speed + getHeight()) <= 600) {
				setY(getY() + speed);
			}
		}
	}


	public void draw( Graphics window )
	{
		if(lives > 0 && readyToBlink && blinkCounter > 0) {

			window.drawImage(image, getX(), getY(), getWidth(), getHeight(), null);
			window.setColor(Color.WHITE);
			window.fillRect(getX(),getY(),getWidth(),getHeight());
			blinkCounter--;

		} else {
			readyToBlink = false;
			blinkCounter = MaxBlinks;
			window.drawImage(image, getX(), getY(), getWidth(), getHeight(), null);
		}

		window.setColor(Color.WHITE);
		window.drawString("Remaining Ship Life = " + lives, 600, 20);

		window.drawRect(600, 28, LiveRectWidth, 18);
		if(lives >= (0.5 * MaxLives)) {
			window.setColor(Color.GREEN);
			window.fillRect(600, 30, (LiveRectWidth*lives)/MaxLives, 15);
		} else if (lives > 0){
			window.setColor(Color.RED);
			window.fillRect(600, 30, (LiveRectWidth*lives)/MaxLives, 15);
		} else {
			window.setColor(Color.RED);
			Font myFont = new Font("Courier New", 1, 40);
			window.setFont(myFont);
			window.drawString("Game Over: Ship Destroyed",80, 80);
		}


	}

	public String toString()
	{
		return super.toString() + " " + getSpeed();
	}
}

