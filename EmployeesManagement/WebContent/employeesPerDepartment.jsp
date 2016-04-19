<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<table class="table">
	<tr>
		<th>Department</th>
		<th>Number of employees</th>
	</tr>
	<c:forEach items="${employeesPerDeprt}" var="item">
		<tr>
			<td>${item.key}</td>
			<td>${item.value}</td>
		</tr>
	</c:forEach>
</table>
</html>


