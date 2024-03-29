function setCookie (name,value) {
	var cookieValue = name + "=" + value + ";";
	document.cookie = cookieValue;
}

function getCookie (name) {
	var nameRead = name + "=";
	var ca = document.cookie.split(';');
		for(var i=0;i < ca.length;i++) {
			var c = ca[i];
			while (c.charAt(0)==' ') c = c.substring(1,c.length);
			if (c.indexOf(nameRead) == 0) return c.substring(nameRead.length,c.length);
		}
	return null;
}
		
function merge(a, b) {
	c = a * b;
	return c; 
}

function testAlert( ) { 
	alert("hello~"); 
}