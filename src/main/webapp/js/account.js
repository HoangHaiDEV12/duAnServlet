function myJsFuncAccount(row, nameC) {

	var id = document.getElementById("userId");
	id.value = row;

	var name = document.getElementById("defaultSelect");
	name.value = nameC;
}

function myJsFunc1(row, nameC) {

	var id = document.getElementById("userId1");
	id.value = row;
	
	document.getElementById('deleteLabel1').innerHTML = 'Bạn có chắn chắn muốn xóa người dùng là  "' + nameC + '"  không ?';
}

