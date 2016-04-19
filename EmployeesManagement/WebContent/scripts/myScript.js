/**
 * 
 */
function getUserDataUI(idEmployee) {
	$.get("userDataUI" + "?idEmployee=" + idEmployee, function(data) {
		$("#divUserData").hide();
		$("#divUserData").html(data);
		$("#divUserData").fadeIn("slow");
	});
	return false;
}
function refreshPartial(url, targetClientID) {
	$.get(url, function(data) {
		$(targetClientID).html(data);
	});
	return false;
}
function editEmployeeData(idEmployee) {
	if (validateData()) {
		$.ajax({
			url : "employees",
			data : {
				"idEmployee" : idEmployee,
				"firstName" : $("#txtFirstName").val(),
				"lastName" : $("#txtLastName").val(),
				"emplDate" : $("#txtEmploymentDate").val(),
				"departmentID" : $("#ddlDepartments").val()
			},
			type : 'POST',
			dataType : 'xml',
			success : function(data, textStatus, jqXHR) {
				refreshData();
			},
			error : function(data, textStatus, jqXHR) {
				console.log('Service call failed!');
			}
		});
	} else {
		refreshLabel("#lblInfo", "Warning: Input data missing!");
	}

	return false;
}
function deleteEmployee(idEmployee) {
	$.get("employees" + "?idEmployee=" + idEmployee, function(data) {
		refreshData();
	});
}
function cancelDataEdit() {
	$("#divUserData").empty();
}

function validateData() {
	var emptyField = false;
	$("input[type='text']").each(function() {
		if ($(this).val() == "") {
			emptyField = true;
			return false;
		}
	});
	if (emptyField == true ||  $("#ddlDepartments option:selected").is(":disabled")==true)
		return false;
	else
		return true;
}
function refreshLabel(lblID, lblText) {
	$(lblID).empty();
	$(lblID).append(lblText);
	$(lblID).fadeIn("fast");
	$(lblID).fadeOut(6000);
}
function refreshData() {
	refreshPartial("employeesList", "#divEmployees");
	refreshPartial("employeesPerDepartment", "#divEmplPerDepartment");
	$("#divUserData").fadeOut("slow");
}