sortitems = 0;  // Automatically sort items within lists? (1 or 0)

/*function move(fbox,tbox) {
	for(var i=0; i<fbox.options.length; i++) {
		if(fbox.options[i].selected && fbox.options[i].value != "") {
			var no = new Option();
			no.value = fbox.options[i].value;
			no.text = fbox.options[i].text;
			tbox.options[tbox.options.length] = no;
			fbox.options[i].value = "";
			fbox.options[i].text = "";
		}
	}
	BumpUp(fbox);
	if (sortitems) SortD(tbox);
}
 */
 
 function move(fbox, tbox) {
var arrFbox = new Array();
var arrTbox = new Array();
var arrLookup = new Array();
var i;
for (i = 0; i < tbox.options.length; i++) {
arrLookup[tbox.options[i].text] = tbox.options[i].value;
arrTbox[i] = tbox.options[i].text;
}
var fLength = 0;
var tLength = arrTbox.length;
for(i = 0; i < fbox.options.length; i++) {
arrLookup[fbox.options[i].text] = fbox.options[i].value;
if (fbox.options[i].selected && fbox.options[i].value != "") {
arrTbox[tLength] = fbox.options[i].text;
tLength++;
}
else {
arrFbox[fLength] = fbox.options[i].text;
fLength++;
   }
}
arrFbox.sort();
arrTbox.sort();
fbox.length = 0;
tbox.length = 0;
var c;
for(c = 0; c < arrFbox.length; c++) {
var no = new Option();
no.value = arrLookup[arrFbox[c]];
no.text = arrFbox[c];
fbox[c] = no;
}
for(c = 0; c < arrTbox.length; c++) {
var no = new Option();
no.value = arrLookup[arrTbox[c]];
no.text = arrTbox[c];
tbox[c] = no;
   }
}
function BumpUp(box)  {
	for(var i=0; i<box.options.length; i++) {
		if(box.options[i].value == "")  {
			for(var j=i; j<box.options.length-1; j++)  {
				box.options[j].value = box.options[j+1].value;
				box.options[j].text = box.options[j+1].text;
			}
			var ln = i;
			break;
		}
	}
	if(ln < box.options.length)  {
		box.options.length -= 1;
		BumpUp(box);
	}
}

function SortD(box)  {
	var temp_opts = new Array();
	var temp = new Object();
	for(var i=0; i<box.options.length; i++)  {
		temp_opts[i] = box.options[i];
	}
	for(var x=0; x<temp_opts.length-1; x++)  {
		for(var y=(x+1); y<temp_opts.length; y++)  {
			if(temp_opts[x].text > temp_opts[y].text)  {
				temp = temp_opts[x].text;
				temp_opts[x].text = temp_opts[y].text;
				temp_opts[y].text = temp;
			}
		}
	}
	for(var i=0; i<box.options.length; i++)  {
		box.options[i].value = temp_opts[i].value;
		box.options[i].text = temp_opts[i].text;
	}
}

function goFilterColumn(status, hide_these, show_these)	{
		
		var st = 'block';
		var st2 = 'block';
		if(status) { st = 'none'; st2 = '';}
		if(!status)	{ st = ''; st2 = 'block';}
		
		c = document.getElementsByTagName("td");
		
		for(i=0; i<c.length; i++)	{
				if(isArray(hide_these))	{
					if (hide_these.inArray(c[i].className)) {
						c[i].style.display = st;
					}
					else if (show_these.inArray(c[i].className)) {
						c[i].style.display = st2;
					}
				}
				else	{
					if(c[i].className == hide_these)	{
							c[i].style.display = st; 			  
					}
					else if(c[i].className == show_these)	{
							c[i].style.display = st2; 			  
					}
				}
		}		
}

//hide some cols from an array
function goFilterColumnsFromArray(hide_these)	{
		var st = 'none';
		c = document.getElementsByTagName("td");
		
		for(i=0; i<c.length; i++)	{
				if(isArray(hide_these))	{
					if (hide_these.inArray(c[i].className)) {
						c[i].style.display = st;
					}
				}
				else	{
					if(c[i].className == hide_these)	{
							c[i].style.display = st; 			  
					}
				}
		}		
}

function goFilterColumnMg(box, my_class)	{
	if(box.checked == true)
		goFilterColumn(true, my_class);
	else if(box.checked == false)
		goFilterColumn(false, my_class);
}

function goFilterColumnFromSelect(field, show_field)	{
	var filters = new Array(field.length);
	var shows = new Array(show_field.length);
		
	//for each value in the select, filter that column
	for(i=0; i<field.length; i++)	{
		filters[i] = field.options[i].value;
	}
	for(i=0; i<show_field.length; i++)	{
		shows[i] = show_field.options[i].value;
	}
	
	goFilterColumn(true, filters, shows);
	
}

/***** helper functions *******************/
function isArray(a) {
    return isObject(a) && a.constructor == Array;
}

Array.prototype.inArray = function (value)
// Returns true if the passed value is found in the
// array.  Returns false if it is not.
{
	var i;
	for (i=0; i < this.length; i++) {
		// Matches identical (===), not just similar (==).
		if (this[i] === value) {
			return true;
		}
	}
	return false;
};

function isFunction(a) {
    return typeof a == 'function';
}

function isObject(a) {
    return (a && typeof a == 'object') || isFunction(a);
}
