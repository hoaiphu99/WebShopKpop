<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="vi">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Danh sách người dùng</title>
	<base href="${pageContext.servletContext.contextPath}/">
    <!-- Custom fonts for this template-->
    <link href="resources/admin/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="resources/admin/css/sb-admin-2.min.css" rel="stylesheet">

</head>

<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Sidebar -->
        <jsp:include page="sidebar.jsp"/>
        <!-- End of Sidebar -->

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

                <!-- Topbar -->
                <jsp:include page="topbar.jsp"/>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <div class="d-sm-flex align-items-center justify-content-between mb-4">
                        <h1 class="h3 mb-0 text-gray-800">Danh sách người dùng</h1>
                    </div>
					<div><a class="btn btn-primary" href="admin/user/add.htm" role="button">Thêm mới</a></div>
					<hr>
                    <!-- Content Row -->
                    <!-- DataTales Example -->
          <div class="card shadow mb-4">
              <div class="card-body">
                  <div class="table-responsive">
                      <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                          <thead>
                              <tr>
                                  <th>Tên đăng nhập</th>
                                  <th>Mật khẩu</th>
                                  <th>Họ tên</th>
                                  <th>Giới tính</th>
                                  <th>Email</th>
                                  <th>Số điện thoại</th>
                                  <th>Địa chỉ</th>
                                  <th>Loại</th>
                                  <th></th>
                                  <th></th>
                              </tr>
                          </thead>
                          <tbody>
                          <c:forEach var="lst" items="${lstUser }">
                              <tr>
                                  <td>${lst.username }</td>
                                  <td>${lst.password }</td>
                                  <td>${lst.name }</td>
                                  <td>${lst.gender ? 'Nam':'Nữ' }</td>
                                  <td>${lst.email }</td>
                                  <td>${lst.phone }</td>
                                  <td>${lst.address }</td>
                                  <td>${lst.userRole }</td>
                                  <td><a href="admin/user/update/${lst.id }.htm" class="btn btn-outline-primary">Edit</a></td>
                                  <td><a href="admin/user/delete/${lst.id }.htm" class="btn btn-outline-danger">Delete</a></td>
                              </tr>
                          </c:forEach>
                          </tbody>
                      </table>
                  </div>
              </div>
          </div>
                    
                    <!-- Content Row -->

                    

                    <!-- Content Row -->
                

                </div>
                <!-- /.container-fluid -->

            </div>
            <!-- End of Main Content -->

            <!-- Footer -->
            <jsp:include page="footer.jsp"/>
            <!-- End of Footer -->

        </div>
        <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->

    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>

    <!-- Logout Modal-->
    <jsp:include page="logout-modal.jsp"/>

    <!-- Bootstrap core JavaScript-->
    <script src="resources/admin/vendor/jquery/jquery.min.js"></script>
    <script src="resources/admin/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="resources/admin/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="resources/admin/js/sb-admin-2.min.js"></script>

    <!-- Page level plugins -->
    <script src="resources/admin/vendor/datatables/jquery.dataTables.min.js"></script>
  	<script src="resources/admin/vendor/datatables/dataTables.bootstrap4.min.js"></script>

    <!-- Page level custom scripts -->
    <script src="resources/admin/js/demo/datatables-demo.js"></script>

</body>

</html>
