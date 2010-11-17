<%@attribute name="content" required="true" fragment="true" %>
<%@attribute name="title" required="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>

<html>
	<head>
  	<title>DropEdit : <c:out value="${title != null ? title : 'Main'}"/></title>
		<style type="text/css" media="screen">
			*																{padding: 0; margin: 0;}
		                    					  			
			body 														{background: #eee;}
			#container											{width: 840px; margin: 20px auto; background: #fff; padding: 20px; -webkit-box-shadow: rgba(0,0,0,0.2) 2px 2px 2px;}
			h1 															{font-family: "Helvetica"; font-size: 60px;}
			h2 															{font-family: "Helvetica"; font-size: 20px; font-weight: normal; margin-top: 20px;}
			form#auth												{float: left; margin-top: 20px; padding: 5px 20px;}
			form#auth input 								{font-size: 20px; margin-top: 4px;}
			form#auth input[type=submit] 		{border: 1px solid #ccc; background: #fff; padding: 1px 10px;}
		
			#signin													{float: left; width: 100%;}
			#signin h2											{width: 250px; float: left; border-right: 1px solid #ccc; margin-right: 5px;}
			p																{font-family: "Arial"; margin: 2em 0 0 0; float: left; font-size: 13px; color: #777;}
			p a															{color: #333;}
			
			#file_list											{list-style: none; float: left; width: 50%; overflow: hidden; clear: left; margin-top: 20px;}
			#file_list li										{float: left; width: 100%; font-family: "Arial"; font-size: 13px; padding: 5px; background: #efefef; margin-bottom: 3px; -webkit-box-shadow: rgba(0,0,0,0.2) 1px 1px 1px;}
			#file_list li strong						{display: block; float: left;}
			#file_list li a.direct					{display: block; float: left; font-weight: bold;}
			#file_list .buttonset						{float: right; margin-right: 10px; text-transform: lowercase;}
			#file_list .buttonset a					{text-decoration: none; color: #888; border-bottom: 1px solid #999;}
		                    					
			.clear													{clear: both;}
		</style>
	</head>
	<body>  
		<div id="container">
			
			<h1>DropEdit</h1>
			
			<jsp:invoke fragment="content"/>
			
			<div class="clear"></div>
			
		</div>
	</body>
</html>