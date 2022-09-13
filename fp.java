import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.applet.*;

import java.io.File;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.SourceDataLine;
import java.applet.AudioClip;
import java.io.*;
import java.applet.Applet;
import java.net.MalformedURLException;
import java.net.URL;

public class fp extends JFrame implements ActionListener,MouseMotionListener,MouseListener
{
	static fp app = new fp();
	static Container c;
	static JPanel p;
	static JButton bot;
	static java.util.Random rg;
	static int x1, y1, x2, y2, x3, y3, x4, y4, x5, y5, x6, y6, x7, y7, x8, y8, x9, y9;
	static int x10, y10, x11, y11, x12, y12, x13, y13, x14, y14, x15, y15;
	static int mode, ms, lose, buffer;
	//static double mt;
	static javax.swing.Timer t;
	
	static Image background;
	static Image hole;
	static Image diglett;
	static Image hammer_result;
	static Image hammer_stay;
	static Image hammer_hit;
	static Image hammer_hit_it;
	static Image background_hard;
	int location_x, location_y;
	int level;
	static MenuBar mb=new MenuBar();             
	static Menu menu1=new Menu("Game");
	static Menu menu2=new Menu("Level");
	static MenuItem mi1=new MenuItem("Start");
	static MenuItem mi2=new MenuItem("Simple");
	static MenuItem mi3=new MenuItem("Hard");
	
