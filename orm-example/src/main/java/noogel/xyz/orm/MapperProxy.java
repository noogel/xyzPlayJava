package noogel.xyz.orm;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;

public class MapperProxy<T> implements InvocationHandler {

    private final Class<T> mapperInterface;

    private final Map<Method, MapperMethod> methodCache;

    public MapperProxy(Class<T> mapperInterface, Map<Method, MapperMethod> methodCache) {
        this.mapperInterface = mapperInterface;
        this.methodCache = methodCache;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        MapperMethod mapperMethod = cachedMapperMethod(method);
        return mapperMethod.execute(objects);
    }

    private MapperMethod cachedMapperMethod(Method method) {
        MapperMethod mapperMethod = methodCache.get(method);
        if (Objects.isNull(mapperMethod)) {
            mapperMethod = new MapperMethod(method);
            methodCache.put(method, mapperMethod);
        }
        return mapperMethod;
    }
}
