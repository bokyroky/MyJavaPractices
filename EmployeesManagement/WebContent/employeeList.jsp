<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<table class="table">
	<tr>
		<th>First name</th>
		<th>Last name</th>
		<th>Date of employment</th>
		<th>Department</th>
	</tr>
	<c:forEach items="${employees}" var="item">
		<tr>
			<td>${item.firstName}</td>
			<td>${item.lastName}</td>
			<td><fmt:formatDate pattern="dd.MM.yyyy"
					value="${item.employmentDate}" /></td>
			<td>${item.department.departmentName}</td>
			<td><a href="#" onclick="getUserDataUI(${item.idEmployee})">Edit</a></td>
			<td><a href="#" onclick="deleteEmployee(${item.idEmployee})">Delete</a></td>
		</tr>

	</c:forEach>
	<tr>
		<td><a href="javascript:void(0);" id="hlNewEmployee"
			onclick="getUserDataUI(-1)" class="btn btn-default">Add new
				employee</a></td>
	</tr>
</table>

</html>
