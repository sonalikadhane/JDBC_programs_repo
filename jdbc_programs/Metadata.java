/*Write a program to display information about the database and list all the tables in the 
database. (Use DatabaseMetaData).*/


package jdbc_programs;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class Metadata {

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/driver","root","root" );
			
			DatabaseMetaData db=conn.getMetaData();
			
			System.out.println("Driver Name="+db.getDriverName());
			System.out.println("Driver Version="+db.getDriverVersion());
			System.out.println("User Name="+db.getUserName());
			System.out.println("Dd Product Name="+db.getDatabaseProductName());
			System.out.println("Dd Product version="+db.getDatabaseProductVersion());
			System.out.println(".............................");
			
			String table[]= {"TABLE"};
			ResultSet rs=db.getTables(null,null,null,table);
			
			System.out.println("\t\t Table name");
			while(rs.next()) {
				System.out.println(rs.getString("TABLE_NAME"));
				
			}
			rs.close();
			conn.close();
			}catch(Exception e) {
				System.out.println(e);
			}
	}

}
