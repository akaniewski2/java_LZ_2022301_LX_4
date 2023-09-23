//------------------------------------------------------------------
// # funkcje pomocnicze OGOLNE


//nvl function
//https://stackoverflow.com/questions/2601978/how-to-check-if-my-string-is-equal-to-null

function nvl(mystr ,znak){
    if(mystr != null && mystr.length>0)
    {return ""+mystr;}
    else
    {return znak;}
}

function nvl2(mystr ,znak,znak2){
    if(mystr != null && mystr.length>0)
    {return znak;}
    else
    {return znak2;}
}
function numberWithSpaces(x) {

}
//https://stackoverflow.com/questions/16637051/adding-space-between-numbers

function numberWithSpaces(x) {
    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, " ");
}
//function numberWithSpaces(x) {
//    var parts = x.toString().split(".");
//    parts[0] = parts[0].replace(/\B(?=(\d{3})+(?!\d))/g, " ");
//    return parts.join(".");
//}
//------------------------------------------------------------------
// # funkcje pomocnicze PROJEKTU




//------------------------------------------------------------------
// # funkcje glowne PROJEKTU


function zmienne(){
var x="[[${x}]]";
var y="[[${y}]]";

//
//<!--//lista z javy mapowana na lite javascript-->
}

function makeMarker(){
   var Marker = L.marker
   Marker.on('mouseover', function(){Marker.bindPopup('HI').openPopup();});

   Marker.on('mouseout', function(){Marker.closePopup();});
}




//console.log("-2");
function main() {

    var map = L.map('mapid').setView([52.95, 19.238], 4);

    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
    }).addTo(map);


legend.addTo(map);


var info = L.control();
var info2 = L.control();
//console.log("-1");
info.onAdd = function (map) {
    //console.log('info.onAdd');
    this._div = L.DomUtil.create('div', 'info'); // create a div with a class "info"
    this.update();
    return this._div;
};

info2.onAdd = function (map) {
    //console.log('info.onAdd');
    this._div = L.DomUtil.create('div', 'info2'); // create a div with a class "info"
    this.update();
    return this._div;
};

//console.log("0");
// method that we will use to update the control based on feature properties passed

data0.forEach(value=>{

info.update = function (props) {
    this._div.innerHTML = '<h4>Liczba osób zakażonych Koronawirusem (SARS-CoV-2)</h4>'+'<h4>Stan na: '+ value.dtDanych2+'<h4>';
        // +  (props ? '<b>' + props.name + '</b><br />' + props.density + ' people / mi<sup>2</sup>' : '<h4>Dane na: '+ dt_danych+'<h4>'  );
};

info2.update = function (props) {
    this._div.innerHTML = '<h4>Świat<hr></h4>'+'<h4>Zakażenia: <span style="color:orange;padding-left:16px;">'+numberWithSpaces(value.confirmed)+'</span>'+
                                               '<br/>Chorzy:   <span style="color:red;padding-left:47px;"> '+numberWithSpaces(value.active)+'</span>'+
                                               '<br/>Wyzdrowienia:<span style="color:green;padding-left:5px;">'+numberWithSpaces(value.recovered)+'</span>'+
                                               '<br/> Zgony:<span style="color:black;padding-left:60px;"> '+numberWithSpaces(value.deaths)+'</span>  <h4>';
        // +  (props ? '<b>' + props.name + '</b><br />' + props.density + ' people / mi<sup>2</sup>' : '<h4>Dane na: '+ dt_danych+'<h4>'  );
};
});


//info2.update = function (props) {
//    this._div.innerHTML = '<div class="a"><h4>Świat<hr></h4>'+'<h4>Zakażenia:  <span style="color:orange">'+numberWithSpaces(data0[0].confirmed)+'</span>'+
//                                               '<br/>Chorzy:<span style="color:red"> '+numberWithSpaces(data0[0].active)+'</span>'+
//                                               '<br/>Wyzdrowienia:<span style="color:green">'+numberWithSpaces(data0[0].recovered)+'</span>'+
//                                               '<br/> Zgony:<span style="color:black"> '+numberWithSpaces(data0[0].deaths)+'</span>  <h4> </div>';
//        // +  (props ? '<b>' + props.name + '</b><br />' + props.density + ' people / mi<sup>2</sup>' : '<h4>Dane na: '+ dt_danych+'<h4>'  );
//};



info.addTo(map);
info2.addTo(map);
//console.log("1");

