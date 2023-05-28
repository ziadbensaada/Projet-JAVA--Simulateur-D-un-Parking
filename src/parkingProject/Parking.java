package parkingProject;

import java.awt.*;
import java.util.concurrent.Semaphore;
import javax.swing.*;


public class Parking extends JPanel {
    
     
	private static final long serialVersionUID = 1L;
	ImageIcon image;
     ImageIcon Car;
     static boolean[] blocEtat ; 
     
     public Parking(){
   
     
         blocEtat =new boolean[4];
         for(int i=0 ; i<blocEtat.length;i++)
         {
               blocEtat[i]=false ; 
         }
         blocEtat[1]=true;
         blocEtat[2]=true;
         
       
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
      //  int WIDTH = 1090;
//        
//        
          image =new ImageIcon("src/img/parkingB.jpeg");
         
          image.paintIcon(this, g, 0,0);
         
            
         
         
    }

    

}