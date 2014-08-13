<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="ru.svyaznoybank.handbook.security.AuthDetails"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title>Внутренние списки</title>
		
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="gwt:property" content="locale=ru_RU">
		
		<script type="text/javascript" src="handbook/handbook.nocache.js"></script>
		<script type="text/javascript">
			<%--
				'isAdmin' variable is used only for interface rendering and does not affect
				server-side logic. Even if user without 'HandbookAdmin' role manualy sets 
				it to 'true', he gets error when try to access secured services.
			--%>
			window.isAdmin = ('true' === '<%= request.isUserInRole(AuthDetails.ADMIN_ROLE) %>');
			window.username = '${pageScope.request.userPrincipal.name}';
		</script>
		
		<style type="text/css">
			@import url('css/handbook.css')
		</style>
	</head>
	<body></body>
</html>