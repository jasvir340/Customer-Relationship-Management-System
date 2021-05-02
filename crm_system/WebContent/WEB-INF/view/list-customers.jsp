<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Customers List</title>
	<link type="text/css" 
		  rel="stylesheet"	
		  href="${pageContext.request.contextPath}/resources/css/style.css"/>
</head>
<body>
	
	<div id="wrapper">
		<div id="header">
			<h2>CRM - Customer Relationship Manager</h2>
		</div>
	</div>
	
	<div id="container">
		<form:form action="searchCustomer" method="GET">
			<label>Search Customer</label>
			<input type="text" name="data"/>
			<input type="submit" value="search" class="add-button"/>
		</form:form>
	</div>
	
	
	<div id="container">
		<div id="content">
			
			<input type="button" value="Add Customer" class="add-button"
			onclick="window.location.href='showFormForAdd'; return false;">
			
			<table>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Action</th>
				</tr>
				<c:forEach var="tempCustomer" items="${customers}">
					<tr>
						<c:url var="updateLink" value="/customer/showFormForUpdate">
							<c:param name="customerId" value="${tempCustomer.id}"></c:param>
						</c:url>
						<c:url var="deleteLink" value="/customer/deleteUser">
							<c:param name="customerId" value="${tempCustomer.id}"/>
						</c:url>
						<td>${tempCustomer.firstName}</td>
						<td>${tempCustomer.lastName}</td>
						<td>${tempCustomer.email}</td>
						<td> 
							<a href="${updateLink}">Update</a> | <a href="${deleteLink}"
								onclick="if (!(confirm('Are you sure you want to delete the customer?'))) return false;">Delete</a>
						</td>
					</tr>
				</c:forEach> 
			</table>
		</div>
	</div>
	
</body>
</html>