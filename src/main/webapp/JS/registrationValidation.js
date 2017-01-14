// Global var's

var FILL_FIELD = "*заполните поле";
var PWD_NOT_EQUAL = "*не совпадают значения паролей";
var BAD_FIRST_SYMBOL = "*некорректный первый символ(A-z)";
var BAD_SYMBOLS = "*некорректные символы";
var LOW_PASSWORD = "*короткий пароль";
var BAD_PASSWORD = "*ненадежный пароль";
var BAD_EMAIL = "*некорректный e-mail";
var BAD_COUNTRY_CODE = "*введите 3 заглавные латинские буквы";
var LOW_LOGIN = "*короткий логин";

function validateName() {
   var errFirstName = document.getElementById("err-firstName");
    errFirstName.innerHTML = "";
    var result = true;
       var usrFirstName = document.forms[4]["firstName"].value;
		if (!usrFirstName) {
			errFirstName.innerHTML = FILL_FIELD;
			result = false;
		} 
        return result;
}

function validateLastName() {
 
    var  errLastName = document.getElementById("err-lastName");
    errLastName.innerHTML = "";
    var result = true;
    var usrLastName = document.forms[4]["lastName"].value;
		if (!usrLastName) {
			errLastName.innerHTML = FILL_FIELD;
			result = false;
		} 
}

function validateLogin(){
    var errLogin = document.getElementById("err-login");
    errLogin.innerHTML = "";
    result = true;
     var login = document.forms[4]["register-login"].value;
    	if (!login) {
			errLogin.innerHTML = FILL_FIELD;
			result = false;
		}else{
             if(new String(login).length < 5 ){
                errLogin.innerHTML = LOW_LOGIN;
                result = false;
            }else{
    
    var pattFirstLetterLogin = new RegExp("^[a-zA-Z]+");
    if (!pattFirstLetterLogin.test(login)) {
			errLogin.innerHTML = BAD_FIRST_SYMBOL;
			result = false;
		}else {
            var pattOtherSymbols = new RegExp("^[a-zA-Z][a-zA-Z0-9_]{5,}$");
            
            if (!pattOtherSymbols.test(login)) {
			errLogin.innerHTML = BAD_SYMBOLS;
			result = false;
		  }
        }
    }
 }
}

function validatePasswords(){
    var errPwd1 =  document.getElementById("err-password");
	var	errPwd2 =  document.getElementById("err-rep-password");
    errPwd1.innerHTML = "";
	errPwd2.innerHTML = ""; 
    var result = true;
    
    var pwd1 = document.forms[4]["register-password"].value;
		if (!pwd1){
			errPwd1.innerHTML = FILL_FIELD;
			result = false;
		} else {
            var passwordRegexp = new RegExp("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{6,}$"); 
            
        if(!passwordRegexp.test(pwd1)) {
            errPwd1.innerHTML = BAD_PASSWORD; 
            document.forms[4]["register-password"].value = "";   // сброс 
            document.forms[4]["rep-password"].value="";  // сброс
            result = false;
        }
    }
    
     var pwd2 = document.forms[4]["rep-password"].value;
		if (!pwd2){
            errPwd2.innerHTML = FILL_FIELD;
			result = false;
		}else{
            if(pwd2 !== pwd1) {
                errPwd1.innerHTML = PWD_NOT_EQUAL; 
                document.forms[4]["register-password"].value = "";   // сброс 
                document.forms[4]["rep-password"].value="";  // сброс
			result = false;
            }
        }
    
    return result;
}

function validateAge(){
    var errAge = document.getElementById("err-age");
    errAge.innerHTML = "";
    var result = true;
    var age = document.forms[4]["age"].value;
		if (!age){
			errAge.innerHTML = FILL_FIELD;
			result = false;
		}
    return result;
} 

function validateEmail(){
    var errEmail = document.getElementById("err-e-mail");
    errEmail.innerHTML = "";
    var result = true;
    var eMail = document.forms[4]["e-mail"].value;
		if (!eMail){
            errEmail.innerHTML = FILL_FIELD;
			result = false;
		}else{
         var eMailRegExp ="(^[a-zA-Z0-9]+)@[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$";
            if(eMail.search(eMailRegExp) == -1){
                 errEmail.innerHTML = BAD_EMAIL;
                 result = false;
            }
        }
    return result;
}

