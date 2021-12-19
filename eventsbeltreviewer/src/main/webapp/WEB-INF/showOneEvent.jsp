<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
	<meta charset="ISO-8859-1">
	<title>${event.name}</title>
</head>
<body>

	<h1>${event.name}</h1>
	<br>
	<a href="/events">Back to main Page</a>
	
	<br>
	<br>
	<!-- remember project.likes.size() -->
	
	<!--  EVENT HOST, DATE, LOCATION, and number of people attending (see note above)-->
	<h4>Host: ${event.host.firstName}</h4>
	<h4>Date: <fmt:formatDate pattern="MMMM dd, YYYY" value="${event.date}"/></h4>
	<h4>Location: ${event.city}, ${event.state}</h4>
	<h4>People who are attending this event: ${event.guests.size()}</h4>
	
	<!-- table for loop NAME of attending and THEIR LOCATION -->
	
	<table class="table border border-dark table-striped table-hover m-auto w-50 p-3">
		<thead>
			<tr>
				<th scope="col">Name</th>
				<th scope="col">City</th>
		    </tr>
		</thead>
			<tbody>
			    <c:forEach var="guest" items="${event.guests}">
			    <tr>
					<td>${guest.firstName}</td>
					<td>${guest.city}</td>
				</tr>
				</c:forEach>
			</tbody>
	</table>
	

	
	
	<!-- SCROLL LIST of COMMENTS for event featuring FIRST NAME, LAST NAME and COMMENT-->
	<div class="row justify-content-around">
		<div class="container w-25 mt-5 p-5 bg-dark">
			<h2 class="text-white">Message Wall</h2>
			<c:forEach var="comment" items="${event.comments}">
				<p class="text-white">${comment.commenter.firstName} says: ${comment.content}</p>
			</c:forEach>
		</div>
		
		
		
		<!-- ADD COMMENT FORM -->
		<div class="container w-25 mt-5 p-5 bg-dark">
			<form method="POST" action="/events/${event.id}/addcomment/${user.id}">
				<h4 class="text-white">Add Comment</h4>
				<p class="text-white"><c:out value="${error}" /></p>
				<p>
					<label for="comments" class="text-white">Comment:</label>
					<textarea name="comments" cols="10" rows="5" class="form-control col-sm-12"></textarea>
				</p>
				
				<input class="mt-3 bg-warning" type="submit" value="Submit"/>
			
			</form>
		</div>
	</div>

</body>
</html>