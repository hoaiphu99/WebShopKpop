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
    <title>About</title>
    <base href="${pageContext.servletContext.contextPath}/">

    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css2?family=Cookie&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;500;600;700;800;900&display=swap"
    rel="stylesheet">

    <!-- Css Styles -->
    <jsp:include page="WEB-INF/views/client/css.jsp"></jsp:include>
</head>

<body>
    <!-- Page Preloder -->
    <div id="preloder">
        <div class="loader"></div>
    </div>

    <!-- Header Begin -->
    <jsp:include page="WEB-INF/views/client/header.jsp"></jsp:include>
    <!-- Header End -->

    <!-- Checkout Section Begin -->
    <section class="checkout spad">
        <div class="container">
        	<h1>404 Not Found</h1>
        	<h5>Xin lỗi trang này không tồn tại!</h5>
        	<br>
        	<div>
        		<a href="index.htm" class="btn btn-danger" role="button" aria-pressed="true">Về trang chủ</a>
        	</div>
        </div>
    </section>
        <!-- Checkout Section End -->

    <!-- Footer Section Begin -->
    <jsp:include page="WEB-INF/views/client/footer.jsp"></jsp:include>
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
    <jsp:include page="WEB-INF/views/client/js.jsp"></jsp:include>
</body>

</html>