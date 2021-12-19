<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<!-- tag not inserted originally? -->
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
	<meta charset="ISO-8859-1">
	<title>Events</title>
</head>
<body>

	<h1>${event.name}</h1>
	
	<a href="/events">Back to main Page</a>
	<br>
	<a href="/events/${event.id}">Event Details</a>

	
	<!-- update form for event for NAME, DATE and LOCATION -->
	<div class="container w-25 mt-5 p-5 bg-dark">	
		<h2 class="justify-content-lg-start text-warning">Edit Event</h2>
		<p class="text-white"><c:out value="${error}" /></p>
	    <form:form class="form-group" method="POST" action="/events/${event.id}/update" modelAttribute="event">
	     	<input type="hidden" name="_method" value="put">
	     	<form:hidden value="${user.id}" path="host"/>
	        <p>
	            <form:label path="name" class="text-white">Name:</form:label>
	            <form:errors path="name" class="text-white"/>
	            <form:input path="name" class="form-control col-sm-12"/>
	           
	        </p>
	        
	        <p>
	            <form:label path="date" class="text-white"> Date:</form:label>
	            <form:errors path="date" class="text-white"/>
	            <form:input type="date" path="date" class="form-control col-sm-12"/>
	            <!-- removed value="${now}" to display current event date -->
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
			
	        <input class="mt-3 bg-warning"type="submit" value="Edit"/>
	    </form:form>
	</div>
	


</body>
</html>