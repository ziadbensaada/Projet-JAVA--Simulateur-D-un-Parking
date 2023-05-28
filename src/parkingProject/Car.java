package parkingProject;


import java.awt.Dimension;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class Car extends JLabel {
   
    ImageIcon car ;
    int id ;
    int x;
    int y;
    static boolean[]  state ; 
    static int[] postionExitCar ;  
    static boolean notExit=false ;
    
       public Car(int id)
    {
        this.id=id;
        this.x=-200 ;
        this.y=300 ;
    
        car=new ImageIcon("src/img/car"+this.id+".png");
        this.setIcon(car);
        Dimension size = this.getPreferredSize();

        setBounds(x, y, size.width, size.height);
        state=new boolean[4];
        postionExitCar=new int[4];
        for(int i=0 ; i<state.length;i++)
         {
               state[i]=false ; 
         }
        for(int i=0 ; i<postionExitCar.length;i++)
         {
               postionExitCar[i]=-300 ; 
         }
    }
       public int getId() {
    	   return this.id;
       }
     
 
   public void setIconParking(int i)
   {
       car=new ImageIcon("src/img/car"+i+"_"+i+".png");

       this.setIcon(car);
       Dimension size = this.getPreferredSize();
       setBounds(x, y, size.width, size.height); // resize car
       
   }
    public void setIconExitParking(int i)
   {
        car=new ImageIcon("src/img/car"+this.id+".png");
        this.setIcon(car);
        Dimension size = this.getPreferredSize();
        setBounds(x, y, size.width, size.height);
   }
    
    
     public void  entrePark()
    {
        
      
        int[] c=new int[4] ;
        
        for(int i =0 ; i<c.length;i++) // initialise to empty
        {
            c[i]=0;
        }
        
        for(int i=0 ; i<4 ; i++) // check if blocs is empty
        {
            if(!Parking.blocEtat[i])
            {
               c[i]=1;
            }
   
        }
                                   
        int b=0 ;                  // le nombre de block vide
         for(int i=0 ; i<4 ; i++) 
        {
            if(c[i]==1)
            {
               b++;
            }
   
        }

        if(b==0) {                  // le nombre de bloc vide    
            for(int i=0 ; i<state.length;i++)
         {
            if(state[i])
                {
                    try { 
                    Thread.sleep(100);  
                } catch (InterruptedException ex) {
                    Logger.getLogger(Car.class.getName()).log(Level.SEVERE, null, ex);
                }
              
               }
         }
            
            moveIN(this,900);
        } 
      
        if(b==1){ 
                      if(c[0]==1){
             
                 moveIN(this,  280);
                  parking(this);    	  
                Parking.blocEtat[0]=true;
            }
          
            else if(c[3]==1) 
            {
                 moveIN(this,480);
                 parking(this);
                 Parking.blocEtat[3]=true;
            }           
        }
        
       
        if(b==2){         
            int x= (int)(Math.random() * 2) + 1 ;
          
            if(c[0]==1 && c[3]==1) // 1 and 4
            {
             
                 if(x==1)
                {
             
                moveIN(this, 280);
                parking(this);
                Parking.blocEtat[0]=true; 
                }
                else {
            
               moveIN(this, 480);
                parking(this);
                Parking.blocEtat[3]=true;
                }
            }
        }    }
     public void sortiPark()
    {
          
           if(this.x<=300) // bloc 1
           {   
              state[0]=true ;
              postionExitCar[0]=this.x;
              exitParking(this);
              state[0]=false ;
              postionExitCar[0]=-300;
              Parking.blocEtat[0]=false;
              moveOut(this);
             
           }
        
             if(x>=500 && x<=900) // bloc 4
           {  
              state[3]=true ;
              postionExitCar[3]=this.x;
              exitParking(this);
              state[3]=false ;
              postionExitCar[3]=-300;
              Parking.blocEtat[3]=false;
              moveOut(this);    
              
           }
             
           
             
     }

 
     
     
     public static void moveIN(Car c , int xMax)
     {
         
                while(c.x<=xMax)
             {
            
               
                 for(int i =0 ; i<1 ; i++){
                   if(Car.state[i]==true &&  c.x<=Car.postionExitCar[i]) // car is moving out from bloc 
                 {
                     try { 
                     Thread.sleep(50);  
                 } catch (InterruptedException ex) {
                     Logger.getLogger(Car.class.getName()).log(Level.SEVERE, null, ex);
                 }
               }

                   
                }
                 
                  c.x+=10;
                  c.setLocation(c.x, c.y);
                 try { 
                     Thread.sleep(50);  
                 } catch (InterruptedException ex) {
                     Logger.getLogger(Car.class.getName()).log(Level.SEVERE, null, ex);
                 }
   
              }
     
             notExit=false;
         
     }
     public static void parking(Car c)
     {
              while(c.y>=30)
             {  
             c.setIconParking(c.id);
             c.y-=10;
             c.setLocation(c.x, c.y);
              try { 
                     Thread.sleep(100);  
                 } catch (InterruptedException ex) {
                     Logger.getLogger(Car.class.getName()).log(Level.SEVERE, null, ex);
                 }

             }
              
              notExit=false;

   
     }
     public static void exitParking(Car c  )
     {
         
      
         while(notExit)
         {
             ;
         }
         
         while(c.y<=140)
            {
                c.y+=10;
               c.setLocation(c.x, c.y);
              try { 
                     Thread.sleep(100);  
                 } catch (InterruptedException ex) {
                     Logger.getLogger(Car.class.getName()).log(Level.SEVERE, null, ex);
                 }
              
            }
         

         
     }
     
      public static void moveOut(Car c)
     {
                while(c.x<=900)
             {  
                	  c.y=300;
                  c.setIconExitParking(c.id);
                  c.x+=10;
                  c.setLocation(c.x, c.y);
                 try { 
                     Thread.sleep(100);  
                 } catch (InterruptedException ex) {
                     Logger.getLogger(Car.class.getName()).log(Level.SEVERE, null, ex);
                 }
   
              }
                notExit=false;
     
               c.x=-200;
               c.y=300;
         
     }
     


}