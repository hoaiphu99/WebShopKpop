<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<div class="col-lg-4">
       <div class="list-group">
       	   <c:if test="${mUser.userRole == 'admin' }"><a href="admin/index.htm" class="list-group-item list-group-item-action list-group-item-warning">Trang admin</a></c:if>
           <a href="thong-tin-tai-khoan.htm" class="list-group-item list-group-item-action active">
            Thông tin tài khoản
            </a>
            <a href="chinh-sua-thong-tin.htm" class="list-group-item list-group-item-action">Chỉnh sửa thông tin</a>
            <a href="xem-don-hang.htm" class="list-group-item list-group-item-action">Xem đơn hàng</a>
            <a href="doi-mat-khau.htm" class="list-group-item list-group-item-action">Đổi mật khẩu</a>
            <a href="logout.htm" class="list-group-item list-group-item-action list-group-item-danger">Đăng xuất</a>
       </div>
</div>