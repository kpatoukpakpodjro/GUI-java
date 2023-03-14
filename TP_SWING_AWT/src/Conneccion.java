import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

public class Conneccion {
	Connection cn;
	public Conneccion(){
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/parking_db","root","");
	    System.out.println("Connection �tablie!");
		}
		catch(Exception e){
			System.out.println("non connect�e!");
			
		}
		
	}
    public Connection laConnection(){
    	return cn;
    }/*
    Class.forName("oracle.jdbc.drive.OracleDriver");
	cn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","dborcl");

*/}
