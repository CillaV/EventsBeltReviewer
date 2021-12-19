<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 

<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
	<meta charset="ISO-8859-1">
	<title>Events</title>
</head>
<body>

	<h1>Welcome ${user.firstName}</h1>
	<a href="/logout">Logout</a>
	
	<h4 class="m-auto w-25 p-3">Here are some events in your state</h4>
	
	<!-- for loop for events based on location (state) of user logged in -->
	<table class="table border border-dark table-striped table-hover m-auto w-50 p-3">
		<thead>
			<tr>
				<th scope="col">Name</th>
				<th scope="col">Date</th>
				<th scope="col">City</th>
				<th scope="col">Host</th>
				<th scope="col">Actions / Status</th>
		    </tr>
		</thead>
			<tbody>
			    <c:forEach var="event" items="${inState}">
			    <tr>
					<td><a href="events/${event.id}">${event.name}</a></td>
					<td><fmt:formatDate pattern="MMMM dd, YYYY" value="${event.date}"/></td>
					<td>${event.city}</td>
					<td>${event.host.firstName}</td>
					<td>
						<c:choose>
							<c:when test="${event.host.id == user.id}">
								<a href="/events/${event.id}/edit">Edit</a>
								<form action="/events/${event.id}/delete" method="post">
								    <input type="hidden" name="_method" value="delete">
								    <button class="btn btn-outline-danger btn-sm">Delete</button>
								</form>
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${event.guests.contains(user)}">
										<span>Attending<a href="/events/${event.id}/cancel/${user.id}">Cancel</a></span>
									</c:when>
									<c:otherwise>
										<a href="/events/${event.id}/join/${user.id}">Join</a>
									</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				</c:forEach>
			</tbody>
	</table>
	
	<!-- table for loop of events by NAME, DATE, LOCATION, HOST, ACTION/STATUS -->
	
	

	<h4 class="m-auto w-25 p-3">Here are some of the events in other states</h4>
	
	<!-- for loop of events outside of user location in different states -->
	<table class="table border border-dark table-striped table-hover m-auto w-50 p-3">
		<thead>
			<tr>
				<th scope="col">Name</th>
				<th scope="col">Date</th>
				<th scope="col">City</th>
				<th scope="col">Host</th>
				<th scope="col">Actions / Status</th>
		    </tr>
		</thead>
			<tbody>
			    <c:forEach var="event" items="${outState}">
			    <tr>
					<td><a href="events/${event.id}">${event.name}</a></td>
					<td><fmt:formatDate pattern="MMMM dd, YYYY" value="${event.date}"/></td>
					<td>${event.city}</td>
					<td>${event.host.firstName}</td>
					<td>
						<c:choose>
							<c:when test="${event.host.id == user.id}">
								<a href="/events/${event.id}/edit">Edit</a> |
								<form action="/events/${event.id}/delete" method="post">
								    <input type="hidden" name="_method" value="delete">
								    <button class="btn btn-outline-danger btn-sm">Delete</button>
								</form>
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${event.guests.contains(user)}">
										<span>Attending | <a href="/events/${event.id}/cancel/${user.id}">Cancel</a></span>
									</c:when>
									<c:otherwise>
										<a href="/events/${event.id}/join/${user.id}">Join</a>
									</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				</c:forEach>
			</tbody>
	</table>

	
	
	
	<div class="container w-25 mt-5 p-5 bg-dark">	
		<h2 class="justify-content-lg-start text-warning">Create An Event</h2>
		
	    <form:form class="form-group" method="POST" action="/events/new" modelAttribute="event">
	        <form:hidden value="${user.id}" path="host"/>
	        <p>
	            <form:label path="name" class="text-white">Name:</form:label>
	            <form:errors path="name" class="text-white"/>
	            <form:input path="name" class="form-control col-sm-12"/>
	           
	        </p>
	        
	        <p>
	            <form:label path="date" class="text-white"> Date:</form:label>
	            <form:errors path="date" class="text-white"/>
	            <form:input type="date" value="${now}" path="date" class="form-control col-sm-12"/>
	        </p>
	        
			<p>
				<form:label path="city" class="text-white">City:</form:label>
				<form:errors path="city" class="text-white"/>
				<form:input path="city" class="form-control col-sm-12"/>
			</p>
			
			<p>
				<form:label path="state" class="text-white">State:</form:label>
				<form:errors path="state" class="text-white"/>
				<form:select path="state" class="form-control col-sm-12">
							<form:option value="AL"></form:option>
							<form:option value="CA"></form:option>
							<form:option value="CO"></form:option>
							<form:option value="FL"></form:option>
							<form:option value="GA"></form:option>
							<form:option value="HI"></form:option>
							<form:option value="NV"></form:option>
							<form:option value="NY"></form:option>
							<form:option value="TN"></form:option>
							<form:option value="TX"></form:option>
							<form:option value="WA"></form:option>
							<form:option value="WV"></form:option>
				</form:select>
			</p>
			
	        <input class="mt-3 bg-warning"type="submit" value="Create"/>
	    </form:form>
	</div>

</body>
</html>