<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div
	class="container height-100 d-flex justify-content-center align-items-center">
	<div class="position-relative">
		<div class="card p-2 text-center">
			<h6>
				Please enter the one time password <br> to verify your account
			</h6>
			<div>
				<span>A code has been sent to</span> <small>${mail}</small>
			</div>
			<form action="otpconfirm" method="POST">
				<div id="otp"
					class="inputs d-flex flex-row justify-content-center mt-2">
					<input class="m-2 text-center form-control rounded otp" type="text"
						id="first" name="otp1" maxlength="1" /> <input name="otp2"
						class="m-2 text-center form-control rounded otp" type="text"
						id="second" maxlength="1" /> <input name="otp3"
						class="m-2 text-center form-control rounded otp" type="text"
						id="third" maxlength="1" /> <input name="otp4"
						class="m-2 text-center form-control rounded otp" type="text"
						id="fourth" maxlength="1" /> <input name="otp5"
						class="m-2 text-center form-control rounded otp" type="text"
						id="fifth" maxlength="1" /> <input name="otp6"
						class="m-2 text-center form-control rounded otp" type="text"
						id="sixth" maxlength="1" />
				</div>
				<div class="mt-4">
					<button class="btn btn-danger px-4 validate">Xác Nhận</button>
				</div>
			</form>
		</div>

		<c:if test=" ${not empty otpstatus }"></c:if>
		<div>
			<h3 style="color: red">${otpstatus}</h3>
		</div>
		<!-- <div class="card-2">
			<div class="content d-flex justify-content-center align-items-center">
				<span>Didn't get the code</span> <a href="#"
					class="text-decoration-none ms-3">Resend(1/3)</a>
			</div>
		</div> -->

		<div class="card-2">
		<div class="content d-flex justify-content-center align-items-center">
			<span id="timer"> <span id="time">60</span>Seconds
			</span>
		</div>
		</div>

	</div>
</div>
