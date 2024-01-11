package com.galo.minhasfinancas.exceptions.commum;

import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.function.Function;

import static com.galo.minhasfinancas.sources.StringUtil.toJson;

public abstract class Comuns {

    protected static final Gson gson = new Gson();

    protected <T> Function functionToClass(T classe) {
        return (Function<String, T>) x -> gson.fromJson(toJson(x), (Type) classe);
    }

    protected <T> T jsonToClass2(String a1, Function<String, T> function) {
        return function.apply(a1);
    }

    public static<T> T jsonToClass(String a1, Class<T> classe) {
        Function<String, T> fu = x -> gson.fromJson(toJson(x), (Type) classe);
        return fu.apply(a1);
    }

    public static<T> T[] jsonToClassArray(String a1, Class<T[]> classe) {
        return (T[]) gson.fromJson(a1,  classe);
    }

}
