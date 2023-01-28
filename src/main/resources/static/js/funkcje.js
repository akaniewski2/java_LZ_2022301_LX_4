function showAlert() {
console.log("The button was clicked!");
    alert("The button was clicked!");

};
function nvl(mystr ,znak){
    if(mystr != null && mystr.length>0)
    {return ""+mystr;}
    else
    {return znak;}
};

function nvl2(mystr ,znak,znak2){
    if(mystr != null && mystr.length>0)
    {return znak;}
    else
    {return znak2;}
};
function numberWithSpaces(x) {

};
//https://stackoverflow.com/questions/16637051/adding-space-between-numbers

function numberWithSpaces(x) {
    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, " ");
};
//-- # FUKCJE DLA PROJEKTU JAVA START ----------------------------------------
// <?php   $strona->WyswietlTvKanaly();   ?>
//<div style="clear:both"></div><div id=1153155135,1153122495 class="kanal filterDiv ogolny film menu")  style="text-align: left;" ";> 2. TVP 2  </div>
// <div class="tresc">

function WyswietlMenu() {

tvChannels.forEach(val=>{
  $(".menu_kontener").append('<div id='+val.code  +' class="menu"> JQeury'+val.name+'</div>');

  });

};

function WyswietlTvKanaly() {

//$("#tvChannels").append('<div id=123121 class="menu"> JQeury    xxxhh h hoihiohi ho </div>');
// $("#tvChannelsDetails").append('<div id=123121 class="menu"> JQeury    xxxhh hhh h h  hoihiohi ho </div>');

//  $("#tvChannels").append('<div class="menu kanal> 1 <div align="right">  muza</div></div>'+
//  '<div class="menu kanal> 11 <div align="right">  muza,film</div></div>'+
//  '<div class="menu kanal> 111 <div align="right">  bajki</div></div>'+
//  '<div class="menu kanal> 1111 <div align="right">  inne</div></div>'+
//  );

tvChannels.forEach(val=>{
 if (val.name!='') {
  $("#tvChannels").append('<div id='+val.code+' class="menu kanal filterDiv" style="text-align: left;padding:5px 10px 5px 10px"> '+val.id+'. '+val.name+'<div style="float: right;"> '+'   '+ val.tag.concat(' ').replace(',',' ')+' </div></div>');
 // $("#tvChannelsDetails").append('<div id='+val.code+' class="menu kanal filterDiv" style="text-align: left;"> '+val.tag.concat(' ').replace(',',' ')+'</div>');
}
  });
};


function WyswietlTvPiloty(pilot='TV_VOLTA', div_id,block) {

i=0;
div_content="";
c_in_line=0;
m=0;

$("#pilot_nazwa").append('<div>' + pilot +'</div>');

tvRemotes.forEach(val=>{

	if (val.name == pilot && val.block==block && val.button!='FIND') {

		button=val.button;
		dec_=val.dec_;
		button_class = val.button_class;
		c_in_line=val.c_in_line;
		m=i+1;

//		div_content='<div id= '+dec_+' data-long-press-delay="500" class="przycisk '+button_class +'"> '+ button +' '+button_class+' '+ val.block + ' '+block +'</div>\n';
		div_content='<div id= '+dec_+' data-long-press-delay="500" class="przycisk '+button_class +'"> '+ button +'</div>\n';
		if((i+1) % c_in_line == 0 ) div_content += '<div style=clear:both;></div>' ;

		//'<div id=test > '+val.name+ ' </div>'
  $(div_id).append(div_content);

// return div_content;


		i++;
	};

  });
};


function f_tvRemotesListUniq() {
	
	const tvRemotesList =[];
	const tvRemotesListUniq =[];

	i=0;
	tvRemotes.forEach(val=>{
		
		tvRemotesList[i] =val.name;
		i++;
		 

		 });
	    console.log("tvRemotesList1:"+tvRemotesList);

		//  return tvRemotesList.sort().filter(function(value, index, array) {
        //     return (index === 0) || (value !== array[index-1]);
        // });
		
     return tvRemotesList.reduce((acc, d) => acc.includes(d) ? acc : acc.concat(d), []);

	 console.log("tvRemotesListUniq"+tvRemotesListUniq);
};




//
//function filterDiv() {
//
// // $("#inputFilter").on("keyup", function() {
//
//    var value = $(this).val().toLowerCase();
//
//    $(".tvChannels div").filter(function() {
//
//      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
//
//    });
//
//  }};



function WyswietlTvKanaly2() {
var trescDiv=document.getElementById('tresc');


tvChannels.forEach(val=>{
//tmp.innerHTML +=("#2ssdsadsads");

//var divTmp = document.createElement('div');
var content='<div id='+val.code+' class="kanal filterDiv" style="text-align: left;">'+val.name+'</div> '; //&nbsp
//filterDiv '+val.tag.replace(',',' ')+ '
//var content =val.name;
trescDiv.innerHTML += content;
console.log(content);
//var content=val.name;

//
//divTmp.setAttribute('id', val.code);
//var classTmp = 'kanal filterDiv '+val.tag.replace(',',' ');
//console.log(classTmp);
//divTmp.setAttribute('class', classTmp);
//divTmp.setAttribute('style', "text-align: left;");
//
//console.log(divTmp);


//var divClearBoth = document.createElement('divClearBoth');
//trescDiv.append(divClearBoth);
//trescDiv.append(divTmp);


//trescDiv.appendChild(div);
// var div = document.createElement('div');
//      div.innerHTML = "my <b>new</b> skill - <large>DOM maniuplation!</large>";
//      div.innerHTML = "my <b>new</b> "+tvChannels[i].name+"</large>";
//      // set style
//      div.style.color = 'red';
//      // better to use CSS though - just set class
//      div.setAttribute('class', 'myclass'); // and make sure myclass has some styles in css
//
//// 4b
//var theDiv = document.getElementById("tv_channels3");
//var content = document.createTextNode("<YOUR_CONTENT>");


});

};

filterSelection("all")
function filterSelection(c) {
	var x, i;
	x = document.getElementsByClassName("filterDiv");
	if (c == "all") c = "";
	for (i = 0; i < x.length; i++) {
		w3RemoveClass(x[i], "show");
		if (x[i].className.indexOf(c) > -1) w3AddClass(x[i], "show");
	}
};

function w3AddClass(element, name) {
	var i, arr1, arr2;
	arr1 = element.className.split(" ");
	arr2 = name.split(" ");
	for (i = 0; i < arr2.length; i++) {
		if (arr1.indexOf(arr2[i]) == -1) { element.className += " " + arr2[i]; }
	}
};

function w3RemoveClass(element, name) {
	var i, arr1, arr2;
	arr1 = element.className.split(" ");
	arr2 = name.split(" ");
	for (i = 0; i < arr2.length; i++) {
		while (arr1.indexOf(arr2[i]) > -1) {
			arr1.splice(arr1.indexOf(arr2[i]), 1);
		}
	}
	element.className = arr1.join(" ");
};

// Add active class to the current button (highlight it)
if (document.readyState === "complete" || document.readyState === "interactive") {

	btnContainer = document.getElementById("myBtnContainer");
	//	alert(btnContainer);
	btns = btnContainer.getElementsByClassName("btn");
	//	alert(btnContainer);
	for (var i = 0; i < btns.length; i++) {
		btns[i].addEventListener("click", function () {
			var current = document.getElementsByClassName("active");
			current[0].className = current[0].className.replace(" active", "");
			this.className += " active";
		});
	}
};