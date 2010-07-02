<!DOCTYPE html public "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/Transitional.dtd">
<HTML>
<HEAD>
<title>Search Results</title>
<META http-equiv="Content-Type" content="text/html;">
<LINK REL=StyleSheet HREF="css/qiStyle.css" TYPE="text/css">

</HEAD> 

<body>
     
<div class="page">    
 
<div class="page">
<table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#A90101">
		<tr bgcolor="#A90101">
				<td width="283" height="37" align="left"><a href="http://www.cancer.gov"><img src="images/logotype.gif" width="283" height="37" border="0"></a></td>
				<td>&nbsp;</td>
				<td width="295" height="37" align="right"><a href="http://www.cancer.gov"><img src="images/tagline.gif" width="295" height="37" border="0"></a></td>

		</tr>
</table> 
<div style="margin-bottom:0px;border-left:1px solid #000000;border-right:0px;border-top:1px solid #000000;border-bottom:0px solid #000000; height:45px;">
<map name="ispyHead">
<area alt="return to ispy trials home" coords="22,10,248,32" href="select.jsp">
</map>
<img src="images/ispyHead.jpg" width="749" height="45" border="0" usemap="#ispyHead"></div>
 

<div class="main">
 

<form method ="post" action ="summaryReport.jsp">



<%@ page language="java" import="gov.nih.nci.qi.db.*" %>
 <%@ page info="this page is the animal model search results page"%>
 <%@ page import="gov.nih.nci.qi.*,gov.nih.nci.qi.util.*"%>
 <%@ page import="java.util.*,java.text.*,java.io.*"%>  
 <%@ page import="java.lang.Long"%> 
 <%@ page buffer="64kb"%>
 <%@ page import="java.lang.reflect.Array"%> 

 <jsp:useBean id="dna_label" scope="request" class="gov.nih.nci.qi.db.Qi_dna_labeling"/>
 <jsp:useBean id="cgh_hyb" scope="request" class="gov.nih.nci.qi.db.Qi_cgh_hybridization"/>
 <jsp:useBean id="dna" scope="request" class="gov.nih.nci.qi.db.Qi_dna"/>
 
 <%	

 
 Vector samples = (Vector)session.getAttribute("gov.nih.nci.qi.searchResult"); 
 Vector labTrakId_vec = (Vector)session.getAttribute("gov.nih.nci.qi.labTrakId_vec"); 
 StringBuffer whereclause = new StringBuffer();
 whereclause.append(" sample_id in (");
 for(int i=0;i<labTrakId_vec.size();i++){
 
   Long sample_Id = (Long)labTrakId_vec.elementAt(i);
    whereclause.append(sample_Id.toString());  
	if(i!=(labTrakId_vec.size()-1)){
	   whereclause.append(","); 
	}   
 }
 whereclause.append(")");
 //String whereclause = " sample_id in (531,535,546,552)";
 System.out.println("whereclause&&&&&&&:"+whereclause.toString());
 
 Vector dna_with_labeling_total = new Vector();
 Vector dna_with_cgh_total = new Vector();
 Vector dna_labels_total = new Vector();
 Vector cgh_hybs_total = new Vector();
 
 Vector high_dna_labels = new Vector();
 Vector medium_dna_labels = new Vector();
 Vector low_dna_labels = new Vector();
 
 Vector good_cgh_hybs = new Vector();
 Vector poor_cgh_hybs = new Vector();

											
		
dna_with_labeling_total = dna.retrieveAllWhere(" dna_id in (select dna_id from QI_DNA_LABELING where  "+
                                                " dna_id is not null) and "+ whereclause.toString());

												
/*dna_with_labeling_total = dna.retrieveAllWhere(" dna_id IN (SELECT dna_id FROM QI_DNA_LABELING) "+
                           +" AND DNA_SUBSAMPLE_ID LIKE '%-2'"+ " AND "+whereclause.toString()")");
*/
dna_labels_total = dna_label.retrieveAllWhere(" dna_id IN (SELECT dna_id FROM  QI_DNA "+
                                              " WHERE "+whereclause.toString()+")"  );	
											
				                     
high_dna_labels = 	dna_label.retrieveAllWhere(" dna_id IN (SELECT dna_id FROM  QI_DNA "+
" WHERE INTENSITY_ID =1 and "+whereclause.toString()+")"  );	
				                     	
medium_dna_labels = dna_label.retrieveAllWhere(" dna_id IN (SELECT dna_id FROM  QI_DNA "+
" WHERE INTENSITY_ID =2 and "+whereclause.toString()+")"  );	
				                          
low_dna_labels = 	dna_label.retrieveAllWhere(" dna_id IN (SELECT dna_id FROM  QI_DNA "+
" WHERE  INTENSITY_ID =3 and "+whereclause.toString()+")"  );	
				                          				   
 
dna_with_cgh_total = dna.retrieveAllWhere(" dna_id in (select dna_id from QI_CGH_HYBRIDIZATION where  "+
                                                " dna_id is not null) and "+ whereclause.toString());

cgh_hybs_total = cgh_hyb.retrieveAllWhere(" dna_id IN (SELECT dna_id FROM  QI_DNA "+
" WHERE "+whereclause.toString()+")"  );	
				                         
good_cgh_hybs = cgh_hyb.retrieveAllWhere(" dna_id IN (SELECT dna_id FROM  QI_DNA "+
" WHERE CGH_QUALITY_ID=1 and "+whereclause.toString()+")"  );	
				                     
poor_cgh_hybs = cgh_hyb.retrieveAllWhere(" dna_id IN (SELECT dna_id FROM  QI_DNA "+
" WHERE CGH_QUALITY_ID=2 and "+whereclause.toString()+")"  );		
				                        						   
							   
 
float high_dna_labels_ratio = (float)high_dna_labels.size()/(float)dna_labels_total.size()*100;
float medium_dna_labels_ratio =(float)medium_dna_labels.size()/(float)dna_labels_total.size()*100;
float low_dna_labels_ratio = (float)low_dna_labels.size()/(float)dna_labels_total.size()*100;

float good_cgh_hybs_ratio = (float)good_cgh_hybs.size()/(float)cgh_hybs_total.size()*100;
float poor_cgh_hybs_ratio = (float)poor_cgh_hybs.size()/(float)cgh_hybs_total.size()*100;
System.out.println("good_cgh_hybs_ratio:"+good_cgh_hybs_ratio);
System.out.println("poor_cgh_hybs_ratio:"+poor_cgh_hybs_ratio);



 String where = " AND "+whereclause.toString();


SummaryReport report = new SummaryReport(samples,where);	
report.setDna_with_labeling_total(dna_with_labeling_total.size()); 
report.setDna_labels_total(dna_labels_total.size()); 
report.setHigh_dna_labels_total(high_dna_labels.size()); 
report.setMedium_dna_labels_total(medium_dna_labels.size()); 
report.setLow_dna_labels_total(low_dna_labels.size()); 

report.setDna_with_cgh_total(dna_with_cgh_total.size());    
report.setCgh_hybs_total(cgh_hybs_total.size()); 
report.setGood_cgh_hybs_total(good_cgh_hybs.size()); 
report.setPoor_cgh_hybs_total(poor_cgh_hybs.size()); 
 
 	
%>

<%@ include file="summaryReport.html"%>   

</form>

</body>
</html>