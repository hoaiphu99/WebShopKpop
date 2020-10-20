<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="form" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
	<title> Kpop Shop </title>
    
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<link rel="stylesheet" href="resources/web/vendor/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="resources/web/css/web.css">
    <link rel="stylesheet" href="resources/web/vendor/fonts/font-awesome.css">
    <link rel="stylesheet" href="resources/web/vendor/fonts/all.css">
    

    <!-- Bootstrap core CSS -->
    <link href="resources/web/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="resources/web/css/small-business.css" rel="stylesheet">
</head>
<body >
    <div class="topheader">
		<div class="container">
			<div class="row">
				<div class="col-sm-8">
					<a href="#" class="logo">
						<img src="resources/web/img/logoBP.jpg" alt="" class="imgLogo">
					</a>
				</div>
				<div class="col-sm-4">
					<form action="" class="form-inline">
                        <input type="search" class="form-control mr-sm-2 mt-sm-3" placeholder="Search">
                        <button type="submit" class="btn btn-outline-success my-2 my-sm-0 mt-sm-3">Tìm</button>
                    </form>
				</div>
			</div>
		</div>
	</div> <!-- end topheader -->
	<div class="bottomheader">
		<div class="container">
			<nav class="navbar navbar-light bg-faded trongsuot">
				<button class="navbar-toggler hidden-sm-up" type="button" data-toggle="collapse" data-target="#mtren">
				
				</button>
				<div class="collapse navbar-toggleable-xs" id="mtren">
				
				<ul class="nav navbar-nav">
					<li class="nav-item active">
						<a class="nav-link" href="#">HOME</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="#">Album</a>
                    </li>
                    <li class="nav-item">
						<a class="nav-link" href="#">Photobook</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="#">Magazine</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="#">Fashion</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="#">Beauty</a>
					</li>
				</ul>
				</div>
			</nav>
		</div>
	</div> <!-- end bottomheader -->
    
    <!-- Page content -->
    <div class="container khoiduoi mt-2">
        <div class="container">
            <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
                <!-- <ol class="carousel-indicators">
                    <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                    <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                    <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
                </ol> -->
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <img class="d-block w-100" src="resources/web/img/sl1.jpg" alt="First slide">
                    </div>
                    <div class="carousel-item">
                        <img class="d-block w-100" src="resources/web/img/sl2.jpg" alt="Second slide">
                    </div>
                    <div class="carousel-item">
                        <img class="d-block w-100" src="resources/web/img/sl3.jpg" alt="Third slide">
                    </div>
                </div>
                <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>
        </div>
        
        <!-- Content Row -->
        <div class="title">Album Chart</div>
        <div class="row">
            <div class="col-md-4 mb-5">
                <div class="topBox">
                    <img src="resources/web/img/t1.jpg" alt="" class="layer1">
                    <div class="background"></div>
                    <div class="iCons">
                        <div class="nutmua"><a href="#">Mua ngay</a></div>
                        <div class="nutmua"><a href="#">Thêm vào giỏ hàng</a></div>
                    </div>
                </div>
                <div class="botBox">
                    <div class="nameProduct">BLACKPINK - 1st FULL ALBUM [THE ALBUM] (Ver.1)</div>
                    <div class="price">$16.70 <del>$22.17</del></div>
                </div>
            </div>
            <!-- /.col-md-4 -->
            <div class="col-md-4 mb-5">
                <div class="topBox">
                    <img src="resources/web/img/t2.jpg" alt="" class="layer1">
                    <div class="background"></div>
                    <div class="iCons">
                        <div class="nutmua"><a href="#">Mua ngay</a></div>
                        <div class="nutmua"><a href="#">Thêm vào giỏ hàng</a></div>
                    </div>
                </div>
                <div class="botBox">
                    <div class="nameProduct">TREASURE - 2nd SINGLE ALBUM [THE FIRST STEP : CHAPTER TWO] (WHITE ver.)</div>
                    <div class="price">$12.30 <del>$16.61</del></div>
                </div>
            </div>
            <!-- /.col-md-4 -->
            <div class="col-md-4 mb-5">
                <div class="topBox">
                    <img src="resources/web/img/t3.jpg" alt="" class="layer1">
                    <div class="background"></div>
                    <div class="iCons">
                        <div class="nutmua"><a href="#">Mua ngay</a></div>
                        <div class="nutmua"><a href="#">Thêm vào giỏ hàng</a></div>
                    </div>
                </div>
                <div class="botBox">
                    <div class="nameProduct">Super Junior D&E - Mini Album Vol.4 [Special Album]</div>
                    <div class="price">$21.37 <del>$17.15</del></div>
                </div>
            </div>
            <!-- /.col-md-4 -->
            <div class="col-md-4 mb-5">
                <div class="topBox">
                    <img src="resources/web/img/t4.jpg" alt="" class="layer1">
                    <div class="background"></div>
                    <div class="iCons">
                        <div class="nutmua"><a href="#">Mua ngay</a></div>
                        <div class="nutmua"><a href="#">Thêm vào giỏ hàng</a></div>
                    </div>
                </div>
                <div class="botBox">
                    <div class="nameProduct">EVERGLOW - Mini Album Vol.2 [-77.82X-78.29] (-78.29 Ver.) (second press)</div>
                    <div class="price">$12.93 <del>$16.61</del></div>
                </div>
            </div>
            <!-- /.col-md-4 -->
            <div class="col-md-4 mb-5">
                    <div class="topBox">
                        <img src="resources/web/img/t5.jpg" alt="" class="layer1">
                        <div class="background"></div>
                        <div class="iCons">
                            <div class="nutmua"><a href="#">Mua ngay</a></div>
                            <div class="nutmua"><a href="#">Thêm vào giỏ hàng</a></div>
                        </div>
                    </div>
                    <div class="botBox">
                        <div class="nameProduct">[SET][3CD SET] THE BOYZ - Mini Album Vol.5 [CHASE] (CHASE ver. + STEALER ver. + TRICK ver.)</div>
                        <div class="price">$45.78 <del>$56.82</del></div>
                    </div>
            </div>
            <!-- /.col-md-4 -->
            <div class="col-md-4 mb-5">
                    <div class="topBox">
                        <img src="resources/web/img/t6.jpg" alt="" class="layer1">
                        <div class="background"></div>
                        <div class="iCons">
                            <div class="nutmua"><a href="#">Mua ngay</a></div>
                            <div class="nutmua"><a href="#">Thêm vào giỏ hàng</a></div>
                        </div>
                    </div>
                    <div class="botBox">
                        <div class="nameProduct">UP10TION - Mini Album Vol.9 [Light UP] (LIGHT SPECTRUM Ver.)</div>
                        <div class="price">$15.89 <del>$19.75</del></div>
                    </div>
            </div>
            <!-- /.col-md-4 -->
    
        </div>
        <!-- /.row -->
    </div> <!-- end page content container -->
    
    <!-- Footer -->
    <footer class="py-5 bg-dark">
        <div class="container">
        <p class="m-0 text-center text-white">Copyright &copy; 2020 by Kpop Shop</p>
        </div>
        <!-- /.container -->
    </footer>

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <script defer src="https://use.fontawesome.com/releases/v5.0.8/js/all.js" integrity="sha384-SlE991lGASHoBfWbelyBPLsUlwY1GwNDJo3jSJO04KZ33K2bwfV9YBauFfnzvynJ" crossorigin="anonymous"></script>

    <!-- bootstrap script -->
    <script type="text/javascript" src="resources/web/vendor/bootstrap/js/bootstrap.js"></script>
	<script type="text/javascript" src="resources/web/js/web.js"></script>
    
</body>
</html>