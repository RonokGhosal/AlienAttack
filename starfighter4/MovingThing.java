import java.awt.Graphics;

public abstract class MovingThing implements Moveable
{
	//add instance variables (look at your constructors)
	private int x, y, w, h;
	public MovingThing()
	{

		this(10,10,10,10);
	}

	public MovingThing(int x, int y)
	{
		//add code here
		this(x,y, 10, 10);
	}

	public MovingThing(int x, int y, int w, int h)
	{
		//add code here
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}

	//add necessary implementations (look at interface)
	public void setPos( int x, int y)
	{
		this.x = x;
		setY(y);
	}
	public void setX( int x )
	{
		this.x = x;
	}
	public void setY( int y)
	{
		this.y = y;
	}

	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}

	public int getWidth()
	{
		return w;
	}
	public int getHeight()
	{
		return h;
	}
	public void setWidth( int w )
	{
		this.w = w;
	}
	public void setHeight( int h )
	{
		this.h = h;
	}

	//do not change code below this line
	public abstract void move(String direction);
	public abstract void draw(Graphics window);

	public String toString()
	{
		return getX() + " " + getY() + " " + getWidth() + " " + getHeight();
	}
}


