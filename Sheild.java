import java.awt.*;
public class Sheild
{
    private int x;
    private int y;
    private int size;
    private Color color;
    public Sheild(int x, int y, Color color)
    {
        this.x = x;
        this.y = y;
        this.size = 20;
        this.color = color;
    }
    public void draw(Graphics g)
    {
        g.setColor(this.color);
        g.fillOval(this.x, this.y, this.size, this.size);
    }
    public void move(int dirX, int dirY)
    {
        this.x += dirX;
        this.y += dirY;
    }
    public boolean manTest(Man man)
    {
       if(this.x+this.size > man.getX() && this.x < man.getX()+man.getSize())
       {
           if(this.y+this.size > man.getY() && this.y < man.getY()+man.getSize())
           {
               return true;
           }
       }
       return false;
    }
    public int getX()
    {
       return this.x; 
    }
    public int getY()
    {
        return this.y;
    }
    public int getSize()
    {
        return this.size;
    }
}

