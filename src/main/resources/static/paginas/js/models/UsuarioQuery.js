class UsuarioQuery {

    constructor(action, id, antiga, nova, descricao){
      this.action = action;
      this.id = id;
      this.antiga = antiga;
      this.nova = nova;
      this.descricao = descricao;
    }

    toJSON(){
            let json = {};

            Object.keys(this).forEach(key =>{
                if(this[key] !== undefined && this[key] !== null) json[key] = this[key];
            });

            return json;
        }
}