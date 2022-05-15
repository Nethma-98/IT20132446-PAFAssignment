
package model;import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;//for map
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
/**
* Servlet implementation class EmployeesAPI
*/@WebServlet("/EmployeesAPI")
public class EmployeesAPI extends HttpServlet {
private static final long serialVersionUID = 1L;
Employee employeeObj = new Employee(); //convert request parameters to a map
private static Map<String, String> getParasMap(HttpServletRequest request) {
Map<String, String> map = new HashMap<String, String>(); try {
Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
scanner.close(); String[] params = queryString.split("&"); for (String param : params) {
String[] p = param.split("=");
map.put(p[0], p[1]);
}
} catch (Exception e) { } return map;
}
/**
* @see HttpServlet#HttpServlet()
*/
public EmployeesAPI() {
super();
} /**
* @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
*/
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
response.getWriter().append("Served at: ").append(request.getContextPath());
} /**
* @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
*/
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//sending values to insert function
String output = employeeObj.insertEmployee( request.getParameter("employeename"),
request.getParameter("employeedob"),
request.getParameter("employeeaddress"),
request.getParameter("employeegender"),
request.getParameter("employeesalary"));
//sending the output to client
response.getWriter().write(output);
}
/**
* @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
*/
protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//parameter map
Map<?, ?> paras = getParasMap(request); //getting values from the map and sending to update function
String output = employeeObj.updateEmployee(paras.get("hidEmployeeIDSave").toString(),
paras.get("employeename").toString(),
paras.get("employeedob").toString(),
paras.get("employeeaddress").toString(),
paras.get("employeegender").toString(),
paras.get("employeesalary").toString());
//sending the output to client
response.getWriter().write(output);
}
/**
* @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
*/
protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//parameter map
	Map<?, ?> paras = getParasMap(request); //getting values from the map and sending to delete function
	String output = employeeObj.deleteEmployee( paras.get("employeeid").toString());
	//sending the output to client
	response.getWriter().write(output);
	}}

