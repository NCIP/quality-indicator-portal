<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>Error Page</title>
    
	<META http-equiv="Content-Type" content="text/html;">
	<LINK REL=StyleSheet HREF="css/qiStyle.css" TYPE="text/css">

  </head>
  
  <body>
  	<div class="page">
  		<%@ include file="/header.html"%> 
	</div>
	
	<div class="main">

	<center>

		<br>
		<br>
		
		<h2 class="title">You do not have sufficient previleges to use the quality indicator portal<br>
			Please contact 
			<A href="mailto:ncicb@pop.nci.nih.gov?subject=QualityIndicator">ncicb@pop.nci.nih.gov</A> 
			to request previleges to access this web site<br> 
        	Click <a href="index.jsp">here</a>to go to the home page
        </h2>

		<%request.getSession(false);%>

	</center>
	</div>
	
  </body>
</html>
