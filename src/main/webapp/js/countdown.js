var counter = 60;
var interval = setInterval(function() {
	counter--;
	// Display 'counter' wherever you want to display it.
	if (counter <= 0) {
		clearInterval(interval);
		$('#timer').html("<h3>Otp đã hết hạn</h3>");
		return;
	} else {
		$('#time').text(counter);
		console.log("Timer --> " + counter);
	}
}, 1000);


var elts = document.getElementsByClassName('otp')
Array.from(elts).forEach(function(elt){
  elt.addEventListener("keyup", function(event) {
    // Number 13 is the "Enter" key on the keyboard
 
    if (event.keyCode === 13 || elt.value.length == 1) {
      	elt.nextElementSibling.focus()
    }
  });
})