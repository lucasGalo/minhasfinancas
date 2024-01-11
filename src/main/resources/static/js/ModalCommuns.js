class ModalCommuns {

  static modalClose(constante) {
    switch (constante) {
      case FECHAMENTO_CAIXA_FISCAL_MODAL:
        $('#modal-close-caixa').modal('hide');
        break;
    }
  }

  static modalCloseId(identification) {
    $('#' + identification).modal('hide');
  }

  static modalOpenId(identification) {
    $('#' + identification).modal('show');
  }

  static modalOpen(constante) {
    switch (constante) {
      case SANGRIA_MODAL:
        $('#modal-sangria').modal('show');
        break;
      case DESCONTO_ITEM_MODAL:
        $('#modal-desconto_item').modal('show');
        break;
      case CANCELAR_ITEM_MODAL:
        $('#modal-default').modal('show');
        break;
      case FECHAMENTO_CAIXA_FISCAL_MODAL:
        $('#modal-fechamento-caixa-fiscal').modal('show');
        break;
    }
  }
}