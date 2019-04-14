<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create New Book</title>
<link rel="stylesheet" href="../css/style.css"/>
<script type="text/javascript" src="../js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
<link rel="stylesheet" href="../css/jquery-ui.min.css"/>
<script type="text/javascript" src="../js/jquery-ui.min.js"></script>

</head>
<body>

	<jsp:directive.include file="header.jsp" />
	<div align="center">
	
		<h2 class="pageheading">
		<c:if test="${book!=null }">
		Edit Book
		</c:if>
		<c:if test="${book==null }">
		Create New Book
		</c:if>
		</h2>
	</div>


	<div align="center">
	    <c:if test="${book!=null }">/
	    <form action="update_user" method="post" id="userForm">
	    <input type="hidden" name="userId" value="${user.userId}">
	    </c:if>
	    <c:if test="${book==null }">
		<form action="create_book" method="post" id="bookForm" enctype="multipart/form-data">
		</c:if>
			<table class="form">
				<tr>
				  <td>Category:</td>
				  <td>
				    <select name="category">
				      <c:forEach items="${listCategory}" var="category">
				          <option value="${category.categoryId}">
				             ${category.name}
				          </option>
				      </c:forEach>
				    </select>
				  </td>
				</tr>
				
				<tr>
					<td align="right">Title:</td>
					<td align="left"><input type="text" value="${book.title}"id="title" name="title" size="20"></td>
				</tr>

				<tr>
					<td align="right">Author:</td>
					<td align="left"><input type="text"  value="${book.author}" id="author" name="author" size="20"></td>
				</tr>
                
                <tr>
					<td align="right">ISBN:</td>
					<td align="left"><input type="text"  value="${book.isbn}" id="isbn" name="isbn" size="20"></td>
				</tr>
				
				<tr>
					<td align="right">Publish Date:</td>
					<td align="left"><input type="text"  value="${book.publishDate}" id="publishDate" name="publishDate" size="20"></td>
				</tr>
				
                <tr>
					<td align="right">Book Image:</td>
					<td align="left">
					<input type="file"  id="bookImage" name="bookImage" size="20"><br/>
					<img id="thumbnail" alt="Image Preview" style="width:20%;margin-top:10px"/>
					</td>
				</tr>
				
                <tr>
					<td align="right">Price:</td>
					<td align="left"><input type="text"  id="price" name="price" size="20" value="${book.price}"></td>
				</tr>
				
				<tr>
					<td align="right">Description:</td>
					<td align="left">
					   <textarea rows="5" cols="50" id="description" name="description"></textarea>
					   
					</td>
				</tr>
				 
            
				<tr>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td colspan="2" align="center">
					<button type="submit"
						>Save</button>&nbsp;&nbsp;&nbsp; 
						<button id="buttonCancel">Cancel</button>
					</td>
				</tr>
			</table>
		</form>
	</div>

	<jsp:directive.include file="footer.jsp" />

</body>

<script type="text/javascript">

$(document).ready(function(){
	$('#publishDate').datepicker();
	
	$('#bookImage').change(function(){
		showImageThumbnail(this);
	});
	
	$("#bookForm").validate({
		rules :{
			category:"required",
			title:"required",
			author:"required",
	        isbn: "required",
	        publishDate: "required",
	        bookImage: "required",
	        description: "required",
	        price: "required",

		},
		messages:{
			category: "Please select a category for the book",
			title: "Please enter title of the book",
			author: "Please enter author of the book",
			isbn: "Please enter ISBN of the book",
			publishDate: "Please enter publish date of the book",
			bookImage: "Please choose an image for the book",
			description: "Please enter description for the book",
			price: "Please enter price of the book"
			
		}
	});
	$("#buttonCancel").click(function(){
		history.go(-1);
	});
});

function showImageThumbnail(fileInput){
	var file=fileInput.files[0];
	var read= new FileReader();
	
	read.onload=function(e){
	  $('#thumbnail').attr('src', e.target.result);	
	};
	
	read.readAsDataURL(file);
}

</script>
</html>