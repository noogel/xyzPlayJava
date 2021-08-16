package noogel.xyz.orm;

import noogel.xyz.annotation.DaoMapper;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class MapperProxyFactory<T> {

    private final Class<T> mapperInterface;

    private Map<Method, MapperMethod> methodCache = new ConcurrentHashMap<>();

    public MapperProxyFactory(Class<T> mapperInterface) {
        if (Objects.isNull(mapperInterface.getAnnotation(DaoMapper.class))) {
            throw new RuntimeException("缺少注解 DaoMapper");
        }
        this.mapperInterface = mapperInterface;
    }


    public T newInstance() {
        final MapperProxy<T> mapperProxy = new MapperProxy<>(mapperInterface, methodCache);
        return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[]{mapperInterface}, mapperProxy);
    }
}
