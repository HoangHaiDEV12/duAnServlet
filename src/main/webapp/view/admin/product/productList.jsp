
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row">

	<form id="addproduct" action="addproduct" method="POST"
		enctype="multipart/form-data">

		<p class="demo-inline-spacing">

			<button class="btn btn-primary me-1" type="button"
				data-bs-toggle="collapse" data-bs-target="#collapseExample"
				aria-expanded="false" aria-controls="collapseExample">Thêm
				mới danh mục</button>
		</p>

		<div class="collapse" id="collapseExample">
			<div class="d-grid d-sm-flex p-3 border">

				<div class="card-body">
					<div class="mb-3 row">
						<label for="html5-text-input" class="col-md-2 col-form-label">Tên
							sản phẩm</label>
						<div class="col-md-10">
							<input class="form-control" type="text" name="productName"
								id="html5-text-input" />
						</div>
					</div>

					<div class="mb-3 row">
						<label for="html5-search-input" class="col-md-2 col-form-label">Chọn
							danh mục</label>
						<div class="col-md-10">
							<select id="defaultSelect" onchange="parentCategotyChange(${pcategory.parentsid})"
								class="form-select" name="parentcategoryId">

								<option value="">Hãy chọn danh mục</option>
								<c:forEach items="${parentsCates}" var="pcategory">
									<option value="${pcategory.parentsid}">${pcategory.parentsCategoryName}</option>


								</c:forEach>
							</select>
						</div>


					</div>
					<div class="mb-3 row">
						<label for="html5-search-input" class="col-md-2 col-form-label">Thể
							loại</label>
						<div class="col-md-10">
							<select id="defaultSelect1" class="form-select" name="categoryId">
								<c:forEach items="${categorys}" var="category">
									<option value="${category.categoryID}">${category.categoryName}</option>


								</c:forEach>
							</select>
						</div>


					</div>
					<div class="mb-3 row">
						<label for="html5-search-input" class="col-md-2 col-form-label">Chọn
							size</label>
						<div class="col-md-10" id="sizetablea" style="color: red">
							<input class="form-control" type="text" disabled="disabled"
								placeholder="click chọn" data-bs-toggle="collapse"
								data-bs-target="#collapseExample1" aria-expanded="false"
								aria-controls="collapseExample1">
						</div>

						<div class="collapse" id="collapseExample1">

							<!--genTable by js -->
						</div>

						<div class="col-md-2">
							<input name="sizelist" id="sizelist" type="hidden">
						</div>


					</div>


					<div class="mb-3 row">
						<label for="html5-url-input" class="col-md-2 col-form-label ">Nhập
							tiêu đề </label>
						<div class="col-md-10">
							<input class="form-control" type="text" id="html5-url-input"
								name="shotTitle" />
						</div>
					</div>
					<div class="mb-3 row">
						<label for="html5-tel-input" class="col-md-2 col-form-label">nhập
							giá</label>
						<div class="col-md-10">
							<input class="form-control" type="number" id="html5-tel-input"
								name="price" />
						</div>
					</div>

					<div class="mb-3 row">
						<label for="html5-number-input" class="col-md-2 col-form-label">Thêm
							hình ảnh</label>
						<div class="col-md-10">
							<input class="form-control" type="file" id="formFileMultiple"
								name="fileName" multiple />
						</div>
					</div>

					<div class="mb-3 row">
						<label for="html5-number-input" class="col-md-2 col-form-label">Nhập
							lưu ý</label>
						<div class="col-md-10">
							<input class="form-control" type="text" id="html5-number-input"
								name="comment" />
						</div>
					</div>

					<div class="mb-3 row">
						<label for="html5-number-input" class="col-md-2 col-form-label">Nhập
							mô tả chi tiết</label>
						<div class="col-md-10">

							<textarea class="form-control" id="exampleFormControlTextarea1"
								rows="3" name="detail"></textarea>
						</div>
					</div>

					<div class="mb-3 row">

						<div class="col-md-11"></div>
						<div class="col-md-1">
							<button class="btn btn-primary">Thêm</button>
						</div>
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
					<th>Tên Sản Phẩm</th>
					<th>Ảnh</th>
					<th>Số Lượng</th>
					<th>Size</th>
					<th>Giá</th>
					<th>Ngày thêm</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody class="table-border-bottom-0">

				<c:forEach items="${productionTetails}" var="pTetails">


					<tr>

						<td class="${pTetails.stt}">${pTetails.stt}</td>
						<td class="${pTetails.stt}">${pTetails.categoryName}</td>
						<td class="${pTetails.stt}">${pTetails.nameProduct}</td>
						<td class="w-25">
							<div class="row">
								<div class="col-md">
									<div id="carouselExample" class="carousel slide"
										data-bs-ride="carousel">
										<ol class="carousel-indicators">

											<c:forEach items="${pTetails.imageList}" var="image">
												<li data-bs-target="#carouselExample" data-bs-slide-to="0"
													class="active"></li>
												<li data-bs-target="#carouselExample"
													data-bs-slide-to="${image.stt + 1}"></li>
											</c:forEach>

										</ol>
										<div class="carousel-inner">
											<div class="carousel-item active">
												<img class="d-block w-100"
													src="${pageContext.request.contextPath}/${pTetails.imageList[0].image}"
													alt="First slide" />
												<div class="carousel-caption d-none d-md-block"></div>
											</div>
											<c:forEach var="image" items="${pTetails.imageList}"
												varStatus="loop">
												<div class="carousel-item">
													<img class="d-block w-100"
														src="${pageContext.request.contextPath}/${image.image}"
														alt="Second slide" />
													<div class="carousel-caption d-none d-md-block"></div>
												</div>
											</c:forEach>

										</div>
										<a class="carousel-control-prev" href="#carouselExample"
											role="button" data-bs-slide="prev"> <span
											class="carousel-control-prev-icon" aria-hidden="true"></span>
											<span class="visually-hidden">Previous</span>
										</a> <a class="carousel-control-next" href="#carouselExample"
											role="button" data-bs-slide="next"> <span
											class="carousel-control-next-icon" aria-hidden="true"></span>
											<span class="visually-hidden">Next</span>
										</a>
									</div>
								</div>

							</div>
						</td>
						<td class="${pTetails.stt}">${pTetails.qty}</td>
						<td class="${pTetails.stt}">${pTetails.sizeName}</td>
						<td class="${pTetails.stt}">${pTetails.price}</td>
						<td class="${pTetails.stt}">${pTetails.createdate}</td>

						<td>
							<div class="dropdown">
								<button type="button" class="btn p-0 dropdown-toggle hide-arrow"
									data-bs-toggle="dropdown">
									<i class="bx bx-dots-vertical-rounded"></i>
								</button>
								<div class="dropdown-menu">
									<a class="dropdown-item " data-bs-toggle="modal"
										data-bs-target="#exLargeModal" href="javascript:void(0);"
										onclick="productDetailFunc(${pTetails.productId});"><i
										class="bx bx-edit-alt me-2"></i> Edit</a> <a class="dropdown-item"
										data-bs-toggle="modal" data-bs-target="#modalCenter"
										onclick="myJsFuncDelete(${pTetails.productId}, '${pTetails.nameProduct}');"
										href="javascript:void(0);"><i class="bx bx-trash me-2"></i>
										Delete</a>
								</div>
							</div>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<form action="updateproduct" method="POST">
			<div class="modal fade" id="exLargeModal" tabindex="-1"
				aria-hidden="true">
				<div class="modal-dialog modal-xl" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalLabel4">Modal title</h5>
							<button type="button" class="btn-close" data-bs-dismiss="modal"
								aria-label="Close"></button>
						</div>
						<div class="modal-body">
							<div class="row">
								<div class="mb-3 row">
									<label for="html5-text-input" class="col-md-2 col-form-label">Tên
										sản phẩm</label>
									<div class="col-md-10">
										<input class="form-control" type="text" name="productName"
											id="html5-text-input-productname" />
									</div>
								</div>

								<div class="mb-3 row">
									<label for="html5-search-input" class="col-md-2 col-form-label">Chọn
										danh mục</label>
									<div class="col-md-10">
										<select id="defaultSelect2" onchange="parentCategotyChange(${pcategory.parentsid})"
											class="form-select" name="parentcategoryId">

											<option value="">Hãy chọn danh mục</option>
											<c:forEach items="${parentsCates}" var="pcategory">
												<option value="${pcategory.parentsid}">${pcategory.parentsCategoryName}</option>


											</c:forEach>
										</select>
									</div>


								</div>
								<div class="mb-3 row">
									<label for="html5-search-input" class="col-md-2 col-form-label">Thể
										loại</label>
									<div class="col-md-10">
										<select id="defaultSelect3" class="form-select"
											name="categoryId">
											<c:forEach items="${categorys}" var="category">
												<option value="${category.categoryID}">${category.categoryName}</option>


											</c:forEach>
										</select>
									</div>


								</div>
								<div class="mb-3 row">
									<label for="html5-search-input" class="col-md-2 col-form-label">Chọn
										size</label>
									<div class="col-md-10" id="sizetablea" style="color: red">

										<input class="form-control" type="text" disabled="disabled"
											placeholder="click chọn" data-bs-toggle="collapse"
											data-bs-target="#collapseExample2" aria-expanded="false"
											aria-controls="collapseExample2">
									</div>

									<div class="collapse" id="collapseExample2">

										<!--genTable by js -->
									</div>

									<div class="col-md-2">
										<input name="sizelist" id="sizelistedt" type="hidden">
									</div>


								</div>


								<div class="mb-3 row">
									<label for="html5-url-input" class="col-md-2 col-form-label ">Nhập
										tiêu đề </label>
									<div class="col-md-10">
										<input class="form-control" type="text" id="html5-url-input-title"
											name="shotTitle" />
									</div>
								</div>
								<div class="mb-3 row">
									<label for="html5-tel-input" class="col-md-2 col-form-label">nhập
										giá</label>
									<div class="col-md-10">
										<input class="form-control" type="number" id="html5-tel-input-price"
											name="price" />
									</div>
								</div>

								<div class="mb-3 row">
									<label for="html5-number-input" class="col-md-2 col-form-label">Thêm
										hình ảnh</label>
									<div class="col-md-10">
										<input class="form-control" type="file" id="formFileMultiple"
											name="fileName" multiple />
									</div>
								</div>

								<div class="mb-3 row">
									<label for="html5-number-input" class="col-md-2 col-form-label">Nhập
										lưu ý</label>
									<div class="col-md-10">
										<input class="form-control" type="text"
											id="html5-number-input-comment" name="comment" />
									</div>
								</div>

								<div class="mb-3 row">
									<label for="html5-number-input" class="col-md-2 col-form-label">Nhập
										mô tả chi tiết</label>
									<div class="col-md-10">

										<textarea class="form-control"
											id="exampleFormControlTextarea2" rows="3" name="detail"></textarea>
									</div>
								</div>

							</div>

						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-outline-secondary"
								data-bs-dismiss="modal">Close</button>
							<button type="button" class="btn btn-primary">Save
								changes</button>
						</div>
					</div>
				</div>
			</div>
		</form>

		<form action="deletecategory" method="POST">
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
									<input type="hidden" id="categoryId1" name="categoryId"
										class="form-control" placeholder="Enter Name" />
								</div>
							</div>

							<label for="nameBasic" class="form-label" id="deleteLabel"></label>

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

