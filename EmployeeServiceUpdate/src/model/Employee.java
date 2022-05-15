
package model;import java.sql.Date;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;public class Employee
{ private PreparedStatement preparedStmt;//A common method to connect to the DB
private Connection connect()
{
Connection con = null;
try
{
Class.forName("com.mysql.jdbc.Driver");
//Provide the correct details: DBServer/DBName, username, password
con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrogrid", "root", "");
}
catch (Exception e)
{e.printStackTrace();}
return con;
}//method to insert employees
public String insertEmployee(String employeename, String employeedob, String employeeaddress, String employeegender, String employeesalary) {
Connection conn = connect();
String Output = "";
try {
if (conn == null) {
return "Error while connecting to the database for inserting";
}
//SQL query
String query = " insert into employee values (?, ?, ?, ?, ?,?)";
PreparedStatement preparedStatement = conn.prepareStatement(query);
//binding data to SQL query
preparedStmt.setInt(1, 0);
preparedStmt.setString(2, employeename);
preparedStmt.setString(3, employeedob);
preparedStmt.setString(4, employeeaddress);
preparedStmt.setString(5, employeegender);
preparedStmt.setString(6, employeesalary);
//execute the SQL statement
preparedStatement.execute();
conn.close(); String newEmployees = readEmployees();
Output = "{\"status\":\"success\", \"data\": \"" + newEmployees + "\"}";
} catch(Exception e) {
Output = "{\"status\":\"error\", \"data\": \"Failed to insert the employee\"}";
System.err.println(e.getMessage());
}
return Output;
}//method to read employee details
public String readEmployees()
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
output = "<table border='1'><tr><th>Employee Name</th>"
+ "<th>Employee DOB</th><th>Employee Address</th>"
+ "<th>Employee Gender</th>"
+ "<th>Employee Salart</th>"
+ "<th>Update</th><th>Remove</th></tr>";
String query = "select * from employees";
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery(query);
// iterate through the rows in the result set
while (rs.next())
{
int employeeid = rs.getInt("employeeid");
String employeename = rs.getString("employeename");
String employeedob = rs.getString("employeedob");
String employeeaddress = rs.getString("employeeaddress");
String employeegender = rs.getString("employeegender");
String employeesalary = rs.getString("employeesalary");
// Add into the html table
output += "<tr style=\"border: 1px solid #ddd; padding: 8px;\"><td style=\"padding-top: 6px; padding-bottom: 6px; text-align: center; color: Violet;\">" + employeeid + "</td>";
output += "<td style=\"padding-top: 6px; padding-bottom: 6px; text-align: center; color: #3B3B3B;\">" + employeename + "</td>";
output += "<td style=\"padding-top: 6px; padding-bottom: 6px; text-align: center; color: #3B3B3B;\">" + employeedob + "</td>";
output += "<td style=\"padding-top: 6px; padding-bottom: 6px; text-align: center; color: #3B3B3B;\">" + employeeaddress + "</td>";
output += "<td style=\"padding-top: 6px; padding-bottom: 6px; text-align: center; color: #3B3B3B;\">" + employeegender + "</td>";
output += "<td style=\"padding-top: 6px; padding-bottom: 6px; text-align: center; color: #3B3B3B;\">" + employeesalary + "</td>";
// buttons
output += "<td><input name='btnUpdate' type='button' value='Update' "
+ "class='btnUpdate btn btn-secondary' data-employeeid='" + employeeid + "'></td>"
+ "<td><input name='btnRemove' type='button' value='Remove' "
+ "class='btnRemove btn btn-danger' data-employeeid='" + employeeid + "'></td></tr>";
}
con.close();
// Complete the html table
output += "</table>";
}
catch (Exception e)
{
output = "Error while reading the employees.";
System.err.println(e.getMessage());
}
return output;
}//method to update employee details
public String updateEmployee(String employeeid, String employeename, String employeedob, String employeeaddress, String employeegender, String employeesalary ) {
Connection conn = connect();
String Output = "";
try {
if (conn == null) {
return "Database connection error";
}
//SQL query
String query = "UPDATE employees SET employeename=?,employeedob=?,employeeaddress=?,employeegender=?,employeesalary=? WHERE employeeid=?";
//binding data to SQL query
PreparedStatement preparedStatement = conn.prepareStatement(query);
preparedStmt.setString(1, employeename);
preparedStmt.setString(2, employeedob);
preparedStmt.setString(3, employeeaddress);
preparedStmt.setString(4, employeegender);
preparedStmt.setString(5, employeesalary);
preparedStmt.setString(6, employeeid);
//execute the SQL statement
preparedStatement.executeUpdate();
conn.close();
String newEmployees = readEmployees();
Output = "{\"status\":\"success\", \"data\": \"" + newEmployees + "\"}";
} catch(Exception e) {
Output = "{\"status\":\"error\", \"data\":\"Failed to update the employee.\"}";
System.err.println(e.getMessage());
}
return Output;
}//method to delete data
public String deleteEmployee(String employeeid) {
String Output = "";
Connection conn = connect();
try {
if (conn == null) {
return "Database connection error";
}
//SQL query
String query = "DELETE FROM employees WHERE employeeid = ?";
//binding data to the SQL query
PreparedStatement preparedStatement = conn.prepareStatement(query);
preparedStatement.setInt(1, Integer.parseInt(employeeid));
//executing the SQL statement
preparedStatement.execute();
conn.close();
String newEmployees = readEmployees();
Output = "{\"status\":\"success\", \"data\": \"" + newEmployees + "\"}";
} catch(Exception e) {
Output = "{\"status\":\"error\", \"data\":\"Failed to delete the employee.\"}";
System.err.println(e.getMessage());
}
return Output;
}
}

