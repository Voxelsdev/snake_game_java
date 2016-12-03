import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
public class Game_Applet extends Applet implements Runnable, KeyListener {
    private Image img;
    private Graphics g;
    private boolean playing;
    private Thread thread;
    private long startTime, endTime, framePeriod;
    private int xPos, yPos, changeX;
    private Man man;
    private ArrayList<Food> food;
    private Wall wall;
    private Wall wall2;
    private Wall wall3;
    private Wall wall4;
    private Wall wall5;
    private Chaser chaser;
    private Sheild sheild;
    private int tenScore;
    private int score;
    private int dirX;
    private int dirY;
    private boolean turbo;
    private ArrayList<Segment> segments;
    /**
     * Initialize the application (much like main method)
     * Initialize all of the instance fields
     * Set the applett size to 500x500
     */
    public void init(){
        this.playing = true;
        this.turbo = false;
        this.resize(500, 500);
        this.img = createImage(getWidth(), getHeight());
        this.g = img.getGraphics();
        this.framePeriod = 60;
        this.thread = new Thread(this);
        addKeyListener(this);
        this.man = new Man();
        this.wall = new Wall(randomX(), randomY(), Color.RED);
        this.wall2 = new Wall(randomX(), randomY(), Color.RED);
        this.wall3 = new Wall(randomX(), randomY(), Color.RED);
        this.wall4 = new Wall(randomX(), randomY(), Color.RED);
        this.wall5 = new Wall(randomX(), randomY(), Color.RED);
        this.chaser = new Chaser(480, 480, Color.PINK);
        this.food = new ArrayList<Food>();
        this.food.add(new Food(40, 40, Color.GREEN));
        this.score = 0;
        this.dirX = 0;
        this.dirY = 0;
        this.segments = new ArrayList<Segment>();
        //do this last!
        thread.start();
    }
    /**
     * reset game when dead
     */
    public void dead()
    {
        this.man = new Man();
        this.wall = new Wall(randomX(), randomY(), Color.RED);
        this.wall2 = new Wall(randomX(), randomY(), Color.RED);
        this.wall3 = new Wall(randomX(), randomY(), Color.RED);
        this.wall4 = new Wall(randomX(), randomY(), Color.RED);
        this.wall5 = new Wall(randomX(), randomY(), Color.RED);
        this.chaser = new Chaser(480, 480, Color.PINK);
        this.food = new ArrayList<Food>();
        this.food.add(new Food(30, 40, Color.GREEN));
        this.score = 0;
        this.dirX = 0;
        this.dirY = 0;
        this.segments = new ArrayList<Segment>();
    }
    /**
     * Draws the board for each frame. 
     * @parm gfx the name of the Graphics object to paint.
     */
    public void paint(Graphics gfx){
      g.setColor(Color.BLACK);
      g.fillRect(0, 0, getWidth(), getHeight());
      man.draw(g);
      wall.draw(g);
      wall2.draw(g);
      wall3.draw(g);
      wall4.draw(g);
      wall5.draw(g);
      chaser.draw(g);
      for(int i=0; i<food.size(); i++)
      {
         food.get(i).draw(g);    
      }
      for(int i = 0; i<segments.size(); i++)
      {
         segments.get(i).draw(g); 
      }
      g.setColor(Color.RED);
      g.drawString(""+score, getWidth()-20, 20);
      //the most important part:
      gfx.drawImage(img, 0, 0, this);
    }
    public void update(Graphics gfx){
        paint(gfx);
    }
    public boolean scoreCheck(int score)
    {
        if(this.tenScore*10 == score)
        {
            this.tenScore++;
            return true;
        }
        return false;
    }
    public Color randomColor()
    {
       int r = (int)Math.round(Math.random()*250);
       int gr = (int)Math.round(Math.random()*250);
       int b = (int)Math.round(Math.random()*250);
       Color c = new Color(r, gr, b);
       return c;
    }
    public int randomX()
    {
        int randomX = (int)Math.floor(Math.random()*25)*20;
        return randomX;
    }
    public int randomY()
    {
        int randomY = (int)Math.floor(Math.random()*25)*20;
        return randomY;
    }
    @Override
    public void run()
    {
        while(playing==true)
        {
          startTime = System.currentTimeMillis();
          if(man.getX() >= 520)
          {
             dead();
          }
          else if(man.getY() == 520)
          {
              dead();
          }
          else if(man.getX() == -20)
          {
              dead();
          }
          else if(man.getY() == -20)
          {
              dead();
          }
          for(int i = segments.size()-1; i > 0; i--)
          {
             Segment thisSegment = segments.get(i);
             Segment nextSegment = segments.get(i-1);
             thisSegment.move(nextSegment.getX(), nextSegment.getY());
             if(thisSegment.hitTest(man) == true)
             {
                 init();
             }
          }
          if(segments.size() > 0)
          {
              segments.get(0).move(man.getX(), man.getY());
          }
          if(chaser.manTest(man) == true)
          {
              dead();
          }
          if(man.wallTest(wall) == true)
          {
              dead();
          }
          if(man.wallTest(wall2) == true)
          {
              dead();
          }
          if(man.wallTest(wall3) == true)
          {
              dead();
          }
          if(man.wallTest(wall4) == true)
          {
              dead();
          }
          if(man.wallTest(wall5) == true)
          {
              dead();
          }
          man.move(dirX, dirY);
          int chaserMoveX = 0;
          int chaserMoveY = 0;
          if(man.getX() >= chaser.getX())
          {
              chaserMoveX = 5;
          }
          if(man.getX() <= chaser.getX())
          {
              chaserMoveX = -5;
          }
          if(man.getY() >= chaser.getY())
          {
              chaserMoveY = 5;
          }
          if(man.getY() <= chaser.getY())
          {
              chaserMoveY = -5;
          }
          chaser.move(chaserMoveX, chaserMoveY);
          for(int i = 0; i<food.size(); i++)
          {
              Food f = food.get(i);
              if(man.hitTest(f))
              {
                 food.remove(i);
                 i--;
                 score++;
                 int randomX = (int)Math.floor(Math.random()*25)*20;
                 int randomY = (int)Math.floor(Math.random()*25)*20;
                 food.add(new Food(randomX, randomY, randomColor()));
                 segments.add(new Segment(man.getX(), man.getY(), randomColor()));
              }
          }
          //important and absolutely essential!
          repaint();
          endTime = System.currentTimeMillis();
          try
          {
              thread.sleep(framePeriod-(endTime-startTime));
          }
          catch(InterruptedException e)
          {
          }
        }
    }
    @Override
    public void keyPressed(KeyEvent e)
    {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
           this.dirX = 20;
           this.dirY = 0;
          
        }
        else if(e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            this.dirX = -20;
            this.dirY = 0;
        }
        else if(e.getKeyCode() == KeyEvent.VK_UP)
        {
           this.dirX = 0;
           this.dirY = -20;
        }
        else if(e.getKeyCode() == KeyEvent.VK_DOWN)
        {
            this.dirX = 0;
            this.dirY = 20;
        }
        else if(e.getKeyCode() == KeyEvent.VK_SHIFT)
        {
            this.turbo = true;
        }
        if(this.turbo){
            this.dirX *=2;
            this.dirY *=2;
        }
        /***
        else if(e.getKeyCode() == KeyEvent.VK_LEFT && e.getKeyCode() == KeyEvent.VK_UP)
        {
            man.move(-3,-3);
        }
        else if(e.getKeyCode() == KeyEvent.VK_LEFT && e.getKeyCode() == KeyEvent.VK_DOWN)
        {
            man.move(-3,3);
        }
        else if(e.getKeyCode() == KeyEvent.VK_RIGHT && e.getKeyCode() == KeyEvent.VK_UP)
        {
            man.move(3,-3);
        }
        else if(e.getKeyCode() == KeyEvent.VK_RIGHT && e.getKeyCode() == KeyEvent.VK_DOWN)
        {
            man.move(3,3);
        }
        ***/
    }
    @Override
    public void keyReleased(KeyEvent e)
    {
        if(e.getKeyCode() == KeyEvent.VK_SHIFT)
        {
            this.turbo = false;
        }
    }
    @Override
    public void keyTyped(KeyEvent e)
    {
        //nothing
    }
}