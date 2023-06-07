<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row">

	<form id="subcategory" action="subcategory" method="POST">

		<p class="demo-inline-spacing">

			<button class="btn btn-primary me-1" type="button"
				data-bs-toggle="collapse" data-bs-target="#collapseExample"
				aria-expanded="false" aria-controls="collapseExample">Thêm
				mới loại sản phẩm</button>
		</p>

		<div class="collapse" id="collapseExample">
			<div class="d-grid d-sm-flex p-3 border">

				<div class="card-body">

					<div class="mt-2 mb-3">
						<label for="largeInput" class="form-label">Nhập tên thể
							loại</label> <input id="largeInput" class="form-control form-control-lg"
							type="text" name="categoryName" placeholder="Tên thể loại" />
					</div>
					<div class="mb-3">
						<label for="defaultSelect" class="form-label">Chọn loại
							danh mục</label> <select id="defaultSelect" class="form-select"
							name="parentcategoryId">
							<c:forEach items="${parentsCates}" var="pcategory">
								<option value="${pcategory.parentsid}">${pcategory.parentsCategoryName}</option>


							</c:forEach>
						</select>
					</div>
					<div>
						<button class="btn btn-primary">Thêm</button>
					</div>
				</div>
			</div>
		</div>

	</form>
</div>
<div class="card">
	<h5 class="card-header">Table Basic</h5>
	<div class="table-responsive text-nowrap">
		<table class="table">
			<thead>
				<tr>
					<th>STT</th>
					<th>Tên Danh Mục</th>
					<th>Tên Thể Loại</th>
					<th>Ngày thêm</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody class="table-border-bottom-0">

				<c:forEach items="${categories}" var="category">


					<tr>

						<td class="${category.stt}">${category.stt}</td>
						<td class="${category.stt}">${category.categoryName}</td>
						<td class="${category.stt}">${category.parentCategoryName}</td>
						<td class="${category.stt}">${category.createDate}</td>
						<td>
							<div class="dropdown">
								<button type="button" class="btn p-0 dropdown-toggle hide-arrow"
									data-bs-toggle="dropdown">
									<i class="bx bx-dots-vertical-rounded"></i>
								</button>
								<div class="dropdown-menu">
									<a class="dropdown-item " data-bs-toggle="modal"
										data-bs-target="#basicModal" href="javascript:void(0);"
										onclick="myJsFunccategory(${category.categoryID},'${category.categoryName}');"><i
										class="bx bx-edit-alt me-2"></i> Edit</a> <a class="dropdown-item"
										data-bs-toggle="modal" data-bs-target="#modalCenter"
										onclick="myJsFuncDeletecategory(${category.categoryID},'${category.categoryName}');"
										href="javascript:void(0);"><i class="bx bx-trash me-2"></i>
										Delete</a>
								</div>
							</div>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<form action="updateCategoryDetail" method="POST">
			<div class="modal fade" id="basicModal" tabindex="-1"
				aria-hidden="true">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalLabel1">Sửa thể loại</h5>
							<button type="button" class="btn-close" data-bs-dismiss="modal"
								aria-label="Close"></button>
						</div>
						<div class="modal-body">

							<div class="row">
								<div class="col mb-3">
									<input type="hidden" id="categoryId2" name="categoryId"
										class="form-control" placeholder="Enter Name" />
								</div>
							</div>
							<div class="row">
								<div class="col mb-3">
									<label for="nameBasic" class="form-label">Name</label> <input
										type="text" id="oldName1" name="categoryName"
										class="form-control" placeholder="Enter Name" />
								</div>
							</div>

						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-outline-secondary"
								data-bs-dismiss="modal">Close</button>


							<button class="btn btn-primary">Lưu</button>


						</div>
					</div>
				</div>
			</div>
		</form>

		<form action="deleteCategoryDetail" method="POST">
			<div class="modal fade" id="modalCenter" tabindex="-1"
				aria-hidden="true">
				<div class="modal-dialog modal-dialog-centered" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="modalCenterTitle">Xóa thể loại</h5>
							<button type="button" class="btn-close" data-bs-dismiss="modal"
								aria-label="Close"></button>

						</div>

						<div class="modal-body">
							<div class="row">
								<div class="col mb-3">
									<input type="hidden" id="categoryId3" name="categoryId"
										class="form-control" placeholder="Enter Name" />
								</div>
							</div>

							<label for="nameBasic" class="form-label" id="deleteLabel1"></label>

						</div>

						<div class="modal-footer">
							<button type="button" class="btn btn-outline-secondary"
								data-bs-dismiss="modal">Close</button>
							<button class="btn btn-primary">Xóa</button>
						</div>
					</div>
				</div>
			</div>

		</form>
	</div>
</div>

