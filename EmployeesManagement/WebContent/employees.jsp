<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">

<link rel="stylesheet"  href="scripts/jQuery/Themes/jquery-ui.css"/>

<link rel="stylesheet" type="text/css"
	href="Content/css/bootstrap.min.css" />
<link rel="stylesheet" type="teyxt/css" href="Content/css/MyStyle.css"/>
<script src="scripts/jQuery/jquery-1.12.1.min.js"></script>
<script src="scripts/bootstrap.min.js"></script>
<script src="scripts/jQuery/jquery-ui.js"></script>
<script src="scripts/myScript.js"></script>
<title>Employees management</title>

</head>
<body>
	<div id=container class="container" style="width: 40%">
		<div id="divHeading">
			<h1>Employee management</h1>
		</div>
		<div id=divEmployees>
			<jsp:include page="employeeList.jsp">
				<jsp:param name="employees" value='${employees}' />
			</jsp:include>
		</div>
		<div id="divUserData" class="form-group" style="float:left"></div>
		<div id="divEmplPerDepartment" style="float:right">
			<jsp:include page="employeesPerDepartment.jsp">
				<jsp:param name="employeesPerDeprt" value='${employeesPerDeprt}' />
			</jsp:include>
		</div>
	</div>
	

</body>
</html>