package parkingProject;


import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
 
 
public class Thread_sem {	
 
    private static class AccesVoiture implements Runnable {
 
       // time to wait after acquiring a "rampe"
       
        TimeUnit unit      = null;
        long     sleepTime = 0;
 
        //-----------------Initial Available spaces
       
	private static int NBR_PLACES = 2;
	private static int NBR_RAMPE  = 1;
	//Cars
	 private Car c;
 
        //------------------- Semaphores
         
	private static Semaphore semPlace = new Semaphore( NBR_PLACES , true );
	private static Semaphore semRampe = new Semaphore( NBR_RAMPE  , true );
 
        public AccesVoiture( long time , TimeUnit unit , Car c) {
            this.unit = unit;
            this.sleepTime = time;
            this.c=c;
            
//------------inserer la npuvelle creation d'entrer dans la base de donnee
            
			inserer inser1=new inserer( ( System.currentTimeMillis() - referenceTime ), c);

        }
 
        /** reference time
         */
        private static final long referenceTime = System.currentTimeMillis();
 
        private String getAccesVoitureDesc() {
        	 	
            return "[" + ( System.currentTimeMillis() - referenceTime ) + "] (Proc : " + Thread.currentThread().getName() + ")";
        }
        //------------------------return date d'dentree--------------------
        
 
	public void run(){
                   System.out.println( getAccesVoitureDesc() + " veut rentrer dans le parking !");
			       //this.c. entrePark();
                    this.entrer_parking(this.c);
            
					System.out.println( getAccesVoitureDesc() + " veut sortir du parking !");
					this.sortir_parking(this.c);
		 
			
	}
 
	public void entrer_parking(Car c) {
		try {
                    semPlace.acquire();
                }
                catch(InterruptedException e) {
                    e.printStackTrace();
                }
		System.out.println( getAccesVoitureDesc()  + " a donné sa carte, ATTENTE");
                try {
                    this.unit.sleep( this.sleepTime );
                	 
                }
                catch(InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    System.out.println( getAccesVoitureDesc() + " a fini son attente");
                }
		try {
			semRampe.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println( getAccesVoitureDesc() + " passe sur la rampe");
		semRampe.release();
		System.out.println( getAccesVoitureDesc() + " est descendu de la rampe et est garde a sa place !");
       c.entrePark();
       
         try {
		this.unit.sleep( this.sleepTime );   // le temps qui va passer la voiture dans le parking
	   } 
         catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	   }
	}
 
	public void sortir_parking(Car c) {
		try {
			semRampe.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println( getAccesVoitureDesc() + " passe sur la rampe (pour sortir)");
		semRampe.release();
		System.out.println( getAccesVoitureDesc() + " est descendu de la rampe (pour sortir)");
		semPlace.release();
		System.out.println( getAccesVoitureDesc() + " est sorti du parking...Place liberee !");
		c.sortiPark();
	}
    }
 
    public static void main(String[] args) {
    	JFrame frame = new JFrame("Parking Simulator");
        Parking panel = new Parking();
        
        
        frame.setContentPane(panel);
        panel.setLayout(null);
        //-----------------------------
//        image =new ImageIcon("src/img/parkingA.jpeg");
//        image.paintIcon(this, g, WIDTH,WIDTH);
//    
//        JPanel j = new JPanelWithBackground(image);
        //---------------------------------
        frame.setSize(870,500);    //app dimentions
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ArrayList<Car> cars=new ArrayList<Car>();
        
     
       
      for(int i=1 ; i<=3 ; i++)
      {   
          Car c = new Car(i); 
          cars.add(c);
          panel.add(c);
       } 
      
    
      frame.setVisible(true);
       
        Thread p1 = new Thread( new AccesVoiture( 10000, TimeUnit.MILLISECONDS,cars.get(0) ),"voiture 1" );
        Thread p2 = new Thread( new AccesVoiture( 20000 , TimeUnit.MILLISECONDS,cars.get(1)),"voiture 2" );
        Thread p3 = new Thread( new AccesVoiture( 30000 , TimeUnit.MILLISECONDS ,cars.get(2) ) ,"voiture 3");
//        Thread p4 = new Thread( new AccesVoiture( 30000 , TimeUnit.MILLISECONDS ,cars.get(3) ) ,"voiture 4");

        p1.start();
        p2.start();
        p3.start();
//        p4.start();
        
 
    }
}
