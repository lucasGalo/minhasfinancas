class TelemetriaQuery {

    constructor(action, session, ip, latitude, longitude, headers){
        this.action = action;
          this.session = session;
          this.ip = ip;
          this.latitude = latitude;
          this.longitude = longitude;
          this.headers = headers;
    }

    toJSON(){
            let json = {};

            Object.keys(this).forEach(key =>{
                if(this[key] !== undefined && this[key] !== null) json[key] = this[key];
            });

            return json;
        }
}