<%@page import="com.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.js"></script>
<script src="Components/users.js"></script>
</head>
<body>
<div class="container"><div class="row"><div class="col-6">
<h1>User Management</h1>
<form id="formUser" name="formUser">
 Name:
 <input id="name" name="name" type="text"
 class="form-control form-control-sm">
 <br> Address:
 <input id="address" name="address" type="text"
 class="form-control form-control-sm">
 <br> Account No:
 <input id="account" name="account" type="text"
 class="form-control form-control-sm">
 <br> Phone No:
 <input id="phone" name="phone" type="text"
 class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save"class="btn btn-primary">
 <input type="hidden" id="hidUserIDSave" name="hidUserIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divIUsersGrid">
 <%
 User userObj = new User();
 out.print(userObj.readUsers());
 %>
</div>
</div> </div> </div>
</body>
</html>