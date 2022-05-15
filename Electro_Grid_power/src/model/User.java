package model;

import java.sql.*; 

public class User {

	 //A common method to connect to the DB
		private Connection connect()
		 {
		 Connection con = null;
		 try
		 {
		 Class.forName("com.mysql.jdbc.Driver");

		 //Provide the correct details: DBServer/DBName, username, password
		 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/power", "root", "San17191*");
		 }
		 catch (Exception e)
		 {e.printStackTrace();}
		 return con;
		 }
		
		//insert
		public String insertUser(String name, String address, String account, String phone)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for inserting."; }
		 
		 // create a prepared statement  insert
		 String query = " insert into user(`id`,`name`,`address`,`account`,phone)"
		 + " values (?, ?, ?, ?, ?)";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		
		 // binding values
		 preparedStmt.setInt(1, 0);
		 preparedStmt.setString(2, name);
		 preparedStmt.setString(3, address);
		 preparedStmt.setString(4, account);
		 preparedStmt.setString(5, phone);
		
		 // execute the statement
		 
		 preparedStmt.execute();
		 con.close();
		 output = "Inserted successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while inserting the user.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 } 
		
		public String readUsers()
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for reading."; }
		
		 // Prepare the html table to be displayed
		 output = "<table border='1'><tr><th>User Name</th><th>User Address</th>" +
		 "<th>User Account</th>" +
		 "<th>User Phone</th>" +
		 "<th>Update</th><th>Remove</th></tr>";

		 String query = "select * from user";
		 Statement stmt = con.createStatement();
		 ResultSet rs = stmt.executeQuery(query);
		
		 
		 // iterate through the rows in the result set
		 
		 
		 while (rs.next())
		 {
		 String id = Integer.toString(rs.getInt("id"));
		 String name = rs.getString("name");
		 String address = rs.getString("address");
		 String account = rs.getString("account");
		 String phone= rs.getString("phone");
		
		 // Add into the html table
		 output += "<tr><td>" + name + "</td>";
		 output += "<td>" + address + "</td>";
		 output += "<td>" + account + "</td>";
		 output += "<td>" +phone + "</td>";
		 
		 
		// buttons
		 output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>" + "<td><form method='post' action='items.jsp'>" + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
		 + "<input name='userID' type='hidden' value='" + id
		 + "'>" + "</form></td></tr>";
		 }
		 con.close();
		
		 // Complete the html table
		 output += "</table>";
		 } 
		 
		 catch (Exception e)
		 {
		 output = "Error while reading the items.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		
		
		//update
		
		public String updateUser(String id,String name, String address, String account, String phone)
		{
			 String output = "";
			 try
			 {
			 Connection con = connect();
			 if (con == null)
			 {return "Error while connecting to the database for updating."; }
			
			 // create a prepared statement
			 String query = "UPDATE user SET name=?,address=?,account=?,phone=? WHERE id=?";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 
			 // binding values
			 preparedStmt.setString(1, name);
			 preparedStmt.setString(2, address);
			 preparedStmt.setString(3, account);
			 preparedStmt.setString(4, phone);
			 preparedStmt.setInt(5, Integer.parseInt(id));
			
			 // execute the statement
			 preparedStmt.execute();
			 con.close();
			 output = "Updated successfully";
			 }
			 
			 catch (Exception e)
			 {
			 output = "Error while updating the user.";
			 System.err.println(e.getMessage());
			 }
			 return output;
			 } 

		
		//delete
		public String deleteUser(String id)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for deleting."; }
		 
		 // create a prepared statement
		 String query = "delete from user where id=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(id));
		 
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Deleted successfully";
		 } 
		 
		 catch (Exception e)
		 {
		 output = "Error while deleting the user.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }


	
}
