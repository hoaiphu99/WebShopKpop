<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
	<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <meta name="description" content="Ashion Template">
    <meta name="keywords" content="Ashion, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>${cateName }</title>
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

    <!-- Header -->
	<jsp:include page="header.jsp"></jsp:include>
	<!-- Header end -->

    <!-- Shop Section Begin -->
    <section class="shop spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-3 col-md-3">
                    <div class="shop__sidebar">
                        <div class="sidebar__categories">
                            <div class="section-title">
                                <h4>Danh mục</h4>
                            </div>
                            <div class="categories__accordion">
                                <div class="accordion" id="accordionExample">
                                <c:forEach var="m" items="${menu }">
	                                <div class="card">
                                        <div class="card-heading">
                                            <a href="category/${m }.htm">${m }</a>
                                        </div>
                                    </div>
                                </c:forEach>
                                </div>
                            </div>
                        </div>
                        <div class="sidebar__filter">
                            <div class="section-title">
                                <h4>Lọc theo giá</h4>
                            </div>
                            <div class="filter-range-wrap">
                                <div class="price-range ui-slider ui-corner-all ui-slider-horizontal ui-widget ui-widget-content"
                                data-min="33" data-max="99"></div>
                                <div class="range-slider">
                                    <div class="price-input">
                                        <p>Price:</p>
                                        <input type="text" id="minamount">
                                        <input type="text" id="maxamount">
                                    </div>
                                </div>
                            </div>
                            <a href="#">Filter</a>
                        </div>
                    </div>
                </div>
                <div class="col-lg-9 col-md-9">
                    <div class="row">
                    <c:forEach var="i" items="${lstProCate }">
                    	<div class="col-lg-3 col-md-4 col-sm-6">
			                <div class="product__item">
			                    <div class="product__item__pic set-bg" data-setbg="resources/client/img/product/${i.photo}">
			                        <c:choose>
			                        	<c:when test="${i.quantity == 0 }"><div class="label stockout">Hết hàng</div></c:when>
			                        	<c:when test="${i.discount > 0}"><div class="label sale">Sale <fmt:formatNumber value="${idiscount }" type="percent"/></div></c:when>
			                        </c:choose>
			                        <ul class="product__hover">
			                            <li><a href="resources/client/img/product/${i.photo }" class="image-popup"><span class="arrow_expand"></span></a></li>
			                            <li><a href="#"><span class="icon_heart_alt"></span></a></li>
			                            <li><a href="add-cart/${i.id }.htm"><span class="icon_bag_alt"></span></a></li>
			                        </ul>
			                    </div>
			                    <div class="product__item__text">
			                        <h6><a href="${i.category.name}/${i.id}.htm">${i.name }</a></h6>
			                        <div class="product__price"><fmt:formatNumber value = "${i.price - i.price*i.discount}" type = "number" maxFractionDigits = "0"/> VNĐ <c:if test="${i.discount != 0 }"><span><fmt:formatNumber value = "${i.price}" type = "number" maxFractionDigits = "0"/> VNĐ</span></c:if></div>
			                    </div>
			                </div>
		            	</div>
                    </c:forEach>
                        <div class="col-lg-12 text-center">
                            <div class="pagination__option">
                            <c:if test="${totalPage > 1 }">
	                            <c:forEach begin = "1" end = "${totalPage }" varStatus = "stt">
	                            	<a href="category/${cateName }/page${stt.index }.htm">${stt.index }</a>
	                            </c:forEach>
                            </c:if>
                                <!-- <a href="#"><i class="fa fa-angle-right"></i></a> -->
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Shop Section End -->

    <!-- Instagram Begin -->
    <!-- <div class="instagram">
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-2 col-md-4 col-sm-4 p-0">
                    <div class="instagram__item set-bg" data-setbg="img/instagram/insta-1.jpg">
                        <div class="instagram__text">
                            <i class="fa fa-instagram"></i>
                            <a href="#">@ ashion_shop</a>
                        </div>
                    </div>
                </div>
                <div class="col-lg-2 col-md-4 col-sm-4 p-0">
                    <div class="instagram__item set-bg" data-setbg="img/instagram/insta-2.jpg">
                        <div class="instagram__text">
                            <i class="fa fa-instagram"></i>
                            <a href="#">@ ashion_shop</a>
                        </div>
                    </div>
                </div>
                <div class="col-lg-2 col-md-4 col-sm-4 p-0">
                    <div class="instagram__item set-bg" data-setbg="img/instagram/insta-3.jpg">
                        <div class="instagram__text">
                            <i class="fa fa-instagram"></i>
                            <a href="#">@ ashion_shop</a>
                        </div>
                    </div>
                </div>
                <div class="col-lg-2 col-md-4 col-sm-4 p-0">
                    <div class="instagram__item set-bg" data-setbg="img/instagram/insta-4.jpg">
                        <div class="instagram__text">
                            <i class="fa fa-instagram"></i>
                            <a href="#">@ ashion_shop</a>
                        </div>
                    </div>
                </div>
                <div class="col-lg-2 col-md-4 col-sm-4 p-0">
                    <div class="instagram__item set-bg" data-setbg="img/instagram/insta-5.jpg">
                        <div class="instagram__text">
                            <i class="fa fa-instagram"></i>
                            <a href="#">@ ashion_shop</a>
                        </div>
                    </div>
                </div>
                <div class="col-lg-2 col-md-4 col-sm-4 p-0">
                    <div class="instagram__item set-bg" data-setbg="img/instagram/insta-6.jpg">
                        <div class="instagram__text">
                            <i class="fa fa-instagram"></i>
                            <a href="#">@ ashion_shop</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div> -->
    <!-- Instagram End -->

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