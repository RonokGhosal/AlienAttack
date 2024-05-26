import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Canvas;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class OuterSpace extends Canvas implements KeyListener, Runnable
{
	private AlienHorde horde;
	private Ship ship;
    private Bullets bullet;
	private boolean[] keys;
	private int MaxHordeCount = 4;
	private int hordeCount = 0;

	private BufferedImage back;

	public OuterSpace()
	{
		setBackground(Color.black);

		keys = new boolean[5];


		//instantiate what you need as you need it (from global objects above)
		//call (x,y,s) constructor because you need x, y, and speed
		ship = new Ship(310, 450, 5);

		horde = new AlienHorde(20, 1, 75, 75, 25, 50, 30, 30, 800);
		hordeCount++;

		bullet = new Bullets();

		this.addKeyListener(this);
		new Thread(this).start(); // run method invoked

		setVisible(true);
	}

	public void update(Graphics window)
	{
		paint(window);
	}

	//the top part of the paint method is done for you
	public void paint( Graphics window )
	{
		initialScreen(window);
		//set up the double buffering to make the game animation nice and smooth
		Graphics2D twoDGraph = (Graphics2D)window;

		//take a snap shot of the current screen and save it as an image
		//that is the exact same width and height as the current screen
		if(back==null)
			back = (BufferedImage)(createImage(getWidth(),getHeight()));

		//create a graphics reference to the back ground image
		//we will draw all changes on the background image
		Graphics graphToBack = back.createGraphics();
		//this sets the background for the graphics window
		graphToBack.setColor(Color.BLACK);
		graphToBack.fillRect(0,0,getWidth(),getHeight());


		//add code to move Ship, Alien, etc.-- Part 1
		if(keys[0] == true)
		{
			ship.move("LEFT");
		}
		if (keys[1] == true)    //reading from keyboard; means if we press left arrow
		{
			ship.move("RIGHT");
		}
		if (keys[2] == true)    //reading from keyboard; means if we press left arrow
		{
			ship.move("UP");
		}
		if (keys[3] == true)    //reading from keyboard; means if we press left arrow
		{
			ship.move("DOWN");
		}

		if (keys[4] == true)
		{
			//remove code here

		}

		//make sure you've drawn all your stuff

		ship.recomputeLives(horde.getAliens());
		ship.draw(graphToBack);

		bullet.moveEmAll();
		bullet.cleanEmUp();
		bullet.drawEmAll(graphToBack);

		horde.moveEmAll();
		horde.removeDeadOnes(bullet.getList());

		if((horde.getAliens().size() == 0) && (hordeCount < MaxHordeCount)) {
			horde = new AlienHorde(hordeCount * 8, hordeCount * 2,
					            hordeCount * 8 + 75, hordeCount * 5 + 75,
					              hordeCount * 10 + 25, hordeCount * 10 + 50,
					              hordeCount * 2 + 30, hordeCount * 2 + 30,800 - (hordeCount * 50));
			hordeCount++;
		}
		if(horde.getAliens().size()==0 && hordeCount == MaxHordeCount) {
			horde.setPLayerWin(true);
		}
		horde.drawEmAll(graphToBack);


		twoDGraph.drawImage(back, null, 0, 0);
		back = null;

	}

	private void initialScreen(Graphics window) {

	}

	public void keyPressed(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_LEFT)    //reading from keyboard; means if we press left arrow
		{
			keys[0] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			keys[1] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			keys[2] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			keys[3] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
		{
				keys[4] = true;
			    bullet.add(new Ammo(ship.getX()+20, ship.getY(), 5));
		}
		//repaint();
	}

	public void keyReleased(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			keys[0] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			keys[1] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			keys[2] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			keys[3] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			keys[4] = false;
		}
		//repaint();
	}

	public void keyTyped(KeyEvent e)
	{
		//no code needed here
		//method needs to be implemented
		//because class implements KeyListner
	}

	public void run()
	{
		try
		{
			while(true)
			{
				if(ship.isALive() && !horde.isFloored() && !horde.getPlayerWin()) {
					Thread.currentThread().sleep(10);
					repaint();
				} else {
					repaint();
					return;
				}
			}
		}catch(Exception e)
		{
			System.out.println("Exception in thread " + e.getMessage());
		}
	}
}




