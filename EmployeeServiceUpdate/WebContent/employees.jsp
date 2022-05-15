
<%@page import="model.Employee"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%><!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/employees.js"></script>
</head>
<body>
<div class="container"><div class="row"><div class="col-6">
<h1>Employee Management</h1>
<form id="formEmployee" name="formEmployee">
Employee Name:
<input id="employeename" name="employeename" type="text"
class="form-control form-control-sm">
<br> Employee DOB:
<input id="employeedob" name="employeedob" type="text"
class="form-control form-control-sm">
<br> Employee Address:
<input id="employeeaddress" name="employeeaddress" type="text"
class="form-control form-control-sm">
<br> Employee Gender:
<input id="employeegender" name="employeegender" type="text"
class="form-control form-control-sm">
<br> Employee Salary:
<input id="employeesalary" name="employeesalary" type="text"
class="form-control form-control-sm">
<br>
<input id="btnSave" name="btnSave" type="button" value="Save"
class="btn btn-primary">
<input type="hidden" id="hidEmployeeIDSave"
name="hidEmployeeIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divEmployeesGrid">
<%
Employee employeeObj = new Employee();
out.print(employeeObj.readEmployees());
%>
</div>
</div> </div> </div>
</body>
</html>

