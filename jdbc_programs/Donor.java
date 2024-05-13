/*) Write a program to display information about all columns in the DONAR table using 
ResultSetMetaData.*/

package jdbc_programs;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class Donor {

	public static void main(String[] args) {
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/donor","root","root" );
		
		Statement st=null;
		st=conn.createStatement();
		ResultSet rs=st.executeQuery("select * from donor");
		ResultSetMetaData rsmd=rs.getMetaData();
		int count=rsmd.getColumnCount();
		System.out.println("number of columns:" +rsmd.getColumnCount());
		for(int i=1;i<=count;i++) {
			System.out.println("column no:" +i);
			System.out.println("column name:" +rsmd.getColumnName(i));
			System.out.println("column type:" +rsmd.getColumnTypeName(i));
			System.out.println("column display size:" +rsmd.getColumnDisplaySize(i));
			System.out.println();
		}
		//close connection
		rs.close();
		st.close();
		conn.close();
		
		}catch(Exception e) {
			System.out.println(e);
		}

	}

}
