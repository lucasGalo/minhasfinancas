class Utils{

    static getValueInput(idInput){
        return document.getElementById(idInput).value;
    }

    static setValueInput(idInput, value){
        if(value == null) return;
        document.getElementById(idInput).value = value;
    }

    static valueSelect(idSelect){
             $("#"+idSelect+" option:selected").val()
        }

    static selecionarSelect(idSelect, id){
         $("#"+idSelect).val(id).select2();
    }

    static criar(idSelect, id, texto){
        var $option = $("<option selected></option>").val(id).text(texto);
        $("#"+idSelect).append($option).trigger('change');
    }

    static getValorInputDouble(id){
            return parseFloat(document.getElementById(id).value != null && document.getElementById(id).value != "" && document.getElementById(id).value.replace(',', '.') > 0 ? document.getElementById(id).value.replace(',', '.') : 0)
    }

    static resetForm(id){ $("#"+id).trigger("reset"); }

    static resetSelect2(id){
        $('#'+id).val(null).trigger('change');
    }
}