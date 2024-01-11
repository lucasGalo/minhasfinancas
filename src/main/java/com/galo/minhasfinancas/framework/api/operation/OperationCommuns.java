package com.galo.minhasfinancas.framework.api.operation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.galo.minhasfinancas.domain.entity.EntityBase;
import com.galo.minhasfinancas.exceptions.exceptions.ClassNotInstanceException;
import com.galo.minhasfinancas.exceptions.exceptions.ConvertValueInClassException;
import com.galo.minhasfinancas.exceptions.exceptions.ObjectNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.galo.minhasfinancas.exceptions.commum.Comuns.jsonToClass;

@SuppressWarnings("all")
public abstract class OperationCommuns {

    protected final ObjectMapper mapper = new ObjectMapper();

    public static <T extends EntityBase, J extends JpaRepository> T getClassFindById(final Class<T> rest, final J repository, final Integer ID) {
        return isPresent(rest, ID, repository.findById(ID));
    }


    private static <T extends EntityBase> T isPresent(Class<T> rest, Integer ID, Optional optional) {
        if (!optional.isPresent()) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id " + ID + ", Tipo '" + rest.getSimpleName() + "'");
        }
        return (T) optional.get();
    }

    public static <T extends EntityBase, J extends JpaRepository> List<T> getClassFindByIdList(final Class<T> rest, final J repository, final List<Integer> IDs) {
        List<T> list = new ArrayList<>();
        for (Integer id : IDs) {
            list.add(getClassFindById(rest, repository, id));
        }
        if (list.isEmpty()) {
            throw new ObjectNotFoundException("Lista vazia nenhum id válido: " + IDs + ", Tipo '" + rest.getSimpleName() + "'");
        }
        return list;
    }

    /**
     * Método capaz de verificar se determinado objeto está null lançara uma exception caso esteja
     *
     * @param obj objeto para verificar se esta nulo
     */
    public static void isObjectNull(final Object obj) {
        if (null == obj) {
            throw new ObjectNotFoundException("Objeto: não informado");
        }
    }

    /**
     * Metodo capaz de criar um dto a partir de um entity
     *
     * @param nome_class nome completo da classe ao qual será instanaciada.
     * @param obj        objeto ao qual obtera os valores.
     * @param <R>        classe que será retornada
     * @param <T>        tipo do objeto passado
     * @return retornara um novo objeto <R> a partir do Objeto <T>
     */
    protected <R, T> R dtoToObject(String nome_class, T obj) {
        try {
            Object o = Class.forName(nome_class).newInstance();

            return (R) dtoToObject(o.getClass(), obj);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new ClassNotInstanceException("Classe não conseguimos instancia a classe: " + nome_class);
        }

    }

    /**
     * Metodo capaz de instanciar uma classe a partir do nome
     *
     * @param nome_class nome completo da classe
     * @return retornara a classe instanciada pelo nome de forma como um objeto generico
     */
    protected Object instanceOf(String nome_class) {
        try {
            return Class.forName(nome_class).newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new ClassNotInstanceException("Classe não conseguimos instancia a classe: " + nome_class);
        }

    }

    protected <T, D> T dtoToObject(Class<T> clazz, D obj) {
        return toClass(toJson(obj), clazz);
    }

    /**
     * Método capaz de converter um objeto em json
     *
     * @param obj objeto ao qual ira ser convertido em josn
     * @return retornara uma String do tipo json.
     */
    protected String toJson(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new ConvertValueInClassException("Erro ao converter class: " + obj.getClass().getSimpleName() + ", em json [erro]" + e.getMessage());
        }
    }

    /**
     * Método capaz de converter um json em Objeto, mesmo o json vindo como umn toString()
     *
     * @param json  a String que será copnvertida para classe
     * @param clazz a classe ao qual o json será convertido
     * @param <T>   tip da classe
     * @return retornará a classe convertida do tipo passadop
     */
    protected <T> T toClass(String json, Class<T> clazz) {
        T t;
        try {
            t = mapper.convertValue(mapper.readTree(json), clazz);
        } catch (JsonProcessingException e) {

            if (json.contains("=") || json.contains("&") || !json.contains("{") || !json.contains("}")) {
                t = jsonToClass(json, clazz);
            } else {
                throw new ConvertValueInClassException("Erro ao converter json:" + json + "\n na classe: " + clazz.getSimpleName());
            }
        }

        return t;
    }

    /**
     * Método capaz de converter um json na classe a partir do nome e pacote.
     *
     * @param json  a String que será copnvertida para classe
     * @param clazz instanciará a classe a partir do nome completo da classe
     * @return retornará o objeto da classe instanciada.
     */
    @SuppressWarnings("unused")
    protected Object toClass(String json, String clazz) {
        try {
            Object o = instanceOf(clazz);
            JsonNode jsonNode = mapper.readTree(json);
            return mapper.convertValue(jsonNode, o.getClass());
        } catch (JsonProcessingException e) {
            throw new ConvertValueInClassException("Erro ao converter json:" + json + "\n na class: " + clazz);
        }
    }

}
