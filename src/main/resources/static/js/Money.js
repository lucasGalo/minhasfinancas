class Money{

    static CurretyMoney(valor){
        return valor.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' });
    }

    static toDouble(valor){
        return valor.replace(".", "").replace("R$", "").replace(",", ".").trim();
    }
}