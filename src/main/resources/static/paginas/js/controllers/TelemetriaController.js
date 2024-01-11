class TelemetriaController{

    static newAcesso(telemetriaAction, position){
    let telemetria;
    if(position != null){
            telemetria = new TelemetriaQuery(telemetriaAction, '', '', position.coords.latitude, position.coords.longitude, '');
        }
    else{
            telemetria = new TelemetriaQuery(telemetriaAction, '', '', 0.00, 0.00, '');
        }

        Fetch.post(urlBase("/api/telemetrias/query"), telemetria.toJSON())
            .then((data) => {
                 console.log(data);
            })
            .catch(e => {
                console.log(e);
                toastr.error("Não consigo realizar a telemetria." + e);
            });
    }

        static async findAllAno(ano){

            Fetch.get(urlBase("/api/telemetrias/ano/"+ano))
                .then((data) => {
                console.log(data);
                return data;
                })
                .catch(e => {
                    console.log(e);
                    toastr.error("Não consigo realizar a telemetria." + e);
                });
        }
}

