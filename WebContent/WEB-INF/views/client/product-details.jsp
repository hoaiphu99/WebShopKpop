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
    <title>${product.name }</title>
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

    <!-- Breadcrumb Begin -->
    <div class="breadcrumb-option">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="breadcrumb__links">
                        <a href="."><i class="fa fa-home"></i> Home</a>
                        <a href="category/${product.category.name }.htm">${product.category.name } </a>
                        <span>${product.name }</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Breadcrumb End -->

    <!-- Product Details Section Begin -->
    <section class="product-details spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-6">
                    <div class="product__details__pic">
                        <div class="product__details__pic__left product__thumb nice-scroll">
                            <a class="pt active" href="#product-1">
                                <img src="resources/client/img/product/${product.photo }" alt="">
                            </a>
                        </div>
                        <div class="product__details__slider__content">
                            <div class="product__details__pic__slider owl-carousel">
                                <img data-hash="product-1" class="product__big__img" src="resources/client/img/product/${product.photo }" alt="">
                                
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="product__details__text">
                        <h3>${product.name }</h3>
                        <div class="product__details__price"><fmt:formatNumber value = "${product.price - product.price*product.discount}" type = "number" maxFractionDigits = "0"/> VNĐ <c:if test="${product.discount != 0 }"><span><fmt:formatNumber value = "${product.price}" type = "number" maxFractionDigits = "0"/> VNĐ</span></c:if></div>
                        
                        <div class="product__details__button">
                            <div class="quantity">
                                <span>Số lượng:</span>
                                <div class="pro-qty">
                                    <input type="number" min="1" max="${product.quantity }" id="quantity-cart-${product.id }" value="1">
                                </div>
                            </div>
                            <button class="cart-btn" data-id="${product.id }"><span class="icon_bag_alt"></span> Thêm vào giỏ hàng</button>
                            
                            <!-- <ul>
                                <li><a href="#"><span class="icon_heart_alt"></span></a></li>
                                <li><a href="#"><span class="icon_adjust-horiz"></span></a></li>
                            </ul> -->
                        </div>
                        <span>Còn lại </span><span id="slsp">${product.quantity }</span><span> sản phẩm</span>
                        <div class="product__details__widget">
                            <ul>
                                <li>
                                    <span>Ưu đãi:</span>
                                    <p>Miễn phí vận chuyển</p>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="col-lg-12">
                    <div class="product__details__tab">
                        <ul class="nav nav-tabs" role="tablist">
                            <li class="nav-item">
                                <a class="nav-link active" data-toggle="tab" href="#tabs-1" role="tab">Mô tả sản phẩm</a>
                            </li>
                            <!-- <li class="nav-item">
                                <a class="nav-link" data-toggle="tab" href="#tabs-2" role="tab">Specification</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" data-toggle="tab" href="#tabs-3" role="tab">Reviews ( 2 )</a>
                            </li> -->
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane active" id="tabs-1" role="tabpanel">
                                <h6>Chi tiết</h6>
                                <p>${product.description }</p>
                            </div>
                            <!-- <div class="tab-pane" id="tabs-2" role="tabpanel">
                                <h6>Specification</h6>
                                <p>Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut loret fugit, sed
                                    quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt loret.
                                    Neque porro lorem quisquam est, qui dolorem ipsum quia dolor si. Nemo enim ipsam
                                    voluptatem quia voluptas sit aspernatur aut odit aut loret fugit, sed quia ipsu
                                    consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Nulla
                                consequat massa quis enim.</p>
                                <p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget
                                    dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes,
                                    nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium
                                quis, sem.</p>
                            </div>
                            <div class="tab-pane" id="tabs-3" role="tabpanel">
                                <h6>Reviews ( 2 )</h6>
                                <p>Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut loret fugit, sed
                                    quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt loret.
                                    Neque porro lorem quisquam est, qui dolorem ipsum quia dolor si. Nemo enim ipsam
                                    voluptatem quia voluptas sit aspernatur aut odit aut loret fugit, sed quia ipsu
                                    consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Nulla
                                consequat massa quis enim.</p>
                                <p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget
                                    dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes,
                                    nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium
                                quis, sem.</p>
                            </div> -->
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12 text-center">
                    <div class="related__title">
                        <h5>Sản phẩm liên quan</h5>
                    </div>
                </div>
                <c:forEach var="i" items="${relatedProd }">
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
    <!-- Product Details Section End -->

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
    <script type="text/javascript">
    	$(".cart-btn").on("click", function(){
            var id = $(this).data("id");
            var quantity = $("#quantity-cart-" + id).val();
            var sl = $("#slsp").text();
    	    if(quantity > sl){
    	    	alert("Sản phẩm đã hết hàng!");
    	    }
    	    else{
    	    	window.location = "add-cart/" + id + "/" + quantity + ".htm";
                alert("Đã thêm vào giỏ hàng!");
    	    }
        });
    </script>
</body>

</html>