data.forEach(value=>{
//    if (value.ile>0) {
//console.log("2");
//console.log(value.confirmed+value.lat+value.lon);
        if (1==1) {

      //  console.log(value.confirmed+value.lat+value.lon);
//<!--     var ile= value.ile+" " +getColor(value.ile);
             var ile1= '<span style="color:'+getColor(value.confirmed)+'">' + value.confirmed   + '</span>';
             var ile2= ""+"<b>"+value.country_Region
                        + nvl2(value.province_State,", "+value.province_State+"<br/>","")//  value.country==value.state ?  "" : nvl2(value.state,", "+value.state+"<br>","")
                        + '<hr>'
                        + 'Zakażenia: <span style="color:'+getColor(value.confirmed)+'">'  +numberWithSpaces(value.confirmed) + '</span>' + "<br/>"
                        + 'Chorzy:<span style="color:red">'  +numberWithSpaces(value.active) + '</span>' + '<br/>'
                        + 'Wyzdrowienia: <span style="color:green">' +numberWithSpaces(value.recovered) + '</span>' +'<br/>'
                        + 'Zgony: <span style="color:black">'  +numberWithSpaces(value.deaths) + '</b>'+ '</span>' +'<br/>'
                        + nvl2(value.confirmedSumKraj,
                          '<br/><b>' + value.country_Region + ' - razem<b>'
                        +'<hr>'
                        + 'Zakażenia: <span style="color:'+getColor(value.confirmedSumKraj)+'">'   +numberWithSpaces(value.confirmedSumKraj) + '</span>' + "<br>"
                        + 'Chorzy:<span style="color:red">'  +numberWithSpaces(value.activeSumKraj) + '</span>' + '<br>'
                        + 'Wyzdrowienia: <span style="color:green">' +numberWithSpaces(value.recoveredSumKraj) + '</span>' +'<br>'
                        + 'Zgony: <span style="color:black">'  +numberWithSpaces(value.deathsSumKraj) +  '</span>' +'<br>'
                        ,"")
                        ;
             //+ "zoom:"+ map.getZoom();
/*
Poland
Confirmed: 684
Deaths: 8
Recovered: 13
Active: 663
*/
//            <!--var numMarker = L.ExtraMarkers.icon({
//            <!--icon:'fa-number',
//            <!--number:12,
//            <!--markerColor:'blue'
//            <!--});
//            <!--      L.marker([value.x, value.y],{icon:numMarker}).addTo(map)


            var numberIcon = L.divIcon({
                  className: "my-icon",
//                  icon: "leaf",
                    //iconSize: [25, 41],
//                  iconAnchor: [10, 44],
//                  popupAnchor: [3, -40],
                  html: ile1

            });
            //console.log(numberIcon.fontSize);
            //numberIcon.fontSize='15px';
            //console.log(numberIcon.fontSize);

//            var leafIcon = helper.getIcon(
//                    {icon: 'leaf',
//                    markerColor: 'red'}
//                );
            //var marker = new L.marker([value.x, value.y],{icon: numberIcon}).addTo(map)


             var marker =new  L.marker([value.lat, value.lon],{icon:numberIcon}).addTo(map);


            //marker.bindPopup('<img src="https://www.rulewatchohio.gov/Assets/Global/BusyIndicator.gif"/>');

            marker.bindPopup(ile2);
            //   .openPopup();



            marker.on('mouseover', function(event){
              marker.openPopup();
            });

            marker.on('mouseout', function(event){
              marker.closePopup();
            });



        }


//              map.on('zoomend', function(e) {
//                    zoom =map.getZoom();
////
//                    console.log("zoomsize:"+12 + ((zoom - 1) * 2) + 'px');
//                    var markerTextStyle = getStyle('.my-icon');
//                    // setMarkerTextSize(zoom);
//                     var x = document.getElementsByClassName('.my-icon').fontSize = 15 ;
//                    // var x = document.getElementsByClassName('.leaflet-marker-icon');
//                     x.fontSize = 12 + ((map.getZoom() - 1) * 2) + 'px';
//
//                    try {
//                    // numberIcon.style.fontSize = 12 + ((zoom - 1) * 4) + 'px';
//                     }catch(error) {console.error(error);}
//                    try {
//                     //markerTextStyle.style.fontSize= 12 + ((zoom - 1) * 4) + 'px';
//                     }catch(error) {console.error(error);}
//                    console.log("(map.getZoom()"+map.getZoom());
//                    });




});


            map.on('popupopen', function(event){
              var ll = event.popup.getLatLng();
            //  var url = 'http://api.openweathermap.org/data/2.5/weather?lat='+ll.lat+'&lon='+ll.lng;
            // $.getJSON(url, function(response){event.popup.setContent('Temperature: ' + response.main.temp); });
            document.getElementsByClassName('.my-icon').fontSize = 15 ;
            });

            map.on('popupclose', function(event){
            //  event.popup.setContent('<img src="https://www.rulewatchohio.gov/Assets/Global/BusyIndicator.gif"/>');

            });

