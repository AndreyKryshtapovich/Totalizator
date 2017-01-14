function validateDropdown() {
	var result = true;
	var  errDropdown = document.getElementById("err-dropdown");
	errDropdown.innerHTML = "";
	var parameters = document.getElementById("event").value;
	if (!parameters) {
		errDropdown.innerHTML = "Please Select an Event";
		result = false;
	}
	return result; 
}