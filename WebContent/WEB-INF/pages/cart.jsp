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
		
		//Ajax: change the quantity of a single item:
		//1. add onchange function on all text contents in this page
		$(":text").change(function(){
			var quantityVal = $.trim(this.value);
			
			var flag = false;
			var reg = /^\d+$/g;
			var quantity = -1;
			if(reg.test(quantityVal)){
				quantity = parseInt(quantityVal);
				if(quantity >= 0)
					flag = true;
			}
			
			if(!flag) {
				alert("Your input is not valid!");
				$(this).val($(this).attr("class"));
				return;
			}
			
			var $tr = $(this).parent().parent();
			var title = $.trim($tr.find("td:first").text());
			
			if(quantity == 0){
				var flag2 = confirm("Are you sure you want to delete " + title + "?");
				if(flag2){
					//get "a" node
					var $a = $tr.find("td:last").find("a");
					
					//execute "a" node's onclick function
					$a[0].onclick();
					return;
				}
			}
			
			var flag = confirm("Are you sure you want to change the quantity of " + title + "?");
			
			if(!flag) {
				$(this).val($(this).attr("class"));
				return;
			}
			
			//2. request url: bookServlet
			var url = "bookServlet";
			
			//3. request params: method(updateItemQuantity), id, quantity, time
			var idVal = $.trim(this.name);
			var args = {"method": "updateItemQuantity", "id": idVal, "quantity": quantityVal, "time": new Date()};
			
			//4. update totalBookNo and totalCost in the current page based on the returned JSON data
			$.post(url, args, function(data){
				var totalBookNo = data.totalBookNo;
				var totalCost = data.totalCost;
				
				$("#totalCost").text("Total: $" + totalCost);
				$("#totalBookNo").text("Your Shopping cart has " + totalBookNo + " book(s)");
			}, "JSON");
		});
	});

</script>

</head>
<body>

	<center>
	
		<br><br>
		<div id="totalBookNo">Your Shopping cart has ${sessionScope.ShoppingCart.totalBookNo } book(s).</div>
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
					<td><input class="${item.quantity }" type="text" size="1" name="${item.book.id }" value="${item.quantity }"/></td>
					<td>${item.book.price }</td>
					<td><a href="bookServlet?method=remove&pageNo=${param.pageNo }&id=${item.book.id }" class="delete">Delete</a></td>
				</tr>
			</c:forEach>
			
			<tr>
				<td colspan="4" id="totalCost">Total: $${sessionScope.ShoppingCart.totalCost }</td>
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