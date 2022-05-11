<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
   <!-- c:out ; c:forEach ; c:if -->
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
   <!-- Formatting (like dates) -->
 <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
   <!-- form:form -->
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
   <!-- for rendering errors on PUT routes -->
 <%@ page isErrorPage="true" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create a Task</title>
  <!-- Bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" 
      rel="stylesheet" 
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" 
      crossorigin="anonymous">

</head>
<body>
    <div class="container p-5 my-3 bg-dark text-white text-center"> <!-- Beginning of Container -->
 		<h1>${joy.name}</h1>
		 	<h2>(added by ${joy.user.firstName})</h2>
	 		<c:choose>
	 			<c:when test="${user_id == joy.user.id}">
				 	<p>Gender: ${joy.gender} </p>
				 	<p>Origin: ${joy.origin}</p>
				 	<h4>
				 	<c:if test="${joy.users.contains(user)}" >
		            You Voted for this name.
		            </c:if>
		            <c:if test="${!joy.users.contains(user)}">
		            <Form action="/upvote/${joy.id}/${user_id}" method="post"> 
		            <input type="hidden" name="_method" value="put">
		            <input type="submit" value="upvote" class="btn btn-primary">
		             </Form>
		            </c:if>
				 	</h4>
				 	<p>Meaing: ${joy.meaning}</p>
				 	<a class="btn btn-primary" href="/names/${id}/edit">Edit</a>
	 			</c:when>
	 			<c:otherwise>
	 				<p>Gender: ${joy.gender} </p>
				 	<p>Origin: ${joy.origin}</p>
				 	<h4>
				 	<c:if test="${joy.users.contains(user)}" >
		            You Voted for this name.
		            </c:if>
		            <c:if test="${!joy.users.contains(user)}">
		            <Form action="/upvote/${joy.id}/${user_id}" method="post"> 
		            <input type="hidden" name="_method" value="put">
		            <input type="submit" value="upvote" class="btn btn-primary">
		             </Form>
		            </c:if>
				 	</h4>
				 	<p>Meaing: ${joy.meaning}</p>
	 			</c:otherwise>
	 		</c:choose>
 		<a href="/home" class="btn btn-info">Lets pick another name.</a>
    </div> <!-- End of Container -->
</body>
</html>