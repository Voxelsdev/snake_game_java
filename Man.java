import java.awt.*;
public class Man
{
    private int x;
    private int y;
    private int size;
    private Color color;
    public Man()
    {
        this.x = 0;
        this.y = 0;
        this.size = 20;
        this.color = Color.BLUE;
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
    public boolean hitTest(Food food)
    {
        if(this.x+this.size > food.getX() && this.x < food.getX()+food.getSize())
        {
            if(this.y+this.size > food.getY() && this.y < food.getY()+food.getSize())
            {
                return true;
            }
        }
        return false;
    }
        public boolean wallTest(Wall wall)
    {
        if(this.x+this.size > wall.getX() && this.x < wall.getX()+wall.getSize())
        {
            if(this.y+this.size > wall.getY() && this.y < wall.getY()+wall.getSize())
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