import java.awt.*;
public class Segment
{
    private int x;
    private int y;
    private int size;
    private Color color;
    public Segment(int x, int y, Color color)
    {
        this.x = x;
        this.y = y;
        this.size = 20;
        this.color = color;
    }
    public void draw(Graphics g)
    {
        g.setColor(this.color);
        g.fillRect(this.x, this.y, this.size, this.size);
    }
    public boolean hitTest(Man man)
    {
        if(this.x+this.size > man.getX() && this.x < man.getX()+man.getSize())
        {
            if(this.y+this.size >  man.getY() && this.y < man.getY()+man.getSize())
            {
                return true;
            }
        }
        return false;
    }
    public void move(int x, int y)
    {
        this.x = x;
        this.y = y;
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