//zoomstart
        map.on('zoomstart', function () {
                        var zoomLevel = map.getZoom();
                        var tooltip = $('.my-icon');
                        tooltip.css('font-size', 15);
                        //console.log( 'map.getZoom(): ', map.getZoom());
                        //console.log( 'font-size: ',tooltip.css('font-size'));
                        fsize = 12 + (( map.getZoom() - 2) * 2);
                        //console.log( 'fsize: ',fsize);
                        tooltip.css('font-size', fsize);
                        //console.log( 'font-size: ',tooltip.css('font-size'));

//                        switch (zoomLevel) {
//                            case -2:
//                                tooltip.css('font-size', );
//                                break;
//                            case -1:
//                                tooltip.css('font-size', 10);
//                                break;
//                            case 0:
//                                tooltip.css('font-size', 12);
//                                break;
//                            case 4:
//                                tooltip.css('font-size', 17);
//                                break;
//                            case 5:
//                                tooltip.css('font-size', 19);
//                                break;
//                            case 6:
//                                tooltip.css('font-size', 21);
//                                break;
//
//                            default:
//                                tooltip.css('font-size', 15);
//                        }
                    });

var markerTextStyle = getStyle('.my-icon');
var zoom=3;
// Set font size for givem zoom
function setMarkerTextSize(zoom) {


 //console.log("3/"+12 + ((zoom - 1) * 2) + 'px' );
 var x = document.getElementsByClassName('.leaflet-interactive');
// var x = document.getElementsByClassName('.leaflet-marker-icon');
 x.fontSize = 12 + ((map.getZoom() - 1) * 2) + 'px';
 ////console.log("2."+markerTextStyle.fontSize );
 }



//map.on('zoomend', function(e) {
////setMarkerTextSize(map.getZoom());
//setMarkerTextSize(zoom);
////console.log("(map.getZoom()"+map.getZoom());
//
//var x = document.getElementsByClassName('.my-icon');
//x.fontSize = 12 + ((map.getZoom() - 1) * 4) + 'px';
//
//
////.fontSize = 12 + ((map.getZoom() - 1) * 4) + 'px';
//
//});

}


var legend = L.control({position: 'bottomright'});

legend.onAdd = function (map) {
    //console.log('legend.onAdd');

    var div = L.DomUtil.create('div', 'info legend'),
        grades = [0, 100, 200, 500, 1000, 2000, 5000, 10000],
        labels = [];

    // loop through our density intervals and generate a label with a colored square for each interval
    for (var i = 0; i < grades.length; i++) {
        div.innerHTML +='<i style="background:' + getColor(grades[i] + 1) + '"></i> '  +
        grades[i] + (grades[i + 1] ? '&ndash;' + grades[i + 1] + '</br>' : '+');
    }
    //&ndash;
    //console.log(div);
    return div;
};






//------------------------------------------------------------------
// # testy

////https://leafletjs.com/examples/choropleth/
function getColor(d) {
    return d > 10000 ? '#BD0026' :
           d > 5000  ? '#eb0c22' :
           d > 2000  ? '#E31A1C' :
           d > 1000  ? '#FC4E2A' :
           d > 500   ? '#FD8D3C' :
           d > 200   ? '#FEB24C' :
           d > 100   ? '#FED976' :
                       '#80e099';
//                       '#FFEDA0';
}

function nvl_2(mystr ,znak){
if (mystr != null && !mystr.isEmpty()) {
    return mystr;
}
else {
    return znak;
}
}


//----------------------------------------------------------------------
// # testy 2

//
//// Function to get style of select CSS class
function getStyle(ruleClass) {
  for (var s = 0; s < document.styleSheets.length; s++) {
    var sheet = document.styleSheets[s];
    if (sheet.href == null) {
      var rules = sheet.cssRules ? sheet.cssRules : sheet.rules;
      if (rules == null) return null;
      for (var i = 0; i < rules.length; i++) {
        if (rules[i].selectorText == ruleClass) {
            return rules[i].style;
        }
      }
    }
  }
  return null;
}


//
////// Set inital font size
//setMarkerTextSize(3);
//


//
//    L.marker([x, y]).addTo(map)
//    .bindPopup('Hello from Poland')
//    .openPopup();
//
////lista z javy mapowana na lite javascript