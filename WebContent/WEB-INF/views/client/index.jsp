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
    <title>Kpop Shop</title>
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
    

    <!-- Categories Section Begin -->
    <section class="categories">
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-6 p-0">
                    <div class="categories__item categories__large__item set-bg"
                    data-setbg="resources/client/img/categories/category-1.jpg">
                    <div class="categories__text">
                        <h1>${menu[0] }</h1>
                        <a href="category/${menu[0] }.htm">Mua ngay</a>
                    </div>
                </div>
            </div>
            <div class="col-lg-6">
                <div class="row">
                    <div class="col-lg-6 col-md-6 col-sm-6 p-0">
                        <div class="categories__item set-bg" data-setbg="resources/client/img/categories/category-2.jpg">
                            <div class="categories__text">
                                <h4>${menu[1] }</h4>
                                <!-- <p>358 items</p> -->
                                <a href="category/${menu[1] }.htm">Mua ngay</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6 col-md-6 col-sm-6 p-0">
                        <div class="categories__item set-bg" data-setbg="resources/client/img/categories/category-3.jpg">
                            <div class="categories__text">
                                <h4>${menu[2] }</h4>
                                <!-- <p>273 items</p> -->
                                <a href="category/${menu[2] }.htm">Mua ngay</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6 col-md-6 col-sm-6 p-0">
                        <div class="categories__item set-bg" data-setbg="resources/client/img/categories/category-4.jpg">
                            <div class="categories__text">
                                <h4>${menu[3] }</h4>
                                <!-- <p>159 items</p> -->
                                <a href="category/${menu[3] }.htm">Mua ngay</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6 col-md-6 col-sm-6 p-0">
                        <div class="categories__item set-bg" data-setbg="resources/client/img/categories/category-5.jpg">
                            <div class="categories__text">
                                <h4>${menu[4] }</h4>
                                <!-- <p>792 items</p> -->
                                <a href="category/${menu[4] }.htm">Mua ngay</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Categories Section End -->

<!-- Product Section Begin -->
<section class="product spad">
    <div class="container">
        <div class="row">
            <div class="col-lg-4 col-md-4">
                <div class="section-title">
                    <h4>New product</h4>
                </div>
            </div>
            <!-- Lọc sản phẩm theo category -->
            <div class="col-lg-8 col-md-8">
                <ul class="filter__controls">
                    <li class="active" data-filter="*">All</li>
                    <li data-filter=".Album">Album</li>
                    <li data-filter=".Magazine">Magazine</li>
                    <li data-filter=".Photobook">Photobook</li>
                    <li data-filter=".Beauty">Beauty</li>
                    <li data-filter=".Fashion">Fashion</li>
                </ul>
            </div>
        </div>
        <div class="row property__gallery">
        <c:forEach var="i" items="${lstPro }">
        	<div class="col-lg-3 col-md-4 col-sm-6 mix ${i.category.name }">
                <div class="product__item">
                    <div class="product__item__pic set-bg" data-setbg="resources/client/img/product/${i.photo }" onclick="location.href='${i.category.name }/${i.id}.htm'">
                        <c:choose>
                        	<c:when test="${i.quantity == 0 }"><div class="label stockout">Hết hàng</div></c:when>
                        	<c:when test="${i.discount > 0}"><div class="label sale">Sale <fmt:formatNumber value="${i.discount }" type="percent"/></div></c:when>
                        </c:choose>
                        <ul class="product__hover">
                            <li><a href="resources/client/img/product/${i.photo }" class="image-popup"><span class="arrow_expand"></span></a></li>
                            <li><a href="#"><span class="icon_heart_alt"></span></a></li>
                            <li><a href="add-cart/${i.id }.htm"><span class="icon_bag_alt"></span></a></li>
                        </ul>
                    </div>
                    <div class="product__item__text">
                        <h6><a href="${i.category.name }/${i.id}.htm">${i.name }</a></h6>
                        <div class="product__price"><fmt:formatNumber value = "${i.price - i.price*i.discount}" type = "number" maxFractionDigits = "0"/> VNĐ <c:if test="${i.discount != 0 }"><span><fmt:formatNumber value = "${i.price}" type = "number" maxFractionDigits = "0"/> VNĐ</span></c:if></div>
                    </div>
                </div>
            </div>
        </c:forEach>
        </div>
    </div>
