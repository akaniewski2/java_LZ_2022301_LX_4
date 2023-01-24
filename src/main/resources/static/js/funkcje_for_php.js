function showAlert() {
console.log("The button was clicked!");
    alert("The button was clicked!");

};

//-- # FUKCJE DLA PROJEKTU JAVA START ----------------------------------------
// <?php   $strona->WyswietlTvKanaly();   ?>
//<div style="clear:both"></div><div id=1153155135,1153122495 class="kanal filterDiv ogolny film menu")  style="text-align: left;" ";> 2. TVP 2  </div>
// <div class="tresc">



function WyswietlTvKanaly() {
var trescDiv=document.getElementById('tresc');

// 4 sposob
tvChannels.forEach(val=>{
//tmp.innerHTML +=("#2ssdsadsads");

var div = document.createElement('div');
div.setAttribute('id', val.code;

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
//trescDiv.appendChild(content);

});

}

//---# FUKCJE DLA PROJEKTU JAVA  END -------------------------------------------


function test(text) { alert(text); }

function open_close_links() {
	var links = document.getElementsByClassName("link");
	var num = links.length;

	for (var i = 0; i < num; i++) {

		var win = window.open(links[i].href, "_blank")

		win.close();

	}

}

function open_close_link(link_href) {
	//link_href = "http://localhost/arkani/prod/v02/glowna.php";
	//alert(link_href);
	var win = window.open(link_href, "kanal", 'status=yes,visible=none,width=1, height=1,toolbar=no,menubar=no,scrollbars=no,resizable=no,left=10000, top=10000, width=1, height=1, visible=none', '');
	//var win = window.open(link_href, "_blank", 'toolbar=no,status=no,menubar=no,scrollbars=no,resizable=no,left=10000, top=10000, width=1, height=1, visible=none', '')
	//win.setTimeout(open_close_link(), 1000);
	//win.focus();
	//	win.top.opener = null;
	//win.close();
	//win.location();

	//win.blur();
	//window.focus();

	// if (document.readyState == "complete") {

	// 	setTimeout(() => { console.log("World!"); }, 2000);

	// }

	n = 0;

	while (n < 11000000) {
		n++;

	}
	win.close();

	// if (document.readyState === "complete" || document.readyState === "interactive") {
	// 	// 	//alert(link_href);
	// 	setTimeout("win.close()", 6000);
	// 	win.close();
	// }
}


filterSelection("all")
function filterSelection(c) {
	var x, i;
	x = document.getElementsByClassName("filterDiv");
	if (c == "all") c = "";
	for (i = 0; i < x.length; i++) {
		w3RemoveClass(x[i], "show");
		if (x[i].className.indexOf(c) > -1) w3AddClass(x[i], "show");
	}
}

function w3AddClass(element, name) {
	var i, arr1, arr2;
	arr1 = element.className.split(" ");
	arr2 = name.split(" ");
	for (i = 0; i < arr2.length; i++) {
		if (arr1.indexOf(arr2[i]) == -1) { element.className += " " + arr2[i]; }
	}
}

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
}

// Add active class to the current button (highlight it)
//if (document.readyState === "complete" || document.readyState === "interactive") {
//
//	btnContainer = document.getElementById("myBtnContainer");
//	//	alert(btnContainer);
//	btns = btnContainer.getElementsByClassName("btn");
//	//	alert(btnContainer);
//	for (var i = 0; i < btns.length; i++) {
//		btns[i].addEventListener("click", function () {
//			var current = document.getElementsByClassName("active");
//			current[0].className = current[0].className.replace(" active", "");
//			this.className += " active";
//		});
//	}
//}