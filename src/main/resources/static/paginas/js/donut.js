/* global Chart:false */

$(function () {
  'use strict'

    /*
     * DONUT CHART
     * -----------
     */
         var donutDataRenda =  JSON.parse($('#donutDataRenda').val());

         $.plot('#donut-chart-renda', donutDataRenda, {
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
         })


    var donutData =  JSON.parse($('#donutDataDespesa').val());

    $.plot('#donut-chart-despesa', donutData, {
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
    })
    /*
     * END DONUT CHART
     */

  })

  /*
   * Custom Label formatter
   * ----------------------
   */
  function labelFormatter(label, series) {
    return '<div style="font-size:13px; text-align:center; padding:2px; color: #fff;">'
      + label
      + '</div>'
  }


  function update(ano, tipo){

       let url = "/resumos/"+tipo+"?ano="+ano

        $("#"+tipo+"Donut").html("");
        $("#"+tipo+"Donut").load(url);
        renderDonut(url, tipo);
  }

  function renderDonut(url, tipo) {
  $.get( "/api"+url, function( data ) {

           var donutData =  JSON.parse(data);

           $.plot('#donut-chart-'+tipo, donutData, {
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
           })
             });
    }


  $("#selectAnoRenda").on("change", function() {
    var ano = $(this).val();   // aqui vc pega cada valor selecionado com o this
        update(ano, 'renda');
  })

    $("#selectAnoDespesa").on("change", function() {
      var ano = $(this).val();   // aqui vc pega cada valor selecionado com o this
          update(ano, 'despesa');
    })
