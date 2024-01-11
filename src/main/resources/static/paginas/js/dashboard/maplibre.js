
/* Ao carregar a página preencher o map com os pontos*/
window.onload = function() {
  Marker();
};
/* add Market no map*/
function addMarket(Lat, Lng, map){
    const market = new maplibregl.Marker()
        .setLngLat([Lng, Lat])
        .addTo(map);
    map.on('error', function(err) {
        toastr.error("Erro ao add marcador, erro: " + err);
    });
}

/* Buscar e criar o map com os marcadores indicando os pontos dos usuários q acessaram o site. */
function Marker(){

    Fetch.get(urlBase("/api/telemetrias/ano/2023"))
        .then((data) => {
            var map = new maplibregl.Map({
                container: 'map',
                style:
                'https://api.maptiler.com/maps/streets/style.json?key=QBUYigYVF2GlUmXdiwBM',
                center: [-47.8849878,-21.1794523],
                zoom: 8
            });

           var Lat , Lng = 0;
           for (var i = 0; i < data.length; i++){
            Lat = data[i].latitude; Lng = data[i].longitude;
            if(Lat != null && Lng != null){
                console.log("ADD: " + Lng + ","+ Lat);
                addMarket(Lat, Lng, map);
              }
           }

        })
        .catch(e => {
            console.log(e);
            toastr.error("Não consigo realizar a telemetria." + e);
        });
}