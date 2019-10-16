<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>

<title>Trang chủ</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="header.jsp"></jsp:include>


</head>
<body>
	<div id="header-homepage" class="container-fluid">
	 <nav class="navbar navbar-default none-nav">
			<div class="container-fluid">
				<!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
						aria-expanded="false">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="/"
						style="color: deepskyblue">Yame Shop</a>
				</div>

				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						<li><a href="/">Trang chủ</a></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-haspopup="true"
							aria-expanded="false">Sản phẩm <span class="caret"></span></a>
							<ul class="dropdown-menu ">
								<c:forEach var="value" items="${danhMucSanPhams}">
									<li><a
										href="product/${value.madanhmuc}/${value.tendanhmuc}">${value.tendanhmuc}</a></li>
								</c:forEach>
							</ul></li>
						<li><a href="#">Dịch vụ</a></li>
						<li><a href="#">Liên hệ</a></li>
					</ul>
					<ul class="nav navbar-nav navbar-right">
					<%-- 	<c:choose>
							<c:when test="${user != null}">
								<li><a href="#">Xin chào, <span style="color: blue">
											${user}</span></a></li>
								 <li><a href="/logout">Đăng xuất</a></li> 
							</c:when>

							<c:otherwise>
								<li><a href="/registration">Đăng kí</a>
								<li><a href="/login">Đăng nhập </a></li>
							</c:otherwise>
						</c:choose> --%>

						<sec:authorize var="loggedIn" access="isAuthenticated()" />
						<c:choose>
							<c:when test="${loggedIn}">
								 <li><a href="#">Xin chào <span style="color:blue">${username }</span></a></li>
								 <li><a href="/logout">Đăng xuất</a></li> 
							</c:when>
							<c:otherwise>
								<li><a href="/registration">Đăng kí</a>
								<li><a href="/login">Đăng nhập </a></li>
							</c:otherwise>
        
						</c:choose>
						<li><a href="/mycart"><span
								class="glyphicon glyphicon-shopping-cart"></span> 
								 <c:choose>
									<c:when test="${soluongsanphammua >0}">
										<div class="product-number"
											style="height: 20px; text-align: center; position: absolute; width: 28px; font-size: 11px; border-radius: 40px; background: red; line-height: 22px; margin-top: -25px; margin-left: 10px; color: white;">
											<span>${soluongsanphammua}</span>
										</div>
									</c:when>
								
									<c:otherwise>
										<div id="product-number-temp">
											
										</div>
									</c:otherwise>
								</c:choose>  
						</a></li>
					</ul>
				</div>
				<!-- /.navbar-collapse -->
			</div>
			<!-- /.container-fluid -->
		</nav> 

		<div class="event-header container wow bounceInUp">
			<span>Từ ngày 17/10 - 23/10/2017</span><br> <span
				style="font-size: 90px">MUA 1 TẶNG 1</span><br>
			<button>Xem ngay</button>
		</div>
	</div>

	<div id="info" class="container">
		<div class="row">
			<div class="col-md-4 wow tada" data-wow-duration="2s">
				<img class="icon"
					src="<c:url value="/resources/images/icon-chatluong.png" />" alt=""><br>
				<span style="font-size: 32px; font-weight: 400">CHẤT LƯỢNG</span><br>
				<span>Chúng tôi cam kết sẽ mang đến cho bạn chất lượng sản
					phẩm tốt nhất</span>
			</div>
			<div class="col-md-4 wow tada" data-wow-duration="2s"
				data-wow-delay="1s">

				<img class="icon"
					src="<c:url value="/resources/images/saving-money-icon.jpg" />"
					alt=""><br> <span
					style="font-size: 32px; font-weight: 400">TIẾT KIỆM CHI PHÍ</span><br>
				<span>Cam kết giá cả hợp lí ,giúp các bạn tiết kiệm hơn 10%
					cho từng sản phẩm</span>
			</div>
			<div class="col-md-4 wow tada" data-wow-duration="2s"
				data-wow-delay="2s">
				<img class="icon"
					src="<c:url value="/resources/images/icon-shipping.png" />" alt=""><br>
				<span style="font-size: 32px; font-weight: 400">GIAO HÀNG</span><br>
				<span>Cam kết giao hàng tận nơi trong ngày để mang sản phẩm
					đến cho khách hàng tốt nhất</span>
			</div>
		</div>
	</div>
	<div id="sanphammoi" class="container">
		<span>SẢN PHẨM MỚI</span>
		<div class="row">
			<c:forEach var="sanpham" items="${sanPhams}">
				<div class="col-lg-3 ">
					<a href="/detail/${sanpham.masanpham}">
						<div class="sanpham wow zoomIn">
							<img class="icon"
								src="<c:url value="/resources/images/product/${sanpham.hinhsanpham}"/>"
								alt=""><br> <span class="ten">${sanpham.tensanpham}</span><br>
							<span class="gia">${sanpham.giatien} VND</span>
						</div>
					</a>

				</div>
			</c:forEach>
		</div>
	</div>

	<div id="footer" class="container-fluid">
		<div class="row">
			<div class="col-md-4 wow flash">
				<span class="title-footer">Giới thiệu</span><br> <br> <span
					class="info-title-footer">Yame là một thương hiệu thời trang
					đầy uy tín đảm bảo chất lượng sản phẩm tốt nhất cho khách hàng</span><br>
				<span class="info-title-footer">Nhiều sản phẩm với mẫu mã đa
					dạng</span><br> <span class="info-title-footer">Giá thành hợp
					lí</span><br> <span class="info-title-footer">Tận tình giúp đỡ
					, chăm sóc khách hàng</span><br>
			</div>

			<div class="col-md-4 wow flash">
				<span class="title-footer">Liên hệ</span><br> <br> <span
					class="info-title-footer">Tel : 097786672</span><br> <span
					class="info-title-footer">Email : yameshop@gmail.com</span><br>
				<span class="info-title-footer">Address : Cự Lộc,Nhân
					Chính,Thanh Xuân,Hà Nội</span><br>
			</div>

			<div class="col-md-4 wow flash">
				<span class="title-footer">Góp ý của bạn</span><br> <br>
				<form action="" method="post">
					<input name="hoten" type="text" placeholder="Email">
					<textarea name="diachi" placeholder="Nội dung "></textarea>
					<button>Gửi</button>
				</form>
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>

	<%-- <script type="text/javascript" src="<c:url value="/resources/jquery/jquery-3.3.1.min.js" />"> </script>
    <script type="text/javascript" src="<c:url value="/resources/bootstrap/js/bootstrap.min.js" />"></script>--%>

</body>