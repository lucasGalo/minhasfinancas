function dataTable(){

       $("#example1").DataTable({
         "responsive": true, "lengthChange": false, "autoWidth": false,
         "buttons": ["copy", "csv", "excel", "pdf", "print", "colvis"],
         "iDisplayLength": 50,
         "language": {
                   "search": "Pesquisar",
                   "zeroRecords": "Nenhum dado encontrado.",
                   "info": "Mostrando de _START_ até _END_ de _TOTAL_ registros",
                   "infoEmpty": "Mostrando 0 até 0 de 0 registros",
                   "infoFiltered": "(Filtrados de _MAX_ registros)",
                   "infoThousands": ".",
                   "loadingRecords": "Carregando...",
                   "processing": "Processando...",
                   "paginate": {
                     "first": '<i class="fas fa-angle-double-left"></i>',
                     "last": '<i class="fas fa-angle-double-right"></i>',
                     "previous": '<i class="fas fa-angle-left"></i>',
                     "next": '<i class="fas fa-angle-right"></i>',
                   },
         }
       }).buttons().container().appendTo('#example1_wrapper .col-md-6:eq(0)');
}

