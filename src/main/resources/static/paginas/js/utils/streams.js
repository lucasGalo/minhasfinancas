// Referencia
// https://wallacemaxters.com.br/blog/2021/01/12/como-capturar-imagem-da-webcam-com-canvas#:~:text=Para%20fazer%20isso%2C%20precisamos%20capturar,ser%20enviada%20para%20o%20canvas%20.
document.querySelector('#capture').addEventListener('click', function (e) {
    var canvas = document.querySelector("#canvas");
    canvas.height = video.videoHeight;
    canvas.width = video.videoWidth;
    var context = canvas.getContext('2d');
     context.drawImage(video, 0, 0)
    //context.drawImage(video, 0, 0, 320, 240);

    var formData = document.getElementById('file');

    formData.value = canvas.toDataURL('image/jpeg', 1);

    // formData.value = canvas.toDataURL('image/png');

    // var link = document.createElement('a');
    // link.download = 'webcam.png';
    // link.href = canvas.toDataURL();
    // link.textContent = "upload";
    //
    // canvas.toBlob(function (blob) {
    //     formData.value = blob
    // })
})