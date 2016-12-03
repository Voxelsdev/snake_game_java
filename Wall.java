import java.awt.*;
public class Wall
{
    private int x;
    private int y;
    private int size;
    private Color color;
    public Wall(int x, int y, Color color)
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