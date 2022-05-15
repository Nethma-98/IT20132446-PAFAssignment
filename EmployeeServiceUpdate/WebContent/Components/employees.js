$(document).ready(function() {
if ($('#alertSuccess').text().trim() == "") {
$('#alertSuccess').hide();
}



$('#alertError').hide();
})



// SAVE
$(document).on("click","#btnSave", function(event) {
// Clear alerts
$("#alertSuccess").text("");
$("#alertSuccess").hide();
$("#alertError").text("");
$("#alertError").hide();



// Form validation
var status = validateEmployeeForm();
if (status != true)
{
$("#alertError").text(status);
$("#alertError").show();
return;
}

// if hidEmployeeIDSave value is null set as POST else set as PUT
var type = ($("#hidEmployeeIDSave").val() == "") ? "POST" : "PUT";



// ajax communication
$.ajax({
url: "EmployeesAPI",
type: type,
data: $("#formEmployee").serialize(),
dataType: "text",
complete: function(response, status) {
onItemSaveComplete(response.responseText, status);
}
});
});



//after completing save request
function onEmployeeSaveComplete(response, status) {



if (status == "success") { //if the response status is success
var resultSet = JSON.parse(response);



if (resultSet.status.trim() === "success") { //if the json status is success
//display success alert
$("#alertSuccess").text("Successfully saved");
$("#alertSuccess").show();

//load data in json to html
$("#divEmployeesGrid").html(resultSet.data);



} else if (resultSet.status.trim() === "error") { //if the json status is error
//display error alert
$("#alertError").text(resultSet.data);
$("#alertError").show();
}
} else if (status == "error") {
//if the response status is error
$("#alertError").text("Error while saving");
$("#alertError").show();
} else {
//if an unknown error occurred
$("#alertError").text("Unknown error occurred while saving");
$("#alertError").show();
}



//resetting the form
$("#hidEmployeeIDSave").val("");
$("#formEmployee")[0].reset();
}



//UPDATE
//to identify the update button we didn't use an id we used a class
$(document).on("click", ".btnUpdate", function(event)
{
//get employee id from the data-employeeid attribute in update button
$("#hidEmployeeIDSave").val($(this).data('employeeid'));
//get data from <td> element
$("#employeename").val($(this).closest("tr").find('td:eq(0)').text());
$("#employeedob").val($(this).closest("tr").find('td:eq(1)').text());
$("#employeeaddress").val($(this).closest("tr").find('td:eq(2)').text());
$("#employeegender").val($(this).closest("tr").find('td:eq(3)').text());
$("#employeesalary").val($(this).closest("tr").find('td:eq(4)').text());
});



//DELETE
$(document).on("click",".btnRemove", function(s) {
// ajax communication
$.ajax({
url: "EmployeesAPI",
type: "DELETE",
data: "employeeid=" + $(this).data("employeeid"),
dataType: "text",
complete: function(response, status) {
onEmployeeDeleteComplete(response.responseText, status);
}
});
});



//after completing delete request
function onEmployeeDeleteComplete(response, status) {



if (status == "success") { //if the response status is success
var resultSet = JSON.parse(response);



if (resultSet.status.trim() === "success") { //if the json status is success
//display success alert
$("#alertSuccess").text("Successfully deleted");
$("#alertSuccess").show();

//load data in json to html
$("#divItemsGrid").html(resultSet.data);



} else if (resultSet.status.trim() === "error") { //if the json status is error
//display error alert
$("#alertError").text(resultSet.data);
$("#alertError").show();
}
} else if (status == "error") {
//if the response status is error
$("#alertError").text("Error while deleting");
$("#alertError").show();
} else {
//if an unknown error occurred
$("#alertError").text("Unknown error occurred while deleting");
$("#alertError").show();
}
}



//VALIDATION
function validateEmployeeForm() {
// Name
if ($("#employeename").val().trim() == "")
{
return "Insert Employee Name.";
}

// DOB
if ($("#employeedob").val().trim() == "")
{
return "Insert Employee DOB.";
}

// ADDRESS
if ($("#employeeaddress").val().trim() == "")
{
return "Insert Employee Address.";
}

// GENDER
if ($("#employeegender").val().trim() == "")
{
return "Insert Employee Gender.";
}

// SALARY
if ($("#employeesalary").val().trim() == "")
{
return "Insert Employee Salary.";
}

return true;