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
<title>Project Manager Dashboard</title>
  <!-- Bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" 
      rel="stylesheet" 
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" 
      crossorigin="anonymous">

</head>
<body>
    <div class="container p-5 my-3 bg-dark text-white"> <!-- Beginning of Container -->
 		<h2>Hello, ${user_name}. Here are some names that people like.. <a href="/logout" class="btn btn-warning float-end">Logout</a></h2>
 		
 	<table class="table table-dark table-striped table-hover">
      <thead>
        <tr>
          <th class="align-middle"></th>
          <th class="align-middle">Names</th>
          <th class="align-middle"></th>
          <th class="align-middle"></th>
          <th class="align-middle">Votes</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="i" items="${allJoys}">
          <tr>
            <td>            
            <c:if test="${i.users.contains(user)}" >
            Voted
            </c:if>
            <c:if test="${!i.users.contains(user)}">
            <Form action="/upvote/${i.id}/${user_id}" method="post"> 
            <input type="hidden" name="_method" value="put">
            <input type="submit" value="upvote" class="btn btn-primary">
             </Form>
            </c:if>
            </td>
            <td><a href="/names/${i.id}"><c:out value="${i.name}"></c:out></a></td>
			<td><c:out value="${i.gender}"></c:out></td>
			<td>Origin: <c:out value="${i.origin}"></c:out></td>            
            <td>
              <c:out value="${i.users.size()}"></c:out>
            </td>
          </tr>
        </c:forEach>
      </tbody>
   </table>
 	<a href="/names/new" class="btn btn-info">Add a new name</a>
    </div> <!-- End of Container -->
</body>
</html>