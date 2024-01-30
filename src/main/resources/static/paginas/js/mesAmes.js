/* global Chart:false */

$(function () {
  'use strict'

    /*
     * DONUT CHART
     * -----------
     */
    var donutDataRenda =  JSON.parse($('#donutDataRenda').val());
    createDonut('donut-chart-renda', donutDataRenda);

    var donutData =  JSON.parse($('#donutDataDespesa').val());
    createDonut('donut-chart-despesa', donutData);

  })

  function labelFormatter(label, series) {
    return '<div style="font-size:13px; text-align:center; padding:2px; color: #fff;">'
      + label
      + '</div>'
  }


  function update(ano,mes, tipo){

       let url = "/mes/"+tipo+"?ano="+ano
       let urlMes = "/mes/"+tipo+"/mes?ano="+ano+"&mes="+mes

        $("#"+tipo+"Donut").html("");
        $("#"+tipo+"Donut").load(url);
        renderDonut(urlMes, tipo);
  }

  function renderDonut(url, tipo) {
    $.get( "/api"+url, function( data ) {

       var donutData =  JSON.parse(data);
       createDonut('donut-chart-'+tipo, donutData);
    });

  }

    function createDonut(id, donutData){
     $.plot('#'+id, donutData, {
                 series: {
                   pie: {
                     show       : true,
                     radius     : 1,
                     innerRadius: 0.5,
                     label      : {
                       show     : true,
                       radius   : 2 / 3,
                       formatter: labelFormatter,
                       threshold: 0.1
                     }
                   }
                 },
                 legend: {
                   show: false
                 }
               });
    }

  $("#selectAnoRenda").on("change", function() {
    var ano = $(this).val();   // aqui vc pega cada valor selecionado com o this
        let mes = Utils.valueSelect("selectMesRenda")
        update(ano, mes, 'renda');
  })

$("#selectAnoDespesa").on("change", function() {
  var ano = $(this).val();   // aqui vc pega cada valor selecionado com o this
//      let mes = Utils.valueSelect("selectMesDespesa");
  let mes = $("#selectMesDespesa").val()
      update(ano,mes, 'despesa');
})
