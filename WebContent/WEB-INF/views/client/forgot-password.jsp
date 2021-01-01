<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
	<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
	<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="zxx">

<head>
    <meta charset="UTF-8">
    <meta name="description" content="Ashion Template">
    <meta name="keywords" content="Ashion, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Quên mật khẩu</title>
    <base href="${pageContext.servletContext.contextPath}/">

    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css2?family=Cookie&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;500;600;700;800;900&display=swap"
    rel="stylesheet">

    <!-- Css Styles -->
    <jsp:include page="css.jsp"></jsp:include>
</head>

<body>
    <!-- Page Preloder -->
    <div id="preloder">
        <div class="loader"></div>
    </div>

    <!-- Header Begin -->
    <jsp:include page="header.jsp"></jsp:include>
    <!-- Header End -->

    <!-- Checkout Section Begin -->
    <section class="checkout spad">
        <div class="container">
            <div class="row">
            	<div class="col-lg-2"></div>
                <div class="col-lg-8">
                    <h6 class="coupon__link"> Chưa có tài khoản? <a href="signin.htm" style="color: red;">Đăng ký ngay</a></h6>
                </div>
            </div>
            <form action="quen-mat-khau.htm" class="checkout__form" method="POST">
                <div class="row">
                	<div class="col-lg-2"></div>
                    <div class="col-lg-8">
                    	${msg }
                        <h5 class="text-center">Lấy lại mật khẩu</h5>
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="checkout__form__input">
                                    <p>Tên đăng nhập <span>*</span></p>
                                    <input type="text" name="username">
                                </div>
                                <div class="checkout__form__input">
                                    <p>Email <span>*</span></p>
                                    <input type="email" name="email">
                                </div>
                            </div>
                        </div>
                    	<button type="submit" class="site-btn loginout">Gửi email xác nhận</button>
                    </div>
                </div>
            </form>
        </div>
    </section>
        <!-- Checkout Section End -->

    <!-- Footer Section Begin -->
    <jsp:include page="footer.jsp"></jsp:include>
    <!-- Footer Section End -->

    <!-- Search Begin -->
    <div class="search-model">
        <div class="h-100 d-flex align-items-center justify-content-center">
            <div class="search-close-switch">+</div>
            <form class="search-model-form">
                <input type="text" id="search-input" placeholder="Search here.....">
            </form>
        </div>
    </div>
    <!-- Search End -->

    <!-- Js Plugins -->
    <jsp:include page="js.jsp"></jsp:include>
</body>

</html>