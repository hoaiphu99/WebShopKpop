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
    <title>Giỏ hàng</title>
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
    
    <!-- Shop Cart Section Begin -->
    <section class="shop-cart spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="shop__cart__table">
                        <table>
                            <thead>
                                <tr>
                                    <th>Sản phẩm</th>
                                    <th>Giá</th>
                                    <th>Số lượng</th>
                                    <th>Tổng cộng</th>
                                    <th></th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="i" items="${cart }">
                            	<tr>
                                    <td class="cart__product__item">
                                        <img src="resources/client/img/product/${i.value.product.photo }" alt="" width="150px" height="150px">
                                        <div class="cart__product__item__title">
                                            <h6>${i.value.product.name }</h6>
                                        </div>
                                    </td>
                                    <td class="cart__price"><f:formatNumber value="${i.value.product.price }" type="number"/></td>
                                    <td class="cart__quantity">
                                        <div class="pro-qty">
                                            <input type="number" min="1" max="${i.value.product.quantity }" value="${i.value.quantity }" id="quantity-cart-${i.key }" class="cart-quantity-input">
                                        </div>
                                    </td>
                                    <td class="cart__total"><f:formatNumber value="${i.value.totalPrice}" type="number"/></td>
                                    <td class="cart__close"><a href="delete-cart/${i.key }.htm"><span class="icon_close"></span></a></td>
                                    <td class="cart__close"><button class="edit-cart" data-id="${i.key }"><span>Edit</span></button></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="row">
            <div class="col-lg-6 col-md-6 col-sm-6">
	            <c:if test="${totalQuantityCart == 0}">
	            	<span>Không có sản phẩm trong giỏ hàng, hãy tiếp tục mua sắm.</span>
            	</c:if>
	            </div>
            </div>
            <div class="row">
                <div class="col-lg-6 col-md-6 col-sm-6">
                    <div class="cart__btn">
                        <a href=".">Tiếp tục mua sắm</a>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-6">
                    <div class="discount__content">
                        <h6>Mã giảm giá</h6>
                        <form action="#">
                            <input type="text" placeholder="Nhập mã giảm giá ở đây">
                            <button type="submit" class="site-btn" style="<c:if test="${totalQuantityCart == 0}">visibility: hidden;</c:if>">Áp dụng</button>
                        </form>
                    </div>
                </div>
                <div class="col-lg-4 offset-lg-2">
                    <div class="cart__total__procced">
                        <h6>Thành tiền</h6>
                        <ul>
                            <li>Tổng cộng <span class="cart-total-price"><f:formatNumber value="${totalPriceCart }" type="number"/> VNĐ</span></li>
                        </ul>
                        <c:if test="${totalQuantityCart > 0}">
                        <a href="xac-nhan-mua-hang.htm" class="primary-btn">Xác nhận mua hàng</a>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Shop Cart Section End -->
    

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
    	$(".edit-cart").on("click", function(){
            var id = $(this).data("id");
            var quantity = $("#quantity-cart-" + id).val();
            window.location = "edit-cart/" + id + "/" + quantity + ".htm";
        });
    </script>
</body>

</html>