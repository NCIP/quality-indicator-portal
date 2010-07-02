

function newImage(arg) {
	if (document.images) {
		rslt = new Image();
		rslt.src = arg;
		return rslt;
	}
}

function changeImages() {
	if (document.images) {
		for (var i=0; i<changeImages.arguments.length; i+=2) {
			document[changeImages.arguments[i]].src = changeImages.arguments[i+1];
		}
	}
}

var preloadFlag = false;
function preloadImages() {
	if (document.images) {
		ispyButtons_01_over = newImage("images/ispyButtons_01-over.gif");
		ispyButtons_02_over = newImage("images/ispyButtons_02-over.gif");
		preloadFlag = true;
	}
}

function showHelp(txt, caption)	{
	txt = txt.replace("|", "<br/>");
	return overlib(txt, CAPTION, caption);
}

function hideLoadingMessage(){
	if(document.getElementById('spnLoading') != null)
			document.getElementById('spnLoading').style.display = "none" ;
}


var loadingMsg = "<span id=\"spnLoading\"  style=\"display:inline; width:500; text-align:center; width:100%\" >"+
		"<br><p align=\"center\" style=\"font: 14px arial bold\">"+
		"<img src=\"images/statusBar2.gif\">"+
		"<br>Loading...please wait<br>"+
		"</p>"+
		"</span>";

function formNewTargetSimple(theForm, windowName, winw, winh)	{
	var d = new Date();
	var stamp = "";
	stamp = d.getDate().toString()+(d.getMonth()+1).toString()+d.getFullYear().toString()+d.getHours().toString()+d.getMinutes().toString()+d.getSeconds().toString();

	//this line enables multi view
	windowName += stamp;

	var newwin = spawnx("", winw, winh, windowName);
	theForm.target = windowName;

	//alert(windowName);
	//return true;
	//newwin.document.write("loading ... <br>");
	//newwin.document.write(loadingMsg);
}

function formNewTarget(theForm, windowName, winw, winh)	{
		var newwin = spawnx("", winw, winh, windowName);
		
		newwin.focus();
		//newwin.document.write("loading ... <br>");
		newwin.document.write(loadingMsg);
		theForm.target = windowName;
		//return true;
		
}


function spawnCentered(url, winw, winh, name)	{
	var winl = (screen.width-winw)/2;
	var wint = (screen.height-winh)/2;

	var w = window.open(url, name,
      "screenX=0,screenY=0,status=yes,toolbar=no,menubar=no,location=no,width=" + winw + ",height=" + winh + 
      "top="+wint+"left="+winl+",scrollbars=yes,resizable=yes");
      	//check for pop-up blocker
	if (w==null || typeof(w)=="undefined") {
		alert("You have pop-ups blocked.  Please click the highlighted link at the top of this page to view the report.  You may disable your pop-up blocker for this site to avoid doing this in the future.");
		document.write("<Br><Br><span class=\"pop\">You have pop-ups blocked.  Click <a href=\"javascript:spawnx('"+url+"',"+winw+","+winh+",'"+name+"');\">here</a> to view the report.</span>");
	} else {
		w.focus();
	}
	
	return w;
}
function spawnx(url,winw,winh, name) {

  var w = window.open(url, name,
      "screenX=0,screenY=0,status=yes,toolbar=no,menubar=no,location=no,width=" + winw + ",height=" + winh + 
      ",scrollbars=yes,resizable=yes");
	
	//check for pop-up blocker
	if (w==null || typeof(w)=="undefined") {
		alert("You have pop-ups blocked.  Please click the highlighted link at the top of this page to view the report.  You may disable your pop-up blocker for this site to avoid doing this in the future.");
		document.write("<Br><Br><span class=\"pop\">You have pop-ups blocked.  Click <a href=\"javascript:spawnx('"+url+"',"+winw+","+winh+",'"+name+"');\">here</a> to view the report.</span>");
		//scroll(0, 8000);
	} else {
		w.focus();

	}
	
	return w;
} 

function spawn(url,winw,winh) {
  var w = window.open(url, "_blank",
      "screenX=0,screenY=0,status=yes,toolbar=yes,menubar=yes,location=yes,width=" + winw + ",height=" + winh + 
      ",scrollbars=yes,resizable=yes");
} 

function highlightColumn(col, state)	{

	//c = document.getElementsByTagName("td");
	c = document.getElementsByClassName(col.className, "td", document.getElementById("reportTable"));
	//c = document.getElementsByClassName(col.className);
	
	for(i=0; i<c.length; i++)	{
		if(c[i].className == col.className)	{
			if(state)	{
				c[i].style.background = "#E0EDFF";
			}
			else
				c[i].style.background = "#ffffff";
		}
		else	{
			//nada
		}
	}		
}


document.getElementsByClassName = function(ClassName,tagName,parentElement){
	var elements=new Array();
	var d=parentElement ? parentElement : document;
	var allElements;
	
	if(tagName)
		allElements=d.all && d.all.tags(tagName)|| d.getElementsByTagName && d.getElementsByTagName(tagName);
	else 
		allElements=d.all || d.getElementsByTagName("*");
	
	for(var i=0,len=allElements.length; i<len; i++)
		if(allElements[i].className==ClassName)
			elements[elements.length]=allElements[i];
	
	return elements;
}

