$(document).ready(function() {
  $("#cancelarRol").click(function(evento) {
    evento.preventDefault();
    if (confirm("Realmente deseja cancelar esse rol?"))
      window.location.replace($(this).attr('href'));
  });
  $('#selectServico').on('select2:select', function (e) {
      RolController.preencherValor();
      RolController.Total();
  });

})