function validateCountry(){
    var errCountry = document.getElementById("err-country");
    errCountry = document.getElementById("err-country");
     errCountry.innerHTML = "";
    var result = true;
      var country = document.forms[4]["country"].value;
		if (!country) {
			errCountry.innerHTML = FILL_FIELD;
			result = false;
		}else{
         var countryRegExp = new RegExp("[A-Z]{3}");
            if(!countryRegExp.test(country)){
                errCountry.innerHTML = BAD_COUNTRY_CODE;
                result = false;
            }
        }
    return result;
}

function validateSecondEmail(){
     var errEmail = document.getElementById("err-second-e-mail");
    if(errEmail == null){
        return true;
    }
    errEmail.innerHTML = "";
    var result = true;
    
        var eMail = document.forms[4]["second-e-mail"].value;
		if (!eMail){
            errEmail.innerHTML = FILL_FIELD;
			result = false;
		}else{
         var eMailRegExp ="(^[a-zA-Z0-9]+)@[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$";
            if(eMail.search(eMailRegExp) == -1){
                 errEmail.innerHTML = BAD_EMAIL;
                 result = false;
            }
        }
    return result;
}

function validateRegistrationForm() {
	var result = true;
    
  var result = true,
        res1 = true,
        res2 = true,
        res3 = true,
        res4 = true,
        res5 = true,
        res6=true,
        res7 = true,
        res8 = true;
   
    res1 = validateName();
    if (!res1){result=res1;}
    res2 = validateLastName();
    if (!res2){result=res2;}
    res3 = validateAge();
    if (!res3){result=res3;}
    res4 = validateLogin();
    if (!res4){result=res4;}
    res5 = validatePasswords();
    if (!res5){result=res5;}
    res7 = validateEmail();
    if (!res7){result=res7;}
    res6 = validateSecondEmail();
    if (!res6){result=res6;}
    res8 = validateCountry();
    if (!res8){result=res8;}
    
	return result; 
}

function addSecondEmail(){
    var ancestor = document.getElementById("ancestor");
    var prev = document.getElementById("eMailContainer");
    
    var secondEmailDivContainer = document.createElement("div");
    secondEmailDivContainer.setAttribute("class","form-row");
    secondEmailDivContainer.setAttribute("id","secondeEMailContainer");
    var label = document.createElement("label");
    label.setAttribute("for","second-e-mail");
    var textForLabel = document.createTextNode("Second E-mail:");
    label.appendChild(textForLabel);
    
    
    var input = document.createElement("input");
    input.setAttribute("type","text");
    input.setAttribute("name","second-e-mail");
    input.setAttribute("id","second-e-mail");
    
    var errSpan = document.createElement("span");
    errSpan.setAttribute("class","err");
    errSpan.setAttribute("id","err-second-e-mail");

    var addBtn = document.getElementById("addBtn");
    addBtn.setAttribute("disabled","disabled");
    
    
    var delBtn = document.createElement("button");
    var delBtnText = document.createTextNode("Удалить e-mail");
    delBtn.appendChild(delBtnText);
    delBtn.setAttribute("class","addEmailBtn");
    delBtn.setAttribute("onclick", "return deleteSecondEmail()");
    
    
    secondEmailDivContainer.appendChild(label);
    secondEmailDivContainer.appendChild(input);
    secondEmailDivContainer.appendChild(errSpan);
    secondEmailDivContainer.appendChild(delBtn);
    
    ancestor.insertBefore(secondEmailDivContainer,prev);
}

function deleteSecondEmail(){
    var ancestor = document.getElementById("ancestor");
    var divToRemove = document.getElementById("secondeEMailContainer");
    ancestor.removeChild(divToRemove);
    var addBtn = document.getElementById("addBtn");
    addBtn.removeAttribute("disabled");
}