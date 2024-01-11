package com.galo.minhasfinancas.domain.enums;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface BaseEnum {

    String getValue();

    int getCod();

    class BaseEnumMap {

        private static final Map<Class<?>, Map<String, BaseEnum>> map = new HashMap<>();
        private static final Map<Class<?>, Map<Integer, BaseEnum>> mapCod = new HashMap<>();

        private BaseEnumMap() {
            throw new IllegalStateException("Utility class");
        }

        private static Map<Class<?>, Map<String, BaseEnum>> getMap() {
            return map;
        }

        private static Map<Class<?>, Map<Integer, BaseEnum>> getMapCod() {
            return mapCod;
        }
    }

    /**
     * Provided the value set for an enum and the value (the product of BaseEnum.getValue()),
     * return the enumeration object with that value. Uses lazy loading to populate a static map to
     * increase performance, as opposed to iterating across the value set every time
     *
     * @param enums output of enum.values();
     * @param value the output of BaseEnumeration.getValue();
     * @return the enumeration object from enums with the getValue value of value
     */
    static BaseEnum getEnumInstance(BaseEnum[] enums, String value) {
        if (!BaseEnumMap.getMap().containsKey(enums.getClass())) {
            BaseEnumMap.getMap().put(enums.getClass(), buildMap(enums));
        }
        //return BaseEnumMap.getMap().get(enums.getClass()).get(value);

        BaseEnum baseEnum = BaseEnumMap.getMap().get(enums.getClass()).get(value);
        return Objects.isNull(baseEnum) ? null : baseEnum;
    }

    static BaseEnum getEnumInstance(BaseEnum[] enums, Integer cod) {
        if (!BaseEnumMap.getMapCod().containsKey(enums.getClass())) {
            BaseEnumMap.getMapCod().put(enums.getClass(), buildMapCod(enums));
        }
        BaseEnum baseEnum = BaseEnumMap.getMapCod().get(enums.getClass()).get(cod);
        if (Objects.isNull(baseEnum))
            throw new IllegalArgumentException("[BaseEnum] Código inválido: " + cod);

        return baseEnum;
    }

    /**
     * Assemble an enumeration array into a value -> enumeration object map
     *
     * @param list the output of enum.values()
     * @return a map of BaseEnumeration.getValue -> enumeration object
     */
    static BaseEnum getEnumInstance(List<BaseEnum[]> list, String value) {

        for (BaseEnum[] base : list) {
            BaseEnum enumInstance = getEnumInstance(base, value);
            if(enumInstance != null)
                return enumInstance;

        }
        throw  new IllegalArgumentException("[BaseEnum] Não foi encontrado value compativel na lista : " + list);
    }

    /**
     * Assemble an enumeration array into a value -> enumeration object map
     *
     * @param values the output of enum.values()
     * @return a map of BaseEnumeration.getValue -> enumeration object
     */
    static Map<String, BaseEnum> buildMap(BaseEnum[] values) {
        Map<String, BaseEnum> result = new HashMap<>();
        for (BaseEnum e : values) {
            result.put(e.getValue(), e);
        }
        return result;
    }

    static Map<Integer, BaseEnum> buildMapCod(BaseEnum[] values) {
        Map<Integer, BaseEnum> result = new HashMap<>();
        for (BaseEnum e : values) {
            result.put(e.getCod(), e);
        }
        return result;
    }


}
