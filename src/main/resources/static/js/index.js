function removelAll(idInput) {
    $('#div'+idInput+' a').each(function(index, element) {
        element.classList.remove('destaque');
    });
}

function addActive(idInput, valor, click){
    removelAll(idInput)
    click.classList.add('destaque');
    Utils.setValueInput(idInput, valor)
}