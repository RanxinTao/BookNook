<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="scripts/jquery-3.1.1.js"></script>
<script>

	$(function(){
		$("#pageNo").change(function(){
			var val = $(this).val();
			val = $.trim(val);
			var flag = true;
			//1. verify if val is a number
			var reg = /^\d+$/g;
			if(!reg.test(val))
				flag = false;
			//2. verify if val falls within a legal range (1-totalPageNo)
			var pageNo = parseInt(val);
			if(pageNo < 1 || pageNo > parseInt("${bookpage.totalPageNo }"))
				flag = false;
			if(!flag) {
				alert("input is not a valid number");
				$(this).val("");
				return;
			}
			//3. redirect
			var href = "bookServlet?method=getBooks&pageNo=" + pageNo + "&" + $(":hidden").serialize();
			window.location.href = href;
		});
	});
	
</script>
<%@ include file="/commons/queryCondition.jsp" %>
</head>
<body>
	
	<center>
	
		<c:if test="${param.title != null}">
			You have successfully added ${param.title } into your shopping cart.
			<br><br>
		</c:if>
		
		<c:if test="${!empty sessionScope.ShoppingCart.books }">
			Your shopping cart has ${sessionScope.ShoppingCart.totalBookNo } book(s), <a href="bookServlet?method=toCartPage&pageNo=${bookpage.pageNo }">view your shopping cart</a>
		</c:if>
		
		<br><br>
		<form action="bookServlet?method=getBooks" method="post">
			Price: 
			<input type="text" size="1" name="minPrice"/> - 
			<input type="text" size="1" name="maxPrice"/>
			
			<input type="submit" value="Submit"/>
		</form>
		
		<br><br>
		<table cellpadding="10">
			<c:forEach items="${bookpage.list }" var="book">
				<tr>
					<td>
						<a href="bookServlet?method=getBook&pageNo=${bookpage.pageNo }&id=${book.id }">${book.title }</a>
						<br>
						${book.author }
					</td>
					<td>${book.price }</td>
					<td><a href="bookServlet?method=addToCart&pageNo=${bookpage.pageNo }&id=${book.id }&title=${book.title }">add to cart</a></td>
				</tr>
			</c:forEach>
		</table>
		
		<br><br>
		${bookpage.pageNo } in ${bookpage.totalPageNo } pages
		&nbsp;&nbsp;
		
		<c:if test="${bookpage.hasPrevPage() }">
			<a href="bookServlet?method=getBooks&pageNo=1">first page</a>
			&nbsp;&nbsp;
			<a href="bookServlet?method=getBooks&pageNo=${bookpage.prevPageNo }">previous page</a>	
		</c:if>
		
		&nbsp;&nbsp;
		
		<c:if test="${bookpage.hasNextPage() }">
			<a href="bookServlet?method=getBooks&pageNo=${bookpage.nextPageNo }">next page</a>
			&nbsp;&nbsp;
			<a href="bookServlet?method=getBooks&pageNo=${bookpage.totalPageNo }">last page</a>	
		</c:if>
		
		&nbsp;&nbsp;
		
		To page <input type="text" size="1" id="pageNo"/>
		
	</center>

</body>
</html>