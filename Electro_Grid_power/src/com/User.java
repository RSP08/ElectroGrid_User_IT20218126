package com;

import java.sql.*;

public class User {
	
	private Connection connect()
	 {
	 Connection con = null;
	 try
	 {
	 Class.forName("com.mysql.jdbc.Driver");
	 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/power", "root", "San17191*");
	 }
	 catch (Exception e)
	 {
	 e.printStackTrace();
	 }
	 return con;
	 }
	public String readUsers()
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {
	 return "Error while connecting to the database for reading.";
	 }
	 
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>name</th><th>address</th><th>account</th>"+ "<th>phone</th><th>Update</th><th>Remove</th></tr>";
	 String query = "select * from users";
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 
	 // iterate through the rows in the result set
	 while (rs.next())
	 {
	 String id = Integer.toString(rs.getInt("id"));
	 String name = rs.getString("name"); 
	 String address = rs.getString("address");
	 String account = rs.getString("account"); 
	 String phone = rs.getString("phone");
	
	 // Add into the html table
	output += "<tr><td><input id='hidUserIDUpdate' name='hidUserIDUpdate' type='hidden' value='" + id+ "'>" + name + "</td>";
	 output += "<td>" + address + "</td>";
	 output += "<td>" + account + "</td>";
	 output += "<td>" + phone + "</td>";
	
	 // buttons
	output += "<td><input name='btnUpdate'type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"+ "<td><input name='btnRemove' type='button' value='Remove'class='btnRemove btn btn-danger' data-id='"+ id + "'>" + "</td></tr>";
	 }
	 con.close();
	 
	 // Complete the html table
	 output += "</table>";
	 }
	 catch (Exception e)
	 {
	 output = "Error while reading the userss.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	public String insertUser(String name, String address,String account, String phone)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {
	 return "Error while connecting to the database for inserting.";
	 }
	 
	 // create a prepared statement
	 String query = " insert into users(`id`,`name`,`address`,`account`,`phone`)" + " values (?, ?, ?, ?, ?)";
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
	 String newUsers = readUsers();
	 output = "{\"status\":\"success\", \"data\": \"" +newUsers + "\"}";
	 }
	 catch (Exception e)
	 {
	 output = "{\"status\":\"error\", \"data\": \"Error while inserting the user.\"}";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	public String updateUser(String id, String name, String address,String account, String phone)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {
	 return "Error while connecting to the database for updating.";
	 }
	
	 // create a prepared statement
	 String query = "UPDATE users SET name=?,address=?,account=?,phone=? WHERE id=?";
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
	 String newUsers = readUsers();
	 output = "{\"status\":\"success\", \"data\": \"" + newUsers + "\"}";
	 }
	 catch (Exception e)
	 {
	 output = "{\"status\":\"error\", \"data\":\"Error while updating the item.\"}";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	public String deleteUser(String id)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {
	 return "Error while connecting to the database for deleting.";
	 }
	 
	 // create a prepared statement
	 String query = "delete from users where id=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(id));
	 
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 String newUsers = readUsers();
	 output = "{\"status\":\"success\", \"data\": \"" + newUsers + "\"}";
	 }
	 catch (Exception e)
	 {
	 output = "{\"status\":\"error\", \"data\":\"Error while deleting the user.\"}";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }

}
