function main() {

    var startLat=54.545;
    var startLon=18.545;
    var map = L.map('mapid').setView([startLat, startLon],13);

//    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
//    attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
//    }).addTo(map);

    L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
        maxZoom: 19,
        attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
    }).addTo(map);

    L.marker([startLat, startLon]).addTo(map)
    .bindPopup('v.text')
    .openPopup();

points.forEach(value=>{


       var marker =new  L.marker([value.lat, value.lon]).addTo(map);


                //marker.bindPopup('<img src="https://www.rulewatchohio.gov/Assets/Global/BusyIndicator.gif"/>');

                marker.bindPopup(value.text)
                marker.openPopup();
                 });

}