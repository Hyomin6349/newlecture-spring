<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>


<!DOCTYPE html>
<html>

<head>
<title>코딩 전문가를 만들기 위한 온라인 강의 시스템</title>
<meta charset="UTF-8">
<title>공지사항목록</title>

<link href="/css/layout.css" type="text/css" rel="stylesheet" />
<link href="/css/index.css" type="text/css" rel="stylesheet" />
<script>
	
</script>
</head>

<body>
	<!-- header 부분 -->
	<tiles:insertAttribute name="header" />


	<!-- --------------------------- <body> --------------------------------------- -->

	<!-- content 부분 -->



	<div id="visual" class="">
		<div class="content-container">
			<h2 class="hidden">신규 강좌목록</h2>
			<!-- <ul class="mov-button">
			<li class="prev-button">이전</li>
			<li class="next-button">다음</li>
		</ul> -->
			<ul class="banner">


				<li class="banner1"><a href="customer/event/1"> <img
						src="admin/board/event/1/banner-java.png" data-id="1"
						style="cursor: pointer;" />
				</a></li>

			</ul>



			<ul class="banner-button-list"
				style="color: #ffff00; font-size: 20px; position: absolute; left: 10px; bottom: 5px; z-index: 100px; display: flex; flex-direction: row;">
				<li></li>
			</ul>

		</div>

	</div>
	<div id="notice">
		<div class="content-container">
			<span class="title">제대로된 전문가들이 만들어가는 <span
				style="color: yellow; font-size: 15px;">IT PROFESSIONAL <span
					style="color: #00ffff;">NEW</span> ONLINE <span
					style="color: #00ffff;">LECTURE</span> MARKET
			</span></span>
			<!--<a class="detail-button">자세히</a>-->
		</div>
	</div>
	<!-- ----- 공지사항 줄 ------------------------------------------------------------------------------ -->
	<div id="information">
		<div class="content-container">
			<section class="guide">
				<h1 class="title">강의 플레이어 사용방법 안내</h1>
				<div class="margin-top">
					<a href="customer/faq/1"><img
						src="images/customer/installInfo.png" /></a>
				</div>
				<!-- <div>
            2
            </div>
            <div>
            3
            </div> -->
			</section>
			<section class="course-info">
				<h1 class="title text-center">뉴렉처 하이브리드 과정 모집</h1>
				<ul class="list">
					<li>현재 모집 과정이 없습니다.</li>
				</ul>
			</section>
			<section class="notice">
				<h1 class="title">공지사항</h1>
				<ul class="list margin-top">

					<li><span class="notice-title"> <a
							href="notice/detail.html">스프링 8강까지의 예제 코드</a>
					</span> <span>2019-08-18</span></li>

					<li><span class="notice-title"> <a
							href="notice/detail.html">스프링 DI 예제 코드</a>
					</span> <span>2019-08-15</span></li>

					<li><span class="notice-title"> <a
							href="notice/detail.html">뉴렉쌤 9월 초 국기과정 모집 안내</a>
					</span> <span>2019-06-11</span></li>

					<li><span class="notice-title"> <a
							href="notice/detail.html">뉴렉처 강의 수강 방식 안내</a>
					</span> <span>2019-05-24</span></li>

					<li><span class="notice-title"> <a
							href="notice/detail.html">자바 구조적인 프로그래밍 강의 예제 파일</a>
					</span> <span>2019-04-24</span></li>

				</ul>
			</section>
		</div>
	</div>

	<!-- ----- 강좌 목록 시작 줄 --------------------------------------------------------------------------------------------------------- -->

	<tiles:insertAttribute name="body" />
	<!-- ------------------- <footer> --------------------------------------- -->

	<tiles:insertAttribute name="footer" />



</body>

</html>