// *
// * Adicionar multiplos marcadores
// * 2013 - www.marnoto.com
// * Referencia: https://www.marnoto.com/2013/12/mapa-com-varios-marcadores-google-maps.html
// *

// Váriáveis necessárias
var map;
var infoWindow;

// A variável markersData guarda a informação necessária a cada marcador
// Para utilizar este código basta alterar a informação contida nesta variável
var markersData = [
   {
      lat: -21.16088269673196,
      lng: -47.80671777694632,
      nome: "Ribeirão preto",
      morada1:"Rua Diogo Cão, 125",
      morada2: "Praia da Barra",
      codPostal: "14050-070 Ribeirão preto" // não colocar virgula no último item de cada maracdor
   }
];

function initialize() {
   var mapOptions = {
      center: new google.maps.LatLng(-21.16088269673196, -47.80671777694632),
      zoom: 10,
      mapTypeId: google.maps.MapTypeId.ROADMAP,
   };

   map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);

   // cria a nova Info Window com referência à variável infowindow
   // o conteúdo da Info Window será atribuído mais tarde
   infoWindow = new google.maps.InfoWindow();

   // evento que fecha a infoWindow com click no mapa
   google.maps.event.addListener(map, 'click', function() {
      infoWindow.close();
   });

   // Chamada para a função que vai percorrer a informação
   // contida na variável markersData e criar os marcadores a mostrar no mapa
   displayMarkers();

}
google.maps.event.addDomListener(window, 'load', initialize);

// Esta função vai percorrer a informação contida na variável markersData
// e cria os marcadores através da função createMarker
function displayMarkers(){
    visibleMap();
}

// Função que cria os marcadores e define o conteúdo de cada Info Window.
function createMarker(latlng, nome, morada1, morada2, codPostal){
   var marker = new google.maps.Marker({
      map: map,
      position: latlng,
      title: nome
   });

   // Evento que dá instrução à API para estar alerta ao click no marcador.
   // Define o conteúdo e abre a Info Window.
   google.maps.event.addListener(marker, 'click', function() {
      
      // Variável que define a estrutura do HTML a inserir na Info Window.
      var iwContent = '<div id="iw_container" style="color: black">' +
            '<div class="iw_title"> ip: ' + nome + '</div>' +
         '<div class="iw_content">' + morada1 + '<br />' +
         morada2 + '<br />' +
         codPostal + '</div></div>';
      
      // O conteúdo da variável iwContent é inserido na Info Window.
      infoWindow.setContent(iwContent);

      // A Info Window é aberta.
      infoWindow.open(map, marker);
   });
}

function visible(){
   var element = document.getElementById('map-canvas');
   element.style.removeProperty("overflow");
   element.style.removeProperty("position");
   element.style.position = 'sticky';
}

function visibleMap(){

    Fetch.get(urlBase("/api/telemetrias/ano/2023"))
        .then((data) => {

            setMarkersData(data);

            // esta variável vai definir a área de mapa a abranger e o nível do zoom
           // de acordo com as posições dos marcadores
           var bounds = new google.maps.LatLngBounds();

           // Loop que vai estruturar a informação contida em markersData
           // para que a função createMarker possa criar os marcadores
           for (var i = 0; i < markersData.length; i++){

              var latlng = new google.maps.LatLng(markersData[i].lat, markersData[i].lng);
              var nome = markersData[i].nome;
              var morada1 = markersData[i].morada1;
              var morada2 = markersData[i].morada2;
              var codPostal = markersData[i].codPostal;

              createMarker(latlng, nome, morada1, morada2, codPostal);

              // Os valores de latitude e longitude do marcador são adicionados à
              // variável bounds
              bounds.extend(latlng);
           }

           // Depois de criados todos os marcadores
           // a API através da sua função fitBounds vai redefinir o nível do zoom
           // e consequentemente a área do mapa abrangida.
           map.fitBounds(bounds);

        })
        .catch(e => {
            console.log(e);
            toastr.error("Não consigo realizar a telemetria." + e);
        });
}

function setMarkersData(telemetrias){
       for (var i = 0; i < telemetrias.length-1; i++){
           var item = {
               'lat': telemetrias[i].latitude,
               'lng': telemetrias[i].longitude,
               'nome': telemetrias[i].ip,
               'morada1': telemetrias[i].session,
               'morada2': telemetrias[i].createAt,
               'codPostal': telemetrias[i].adesao
           }
            markersData.push(item);
       }
}