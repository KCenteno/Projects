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
    
 		<h1>Change ${joy.name}!</h1>
 		<br />
 		
 		<form:form action="/editingJoy/${joy.id}" method="post" modelAttribute="joy">
 		<input type="hidden" name="user" value="${joy.user.id}" />
 		<input type="hidden" name="_method" value="put"/>
                <div class="form-group">
                    <label>Name: ${joy.name}</label>
                    <form:input path="name" type="hidden"  class="form-control" />
                    <form:errors path="name" class="text-danger" />
                </div>
                <br />
                <br />
                <div>
                   <label>Typical Gender: </label>
                   <form:select path="gender">
                   		<option value="${joy.gender}">${joy.gender}</option>
			 			<option>Male</option>
			 			<option>Female</option>
			 			<option>Neutral</option>
		 			</form:select>
                </div>
                <br />
                <div class="form-group">
                    <label>Origin: </label>
                    <form:input path="origin" class="form-control" />
                    <form:errors path="origin" class="text-danger" />
                </div>
                <br />
                <div class="form-group">
                    <label>Meaning: </label>
                    <form:input path="meaning" class="form-control" />
                    <form:errors path="meaning" class="text-danger" />
                </div>
                <a href="/home" class="btn btn-secondary">Cancel</a> <input type="submit" value="submit" class="btn btn-success my-3" />
            </form:form>
 		<a class="btn btn-danger" href="/delete/${joy.id}">Delete</a>
    </div> <!-- End of Container -->
</body>
</html>