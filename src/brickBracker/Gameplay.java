package brickBracker;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Timer;


import javax.swing.JPanel;

public class Gameplay extends JPanel implements KeyListener,ActionListener{
   public int F=3;public int G=7;public int h=F*G;
	private boolean play=false;
	private int score=0;
	private int totalBricks=h;
	private int delay=8;
	private Timer timer;
	private int playerX=310;
	private int ballposX=10;
	private int ballposY=30;
	private int ballXdir=-2;
	private int ballYdir=-2;
	
	private MapGenerator map;
	public Gameplay()
	{
		map=new MapGenerator(F,G);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer=new Timer(delay,this);
		timer.start();
	}
	
	
	public void paint(Graphics g)
	{
		g.setColor(Color.black);
		g.fillRect(1,1,692,592);
		
		
		g.setColor(Color.white);
		g.setFont(new Font("sherif",Font.BOLD,30));
		g.drawString("score:"+score, 500, 550);
		
		map.draw((Graphics2D)g);
		
		
		g.setColor(Color.cyan);
		g.fillRect(0, 0, 3, 592);
		g.fillRect(0, 0, 692, 3);
		g.fillRect(691, 0, 3, 592);
		
		g.setColor(Color.GREEN);
		g.fillRect(playerX, 550, 100, 8);
		
		g.setColor(Color.green);
		g.fillOval(ballposX, ballposY, 20, 20);
		
		
		if(ballposY>570)
		{
			 ballXdir=0;
			 ballYdir=0;
			 playerX=0;
			 F=3;
			 G=7;
              play=false;
			 g.setColor(Color.red);
			 g.setFont(new Font("sherif",Font.BOLD,60));
			 g.drawString("Game Over ", 200, 260);
			 
			 g.setColor(Color.red);
			 g.setFont(new Font("sherif",Font.BOLD,40));
			 g.drawString("Score is : "+score,240 , 300);
			 
			 g.setColor(Color.CYAN);
			 g.setFont(new Font("sherif",Font.BOLD,30));
			 g.drawString("Press 'ENTER' to restart", 190, 450);
		}
		
		if(totalBricks<=0)
		{    
			G=7;
			F=3;
			 ballXdir=0;
			 ballYdir=0;
			 playerX=0;
             play=false;
			 g.setColor(Color.red);
			 g.setFont(new Font("sherif",Font.BOLD,60));
			 g.drawString("You have won ", 200, 260);
			 
			 g.setColor(Color.red);
			 g.setFont(new Font("sherif",Font.BOLD,40));
			 g.drawString("Score is : "+score,240 , 300);
			 
			 g.setColor(Color.CYAN);
			 g.setFont(new Font("sherif",Font.BOLD,30));
			 g.drawString("Press SPACE for next", 190, 450);
		}
		
		
		g.dispose();
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		if(play)
		{
			if(new Rectangle(ballposX,ballposY,20,20).intersects(new Rectangle(playerX,550,100,8)))
			{
				ballYdir = (-ballYdir);
			}
			
			A:for(int i=0;i<map.map.length;i++)
			{
				for(int j=0;j<map.map[0].length;j++)
				{
					if(map.map[i][j]>0)
					{
						int brickX=j*map.brickWidth+80;
						int brickY=i*map.brickHeight+50;
						int brickWidth=map.brickWidth;
						int brickHeight=map.brickHeight;
						
						Rectangle rect=new Rectangle(brickX,brickY,brickWidth,brickHeight);
						Rectangle ballrect=new Rectangle(ballposX,ballposY,20,20);
						Rectangle brickrect=rect;
						
						if(ballrect.intersects(brickrect))
								{
							          map.setBrickValue(0, i,j);
							          totalBricks--;
							          score=score+5;
							          
							          if(ballposX<=brickrect.x||ballposY+1>=brickrect.x+brickrect.y)
							          {
							        	  ballXdir=-ballXdir;
							          }
							          else 
							        	  {ballYdir=-ballYdir;}
							          break A;
								}
						
					}
				}
			}
			
			ballposX +=ballXdir;
			ballposY +=ballYdir;
			
			if(ballposX<0)
			{
				ballXdir = (-ballXdir);
			}
			if(ballposY<0)
			{
				ballYdir = (-ballYdir);
			}
			if(ballposX>670)
			{
				ballXdir = (-ballXdir);
			}

		}
		
		repaint();
	}
	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void keyReleased(KeyEvent e) {}
	
	@Override
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode()==KeyEvent.VK_RIGHT)
		{
			if(playerX>=590)
			{
				playerX=590;
			}
			else 
			{
				moveRight();
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_LEFT)
		{
			if(playerX<=10)
			{
				playerX=10;
			}
			else 
			{
				moveLeft();
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_SPACE)
		{
			if(!play)
			{
				
				play=true;
				F=F+4;
				G=G+4;
				h=F*G;
				totalBricks=h;
				playerX=310;
				ballposX=10;
				ballposY=30;
				ballXdir=-2;
				ballYdir=-2;
				map=new MapGenerator(F,G);
				repaint();
				
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_ENTER)
		{
			if(!play)
			{
				
				play=true;
				F=F;
				G=G;
				h=F*G;
				totalBricks=h;
				playerX=310;
				ballposX=10;
				ballposY=30;
				ballXdir=-2;
				ballYdir=-2;
				map=new MapGenerator(F,G);
				score=0;
				repaint();
				
			}
		}
		
	}
	
	public void moveRight()
	{
		play=true;
		playerX+=40;
	}
	public void moveLeft()
	{
		play=true;
		playerX-=40;
	}
}
