package parkingProject;
import javax.swing.*;
import java.sql.*;

public class Conexion_MySql {
	static Connection conn=null;
public static Connection ConnecrDb() {
	try {
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/parkingprojet","root","");
	}
	catch(Exception e){
		JOptionPane.showInternalMessageDialog(null, e);
		return null;
	}
	return conn;
	
}
	}
