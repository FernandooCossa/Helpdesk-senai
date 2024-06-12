package com.helpdesksenai.helpdesksenai.enums;

public enum PerfilEnum {
    ADMIN(0, "Administrador"),
    CLIENTE(1, "Cliente"),
    TECNICO(2, "Técnico");
    private Integer codigo;
    private String descricao;

    PerfilEnum(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }
    public static PerfilEnum toEnum(Integer cod){
        if(cod == null){
            return null;
        }
        for (PerfilEnum perfil: PerfilEnum.values()) {
            if (cod.equals(perfil.getCodigo())){
                return perfil;
            }
        } throw new IllegalArgumentException("Perfil inválida"); }
}
