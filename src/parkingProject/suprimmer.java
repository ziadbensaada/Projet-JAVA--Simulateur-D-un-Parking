package parkingProject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class suprimmer {
	
	Connection cnx=null;
	Statement prepared=null;
	ResultSet resultat=null;
	PreparedStatement prs=null;
	
	
	public suprimmer(Car car1) {
		cnx= Conexion_MySql.ConnecrDb();
		//Car car1 = null;
		int id=car1.getId();
		
		String INSERT_USERS_SQL = "DELETE FROM car WHERE id = ?";
		try {
			prs=cnx.prepareStatement(INSERT_USERS_SQL);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			prs.setInt(1,id);    
			prs.execute();	
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	     
	}
	}

