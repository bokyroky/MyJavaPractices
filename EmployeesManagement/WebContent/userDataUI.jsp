<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

	<fieldset>
		<legend><b>Employee data</b></legend>
		<div>
			<input type="hidden" id="hfId" value="$">
		</div>
		<div>
			<label>First name: </label> <input id="txtFirstName" type="text"
				value="${employee.firstName}" class="form-control" />
		</div>
		<div>
			<label>Last name: </label> <input id="txtLastName" type="text"
				value="${employee.lastName}" class="form-control" />
		</div>
		<div>
			<label>Date of employment: </label> <input id="txtEmploymentDate"
				type="text" value="<fmt:formatDate pattern="dd.MM.yyyy" 
            value="${employee.employmentDate}" />" class="form-control" />
		</div>
		<div>
			<label>Department: </label> <select id="ddlDepartments"
				class="form-control">
				<option selected disabled>Choose department</option>
				<c:forEach items="${departments}" var="item">
					<option value="${item.id}">${item.departmentName}</option>
				</c:forEach>
			</select>
		</div>
		<div>
			<a href="javascript:void(0);" onclick="editEmployeeData(${employee.idEmployee})"
				class="btn btn-default">Save data</a>
				<a href="javascript:void(0);" onclick="cancelDataEdit()"
				class="btn btn-default" style="margin-left:6.2em">Cancel</a>
		</div>
		<div id="divInfo">
		<label id="lblInfo"></label>
		</div>
	</fieldset>
<c:set var="idDepartment" value="${employee.department.id}" />

<script>
	$(document).ready(function() {
		$("#txtEmploymentDate").datepicker({
			dateFormat : "dd.mm.yy",
			maxDate : new Date(),
			changeMonth : true,
			changeYear : true
		});
		var idDep = '${idDepartment}';
		if (idDep != "")
			$("#ddlDepartments").val(idDep);
	})
</script>