</section>
<!-- Product Section End -->

<!-- Banner Section Begin -->
<section class="banner set-bg" data-setbg="img/banner/banner-1.jpg">
    <div class="container">
        <div class="row">
            <div class="col-xl-7 col-lg-8 m-auto">
                <div class="banner__slider owl-carousel">
                	<c:forEach var="i" items="${lstSlider }">
                	<div class="banner__item">
                        <div class="banner__text">
                            <span>${i.smallTitle }</span>
                            <h1>${i.bigTitle }</h1>
                            <a href="${i.link }">Mua ngay</a>
                        </div>
                    </div>
                	</c:forEach>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Banner Section End -->

<!-- Trend Section Begin -->
<section class="trend spad">
    <div class="container">
        <div class="row">
            <div class="col-lg-4 col-md-4 col-sm-6">
                <div class="trend__content">
                    <div class="section-title">
                        <h4>Hot Trend</h4>
                    </div>
                    <c:forEach var="i" items="${lstHot}">
                    <div class="trend__item">
                        <div class="trend__item__pic">
                            <img src="resources/client/img/product/${i.product.photo }" alt="" style="width: 100px;">
                        </div>
                        <div class="trend__item__text">
                            <h6><a href="${i.product.category.name }/${i.product.id}.htm" class="trend_text">${i.product.name }</a></h6>
                            <div class="product__price"><fmt:formatNumber value = "${i.product.price - i.product.price*i.product.discount}" type = "number" maxFractionDigits = "0"/> VNĐ <c:if test="${i.product.discount != 0 }"><span><fmt:formatNumber value = "${i.product.price}" type = "number" maxFractionDigits = "0"/> VNĐ</span></c:if></div>
                        </div>
                    </div>
                    </c:forEach>
                </div>
            </div>
            <div class="col-lg-4 col-md-4 col-sm-6">
                <div class="trend__content">
                    <div class="section-title">
                        <h4>Best seller</h4>
                    </div>
                    <c:forEach var="i" items="${lstBestSaler }">
                    <div class="trend__item">
                        <div class="trend__item__pic">
                            <img src="resources/client/img/product/${i[2] }" alt="" style="width: 100px;">
                        </div>
                        <div class="trend__item__text">
                            <h6><a href="${i[5] }/${i[0]}.htm" class="trend_text">${i[1] }</a></h6>
                        	<div class="product__price"><fmt:formatNumber value = "${i[3] - i[3]*i[4]}" type = "number" maxFractionDigits = "0"/> VNĐ <c:if test="${i[4] != 0 }"><span><fmt:formatNumber value = "${i[3]}" type = "number" maxFractionDigits = "0"/> VNĐ</span></c:if></div>
                        </div>
                    </div>
                    </c:forEach>
                </div>
            </div>
            <div class="col-lg-4 col-md-4 col-sm-6">
                <div class="trend__content">
                    <div class="section-title">
                        <h4>Feature</h4>
                    </div>
                    <c:forEach var="i" items="${lstFeature}">
                   	<div class="trend__item">
                       <div class="trend__item__pic">
                           <img src="resources/client/img/product/${i[2] }" alt="" style="width: 100px;">
                       </div>
                       <div class="trend__item__text">
                            <h6><a href="${i[5] }/${i[0]}.htm" class="trend_text">${i[1] }</a></h6>
                        	<div class="product__price"><fmt:formatNumber value = "${i[3] - i[3]*i[4]}" type = "number" maxFractionDigits = "0"/> VNĐ <c:if test="${i[4] != 0 }"><span><fmt:formatNumber value = "${i[3]}" type = "number" maxFractionDigits = "0"/> VNĐ</span></c:if></div>
                       </div>
                    </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Trend Section End -->

