class UsuarioController{

    static isNome(nomeAtual){

        let nome = Utils.getValueInput('inputNome')
        if(nome != '' && nomeAtual != nome) {
           Fetch.get("/api/usuarios/nome?nome=" + nome, null)
                       .then((data) => {
                           if(data){
                            Utils.setValueInput('inputNome', '');
                            document.getElementById("inputNome").style.border = "1px solid red";
                            document.getElementById("inputNome").focus();
                            toastr.error("Ja existe este nome cadastrado, favor utilizar outro: ");
                           }
                           document.getElementById("inputNome").style.border = "1px solid green";
                       })
                       .catch(e => { toastr.error("Não consigo realizar tal consulta. erro: " + e); });
        }
    }

static isEmail(emailAtual){

    let email = Utils.getValueInput('inputEmail')
    if(email != '' && emailAtual != email) {
       Fetch.get("/api/usuarios/email?email=" + email, null)
                   .then((data) => {
                       if(data){
                        Utils.setValueInput('inputEmail', '');
                        document.getElementById("inputEmail").style.border = "1px solid red";
                        document.getElementById("inputEmail").focus();
                        toastr.error("Ja existe este e-mail cadastrado, favor utilizar outro: ");
                       }
                       document.getElementById("inputEmail").style.border = "1px solid green";
                   })
                   .catch(e => { toastr.error("Não consigo realizar tal consulta. erro: " + e); });
    }
}
static Limpar(){
    $("#tblCliente").html("");
    $("#tblItem").html("");
    this.LimparCampos();
}

static LimparCampos(){
        document.getElementById("inputNomePes").value = '';
        document.getElementById("inputNomePes").value = '';
        document.getElementById("inputTelefone1Pes").value = '';
        document.getElementById("inputTelefone2Pes").value = '';
        document.getElementById("inputLogradouroPes").value = '';
        document.getElementById("inputRol").value = '';
        document.getElementById("inputLacre").value = '';
        document.getElementById("cpfcnpjPes").value = ''
        document.getElementById("inputData").value = '';
}

static buscaAjax(){
    document.getElementById("loading").style.display = 'inherit';
    this.disableBtn(true);

    let nome = Utils.getValueInput('inputNomePes').replaceAll(" ", "+");
    let cpfcnpj = Utils.getValueInput('cpfcnpjPes');
    let telefone1 = Utils.getValueInput('inputTelefone1Pes').replaceAll(" ", "+");
    let telefone2 = Utils.getValueInput('inputTelefone2Pes').replaceAll(" ", "+");
    let logradouro = Utils.getValueInput('inputLogradouroPes').replaceAll(" ", "+");
    let rol = Utils.getValueInput('inputRol').replaceAll(" ", "+");
    let lacre = Utils.getValueInput('inputLacre').replaceAll(" ", "+");
    let data = Utils.getValueInput('inputData');
    let url = "/usuarios/busca?nome="+nome+"&cpfcnpj="+cpfcnpj+"&telefone1="+telefone1+"&telefone2="+telefone2+"&logradouro="+logradouro+"&rol="+rol+"&lacre="+lacre+"&data="+data
    $("#tblCliente").html("<tr><td colspan='10'>Buscando.........</td></tr>");
    $("#tblCliente").load(url);
    setTimeout(function(){
      document.getElementById("loading").style.display = 'none';
      UsuarioController.disableBtn(false);
    }, 2000);
    this.LimparCampos();
}
static disableBtn(valor){
    document.getElementById("loading").disabled  = valor;
    document.getElementById("loadingItem").disabled  = valor;
}

static itemAjax(){
    document.getElementById("loadingItem").style.display = 'inherit';
     this.disableBtn(true);

    let nome = Utils.getValueInput('inputNomePes').replaceAll(" ", "+");
    let cpfcnpj = Utils.getValueInput('cpfcnpjPes');
    let telefone1 = Utils.getValueInput('inputTelefone1Pes').replaceAll(" ", "+");
    let telefone2 = Utils.getValueInput('inputTelefone2Pes').replaceAll(" ", "+");
    let logradouro = Utils.getValueInput('inputLogradouroPes').replaceAll(" ", "+");
    let rol = Utils.getValueInput('inputRol').replaceAll(" ", "+");
    let lacre = Utils.getValueInput('inputLacre').replaceAll(" ", "+");
    let data = Utils.getValueInput('inputData');
    let url = "/items/busca?nome="+nome+"&cpfcnpj="+cpfcnpj+"&telefone1="+telefone1+"&telefone2="+telefone2+"&logradouro="+logradouro+"&rol="+rol+"&lacre="+lacre+"&data="+data
    $("#tblItem").html("<tr><td colspan='10'>Buscando.........</td></tr>");
    $("#tblItem").load(url);
    setTimeout(function(){
        document.getElementById("loadingItem").style.display = 'none';
        UsuarioController.disableBtn(false);
    }, 2000);
    this.LimparCampos();
}

static isConfirmado(confirma){
    let nova = Utils.getValueInput('inputNova');
    if(confirma != null && confirma != '' && confirma == nova){
        document.getElementById("inputConfirme").style.border = "1px solid green";
        return true;
    }else{
        document.getElementById("inputConfirme").style.border = "1px solid red";
        return false;
    }
}
static trocarSenha(){
    let confirma = Utils.getValueInput('inputConfirme');
    if(!this.isConfirmado(confirma)) {
        return toastr.error("Senhas nao conferem ");
    }

    let id = Utils.getValueInput('perfilId');
    let antiga = Utils.getValueInput('inputAntiga');
    let nova = Utils.getValueInput('inputNova');

    let usuario = new UsuarioQuery('trocasenha', id, antiga, nova, null);
    Fetch.post(urlBase("/api/usuarios/query"), usuario.toJSON())
        .then((data) => {
             if(data == true){
                toastr.success("Senha trocada com sucesso! ")
                Utils.setValueInput('inputAntiga', '')
                Utils.setValueInput('inputNova', '')
                Utils.setValueInput('inputConfirme', '')
                document.getElementById("inputAntiga").style.border = "1px solid black";
                document.getElementById("inputNova").style.border = "1px solid black";
                document.getElementById("inputConfirme").style.border = "1px solid black";
             }else{
                document.getElementById("inputAntiga").style.border = "1px solid red";
                toastr.error("Senha Antiga errada ");
             }
        })
        .catch(e => {
            console.log(e);
            toastr.error("Não foi possivel trocar a senha. erro:" + e);
        });
}
static diminuir(valor){
    let resultado = 1000 - valor.length ;
    document.getElementById("limit").innerHTML = resultado+'/1000';
}

static anotacoes(anotacoes, id){

    if(anotacoes.length > 1000) {
        return toastr.error("o limite de texto é 1000 caracter");
    }

    let usuario = new UsuarioQuery('anotacoes', id, null, null, anotacoes);
    Fetch.post(urlBase("/api/usuarios/query"), usuario.toJSON())
        .then((data) => {
             if(data == true){
                toastr.success("Anotações salvas com sucesso! ")
             }else{
                toastr.error("Erro ao salvar anotações ");
             }
        })
        .catch(e => {
            console.log(e);
            toastr.error("Não foi possivel salvar as anotações. erro:" + e);
        });
}

}
