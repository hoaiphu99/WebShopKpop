<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
	<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f" %>
	<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="description" content="Ashion Template">
    <meta name="keywords" content="Ashion, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Xác nhận mua hàng</title>
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
	                    <h6 class="coupon__link">Để thay đổi địa chỉ và số điện thoại vui lòng vào <a href="chinh-sua-thong-tin.htm" style="color: red;">chỉnh sửa thông tin cá nhân</a></h6>
	                </div>
            </div>
            <form:form action="xac-nhan-mua-hang.htm" class="checkout__form" modelAttribute="user" method="post">
                <div class="row">
                    <div class="col-lg-8">
                        <h5>Chi tiết đơn hàng</h5>
                        <div class="row">
                            <div class="col-lg-12">
                            	<div class="checkout__form__input">
                                    <p>Họ và tên</p>
                                    <form:input path="name" type="text" readonly="true"/>
                                </div>
                                <div class="checkout__form__input">
                                    <p>Địa chỉ</p>
                                    <form:input path="address" type="textarea" readonly="true"/>
                                </div>
                                <div class="checkout__form__input">
                                    <p>Số điện thoại</p>
                                    <form:input path="phone" type="number" readonly="true"/>
                                </div>
                                <div class="checkout__form__input">
                                    <p>Email</p>
                                    <form:input path="email" type="email" readonly="true"/>
                                </div>
                            </div>
                        </div>
                    </div>
                        <div class="col-lg-4">
                            <div class="checkout__order">
                                <h5>Đơn hàng của bạn</h5>
                                <div class="checkout__order__product">
                                    <ul>
                                        <li>
                                            <span class="top__text">Sản phẩm</span>
                                            <span class="top__text__right">Tổng cộng</span>
                                        </li>
                                        <c:forEach var="i" items="${cart }" varStatus="status">
                                        	<li>${status.index + 1 }. ${i.value.product.name } <span><f:formatNumber value="${i.value.totalPrice}" type="number"/></span></li>
                                        </c:forEach>
                                    </ul>
                                </div>
                                <div class="checkout__order__total">
                                    <ul>
                                        <li>Tổng cộng <span><f:formatNumber value="${totalPriceCart }" type="number"/> VNĐ</span></li>
                                    </ul>
                                </div>
                                <div class="checkout__order__widget">
                                    <label for="o-acc">
                                        Hình thức thanh toán
                                    </label>
                                    <p>Thanh toán khi nhận hàng</p>
                                    
                                </div>
                                <button type="submit" class="site-btn">Đặt hàng</button>
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