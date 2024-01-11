class StreamsController{

    /* ira habilitar a web cam e tirar uma foto */
    static capturarFoto() {

        $("#imagen").css('display', 'none');

        navigator.mediaDevices.getUserMedia({video: true})
            .then(function (mediaStream) {
                var video = document.querySelector('#video');

                video.srcObject = mediaStream;
                video.play();
            })
            .catch(function (err) {
                console.log('Não há permissões para acessar a webcam')
            })
    }
}