package com.galo.minhasfinancas.reflectionCuston;

import com.galo.minhasfinancas.domain.dto.DTO;
import com.galo.minhasfinancas.domain.entity.EntityBase;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/*https://stackoverflow.com/questions/9213708/using-java-reflection-how-to-get-the-constructor-of-a-class-specifying-a-derive*/
public class ReflexaoCustom {


  public static <T extends DTO, J extends EntityBase> T custom(Object obj, Class<T> dto, Class excluir) {
    if (null == obj) return null;
    Constructor<?> constructorForArgs = ReflexaoCustom.getConstructorForArgs(dto, new Class[]{obj.getClass(), String.class});
    try {
      return (T) constructorForArgs.newInstance(obj, excluir.getSimpleName());
    } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
      throw new RuntimeException(e);
    }
  }

  public static <T extends DTO, J extends EntityBase> List<T> custom(List<J> obj, Class<T> dto, Class excluir) {
    if (obj != null) {
      return (List<T>) toList(obj, dto, excluir);
    } else
      return new ArrayList<>();
  }

  private static <T extends DTO, J extends EntityBase> List toList(List<J> list, Class<T> dto, Class excluir) {
    return list.stream().map(it -> custom(it, dto, excluir)).collect(Collectors.toList());
  }

  public static <T extends DTO> Object sendMethod(DTO Klass, String metodo) {
    for (Method method : Klass.getClass().getDeclaredMethods()) {
      if (method.getName().equals(metodo)) {
        try {
          return method.invoke(Klass);
        } catch (IllegalAccessException | InvocationTargetException e) {
          throw new RuntimeException(e);
        }
      }
    }
    return null;
  }

  private static Constructor<?> getConstructorForArgs(Class<?> klass, Class[] args) {
    //Get all the constructors from given class
    Constructor<?>[] constructors = klass.getConstructors();

    for (Constructor<?> constructor : constructors) {
      //Walk through all the constructors, matching parameter amount and parameter types with given types (args)
      Class<?>[] types = constructor.getParameterTypes();
      if (types.length == args.length) {
        boolean argumentsMatch = true;
        for (int i = 0; i < args.length; i++) {
          //Note that the types in args must be in same order as in the constructor if the checking is done this way
          if (!types[i].isAssignableFrom(args[i])) {
            argumentsMatch = false;
            break;
          }
        }

        if (argumentsMatch) {
          //We found a matching constructor, return it
          return constructor;
        }
      }
    }

    //No matching constructor
    return null;
  }
}
