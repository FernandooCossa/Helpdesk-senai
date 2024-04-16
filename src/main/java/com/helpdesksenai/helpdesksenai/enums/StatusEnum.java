package com.helpdesksenai.helpdesksenai.enums;

public enum StatusEnum {
    ABERTO(0, "Aberto"),
    ANDAMENTO(0, "Andamento"),
    ENCERRADO(0, "Encerrado");
    private Integer codigo;
    private String descricao;

    StatusEnum(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }
    public static StatusEnum toEnum(Integer cod){
        if(cod == null){
            return null;
        } for (StatusEnum status: StatusEnum.values()) {
            if (cod.equals(status.getCodigo())){
                return status;
            }
        } throw new IllegalArgumentException("Status inválido"); }
}
