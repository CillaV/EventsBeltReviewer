<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
	<meta charset="ISO-8859-1">
	<title>Welcome</title>
</head>
<body>

	<h1>Welcome</h1>
	
	<!-- shows all form errors -->
	<!-- could use form errors with each instead to show errors there instead of up here. -->
	<p><form:errors path="user.*"/></p>
	
	
	
	<div class="row justify-content-around">
		
			<div class="container w-25 mt-5 p-5 bg-dark">
			
				<h2 class="justify-content-lg-start text-warning">Register</h2>
				
				<form:form method="POST" action="/register" modelAttribute="user" class="form-group">
					<p>
						<form:label path="firstName" class="text-white">First Name:</form:label>
						<form:input path="firstName" class="form-control col-sm-12"/>
					</p>		
					
					<p>
						<form:label path="lastName" class="text-white">Last Name:</form:label>
						<form:input path="lastName" class="form-control col-sm-12"/>
					</p>		
					
					<p>
						<form:label path="email" class="text-white">Email:</form:label>
						<form:input path="email" class="form-control col-sm-12"/>
					</p>
					
					<p>
						<form:label path="city" class="text-white">City:</form:label>
						<form:input path="city" class="form-control col-sm-12"/>
					</p>
					
					<p>
						<form:label path="state" class="text-white">State:</form:label>
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
					
					<p>
						<form:label path="password" class="text-white">Password:</form:label>
						<form:password path="password" class="form-control col-sm-12"/>
					</p>
					
					<p>
						<form:label path="passwordConfirmation" class="text-white">Password Confirmation:</form:label>
						<form:password path="passwordConfirmation" class="form-control col-sm-12"/>
					</p>
					
					<input class="mt-3 bg-warning" type="submit" value="Register"/>
				
				</form:form>
			</div>
			
			
			<div class="container w-25 mt-5 p-5 bg-dark">
				
				<h2 class="justify-content-lg-start text-warning">Login</h2>
				<p class="text-white"><c:out value="${error}" /></p>
				<form method="POST" action="/login" >
					<p>
						<label for="email" class="text-white">Email:</label>
						<input type="email" name="email" class="form-control col-sm-12"/>
					</p>
					
					<p>
						<label for="password" class="text-white">Password:</label>
						<input type=password name="password" class="form-control col-sm-12"/>
					</p>
					
					<input class="mt-3 bg-warning" type="submit" value="Login"/>
				
				</form>
			</div>
	</div>

</body>
</html>