<!-- Discount Section Begin -->
<section class="discount">
    <div class="container">
        <div class="row">
            <div class="col-lg-6 p-0">
                <div class="discount__pic">
                    <img src="resources/client/img/banner/${lstBanner[0].image }" alt="" style="height:390px;">
                </div>
            </div>
            <div class="col-lg-6 p-0">
                <div class="discount__text">
                    <div class="discount__text__title">
                        <span>${lstBanner[0].smallTitle }</span>
                        <h2>${lstBanner[0].bigTitle }</h2>
                        <h5><span>Sale</span> ${lstBanner[0].discount } %</h5>
                    </div>
                    <div class="discount__countdown" id="countdown-time">
                        <div class="countdown__item">
                            <span>${lstBanner[0].day }</span>
                            <p>Ngày</p>
                        </div>
                        <div class="countdown__item">
                            <span>12</span>
                            <p>Giờ</p>
                        </div>
                        <div class="countdown__item">
                            <span>60</span>
                            <p>Phút</p>
                        </div>
                        <div class="countdown__item">
                            <span>60</span>
                            <p>Giây</p>
                        </div>
                    </div>
                    <a href="${lstBanner[0].link }">Mua ngay</a>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Discount Section End -->

<!-- Services Section Begin -->
<section class="services spad">
    <div class="container">
        <div class="row">
            <div class="col-lg-3 col-md-4 col-sm-6">
                <div class="services__item">
                    <i class="fa fa-car"></i>
                    <h6>Miễn phí vận chuyển</h6>
                    <p>Cho tất cả đơn hàng</p>
                </div>
            </div>
            <div class="col-lg-3 col-md-4 col-sm-6">
                <div class="services__item">
                    <i class="fa fa-money"></i>
                    <h6>Hoàn lại tiền</h6>
                    <p>Nếu sản phẩm có vấn đề</p>
                </div>
            </div>
            <div class="col-lg-3 col-md-4 col-sm-6">
                <div class="services__item">
                    <i class="fa fa-support"></i>
                    <h6>Hỗ trợ 24/7</h6>
                    <p>Hỗ trợ nhiệt tình</p>
                </div>
            </div>
            <div class="col-lg-3 col-md-4 col-sm-6">
                <div class="services__item">
                    <i class="fa fa-headphones"></i>
                    <h6>Bảo mật thanh toán</h6>
                    <p>Thanh toán bảo mật 100%</p>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Services Section End -->

<!-- Instagram Begin -->
<!-- <div class="instagram">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-2 col-md-4 col-sm-4 p-0">
                <div class="instagram__item set-bg" data-setbg="resources/client/img/instagram/insta-1.jpg">
                    <div class="instagram__text">
                        <i class="fa fa-instagram"></i>
                        <a href="#">@ ashion_shop</a>
                    </div>
                </div>
            </div>
            <div class="col-lg-2 col-md-4 col-sm-4 p-0">
                <div class="instagram__item set-bg" data-setbg="resources/client/img/instagram/insta-2.jpg">
                    <div class="instagram__text">
                        <i class="fa fa-instagram"></i>
                        <a href="#">@ ashion_shop</a>
                    </div>
                </div>
            </div>
            <div class="col-lg-2 col-md-4 col-sm-4 p-0">
                <div class="instagram__item set-bg" data-setbg="resources/client/img/instagram/insta-3.jpg">
                    <div class="instagram__text">
                        <i class="fa fa-instagram"></i>
                        <a href="#">@ ashion_shop</a>
                    </div>
                </div>
            </div>
            <div class="col-lg-2 col-md-4 col-sm-4 p-0">
                <div class="instagram__item set-bg" data-setbg="resources/client/img/instagram/insta-4.jpg">
                    <div class="instagram__text">
                        <i class="fa fa-instagram"></i>
                        <a href="#">@ ashion_shop</a>
                    </div>
                </div>
            </div>
            <div class="col-lg-2 col-md-4 col-sm-4 p-0">
                <div class="instagram__item set-bg" data-setbg="resources/client/img/instagram/insta-5.jpg">
                    <div class="instagram__text">
                        <i class="fa fa-instagram"></i>
                        <a href="#">@ ashion_shop</a>
                    </div>
                </div>
            </div>
            <div class="col-lg-2 col-md-4 col-sm-4 p-0">
                <div class="instagram__item set-bg" data-setbg="resources/client/img/instagram/insta-6.jpg">
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
<script>
$("div").click(function(){
	   window.location=$(this).find("a").attr("href"); return false;
	});
</script>
<!-- Js Plugins -->
<jsp:include page="js.jsp"></jsp:include>
</body>

</html>