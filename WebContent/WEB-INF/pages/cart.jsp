<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="scripts/jquery-3.1.1.js"></script>
<%@ include file="/commons/queryCondition.jsp" %>
<script>
	
	$(function(){
		$(".delete").click(function(){
			var $tr = $(this).parent().parent();
			var title = $.trim($tr.find("td:first").text());
			var flag = confirm("Are you sure you want to delete " + title + "?");
			
			if(flag) {
				return true;
			}
			return false;
		});
	});

</script>

</head>
<body>

	<center>
	
		<br><br>
		Your Shopping cart has ${sessionScope.ShoppingCart.totalBookNo } book(s).
		<table cellpadding="10">
			<tr>
				<td>Title</td>
				<td>Quantity</td>
				<td>Price</td>
				<td>&nbsp;</td>
			</tr>
			
			<c:forEach items="${sessionScope.ShoppingCart.items }" var="item">
				<tr>
					<td>${item.book.title }</td>
					<td>${item.book.price }</td>
					<td>${item.quantity }</td>
					<td><a href="bookServlet?method=remove&pageNo=${param.pageNo }&id=${item.book.id }" class="delete">Delete</a></td>
				</tr>
			</c:forEach>
			
			<tr>
				<td colspan="4">Total: $${sessionScope.ShoppingCart.totalCost }</td>
			</tr>
			
			<tr>
				<td colspan="4">
					<a href="bookServlet?method=getBooks&pageNo=${param.pageNo }">Continue Shopping</a>
					&nbsp;&nbsp;
					
					<a href="bookServlet?method=clear">Clear Shopping Cart</a>
					&nbsp;&nbsp;
					
					<a href="">Check Out</a>
					&nbsp;&nbsp;
				</td>
			</tr>
			
		</table>
	
	</center>

	
	<br><br>
	
</body>
</html>