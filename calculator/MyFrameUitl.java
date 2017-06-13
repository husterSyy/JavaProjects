package calculator;

import java.awt.*;

import javax.swing.*;

public class MyFrameUitl {
	public static void init(JFrame jFrame,Dimension frameSize,String title,
			String iconFileName,boolean resizable){
		Toolkit tk=Toolkit.getDefaultToolkit();
		Dimension screenSize=tk.getScreenSize(); //获取屏幕大小
		 int screenWidth=screenSize.width;
		 int screenHeight=screenSize.height;
		 int centerX=screenWidth/2;
		 int centerY=screenHeight/2;
		 int frameWidth=frameSize.width;
		 int frameHeight=frameSize.height;
		 
		 jFrame.setBounds(centerX-frameWidth/2,centerY-frameHeight/2,frameWidth,frameHeight);
		 jFrame.setTitle(title);
		 if(iconFileName!=null){
			 jFrame.setIconImage(tk.getImage(iconFileName));
		 }
		 try{
			 UIManager
			 .setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			 SwingUtilities.updateComponentTreeUI(jFrame);
		 }catch(Exception el){
			 el.printStackTrace();
		 }
		 jFrame.setResizable(resizable);
		 jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 jFrame.setVisible(true);
		 
	}
	
}
