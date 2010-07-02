<!-- Web Site:  http://www.philwebb.com -->

<!-- This script and many more are available free online at -->
<!-- The JavaScript Source!! http://javascript.internet.com -->
     
        
		 
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

function saveMe(tbox) {
var strValues = "";
var boxLength = tbox.length;
var count = 0;
if (boxLength != 0) {
for (i = 0; i < boxLength; i++) {
if (count == 0) {
strValues = tbox.options[i].value;
}
else {
strValues = strValues + "," + tbox.options[i].value;
}
count++;
   }
}
if (strValues.length == 0) {
//alert("You have not made any selections");
}
else {
//alert("Here are the values you've selected:\r\n" + strValues);
for(i=0; i < boxLength-1; i++)
  {
    tbox.options[i].selected = true;
  }

}

}
 
    