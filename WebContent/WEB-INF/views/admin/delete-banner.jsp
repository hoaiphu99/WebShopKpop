<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="vi">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Xóa banner</title>
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
                        <h1 class="h3 mb-0 text-gray-800">Xóa banner</h1>
                    </div>

                    <!-- Content Row -->
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-md-6">
                            <div>
							<p style="text-align: center; color: red">
								<b>${failFile}</b>
							</p>

							<p style="text-align: center; color: red">
								<b>${failAdd}</b>
							</p>
							<p style="text-align: center; color: red">
								<b>${msgEmpty}</b>
							</p>

							<p style="text-align: center; color: red">
								<b>${successAdd}</b>
							</p>
						</div>
                                <form:form action="admin/banner/delete.htm" method="POST" modelAttribute="banner" enctype="multipart/form-data">
                                    <div class="form-group">
                                        <label>ID</label>
                                        <form:input path="id" type="text" class="form-control" readonly="true"/>
                                    </div>
                                    <div class="form-group">
                                        <label>Tiêu đề trên</label>
                                        <form:input path="smallTitle" type="text" class="form-control" readonly="true"/>
                                    </div>
                                    <div class="form-group">
                                        <label for="">Tiêu đề dưới</label>
                                        <form:input path="bigTitle" type="text" class="form-control" readonly="true"/>
                                    </div>
                                    <div class="form-group">
                                        <label for="">Giảm giá</label>
                                        <div class="input-group-prepend">
                                            <form:input type="number" path="discount" class="form-control" placeholder="" readonly="true"/>
                                            <span class="input-group-text" id="inputGroupPrepend">%</span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="">Link sản phẩm</label>
                                        <form:input path="link" type="text" class="form-control" readonly="true"/>
                                    </div>
                                    <div class="form-group">
                                        <label>Số ngày</label>
                                        <form:input path="day" type="number" class="form-control" readonly="true"/>
                                    </div>
                                    <div class="form-group">
                                         <div class="btn btn-default btn-file">
											<i class="fa fa-paperclip"> </i> Image <input type="file"
												name="attachment">
										</div>
                                    </div>
                                    <button type="submit" class="btn btn-primary">Xóa</button>
                                    <a class="btn btn-secondary" href="admin/banner/list.htm" role="button">Thoát</a>
                                </form:form>
                            </div> <!-- end of col-md-6 -->
                        </div> <!-- end of row -->
                    </div> <!-- end of container-fluid -->
                    
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
    <jsp:include page="logout-modal.jsp" />

    <!-- Bootstrap core JavaScript-->
    <script src="resources/admin/vendor/jquery/jquery.min.js"></script>
    <script src="resources/admin/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="resources/admin/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="resources/admin/js/sb-admin-2.min.js"></script>

    <!-- Page level plugins -->
    <script src="resources/admin/vendor/chart.js/Chart.min.js"></script>

    <!-- Page level custom scripts -->
    <script src="resources/admin/js/demo/chart-area-demo.js"></script>
    <script src="resources/admin/js/demo/chart-pie-demo.js"></script>

</body>

</html>
