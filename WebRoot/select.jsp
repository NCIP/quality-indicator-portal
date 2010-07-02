<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<html>
<head>
	<title>Select : Submit/Search</title>
	<LINK REL=StyleSheet HREF="css/qiStyle.css" TYPE="text/css">
</head>

<body>
<div class="page">
<%@ include file="header.html"%> 

<div class="main">
<H2 class="title">Select Submit or Search</H2>
<!--<FORM Name="main" method="POST" action="select.jsp">   


<H2 class="title">Select Submit or Search</H2>
			
			 <input type="radio" name="select_type" value="1">
					Submit Quality Indicators<br /><br />
			<input type="radio" name="select_type" value="2">
			       Search Quality Indicators				
					<br /><br />
				<INPUT type="SUBMIT" class="buttons" name="submitType" value="  Enter  " tabindex="3">
				<INPUT type="RESET" class="buttons" value="  Clear  " tabindex="4">
			
 <%
   if(request.getParameter("submitType") != null){
     String options = (String)request.getParameter("select_type");
	 System.out.println("options is :"+options);
	 if(options != null && options.equals("1")){
	   response.sendRedirect("intro.jsp");	   
	  }
	 else if( options != null && options.equals("2")){
	     response.sendRedirect("search.jsp");
	   }
     
	 }
 %>      

	
</FORM>
-->

<div align="center">

<table border="0" width="650" cellspacing="0">
<tr><td><img src="images/spanYellow.gif" height="2" width="100%" /></td>
    <td><img src="images/spanPurple.gif" height="2" width="100%" /></td>
</tr>
<tr>
    <td style="padding-right:30px;padding-bottom:15px;"><a href="intro.jsp"><img src="images/check.gif" alt="submit" border="0" /></a>
       <a href="intro.jsp">Submit Quality Indicator</a>
        <p>Submit quality indicator data for the I-SPY study. Portal accepts 
        the following data types: general sample information, H&E data, PCR, DNA, RNA and protein
        quality indicator data.  </p></td>
        
    <td style="padding-left:10px;padding-bottom:15px;">
        <a href="search.jsp"><img src="images/magGlass.gif" alt="search" border="0" /></a>
        <a href="search.jsp">Search Quality Indicator</a>
        <p>Query the Quality Indicator database for data submitted by I-SPY researchers. 
        Retrieve information about the frozen and paraffin core sample quality indicators, the H&E 
        review and usability quality indicators, PCR, DNA sub sample, RNA sub sample and protein quality indicators.  
        The ability to sort by columns is also provided.</p>
        
</tr>

</table>



</div>


</div>

</div>

<%@ include file="footer.jsp"%>
</body>
</html>
