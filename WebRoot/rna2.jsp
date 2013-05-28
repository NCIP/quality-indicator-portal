<%--L
  Copyright SAIC

  Distributed under the OSI-approved BSD 3-Clause License.
  See http://ncip.github.com/quality-indicator-portal/LICENSE.txt for details.
L--%>

  
<%@ page language="java" import="gov.nih.nci.qi.db.*,gov.nih.nci.qi.util.*, gov.nih.nci.qi.*" %>
<%@ page buffer="32kb"%>
<%@ page import="java.lang.*,java.text.*,java.sql.*"%>
<%@ page import="java.io.*,java.util.*,com.bigfoot.bugar.servlet.http.*"%>
<%@ page info="this page is the password validation page"%>

<%  try{
 MultipartFormData multipart = new MultipartFormData(request, 5242880L );
 

//5 MB limit on files
 
    gov.nih.nci.qi.DatabaseSetup.setPageExpiration(response);
	//SessionManager.verifySession(request,response,"Error.jsp");
  
    response.setHeader("Cache-Control","no-cache");
    response.setHeader("Pragma","no-cache");	

   //qi
	Long sampleId = null;
	Long rnaId = null;
	String mode = null;
    mode =(String)request.getParameter("mode");	
	SimpleDateFormat formatter = new SimpleDateFormat ("MM/dd/yyyy");
	
	// starting intializing image server related parameters
	String userDir = System.getProperty("user.dir");
	String url = null;    
	String path = null;    
	String username = null;  
	String pwd = null;
	String path2 = null;
	
	if(userDir.indexOf(":")!= -1){ //window
		url = getServletConfig().getServletContext().getInitParameter("ftpur1_WIN");	
		path = getServletConfig().getServletContext().getInitParameter("ftppath_WIN");		
	}
	else {
		url = getServletConfig().getServletContext().getInitParameter("ftpur1_UNIX");	
		path = getServletConfig().getServletContext().getInitParameter("ftppath_UNIX");
	}
	
	
	if(userDir.indexOf(":")!= -1){ 
	  path2 = path+"/QI_Images/RNA_Gel_Images";
	  path = path+"/QI_Images/RNA_Trace_Files";
	}
	
	else{	
	  path2 = path+"/QI_Images/RNA_Gel_Images";
	  path = path+"/QI_Images/RNA_Trace_Files";
	}
	
	System.out.println("path:"+path);
	if(userDir.indexOf(":")!= -1){ 
		username = getServletConfig().getServletContext().getInitParameter("ftpusername_WIN");
		pwd = getServletConfig().getServletContext().getInitParameter("ftppwd_WIN");
	}
	else {
	    username = getServletConfig().getServletContext().getInitParameter("ftpusername_UNIX");
		pwd = getServletConfig().getServletContext().getInitParameter("ftppwd_UNIX");
	}
	
	
	// end of image related server parameters	
			  
    Long modelId = null;    
	QISession mySession = (QISession) session.getAttribute("nci.mmhcc.mmhccSession"); 
	if(mySession != null){
	    sampleId = mySession.getSampleId();				
	}
%> 
<jsp:useBean id="rna" scope="request" class="gov.nih.nci.qi.db.Qi_rna"/>
<jsp:useBean id="rna_img" scope="request" class="gov.nih.nci.qi.db.Rna_quality_image"/>


<%  
	String id = (String) multipart.getParameter("id");	
	
	if(id != null && !(id.equals(""))){
		rnaId = Long.decode(id);			
	    rna.retrieveByKey(rnaId);%>
		
	<%}
	else{
		id="";
		if(mode != null && mode.equals("Submit")){
		   rna = new Qi_rna();
		   }
		}
%>  	



<jsp:setProperty name="rna" property="institution_id" param="institution"/>
<jsp:setProperty name="rna" property="rna_reading" param="rnaReading"/>
<jsp:setProperty name="rna" property="instrument_id" param="reading_instrument"/>
<jsp:setProperty name="rna" property="rna_extraction_quality" param="rnaQuality"/>
<jsp:setProperty name="rna" property="rna_subsample_id" param="required_Sub-sampleId"/>
<jsp:setProperty name="rna" property="rna_quantity_unit" param="rna_unit"/>


<%	
   
	boolean Status = false;
	boolean status2  = false;
	
	if(sampleId != null){
       rna.setSample_id(sampleId);
     }	
    String thePage = "rna.jsp";
    session.setAttribute("gov.nih.nci.qi.constaint",thePage);
	boolean deleteStatus = false;
	
if(multipart.getParameter("bk") != null){
	     	%>
			 <jsp:forward page="Submission.jsp"/>
			<%}
	
	
if(multipart.getParameter("delete_data") != null){
  DeleteRecord delete  = new DeleteRecord(); 
  delete.init (config);
  deleteStatus = delete.deleteRNA(rnaId);
  if(deleteStatus = true){%>
    <jsp:forward page="Submission.jsp"/>
     <%
   }  
 }

if(multipart.getParameter("submit_rna_quality") != null)
{
	
	boolean flag = DatabaseSetup.checkMissingRequiredFields(request,multipart);	
	if(flag)
	{
	   
	   System.err.println("yes missing");	
	   Vector missing_feilds = new Vector();	  
	   missing_feilds.add("RNA Sub-sample ID"); 
	   flag = true;
	   request.setAttribute("gov.nih.nci.qi.MissingFeilds",missing_feilds);
	   }   
	
	System.err.println("flag is :"+flag);    
	
	if (flag)
	{ 
	   %>
	   <jsp:forward page="missing_required_feilds.jsp"/>
	<%}
	
	if(multipart.getParameter("required_Sub-sampleId") != null)	{
		 rna.setRna_subsample_id(multipart.getParameter("required_Sub-sampleId"));
	}
	
	if(rnaId != null){
	   rna.setRna_id(rnaId);
	   if(multipart.getParameter("required_rna_Sub-sampleId") != null && !multipart.getParameter("required_rna_Sub-sampleId").equals("")){			
		    rna.setRna_subsample_id(mySession.getLabtrak_id()+"-"+(String)multipart.getParameter("required_rna_Sub-sampleId"));
	      }	 
	  
	   
	  try{		   
			session.setAttribute("gov.nih.nci.qi.id",id);
	        Status = rna.updateByKey();
		}
	  catch(Exception e){%>
			<jsp:forward page="constraintError.jsp"/>	
	   <%}	
	   System.err.println("update ");
	}
	else{
		rnaId = mySession.getDatabaseUID("QI_RNA");
		if(multipart.getParameter("required_rna_Sub-sampleId") != null && !multipart.getParameter("required_rna_Sub-sampleId").equals("")){			
		    rna.setRna_subsample_id(mySession.getLabtrak_id()+"-"+(String)multipart.getParameter("required_rna_Sub-sampleId"));
	      }	 
		
		if(rnaId != null){
		 	rna.setRna_id(rnaId);					 
			try{
		 	   Status = rna.insert();
			   }
			catch(Exception e){
				  
				   %>
				   <jsp:forward page="constraintError.jsp"/>	
				<%}
			System.err.println("insert ");
			}
		}
	if(Status){		 
	
	  if(multipart.getParameter("institution") != null && !multipart.getParameter("institution").equals(""))	{
	    rna.setInstitution_id(new Long(multipart.getParameter("institution")));
	  } 
	  if(multipart.getParameter("rnaReading") != null && !multipart.getParameter("rnaReading").equals(""))	{
     	System.out.println(multipart.getParameter("rnaReading"));
		rna.setRna_reading(multipart.getParameter("rnaReading"));
	  } 
	  if(multipart.getParameter("rna_unit") != null && !multipart.getParameter("rna_unit").equals(""))	{
     	rna.setRna_quantity_unit(multipart.getParameter("rna_unit"));
	  } 
	  if(multipart.getParameter("reading_instrument") != null && !multipart.getParameter("reading_instrument").equals("") )	{
	    rna.setInstrument_id(new Long(multipart.getParameter("reading_instrument")));
	  } 
	  if(multipart.getParameter("rnaQuality") != null && !multipart.getParameter("rnaQuality").equals(""))	{
	    rna.setRna_extraction_quality(multipart.getParameter("rnaQuality"));
	  }
	 if(multipart.getParameter("analysis_instrument") != null && !multipart.getParameter("analysis_instrument").equals(""))	{
	    rna.setRna_analysis_instrument_id(new Long(multipart.getParameter("analysis_instrument")));
	  }
	if(multipart.getParameter("rna_analysis_Quality") != null && !multipart.getParameter("rna_analysis_Quality").equals(""))	{
	    rna.setRna_analysis_quality(multipart.getParameter("rna_analysis_Quality"));
	  }
	  
	    if(multipart.getParameter("process_date") != null && !multipart.getParameter("process_date").equals(""))	{
			   String processDate = null;    
			   ParsePosition pos = new ParsePosition(0);	
			   processDate = (String) multipart.getParameter("process_date");			  
			   processDate.trim();			     			
			   java.util.Date pDate = formatter.parse(processDate,pos);			  
			   Timestamp ts  = new Timestamp(pDate.getTime());					
			   rna.setProcess_date(ts);					  
			 }
			 
		 if(multipart.getParameter("analysis_date") != null && !multipart.getParameter("analysis_date").equals(""))	{
			   String analysisDate = null;    
			   ParsePosition pos2 = new ParsePosition(0);   
			   analysisDate = (String) multipart.getParameter("analysis_date");
			   analysisDate.trim();				
			   java.util.Date aDate = formatter.parse(analysisDate,pos2);
			   Timestamp ts2  = new Timestamp(aDate.getTime());					
			   rna.setAnalysis_date(ts2);					
		    }
			
		ParsePosition pos3 = new ParsePosition(0);	
		java.util.Date currentTime_1 = new java.util.Date();
		String dateString  = formatter.format(currentTime_1);		
		java.util.Date timestamp = formatter.parse(dateString,pos3);		
	    Timestamp ts3  = new Timestamp(timestamp.getTime());		
	    rna.setTimestamp(ts3);	
	   
	   
	    if(multipart.getParameter("comment") != null && !multipart.getParameter("comment").equals(""))	{
			rna.setComments(multipart.getParameter("comment"));
		  }
			 
		
		 Status = rna.updateByKey();	 
			 
			 
		// starting rna trace file upload
		 Long rna_img_id = null;
		
		 String uploadObject =  multipart.getFileName( "myUploadObject" );
		 if(uploadObject != null && !(uploadObject.equals(""))){	// either submit or update a trace file
			
			Vector rna_img_vec = new Vector();
			rna_img_vec = rna_img.retrieveAllWhere("RNA_ID = " +rna.getRna_id()+ "AND IMAGE_TYPE ="+"\'"+"RNA_Trace"+"\'");		
			// the size of rna_img_vec is always 1 or 0, b/c rna to trace img is one to one
		    
			String oldOriginalImgName = null;			
		    String oldImgFileId= null;
				 for(int p=0; p<rna_img_vec.size();p++){
				    rna_img = (Rna_quality_image)rna_img_vec.elementAt(p);
			        oldOriginalImgName =  rna_img.getOriginal_image_name();
					rna_img_id = rna_img.getRna_quality_image_id();
					oldImgFileId = rna_img.getImage_file_id();
				 
				 }
			
			 
			String fileSeparator = null;
				 if(uploadObject.indexOf("\\") != -1) { //uploaded file from windows
					  fileSeparator = "\\";
				}
				 else if (uploadObject.indexOf("/") != -1) { //uploaded file from unix or linux
				   fileSeparator = "/";
				  }
				 else { //if(uploadObject.indexOf(":") != -1) { //uploaded file from macs
				   fileSeparator = null;
				  }
		    	String imageName = uploadObject;// get file name only
				if(fileSeparator != null){
				   	 imageName = uploadObject.substring(uploadObject.lastIndexOf(fileSeparator)+1);// get file name only
				  	} 
			
			   String imageDir = multipart.getLocalPath( "myUploadObject" );
			   imageDir = imageDir.substring(0,imageDir.lastIndexOf(File.separator));			   
			   File file = new File( imageDir,imageName);				  
			   new File( multipart.getLocalPath( "myUploadObject" ) ).renameTo( file );
			   File[] file_ = {file}; 
			
			if(rna_img_vec.size() == 0){// insert only
			    rna_img_id = mySession.getDatabaseUID("RNA_QUALITY_IMAGE");
			    rna_img.setRna_quality_image_id(rna_img_id);
			    String imageType = uploadObject.substring(uploadObject.indexOf("."));
			    rna_img.setImage_file_id(rna_img_id+imageType);
				rna_img.setRna_id(rna.getRna_id());
				rna_img.setImage_type("RNA_Trace");
				rna_img.setOriginal_image_name(uploadObject);
			    rna_img.insert();  				
				  	
		  	      FTPUtil.transfer(file_, null, rna_img.getRna_quality_image_id(),url, path,username,pwd);	
		          file.delete();
				  }	 // end of insert
				
		   else if (rna_img_vec.size() == 1){// need to see if img needs to be updated
		    
			   if(oldOriginalImgName != null && !oldOriginalImgName.equals(uploadObject)){// replace the img			      
				   rna_img.setOriginal_image_name(uploadObject);	
				   String imageType = uploadObject.substring(uploadObject.indexOf("."));				   
				   rna_img.setImage_file_id(rna_img_id+imageType);	
				   rna_img.updateByKey(); 		   			    
				   File file2 = new File(oldImgFileId);	//getOriginalimagename()			
			       File[] file2_ = {file2};				  				  				 			 
		           FTPUtil.transfer(file_, file2_,rna_img.getRna_quality_image_id(),url, path,username,pwd);	
			       file2.delete();
				    }				    
			     }				    
			 }
			 
			 Vector rna_trace_img_vec = new Vector();							  
			 if(rna.getRna_id() != null){			  
			   String where = "RNA_ID = " +rna.getRna_id()+ " AND IMAGE_TYPE ="+"\'"+"RNA_Trace"+"\'" + "AND IMAGE_FILE_ID is not null";
			   rna_trace_img_vec = rna_img.retrieveAllWhere(where);				  
			   }
							 
			 if(rna_trace_img_vec.size()==1){
			   for(int j=0; j<rna_trace_img_vec.size();j++){
				   rna_img = (Rna_quality_image)rna_trace_img_vec.elementAt(j);	
				   rna_img_id = rna_img.getRna_quality_image_id();   
			   }
			  if(multipart.getParameter("file_title") != null)	{
			     rna_img.setImage_title(multipart.getParameter("file_title"));
			     }
		      if(multipart.getParameter("file_description") != null)	{
			     rna_img.setImage_desc(multipart.getParameter("file_description"));
			    }
			
			  rna_img.setRna_quality_image_id(rna_img_id);	
			  rna_img.updateByKey();   
			  } 			 
			  // end if inserting or updating trace file
		     
			// end if uploadObject is not null for both inserting and updating trace files
			 // end of rna trace file uplaod
			 
			 
			 
			 // starting rna gel file upload
			 
			 
			  String uploadObject2 =  multipart.getFileName( "myUploadObject2" );
			  if(uploadObject2 != null && !(uploadObject2.equals(""))){	// either submit or update a rna gel file
		         Vector rna_img_vec2 = new Vector();
			     rna_img_vec2 = rna_img.retrieveAllWhere("RNA_ID = " +rna.getRna_id()+ "AND IMAGE_TYPE ="+"\'"+"RNA_Gel"+"\'");		
		         String oldOriginalImgName = null;
				 String oldImgFileId= null;
				 for(int p=0; p<rna_img_vec2.size();p++){
				    rna_img = (Rna_quality_image)rna_img_vec2.elementAt(p);
			        oldOriginalImgName =  rna_img.getOriginal_image_name();
					rna_img_id = rna_img.getRna_quality_image_id();
					oldImgFileId = rna_img.getImage_file_id();
					 
				 }
				 
				 String fileSeparator = null;
				 if(uploadObject2.indexOf("\\") != -1) { //uploaded file from windows
					  fileSeparator = "\\";
				}
				 else if (uploadObject2.indexOf("/") != -1) { //uploaded file from unix or linux
				   fileSeparator = "/";
				  }
				 else { //if(uploadObject.indexOf(":") != -1) { //uploaded file from macs
				   fileSeparator = null;
				  }
		    	String imageName = uploadObject2;// get file name only
				if(fileSeparator != null){
				   	 imageName = uploadObject2.substring(uploadObject2.lastIndexOf(fileSeparator)+1);// get file name only
				  	} 
					
				String imageDir = multipart.getLocalPath( "myUploadObject2" );
			    imageDir = imageDir.substring(0,imageDir.lastIndexOf(File.separator));			   
			    File file = new File( imageDir,imageName);				  
			    new File( multipart.getLocalPath( "myUploadObject2" ) ).renameTo( file );
			    File[] file_ = {file}; 	
				
				
				if(rna_img_vec2.size() == 0){// insert only					
				    rna_img_id = mySession.getDatabaseUID("RNA_QUALITY_IMAGE");
				    rna_img.setRna_quality_image_id(rna_img_id);
				    String imageType = uploadObject2.substring(uploadObject2.indexOf("."));
				    rna_img.setImage_file_id(rna_img_id+imageType);
					rna_img.setRna_id(rna.getRna_id());
					rna_img.setImage_type("RNA_Gel");
					rna_img.setOriginal_image_name(uploadObject2);
				    rna_img.insert();  				
					  	
			  	    FTPUtil.transfer(file_, null, rna_img.getRna_quality_image_id(),url, path2,username,pwd);	
			        file.delete();
				  }	 // end of insert
					
			    else if (rna_img_vec2.size() == 1){// need to see if img needs to be updated
				   if(oldOriginalImgName != null && !oldOriginalImgName.equals(uploadObject2)){// replace the img			      
					   rna_img.setOriginal_image_name(uploadObject2);	
					   String imageType = uploadObject2.substring(uploadObject2.indexOf("."));
				       rna_img.setImage_file_id(rna_img_id+imageType);	
					   rna_img.updateByKey(); 		    
					   File file2 = new File(oldImgFileId);//getOriginalimagename()			
				       File[] file2_ = {file2};				  				  				 			 
			           FTPUtil.transfer(file_, file2_,rna_img.getRna_quality_image_id(),url, path2,username,pwd);	
				       file2.delete();
					    }				    
			        }		 
			 
			   }    
			   
			 Vector rna_gel_img_vec = new Vector();							  
			 if(rna.getRna_id() != null){			  
			   String where = "RNA_ID = " +rna.getRna_id()+ " AND IMAGE_TYPE ="+"\'"+"RNA_Gel"+"\'" + "AND IMAGE_FILE_ID is not null";
			   rna_gel_img_vec = rna_img.retrieveAllWhere(where);				  
			   }
							 
			 if(rna_gel_img_vec.size()==1){
			   for(int j=0; j<rna_gel_img_vec.size();j++){
				   rna_img = (Rna_quality_image)rna_gel_img_vec.elementAt(j);	
				   rna_img_id = rna_img.getRna_quality_image_id();   
			   }
			  
			  if(multipart.getParameter("gel_title") != null)	{			    
			     rna_img.setImage_title(multipart.getParameter("gel_title"));
			     }
		      if(multipart.getParameter("gel_description") != null)	{
			     rna_img.setImage_desc(multipart.getParameter("gel_description"));
			    }
				
			  rna_img.setRna_quality_image_id(rna_img_id);
			  rna_img.updateByKey();   
			  } 			 
			 
			 // end of rna gel file upload
			 
			 multipart.cleanUp();			  
	
		
	// end of image upload
	
	 
	
	
	try{		
	  
	  session.setAttribute("gov.nih.nci.qi.id",id);		  
	  Status = rna.updateByKey();
	}
	catch(Exception e){ 
	}
	  if(rna.getRna_subsample_id() != null){
	    String label = rna.getRna_subsample_id()+"(Chuck Perou Lab)";		
		mySession.setObjectID("RNA", rnaId, rna.getRna_subsample_id());
		}
	  else if(rna.getRna_subsample_id() == null && rna.getRna_reading() != null && rna.getRna_quantity_unit()!= null){
        String label = rna.getRna_reading()+ " " +rna.getRna_quantity_unit();				    
	    mySession.setObjectID("RNA",rnaId,label);
		} 
	
	%>
	 <jsp:forward page="Submission.jsp"/>
	<% //response.sendRedirect("Submission.jsp");
	%>

<%   }	
   }	
}catch (ByteLimitExceededException ble)
  	{
  		Vector byte_errors = new Vector();
		byte_errors.add("Uploaded file cannot be larger than 5 MB");				
		request.setAttribute("nci.mmhcc.MissingFeilds",byte_errors);%>
		<jsp:forward page="missing_required_feilds.jsp"/>
  <%} 	
  catch (EOFException eof)
  	{
  	    Vector byte_errors = new Vector();
		byte_errors.add("Uploaded file cannot be larger than 5 MB");				
		request.setAttribute("nci.mmhcc.MissingFeilds",byte_errors);%>
		<jsp:forward page="missing_required_feilds.jsp"/>
  <%} %>	


   
