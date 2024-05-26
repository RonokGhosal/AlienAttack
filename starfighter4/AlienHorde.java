
import java.awt.*;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.List;

public class AlienHorde
{
    private List<Alien> aliens;
    boolean playerWin = false;

    public AlienHorde(int size, int speed, int spacing, int shiftDown, int xPos, int yPos, int xDim, int yDim, int width)
    {
        aliens = new ArrayList<Alien>();
        int x = xPos;
        int y = yPos;

        for(int i = 0; i < size; i++){
            if (x > width - xDim) {
                x = xPos;
                y+= shiftDown;
            }
            aliens.add(new Alien(x,y,xDim,yDim,speed));
            x += spacing;

        }
        //initialize ArrayList
        //and fill with size amount of aliens (75 pixels apart)
        //if your row is full (out of bounds of screen)
        //move down a row (75 pixels)
        //starting point is 25, 50
        //first add aliens with speed of 0 to make sure you spacing is good

    }

    public void setPLayerWin(boolean bool) {
        playerWin = bool;
    }

    public boolean getPlayerWin() {
        return playerWin;
    }

    public void add(Alien al)
    {
        //add an al to the list
        aliens.add(al);
    }

    public void drawEmAll( Graphics window )
    {
        // Check if any of the Aliens touched the floor - then it is Game Over
        if(isFloored()) {
            window.setColor(Color.RED);
            Font myFont = new Font("Courier New", 1, 40);
            window.setFont(myFont);
            window.drawString("Game Over: Aliens Survived",80, 80);
        } else if(playerWin) {
            window.setColor(Color.GREEN);
            Font myFont = new Font("Courier New", 1, 80);
            window.setFont(myFont);
            window.drawString("CONGRATS - YOU WON",100, 150);
        }

        //make sure you draw all aliens in the list
        for(Alien a : aliens)
            a.draw(window);
    }

    public void moveEmAll()
    {
        //make sure you move all aliens in the list
        for(Alien a : aliens)
            a.move(""); // Aliens are predetermined to move in a certain direction
    }

    // For each shot, remove the impacted Ammo and the Alien
    public void removeDeadOnes(List<Ammo> shots) {
        for (int j = shots.size()-1; j >= 0; j--) {
            Ammo am = shots.get(j);
            for (int i = aliens.size()-1; i >= 0; i--) {
              Alien al =  aliens.get(i);
                if ((am.getX() >= al.getX() && am.getX() <= al.getX()+al.getWidth()) ||
                            (am.getX()+am.getWidth() >= al.getX() && am.getX()+am.getWidth() <= al.getX()+al.getWidth()))
                {
                    if ((am.getY() >= al.getY() && am.getY() <= al.getY() + al.getHeight()) ||
                            (am.getY() + am.getHeight() >= al.getY() && am.getY() + am.getHeight() <= al.getY() + al.getHeight()))
                    {
                        aliens.remove(i);
                        shots.remove(j);
                        break;
                    }
                }
            }

        }
    }

    public boolean isFloored () {
        boolean touchedFloor = false;
        for(Alien a : aliens) {
            if (a.getY() >= 600) {
                touchedFloor = true;
                break;
            }
        }
        return touchedFloor;
    }


    public List<Alien> getAliens() {
        return aliens;
    }
    public String toString()
    {
        return "" + aliens;
    }

}