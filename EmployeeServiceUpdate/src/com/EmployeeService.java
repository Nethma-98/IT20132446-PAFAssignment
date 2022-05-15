
package com;import java.sql.Date;//For REST Service
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;//For JSON
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;//For XML
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;import model.Employee;//Insert method
@POST
@Path("/")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED) //Admin sending details
@Produces(MediaType.TEXT_PLAIN) //Client getting back details
public String insertEmployee(@FormParam("employeeid") String employeeid,
@FormParam("employeename") String employeename,
@FormParam("employeedob") String employeedob,
@FormParam("employeeaddress") String employeeaddress,
@FormParam("employeegender") String employeegender,
@FormParam("employeesalary") String employeesalary)
{
//Execute output
String output = employeeObj.insertEmployee(employeename, employeedob, employeeaddress, employeegender, employeesalary);
return output;
}//Read method
@GET
@Path("/")
@Produces(MediaType.TEXT_HTML)
public String readEmployees()
{
return employeeObj.readEmployees();
}//Update method
@PUT
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.TEXT_PLAIN)
public String updateEmployee(String employeeData)
{
//Convert the input string to a JSON object
JsonObject employeeObject = new JsonParser().parse(employeeData).getAsJsonObject();
//Read the values from the JSON object
int employeeid = employeeObject.get("employeeid").getAsInt();
String employeename = employeeObject.get("employeename").getAsString();
String employeedob = employeeObject.get("employeedob").getAsString();
String employeeaddress = employeeObject.get("employeeaddress").getAsString();
String employeegender = employeeObject.get("employeegender").getAsString();
String employeesalary = employeeObject.get("employeesalary").getAsString();//Execute output
String output = employeeObj.updateEmployee(employeeid, employeename, employeedob, employeeaddress, employeegender, employeesalary);
return output;
//Delete method
@DELETE
@Path("/")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.TEXT_PLAIN)
public String deleteEmployee(String employeeData)
{
//Convert the input string to an XML document
Document doc = Jsoup.parse(employeeData, "", Parser.xmlParser());
//Read the value from the element <employeeid>
String employeeid = doc.select("employeeid").text();
String output = employeeObj.deleteEmployee(employeeid);
return output;
}
}

