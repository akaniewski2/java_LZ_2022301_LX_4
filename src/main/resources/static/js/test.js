//https://stackoverflow.com/questions/29799493/thymeleaf-iterating-over-a-model-attribute-inside-javascript-code

function showAlert() {
console.log("The button was clicked!");
    alert("The button was clicked!");

};

function showAlert_() {
console.log("The button was clicked!");
    alert("The button was clicked!");

};


function f_tvChannels() {

//var tvChannels = [[${tvChannels}]];
    for (i = 0; i < tvChannels.length; i++) {



      //document.getElementsByClassName('tv_channels').appendChild(divElement)

//--1 sposob
      var div = document.createElement('div');
      div.innerHTML = "my <b>new</b> skill - <large>DOM maniuplation!</large>";
      div.innerHTML = "my <b>new</b> "+tvChannels[i].name+"</large>";
      // set style
      div.style.color = 'red';
      // better to use CSS though - just set class
      div.setAttribute('class', 'myclass'); // and make sure myclass has some styles in css
   //   document.body.appendChild(div);

//--2 sposob
      var tmp= document.getElementById('tv_channels');
      var divClearBoth = document.createElement('divClearBoth');
      tmp.append(divClearBoth);
      tmp.append(div);

//--3 sposob
      $("#tv_channels").append('<div class="classname"> JQeury'+tvChannels[i].name+'</div>');


//         theList[i]);



    }



    };

function f_tvChannels2() {

var tmp=document.getElementById('tv_channels2');

// 4 sposob
tvChannels.forEach(val=>{
// 4a
tmp.innerHTML +=("#2ssdsadsads"+val.name);


// 4b
var theDiv = document.getElementById("tv_channels3");
var content = document.createTextNode("<YOUR_CONTENT>"+val.name);
theDiv.appendChild(content);

});
    };



// prawdopodobnie dodawanie do listy
//    var items = [];
//
//    /*[# th:each="n : ${items}"]*/
//
//    items.push("[(${n})]");
//
//    /*[/]*/



//};