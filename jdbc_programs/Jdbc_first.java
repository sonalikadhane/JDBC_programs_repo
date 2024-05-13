package jdbc_programs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Jdbc_first {
	Scanner sc=new Scanner(System.in);
	public static void main(String[] args) 
	{
		Jdbc_first my=new Jdbc_first();
		Scanner sc=new Scanner(System.in);
		System.out.println("Select your choice");
		
		char ans;
		do
		{
			System.out.println("1:Create table\n2:Insert Record "
					+ "\n3:View Record\n4:Delete Record\n"
					+ "5:search record \n6:Update records");
			int choice=sc.nextInt();
			switch(choice)
			{
			case 1:
				my.create_table();
				break;
			case 2:
				my.insert_rec();
				break;
			case 3:
				my.view_data();
				break;
			case 4:
				my.delete_rec();
				break;
			case 5:
				my.search_data();
				break;
			case 6:
				my.update_data();
				break;
			default:
					System.out.println("Invalid choice");
					break;
			}
			System.out.println("Press Y for repeating the process");
			ans=sc.next().charAt(0);
		}
		while(ans=='y'||ans=='Y');
		
	}
	private void update_data() 
	{
		String url="jdbc:mysql://localhost:3306/stud";
		String username="root";
		String passwrd="root";
		int food_id,food_pri;
		String qur="update my_menu set food_price=? where food_name=?";
		Scanner sc=new Scanner(System.in);
		//System.out.println("Enter food ID");
		//food_id=sc.nextInt();
		System.out.println("Enter the name of food");
		String name=sc.next();
		System.out.println("Enter new Price ");
		food_pri=sc.nextInt();
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");//1
			Connection conn=DriverManager.getConnection(url,username ,passwrd );
			
			PreparedStatement ps=conn.prepareStatement(qur);
			ps.setInt(1,food_pri);
			//ps.setInt(2, food_id);
			ps.setString(2, name);
			int count=ps.executeUpdate();
			if(count>0)
			{
				System.out.println("Data Updates");
			}
			else
			{
				System.out.println("Invalid ID\n First check the Food List");
			}
			conn.close();
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void search_data() 
	{
		String url="jdbc:mysql://localhost:3306/stud";
		String username="root";
		String passwrd="root";
		String qur="select * from menu where food_id=?";
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");//1
			Connection conn=DriverManager.getConnection(url,username ,passwrd );
			PreparedStatement ps=conn.prepareStatement(qur);
			System.out.println("Enter the Food ID");
			ps.setInt(1,sc.nextInt());
			ResultSet rs=ps.executeQuery();
			System.out.println("Food ID\t Food Name\t Food Price");
			while(rs.next())
			{
				System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t\t"+rs.getInt(3));
			}
			
			conn.close();
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void create_table()
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/stud", "root", "root");
			Statement st=conn.createStatement();
			st.execute("create table menu (food_id int not null primary key,food_name varchar(25),food_price int)");
			System.out.println("Table created Succesfully");
			st.close();
			conn.close();
		} 
		catch (Exception e) 
		{
			System.out.println(e.getMessage());
		}
		
	}

	public void insert_rec()
	{
		Scanner sc=new Scanner(System.in);
		int f_id,f_price;
		String f_name;
		System.out.println("Enter food ID");
		f_id=sc.nextInt();
		System.out.println("Enter name");
		f_name=sc.next();
		System.out.println("Enter price");
		f_price=sc.nextInt();
		String url="jdbc:mysql://localhost:3306/stud";
		String username="root";
		String passwrd="root";
		String qur="insert into menu values (?,?,?)";
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn=DriverManager.getConnection(url,username ,passwrd );
			
			PreparedStatement ps=conn.prepareStatement(qur);
			ps.setInt(1, f_id);
			ps.setString(2, f_name);
			ps.setInt(3, f_price);
			
			int cout=ps.executeUpdate();
			if(cout>0)
			{
				System.out.println("Data inseted");
			}
			conn.close();
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void view_data()
	{
		String url="jdbc:mysql://localhost:3306/stud";
		String username="root";
		String passwrd="root";
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");//1
			Connection conn=DriverManager.getConnection(url,username ,passwrd );
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery("select * from menu");
			System.out.println("Food ID\t Food Name\t Food Price");
			while(rs.next())
			{
				System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t\t"+rs.getInt(3));
			}
			
			conn.close();
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void delete_rec()
	{
		String url="jdbc:mysql://localhost:3306/stud";
		String username="root";
		String passwrd="root";
		int food_id;
		String qur="delete from menu where food_id=?";
		Scanner sc=new Scanner(System.in);
		food_id=sc.nextInt();
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");//1
			Connection conn=DriverManager.getConnection(url,username ,passwrd );
			
			PreparedStatement ps=conn.prepareStatement(qur);
			ps.setInt(1,food_id);
			int count=ps.executeUpdate();
			if(count>0)
			{
				System.out.println("Data Deleted");
			}
			else
			{
				System.out.println("Invalid ID\n First check the Food List");
			}
			conn.close();
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

