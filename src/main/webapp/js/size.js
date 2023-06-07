function myJsFuncSize(row, nameC) {

	var id = document.getElementById("sizeId");
	id.value = row;
	
	var name = document.getElementById("oldName");
	name.value = nameC;
}

function myJsFuncDeleteSize(row, nameC) {

	var id = document.getElementById("sizeId1");
	id.value = row;
	
	document.getElementById('deleteLabel').innerHTML = 'Bạn có chắn chắn muốn xóa danh mục tên Size là  "' + nameC + '"  không ?';
}