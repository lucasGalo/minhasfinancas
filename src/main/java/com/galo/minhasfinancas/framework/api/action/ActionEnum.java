package com.galo.minhasfinancas.framework.api.action;

public interface ActionEnum<T, R> {
    Object execute(T query, R services);
}
