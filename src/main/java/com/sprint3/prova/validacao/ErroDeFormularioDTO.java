package com.sprint3.prova.validacao;

public class ErroDeFormularioDTO {
private String campo;
private String mensagemErro;

    public ErroDeFormularioDTO(String campo, String mensagemErro) {
        this.campo = campo;
        this.mensagemErro = mensagemErro;
    }

    public String getCampo() {
        return campo;
    }

    public String getMensagemErro() {
        return mensagemErro;
    }


}
