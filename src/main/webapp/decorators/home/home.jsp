<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="dec"%>
<!DOCTYPE html>
<html lang="en" class="light-style layout-menu-fixed" dir="ltr"
	data-theme="theme-default"
	data-assets-path="<c:url value='/template/admin/assets/' />"
	data-template="vertical-menu-template-free">
<head>
<meta charset="utf-8">
<title>EShopper - Bootstrap Shop Template</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="Free HTML Templates" name="keywords">
<meta content="Free HTML Templates" name="description">

<!-- Favicon -->
<link href="img/favicon.ico" rel="icon">


<!-- Google Web Fonts -->
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap"
	rel="stylesheet">

<!-- Font Awesome -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css"
	rel="stylesheet">


<!-- Icons. Uncomment required icon fonts -->
<link
	href="<c:url value='/template/home/lib/owlcarousel/assets/owl.carousel.min.css' />"
	rel="stylesheet" />

<link href="<c:url value='/template/home/css/style.css' /> "
	rel="stylesheet" />


</head>

<body>

	<%@ include file="/common/home/header.jsp"%>

	<div class="container-fluid mb-5">

		<!-- Navbar Start -->
		<div class="container-fluid">
			<div class="row border-top px-xl-5">
				<div class="col-lg-3 d-none d-lg-block">
					<a
						class="btn shadow-none d-flex align-items-center justify-content-between bg-primary text-white w-100"
						data-toggle="collapse" href="#navbar-vertical"
						style="height: 65px; margin-top: -1px; padding: 0 30px;">
						<h6 class="m-0">Categories</h6> <i
						class="fa fa-angle-down text-dark"></i>
					</a>
					<nav
						class="collapse position-absolute navbar navbar-vertical navbar-light align-items-start p-0 border border-top-0 border-bottom-0 bg-light"
						id="navbar-vertical" style="width: calc(100% - 30px); z-index: 1;">
						<div class="navbar-nav w-100 overflow-hidden"
							style="height: 410px">
							<div class="nav-item dropdown">
								<a href="#" class="nav-link" data-toggle="dropdown">Dresses
									<i class="fa fa-angle-down float-right mt-1"></i>
								</a>
								<div
									class="dropdown-menu position-absolute bg-secondary border-0 rounded-0 w-100 m-0">
									<a href="" class="dropdown-item">Men's Dresses</a> <a href=""
										class="dropdown-item">Women's Dresses</a> <a href=""
										class="dropdown-item">Baby's Dresses</a>
								</div>
							</div>
							<a href="" class="nav-item nav-link">Shirts</a> <a href=""
								class="nav-item nav-link">Jeans</a> <a href=""
								class="nav-item nav-link">Swimwear</a> <a href=""
								class="nav-item nav-link">Sleepwear</a> <a href=""
								class="nav-item nav-link">Sportswear</a> <a href=""
								class="nav-item nav-link">Jumpsuits</a> <a href=""
								class="nav-item nav-link">Blazers</a> <a href=""
								class="nav-item nav-link">Jackets</a> <a href=""
								class="nav-item nav-link">Shoes</a>
						</div>
					</nav>
				</div>
				<div class="col-lg-9">
					<nav
						class="navbar navbar-expand-lg bg-light navbar-light py-3 py-lg-0 px-0">
						<a href="" class="text-decoration-none d-block d-lg-none">
							<h1 class="m-0 display-5 font-weight-semi-bold">
								<span class="text-primary font-weight-bold border px-3 mr-1">E</span>Shopper
							</h1>
						</a>
						<button type="button" class="navbar-toggler"
							data-toggle="collapse" data-target="#navbarCollapse">
							<span class="navbar-toggler-icon"></span>
						</button>
						<div class="collapse navbar-collapse justify-content-between"
							id="navbarCollapse">
							<div class="navbar-nav mr-auto py-0">
								<a href="index.html" class="nav-item nav-link">Home</a> <a
									href="shop.html" class="nav-item nav-link">Shop</a> <a
									href="detail.html" class="nav-item nav-link active">Shop
									Detail</a>
								<div class="nav-item dropdown">
									<a href="#" class="nav-link dropdown-toggle"
										data-toggle="dropdown">Pages</a>
									<div class="dropdown-menu rounded-0 m-0">
										<a href="cart.html" class="dropdown-item">Shopping Cart</a> <a
											href="checkout.html" class="dropdown-item">Checkout</a>
									</div>
								</div>
								<a href="contact.html" class="nav-item nav-link">Contact</a>
							</div>
							<div class="navbar-nav ml-auto py-0">

								<c:if test="${ empty name}">
									<a href="/com.tulamweb/login" class="nav-item nav-link">Đăng
										nhập</a>
									<a href="/com.tulamweb/regist" class="nav-item nav-link">Đăng kí</a>
								</c:if>
								<c:if test="${not empty name}">
									<a href="##" class="nav-item nav-link">Xin chào,  ${name}</a>
									<a href="/com.tulamweb/homepage" class="nav-item nav-link">Đăng xuất</a>
									<c:if test="${role eq 'ADMIN'}">
										<a href="/com.tulamweb/admin?name = ${name}" class="nav-item nav-link">Về Trang quản trị</a>
									</c:if>
								</c:if>

							</div>
						</div>
					</nav>
				</div>
			</div>
		</div>
		<div class="row border-top px-xl-5">
			<dec:body />
		</div>
	</div>


	<%@ include file="/common/home/footer.jsp"%>

	<!-- Back to Top -->
	<a href="#" class="btn btn-primary back-to-top"><i
		class="fa fa-angle-double-up"></i></a>
	<!-- JavaScript Libraries -->
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>



	<script src="<c:url value='/template/home/lib/easing/easing.min.js' />"></script>

	<script
		src="<c:url value='/template/home/lib/owlcarousel/owl.carousel.min.js' />"></script>

	<!-- Contact Javascript File -->
	<script
		src="<c:url value='/template/home/mail/jqBootstrapValidation.min.js' />"></script>
	<script src="<c:url value='/template/home/mail/contact.js' />"></script>

	<script src="<c:url value='/template/home/js/main.js' />"></script>
</body>
</html>