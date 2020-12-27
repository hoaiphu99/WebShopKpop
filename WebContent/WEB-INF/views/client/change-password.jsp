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
    <title>Đổi mật khẩu</title>
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
    
    <!-- Profile Section Begin -->
    <section class="checkout spad">
        <div class="container">
            <div class="row">
                <!-- right side bar -->
                <jsp:include page="profile-sidebar.jsp"/>
                <!-- right side bar end-->
                <div class="col-lg-8">
                	${msg }
                	<h5>Chỉnh sửa thông tin</h5>
                	<hr>
                    <form:form action="doi-mat-khau.htm" class="checkout__form" modelAttribute="user" method="post">
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="checkout__form__input">
                                    <p>Nhập mật khẩu cũ</p>
                                    <input type="password" name="old_password">
                                </div>
                                <div class="checkout__form__input">
                                    <p>Nhập mật khẩu mới</p>
                                    <form:input path="password" type="password"/>
                                </div>
                                <div class="checkout__form__input">
                                    <p>Nhập lại mật khẩu mới</p>
                                    <input type="password" name="confirm_password">
                                </div>
                            </div>
                        </div>
                        <button type="submit" class="site-btn">Đổi mật khẩu</button>
                </form:form>
                </div>
            </div>
        </div>
    </section>
    <!-- Profile Section End -->
    
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