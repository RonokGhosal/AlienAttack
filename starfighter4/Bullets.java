import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Bullets
{
	private List<Ammo> ammo;

	public Bullets()
	{
		ammo = new ArrayList<Ammo>();
	}

	public void add(Ammo al)
	{
		ammo.add(al);
	}

	public void drawEmAll( Graphics window )
	{
		for (Ammo a : ammo) {
			a.draw(window);
		}
	}

	public void moveEmAll()
	{
		if (ammo.size() > 0) {
			for (Ammo b : ammo) {
				b.move("UP");
			}
		}
	}

	public void cleanEmUp()
	{
      //for every ammo in the list
      //if the ammo is out of bounds
      //remove it
		if (ammo.size() > 0) {
			for (int i=ammo.size()-1; i >= 0; i--) {
				if (ammo.get(i).getY() < -1) {
					ammo.remove(i);
				}
			}
		}

	}

	public List<Ammo> getList()
	{
		return ammo;
   }

	public String toString()
	{
		return "" + ammo;
	}
}
