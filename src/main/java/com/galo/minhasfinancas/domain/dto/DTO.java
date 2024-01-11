package com.galo.minhasfinancas.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.galo.minhasfinancas.domain.entity.EntityBase;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.galo.minhasfinancas.reflectionCuston.ReflexaoCustom.sendMethod;
import static com.galo.minhasfinancas.sources.DateUtil.compare;
import static com.galo.minhasfinancas.sources.DateUtil.dataAtual;
import static com.galo.minhasfinancas.sources.LocalDateUtil.format;

import java.util.StringJoiner;

public abstract class DTO extends EntityBase {

    @JsonIgnore
    protected void setDefault(EntityBase obj){
        setId(obj.getId());
        setNome(obj.getNome());
        setDescricao(obj.getDescricao());
        setAtivo(obj.getAtivo());
        setGuid(obj.getGuid());
        setUpdateAt(obj.getUpdateAt());
        setCreateAt(obj.getCreateAt());
        setUrlPage(obj.getUrlPage());
    }

    public String concat() {
        return new StringJoiner(", ", "", "")
                .add("id=" + getId())
                .add("nome='" + getNome() + "'")
                .add("isAtivo=" + getAtivo())
                .add("guid=" + getGuid())
                .add("isPersistido=" + isPersistido())
                .add("urlPage='" + getUrlPage() + "'")
                .add("createAt=" + getCreateAt())
                .add("updateAt=" + getUpdateAt())
                .toString();
    }

    public String getAdesao() {
        Date data = getCreateAt();
        if (data == null)
            data = getUpdateAt();
        if (data == null)
            data = new Date();
        final LocalDate localDate = data.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return format(localDate, DD_MM_YYYY);
    }

    @JsonIgnore
    public String getCorte(String corte, int tamanho){
        if(corte != null && corte.length() > tamanho)
            return corte.substring(0, tamanho-3)+"...";
        else return corte;
    }
    @JsonIgnore
    private static final ObjectMapper mapper = new ObjectMapper();

    @JsonIgnore
    protected <T extends DTO> Date getDateisNotNull(T dto ){
        if(null != dto.getUpdateAt())
            return dto.getUpdateAt();
        else
            return dto.getCreateAt();
    }
    public String concatNome(List<? extends DTO> list){
        if(null == list || list.size() == 0) return "-";
        return list.stream().map(it -> it.getNome().split(" ")[0]).collect(Collectors.joining(", "));
    }
    public String concatNome(List<? extends DTO> list, String... metodos){
        if(null == list || list.size() == 0) return "-";
        return list.stream().map(
                it -> {
                    StringBuilder sb = new StringBuilder();
                    for (String met : metodos) {
                        sb.append(sendMethod(it, met));
                    }

                    return sb.toString();
                }
        ).collect(Collectors.joining("; "));
    }

    public Date getDataCad(){
        if(null == getUpdateAt() && null == getCreateAt()) return null;
        if(null != getCreateAt())
            return getCreateAt();
        else
            return getUpdateAt();
    }

    public Date getDataRecente(){
        if(null == getUpdateAt() && null == getCreateAt()) return new Date();
        if(null != getUpdateAt())
            return getUpdateAt();
        else
            return getCreateAt();
    }

    @JsonIgnore
    protected <T extends DTO> List<T> filterData(List<T> lista){
        return lista.stream()
                .filter(obj -> obj.getCreateAt() != null && compare(obj.getCreateAt(),dataAtual(), DD_MM_YYYY))
                .collect(Collectors.toList());
    }
    @JsonIgnore
    protected String toJson(Object obj){
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erro ao converter class: "+obj.getClass().getSimpleName()+", em json ");
        }
    }
}
