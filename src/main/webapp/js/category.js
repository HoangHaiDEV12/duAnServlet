function myJsFunc(row, nameC) {

	var id = document.getElementById("categoryId");
	id.value = row;

	var name = document.getElementById("oldName");
	name.value = nameC;
}

function myJsFuncDelete(row, nameC) {

	var id = document.getElementById("categoryId1");
	id.value = row;

	document.getElementById('deleteLabel').innerHTML = 'Bạn có chắn chắn muốn xóa danh mục ' + nameC + ' không ?';
}
function myJsFunccategory(row, nameD) {

	var id = document.getElementById("categoryId2");
	id.value = row;

	var name = document.getElementById("oldName1");
	name.value = nameD;
}
function myJsFuncDeletecategory(row, nameD) {

	var id = document.getElementById("categoryId3");
	id.value = row;

	document.getElementById('deleteLabel1').innerHTML = 'Bạn có chắn chắn muốn xóa danh mục ' + nameD + ' không ?';
}

