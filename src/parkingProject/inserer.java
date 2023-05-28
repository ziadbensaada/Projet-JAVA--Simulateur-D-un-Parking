package parkingProject;
import java.sql.*;
import java.util.Date;
public class inserer {

	
	Connection cnx=null;
	Statement prepared=null;
	ResultSet resultat=null;
	PreparedStatement prs=null;
	
	public inserer(long date,Car car1) {
			cnx= Conexion_MySql.ConnecrDb();
						
					String INSERT_USERS_SQL = "INSERT INTO car (enter,car_id) VALUES (?, ?)";
					try {
						prs=cnx.prepareStatement(INSERT_USERS_SQL);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try { 
						prs.setLong (1,date);                  //date entree
						prs.setInt (2,car1.getId());             //date sortie
						prs.execute();	
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				     
				}
			};
				

