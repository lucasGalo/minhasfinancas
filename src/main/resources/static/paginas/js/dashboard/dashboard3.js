$(function () {
  'use strict'

  var ticksStyle = {
    fontColor: '#495057',
    fontStyle: 'bold'
  }

  var mode = 'index'
  var intersect = true

  var $salesChart = $('#sales-chart')
  var piramide = JSON.parse($('#piramide').val());
  var optionsSales ={
                          maintainAspectRatio: false,
                          tooltips: {
                            mode: mode,
                            intersect: intersect
                          },
                          hover: {
                            mode: mode,
                            intersect: intersect
                          },
                          legend: {
                            display: false
                          },
                          scales: {
                            yAxes: [{
                              // display: false,
                              gridLines: {
                                display: true,
                                lineWidth: '4px',
                                color: 'rgba(0, 0, 0, .2)',
                                zeroLineColor: 'transparent'
                              },
                              ticks: $.extend({
                                beginAtZero: true,

                                // Include a dollar sign in the ticks
                                callback: function (value) {
                                  if (value >= 1000) {
                                    value /= 1000
                                    value += 'k'
                                  }

                                  return 'R$' + value
                                }
                              }, ticksStyle)
                            }],
                            xAxes: [{
                              display: true,
                              gridLines: {
                                display: false
                              },
                              ticks: ticksStyle
                            }]
                          }
                        }
  // eslint-disable-next-line no-unused-vars
  var salesChart = new Chart($salesChart, {
    type: 'bar',
    data: piramide,
    options: optionsSales
  })

  var $visitorsChart = $('#visitors-chart')

    var visitors = JSON.parse($('#visitors').val());

    var options = {
                        maintainAspectRatio: false,
                        tooltips: {
                          mode: mode,
                          intersect: intersect
                        },
                        hover: {
                          mode: mode,
                          intersect: intersect
                        },
                        legend: {
                          display: false
                        },
                        scales: {
                          yAxes: [{
                            // display: false,
                            gridLines: {
                              display: true,
                              lineWidth: '4px',
                              color: 'rgba(0, 0, 0, .2)',
                              zeroLineColor: 'transparent'
                            },
                            ticks: $.extend({
                              beginAtZero: true,
                              suggestedMax: 50
                            }, ticksStyle)
                          }],
                          xAxes: [{
                            display: true,
                            gridLines: {
                              display: false
                            },
                            ticks: ticksStyle
                          }]
                        }
                      }

    var visitorsChart = new Chart($visitorsChart, {
      data: visitors,
      options: options
    } )


})

// lgtm [js/unused-local-variable]
