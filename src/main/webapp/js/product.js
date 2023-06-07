var g_objects;

function parentCategotyChange(id, element, collapse, type) {

	if (!id) {
		id = document.getElementById("defaultSelect").value;
	}
	if (!element) {
		element = 'defaultSelect1';
	}

	if (!collapse) {
		collapse = 'collapseExample1';
	}
	var url = "http://localhost:8080/com.tulamweb/admin/api?parenrid=" + id;
	var request = new XMLHttpRequest();

	request.onreadystatechange = function() {
		if (this.readyState === 4 && this.status === 200) {

			var HTML = "<table class='table' id='tablesize' style='margin-left: 225px; width: 82%'> <thead><tr><th> checkbox</th><th>Têb size</th><th>Size ID</th><th>Số lượng</th></tr ></thead> <tbody table-border-bottom-0>";
			var objcategory = JSON.parse(request.response).categorys;
			var objsize = JSON.parse(request.response).sizes;

			var objc = JSON.parse(objcategory);
			var objs = JSON.parse(objsize);

			var sel = document.getElementById(element);
			var options = sel.getElementsByTagName('OPTION');
			for (var i = 0; i < options.length; i++) {

				sel.removeChild(options[i]);
				i--; // options have now less element, then decrease i

			}
			for (var i = 0; i < objc.length; i++) {
				var opt = document.createElement('option');
				opt.innerHTML = objc[i].categoryName;
				opt.value = objc[i].categoryID;
				sel.appendChild(opt);
			}
			if (objs) {
				var count = 0;
				for (var i = 0; i < objs.length; i++) {

					HTML += "<tr>";
					if (type == "edit") {
						HTML += " <td><label><input class= 'form-check-input checkboxselect' type='checkbox' onchange='GetSelected('"+type+"')'/></label></td>";
					} else {
						HTML += " <td><label><input class= 'form-check-input checkboxselect' type='checkbox' onchange='GetSelected()'/></label></td>";
					}

					HTML += " <td>" + objs[i].sizeName + "</td>";
					HTML += " <td>" + objs[i].sizeID + "</td>";
					HTML += " <td contenteditable='true'></td>";

					HTML += "</tr>";
					count++;
				}

				HTML += "</tbody></table>";
				document.getElementById(collapse).innerHTML = HTML;
			}

			if (type == "edit") {

				GetSelected(type);
			}

		}
	};

	request.open("GET", url, true);
	request.send();
}

function GetSelected(type) {

	var grid = document.getElementById("tablesize");

	var checkBoxes = grid.getElementsByTagName("INPUT");
	var message = "";

	var listSizeQty = {};
	for (var i = 0; i < checkBoxes.length; i++) {
		if (type == "edit") {
			// checkBoxes[i].checked = true;
			var row = checkBoxes[i].parentNode.parentNode.parentNode.cells;
			for (var j = 0; j < g_objects.length; j++) {
				if (row[2].innerHTML == g_objects[j].sizeId) {
					checkBoxes[i].checked = true;
					row[3].innerHTML = g_objects[j].qty;

				}
			}

		}
		if (checkBoxes[i].checked) {
			var sizeQty = {};
			var row = checkBoxes[i].parentNode.parentNode.parentNode.cells;

			if (row[3].innerHTML) {
				checkBoxes[i].checked = true;

			} else {
				checkBoxes[i].checked = false;
				document.getElementById("sizetablea").innerHTML = "Hãy Nhập số lượng trước khi chọn!";
			}
			sizeQty['sizeId'] = row[2].innerHTML;
			sizeQty['sizeName'] = row[1].innerHTML;
			sizeQty['qty'] = row[3].innerHTML;

			listSizeQty[i] = sizeQty;
		}

	}
	var myJsonString = JSON.stringify(listSizeQty);
	if (type == "edit") {
		document.getElementById('sizelistedt').value = myJsonString;
	} else {

		document.getElementById('sizelist').value = myJsonString;
	}

}

function productDetailFunc(id) {

	var url = "http://localhost:8080/com.tulamweb/admin/api?productid=" + id;
	var request = new XMLHttpRequest();

	request.onreadystatechange = function() {
		if (this.readyState === 4 && this.status === 200) {

			var product = JSON.parse(request.response).product;
			var objsize = JSON.parse(request.response).sizes;

			var objp = JSON.parse(product);
			var objs = JSON.parse(objsize);
			g_objects = objs;

			document.getElementById("html5-text-input-productname").value = objp.nameProduct;
			var defaultSelect = document.getElementById("defaultSelect2")
			var optionElement = defaultSelect.querySelector("option[value='"
					+ objp.parentCatetoryId + "']");
			optionElement.selected = true;

			parentCategotyChange(objp.parentCatetoryId, 'defaultSelect3',
					'collapseExample2', 'edit');
			
			document.getElementById("html5-url-input-title").value = objp.shotTitle;
			document.getElementById("html5-tel-input-price").value = objp.price;
			document.getElementById("html5-number-input-comment").value = objp.comment;
			document.getElementById("exampleFormControlTextarea2").value = objp.detail;
			
			

		}
	};

	request.open("GET", url, true);
	request.send();
}