	static boolean flag = true;
	static void playMusic() {
    	try {
    		AudioInputStream ais = AudioSystem.getAudioInputStream(new File("bongos.wav"));    
    		AudioFormat aif = ais.getFormat();
    		final SourceDataLine sdl;
    		DataLine.Info info = new DataLine.Info(SourceDataLine.class, aif);
    		sdl = (SourceDataLine) AudioSystem.getLine(info);
    		sdl.open(aif);
    		sdl.start();
    		FloatControl fc = (FloatControl) sdl.getControl(FloatControl.Type.MASTER_GAIN);
    		double value = 1;
    		float dB = (float) (Math.log(value == 0.0 ? 0.0001 : value) / Math.log(10.0) * 20.0);
    		fc.setValue(dB);
    		int nByte = 0;
    		final int SIZE = 1024 * 64;
    		byte[] buffer = new byte[SIZE];
    		while (nByte != -1) {
    			//nByte = ais.read(buffer, 0, SIZE);
    			//sdl.write(buffer, 0, nByte);
				if(flag) {
					
					nByte = ais.read(buffer, 0, SIZE);
					
					sdl.write(buffer, 0, nByte);
					
				}else {
					
					nByte = ais.read(buffer, 0, 0);
					
				}

    		}
    		sdl.stop();
 
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
        }
	
	static void playMusic2() {
    	try {
    		AudioInputStream ais = AudioSystem.getAudioInputStream(new File("thwap.wav"));    
    		AudioFormat aif = ais.getFormat();
    		final SourceDataLine sdl;
    		DataLine.Info info = new DataLine.Info(SourceDataLine.class, aif);
    		sdl = (SourceDataLine) AudioSystem.getLine(info);
    		sdl.open(aif);
    		sdl.start();
    		FloatControl fc = (FloatControl) sdl.getControl(FloatControl.Type.MASTER_GAIN);
    		double value = 1;
    		float dB = (float) (Math.log(value == 0.0 ? 0.0001 : value) / Math.log(10.0) * 20.0);
    		fc.setValue(dB);
    		int nByte = 0;
    		final int SIZE = 1024 * 64;
    		byte[] buffer = new byte[SIZE];
    		while (nByte != -1) {
    			nByte = ais.read(buffer, 0, SIZE);
    			sdl.write(buffer, 0, nByte);
    		}
    		sdl.stop();
 
    	} catch (Exception e) {
    		e.printStackTrace();
		}
	}
	
	static void playMusic3() {
    	try {
    		AudioInputStream ais = AudioSystem.getAudioInputStream(new File("fart.wav"));    
    		AudioFormat aif = ais.getFormat();
    		final SourceDataLine sdl;
    		DataLine.Info info = new DataLine.Info(SourceDataLine.class, aif);
    		sdl = (SourceDataLine) AudioSystem.getLine(info);
    		sdl.open(aif);
    		sdl.start();
    		FloatControl fc = (FloatControl) sdl.getControl(FloatControl.Type.MASTER_GAIN);
    		double value = 1;
    		float dB = (float) (Math.log(value == 0.0 ? 0.0001 : value) / Math.log(10.0) * 20.0);
    		fc.setValue(dB);
    		int nByte = 0;
    		final int SIZE = 1024 * 64;
    		byte[] buffer = new byte[SIZE];
    		while (nByte != -1) {
    			nByte = ais.read(buffer, 0, SIZE);
    			sdl.write(buffer, 0, nByte);
    		}
    		sdl.stop();
 
    	} catch (Exception e) {
    		e.printStackTrace();
		}
	}
	

	public static void main(String args[]) 
	{
		app.setTitle("Game");
		
		background = Toolkit.getDefaultToolkit().getImage("background_final.png");
		hole = Toolkit.getDefaultToolkit().getImage("hole.png");
		diglett = Toolkit.getDefaultToolkit().getImage("diglett.png");
		hammer_stay = Toolkit.getDefaultToolkit().getImage("hammer2.png");
		hammer_hit = Toolkit.getDefaultToolkit().getImage("hammer.png");
		hammer_hit_it = Toolkit.getDefaultToolkit().getImage("hammer3.png");
		background_hard = Toolkit.getDefaultToolkit().getImage("background_hard.png");
		
		x1 = 150; x2 = 250; x3 = 350; x4 = 150; x5 = 250; x6 = 350; x7 = 150; x8 = 250; x9 = 350;
		y1 = 100; y2 = 100; y3 = 100; y4 = 200; y5 = 200; y6 = 200; y7 = 300; y8 = 300; y9 = 300;
		x10 = 50; x11 = 450; x12 = 50; x13 = 450; x14 = 50; x15 = 450;
		y10 = 100; y11 = 100; y12 = 200; y13 = 200; y14 = 300; y15 = 300;

		mode = 0;
		lose = 0;
		rg = new Random();

		/*p = new JPanel();
		bot = new JButton("Start");
		p.add(bot);
		app.add(p);

		Color mycolor = new Color(255, 250, 240);
		bot.setBackground(mycolor);
		mycolor = new Color(255, 222, 173);
		bot.setForeground(mycolor);
		
		bot.addActionListener(app);*/

		app.addMouseMotionListener(app);
		app.addMouseListener(app);

		t = new javax.swing.Timer(900, app);
		  
		app.setSize(600, 450);
		app.setVisible(true);
		
		mb.add(menu1);
		mb.add(menu2);
		menu1.add(mi1);
		menu2.add(mi2);
		menu2.add(mi3);
		mi1.addActionListener(app);   
		mi2.addActionListener(app);  
		mi3.addActionListener(app); 		
		
		app.setMenuBar(mb);
		
		
		app.addWindowListener(new WindowAdapter(){
		public void windowClosing(WindowEvent e)
		{
			flag = false;
			System.exit(0);
		}
		}); 
		while(flag && lose != 3)
			playMusic();
	}
	
	public void update(Graphics g)
	{
		paint(g);
	}

	public void paint(Graphics g)
	{
		super.paint(g);
		if(level == 0)
			g.drawImage(background, 12, 47, this);
		else if(level == 1)
			g.drawImage(background_hard, 12, 47, this);
		Color mycolor = new Color(255, 250, 240);
		g.setColor(mycolor);
		g.setColor(Color.black);
		Font f6 = new Font("Dialog",Font.BOLD,20);
		g.setFont(f6);
		g.drawString("Score: " + ms, 45, 380);
	
		if(mode == 1)
			g.drawImage(diglett, x1, y1, 100, 47, this);
		else if(mode == 2)
			g.drawImage(diglett, x2, y2, 100, 47, this);
		else if(mode == 3)
			g.drawImage(diglett, x3, y3, 100, 47, this);
		else if(mode == 4)
			g.drawImage(diglett, x4, y4, 100, 47, this);
		else if(mode == 5)
			g.drawImage(diglett, x5, y5, 100, 47, this);
		else if(mode == 6)
			g.drawImage(diglett, x6, y6, 100, 47, this);
		else if(mode == 7)
			g.drawImage(diglett, x7, y7, 100, 47, this);
		else if(mode == 8)
			g.drawImage(diglett, x8, y8, 100, 47, this);
		else if(mode == 9)
			g.drawImage(diglett, x9, y9, 100, 47, this);
		else if(mode == 10)
			g.drawImage(diglett, x10, y10, 100, 47, this);
		else if(mode == 11)
			g.drawImage(diglett, x11, y11, 100, 47, this);
		else if(mode == 12)
			g.drawImage(diglett, x12, y12, 100, 47, this);
		else if(mode == 13)
			g.drawImage(diglett, x13, y13, 100, 47, this);
		else if(mode == 14)
			g.drawImage(diglett, x14, y14, 100, 47, this);
		else if(mode == 15)
			g.drawImage(diglett, x15, y15, 100, 47, this);
		

		mycolor = new Color(255, 0, 0);
		g.setColor(mycolor);
		if(lose == 0)
		{
			g.fillOval(30, 80, 20, 20);
			g.fillOval(55, 80, 20, 20);
			g.fillOval(80, 80, 20, 20);
		}
		if(lose == 1)
		{
			g.fillOval(30, 80, 20, 20);
			g.fillOval(55, 80, 20, 20);
		}
		if(lose == 2)
		{
			g.fillOval(30, 80, 20, 20);
		}
		g.drawImage(hammer_result, location_x - 40, location_y - 50, 100, 100, app);
	}
	public void mouseDragged(MouseEvent xxx) { };
	public void mouseMoved(MouseEvent e){ 
		location_x = e.getX();
		location_y = e.getY();
		//repaint();
	};

	public void actionPerformed(ActionEvent e)
	{
		if(lose == 3) //3條命都用完時會跳出的視窗
		{
			t.stop();
			//app.dispose();
			Object[] options = { "OKAY" }; 
			int res = JOptionPane.showOptionDialog(null, "YOU LOSE!", "YOU LOSE!",  JOptionPane.DEFAULT_OPTION, 
			JOptionPane.WARNING_MESSAGE,  null, options, options[0]);
			if(res == JOptionPane.DEFAULT_OPTION)
			{ 
				flag = false;
				app.dispose();
			}
			app.dispose();
		}

		if(e.getSource() == t){
			//mt = mt - 1;
			if(level == 0)  //簡單版冒出隨機地鼠
				mode = rg.nextInt(9) + 1;
			else if(level == 1)  //困難版冒出隨機地鼠
				mode = rg.nextInt(15) + 1;
			repaint();
		}
		else{  
			MenuItem mi=(MenuItem) e.getSource(); 
			if(mi == mi1){  //遊戲開始
				//mt = 60;
				ms = 0;
				t.start();
			}                      
			else if(mi == mi2){  //選簡單版
				level = 0;
				//mt = mt - 1;
				mode = rg.nextInt(9) + 1;
				repaint();
			}
			else if(mi == mi3){  //選困難版
				level = 1;
				//mt = mt - 1;
				mode = rg.nextInt(15) + 1;
				repaint();
			}     
		}
	}
	public void mouseEntered(MouseEvent e){
		hammer_result = hammer_stay;
	};
	public void mouseExited(MouseEvent e){ };
	public void mousePressed(MouseEvent e)
	{
		int mx, my, buffer;
		buffer = ms;
		mx = e.getX(); my = e.getY();
		if (mode == 1)
		{
			if (mx > x1 && mx < x1 + 75 && my > y1 && my < y1 + 75)
			{
				ms = ms + 1;
				hammer_result = hammer_hit_it;
				mode = 0;
				repaint();
				playMusic2();
				
			}
			else
			{
				hammer_result = hammer_hit;
				repaint();
				playMusic3();
			}
				
		}
		else if (mode == 2)
		{
			if (mx > x2 && mx < x2 + 75 && my > y2 && my < y2 + 75)
			{
				ms = ms + 1;
				hammer_result = hammer_hit_it;
				mode = 0;
				repaint();
				playMusic2();
				
			}
			else
			{
				hammer_result = hammer_hit;
				repaint();
				playMusic3();
			}
		}
		else if (mode == 3)
		{
			if (mx > x3 && mx < x3 + 75 && my > y3 && my < y3 + 75)
			{
				ms = ms + 1;
				hammer_result = hammer_hit_it;
				mode = 0;
				repaint();
				playMusic2();
				
			}
			else
			{
				hammer_result = hammer_hit;
				repaint();
				playMusic3();
			}
		}
		else if (mode == 4)
		{
			if (mx > x4 && mx < x4 + 75 && my > y4 && my < y4 + 75)
			{
				ms = ms + 1;
				hammer_result = hammer_hit_it;
				mode = 0;
				repaint();
				playMusic2();
				
			}
			else
			{
				hammer_result = hammer_hit;
				repaint();
				playMusic3();
			}
		}
		else if (mode == 5)
		{
			if (mx > x5 && mx < x5 + 75 && my > y5 && my < y5 + 75)
			{
				ms = ms + 1;
				hammer_result = hammer_hit_it;
				mode = 0;
				repaint();
				playMusic2();
				
			}
			else
			{
				hammer_result = hammer_hit;
				repaint();
				playMusic3();
			}
		}
		else if (mode == 6)
		{
			if (mx > x6 && mx < x6 + 75 && my > y6 && my < y6 + 75)
			{
				ms = ms + 1;
				hammer_result = hammer_hit_it;
				mode = 0;
				repaint();
				playMusic2();
				
			}
			else
			{
				hammer_result = hammer_hit;
				repaint();
				playMusic3();
			}
		}
		else if (mode == 7)
		{
			if (mx > x7 && mx < x7 + 75 && my > y7 && my < y7 + 75)
			{
				ms = ms + 1;
				hammer_result = hammer_hit_it;
				mode = 0;
				repaint();
				playMusic2();
				
			}
			else
			{
				hammer_result = hammer_hit;
				repaint();
				playMusic3();
			}
		}
		else if (mode == 8)
		{
			if (mx > x8 && mx < x8 + 75 && my > y8 && my < y8 + 75)
			{
				ms = ms + 1;
				hammer_result = hammer_hit_it;
				mode = 0;
				repaint();
				playMusic2();
				
			}
			else
			{
				hammer_result = hammer_hit;
				repaint();
				playMusic3();
			}
		}
		else if (mode == 9)
		{
			if (mx > x9 && mx < x9 + 75 && my > y9 && my < y9 + 75)
			{
				ms = ms + 1;
				hammer_result = hammer_hit_it;
				mode = 0;
				repaint();
				playMusic2();
				
			}
			else
			{
				hammer_result = hammer_hit;
				repaint();
				playMusic3();
			}
		}
		else if(level==1){
			if (mode == 10)
			{
			if (mx > x9 && mx < x9 + 75 && my > y9 && my < y9 + 75)
			{
				ms = ms + 1;
				hammer_result = hammer_hit_it;
				mode = 0;
				repaint();
				playMusic2();
			}
			else{
				hammer_result = hammer_hit;
				repaint();
				playMusic3();
			}
		}
		if (mode == 11)
			{
			if (mx > x11 && mx < x11 + 75 && my > y11 && my < y11 + 75)
			{
				ms = ms + 1;
				hammer_result = hammer_hit_it;
				mode = 0;
				repaint();
				playMusic2();
			}
			else{
				hammer_result = hammer_hit;
				repaint();
				playMusic3();
			}
		}
		if (mode == 12)
			{
			if (mx > x12 && mx < x12 + 75 && my > y12 && my < y12 + 75)
			{
				ms = ms + 1;
				hammer_result = hammer_hit_it;
				mode = 0;
				repaint();
				playMusic2();
			}
			else{
				hammer_result = hammer_hit;
				repaint();
				playMusic3();
			}
		}
		if (mode == 13)
			{
			if (mx > x13 && mx < x13 + 75 && my > y13 && my < y13 + 75)
			{
				ms = ms + 1;
				hammer_result = hammer_hit_it;
				mode = 0;
				repaint();
				playMusic2();
			}
			else{
				hammer_result = hammer_hit;
				repaint();
				playMusic3();
			}
		}
		if (mode == 14)
			{
			if (mx > x14 && mx < x14 + 75 && my > y14 && my < y14 + 75)
			{
				ms = ms + 1;
				hammer_result = hammer_hit_it;
				mode = 0;
				repaint();
				playMusic2();
			}
			else{
				hammer_result = hammer_hit;
				repaint();
				playMusic3();
			}
		}if (mode == 15)
			{
			if (mx > x15 && mx < x15 + 75 && my > y15 && my < y15 + 75)
			{
				ms = ms + 1;
				hammer_result = hammer_hit_it;
				mode = 0;
				repaint();
				playMusic2();
				
			}
			else{
				hammer_result = hammer_hit;
				repaint();
				playMusic3();
				
			}
		}
		}

		if(ms == buffer)
				lose++;

	};

	public void mouseReleased(MouseEvent e){
		hammer_result = hammer_stay;
		repaint();
	};
	public void mouseClicked(MouseEvent e){ };
}