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
    <title>Đăng ký tài khoản</title>
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
                    <h6 class="coupon__link"> Những trường có dấu <span>*</span> là bắt buộc</h6>
                </div>
            </div>
            <form:form action="signin.htm" modelAttribute="user" class="checkout__form" method="post">
                <div class="row">
                <div class="col-lg-2"></div>
                    <div class="col-lg-8">
                    	${msg }
                        <h5>Đăng ký</h5>
                        <div class="row">
                            <div class="col-lg-12">
                            	<div class="checkout__form__input">
                            		<div class="col-form-label"><form:errors path="name" class="badge badge-danger"/></div>
                                    <p>Họ và tên <span>*</span></p>
                                    <form:input path="name" type="text"/>
                                </div>
                                <div class="checkout__form__input">
                                	<div class="col-form-label"><form:errors path="username" class="badge badge-danger"/></div>
                                    <p>Tên đăng nhập <span>*</span></p>
                                    <form:input path="username" type="text"/>
                                </div>
                                <div class="checkout__form__input">
                                	<div class="col-form-label"><form:errors path="password" class="badge badge-danger"/></div>
                                    <p>Mật khẩu <span>*</span></p>
                                    <form:input path="password" type="password"/>
                                </div>
                                <div class="checkout__form__input">
                                    <p>Nhập lại mật khẩu <span>*</span></p>
                                    <input type="password" name="confirm_password"></input>
                                </div>
                                    <div class="form-group">
									    <div class="checkout__form__input"><p>Giới tính <span>*</span></p></div>
									    <div class="col-sm-6">
									    	<%-- <form:select path="gender" items="${gender }" class="form-control"/> --%>
										    <div class="form-check">
												<form:radiobutton path="gender" value="true" class="form-check-input" checked="true"/>
												<label for="gender1" class="form-check-label">Nam</label>
											</div>
										    <div class="form-check">
												<form:radiobutton path="gender" value="false" class="form-check-input"/>
												<label for="gender2" class="form-check-label">Nữ</label>
											</div>
								    </div>
                                <div class="checkout__form__input">
                                	<div class="col-form-label"><form:errors path="address" class="badge badge-danger"/></div>
                                    <p>Địa chỉ <span>*</span></p>
                                    <form:input path="address" type="textarea"/>
                                </div>
                                <div class="checkout__form__input">
                                	<div class="col-form-label"><form:errors path="phone" class="badge badge-danger"/></div>
                                    <p>Số điện thoại <span>*</span></p>
                                    <form:input path="phone" type="number"/>
                                </div>
                                <div class="checkout__form__input">
                                	<div class="col-form-label"><form:errors path="email" class="badge badge-danger"/></div>
                                    <p>Email<span>*</span></p>
                                    <form:input path="email" type="email"/>
                                </div>
                            </div>
                        </div>
                    	<button type="submit" class="site-btn loginout">Đăng ký</button>
                    </div>
                </div>
                </div>
            </form:form>
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