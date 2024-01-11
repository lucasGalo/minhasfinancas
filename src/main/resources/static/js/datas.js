function dataAtualAnoMesDia() {
    let data = new Date();
    return data.getFullYear() + "-" + (data.getMonth() + 1) + "-" + data.getDate();
}

function dataAtual() {
    let data = new Date();
    return ((data.getFullYear())) + "-" + ((data.getMonth() + 1)) + "-" + data.getDate() + "T" + data.getHours() + ":" + data.getMinutes() + ":" + data.getSeconds() + "." + data.getMilliseconds() + "S";
}

function dataAtualDiaMesAnoHoraMinSeng() {
    let data = new Date();
    return ((data.getDay())) + "/" + ((data.getMonth() + 1)) + "/" + data.getFullYear() + " " + data.getHours() + ":" + data.getMinutes() + ":" + data.getSeconds();
}
