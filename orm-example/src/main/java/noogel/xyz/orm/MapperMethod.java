package noogel.xyz.orm;

import noogel.xyz.annotation.Param;
import noogel.xyz.annotation.Selector;
import noogel.xyz.utils.JdbcUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.sql.SQLException;

public class MapperMethod<T> {
    private String sql;
    private Class<?> resType;
    private int[] paramsIndex;


    public MapperMethod(Method method) {
        this.resType = method.getReturnType();
        String sourceSql = method.getAnnotation(Selector.class).value();
        Parameter[] parameters = method.getParameters();
        int flag = 0;
        this.paramsIndex = new int[parameters.length];
        for (Parameter parameter: parameters) {
            String paramName = parameter.getAnnotation(Param.class).value();
            String paramFullName = String.format("#{%s}", paramName);
            int indexOf = sourceSql.indexOf(paramFullName);
            this.paramsIndex[flag] = indexOf;
            flag++;
            this.sql = sourceSql.replace(paramFullName, "?");
        }
    }

    public Object execute(Object[] objects) {
        JdbcUtil jdbcUtil = new JdbcUtil();
        try {
            return jdbcUtil.query(this.resType, this.sql, objects);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }
}
