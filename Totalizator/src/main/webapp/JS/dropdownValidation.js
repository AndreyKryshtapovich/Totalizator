
function validateDropdown() {
	var result = true;
	var  errDropdown = document.getElementById("err-dropdown");
	errDropdown.innerHTML = "";
	var parameters = document.getElementById("game").value;
	if (!parameters) {
		errDropdown.innerHTML = "* Fill The Field";
		result = false;
	}
	return result; 
}