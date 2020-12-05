package noogel.xyz.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.time.LocalDateTime;


public class JdbcUtil {
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询
     * @param clazz model类
     * @param sql
     * @param params
     * @param <T>
     * @return
     */
    public <T> T query(Class<T> clazz, String sql, Object... params) throws SQLException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        Object model =  clazz.newInstance();
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cat", "root", "123456")) {
            PreparedStatement statement = conn.prepareStatement(sql);
            int flag = 1;
            for (Object obj : params) {
                setValue(statement, flag, obj);
                flag++;
            }
            ResultSet resultSet = statement.executeQuery();
            resultSet.afterLast();
            resultSet.previous();
            fullRes(resultSet, model);
        }
        return (T) model;
    }

    private static void setValue(PreparedStatement statement, int flag, Object obj) throws SQLException {
        if (obj instanceof Integer) {
            statement.setInt(flag, (Integer) obj);

        } else if (obj instanceof String) {
            statement.setString(flag, (String) obj);

        } else if (obj instanceof LocalDateTime) {
            Timestamp timestamp = Timestamp.valueOf((LocalDateTime) obj);
            statement.setTimestamp(flag, timestamp);
        } else {
            statement.setObject(flag, obj);
        }
    }

    private static void fullRes(ResultSet resultSet, Object model) throws SQLException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Field[] declaredFields = model.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            String fieldName = field.getName();
            if (fieldName.toUpperCase().equals(fieldName)) {
                continue;
            }
            String setFuncName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            String fieldType = field.getGenericType().toString();

            Object object = resultSet.getObject(fieldName);
            if (fieldType.equals("class java.lang.String")) {
                Method m = model.getClass().getMethod(setFuncName, String.class);
                m.invoke(model, object);
            } else if (fieldType.equals("class java.lang.Integer")) {
                Method m = model.getClass().getMethod(setFuncName, Integer.class);
                m.invoke(model, object);
            } else if (fieldType.equals("class java.time.LocalDateTime")) {
                Method m = model.getClass().getMethod(setFuncName, LocalDateTime.class);
                if (object instanceof Timestamp) {
                    object = ((Timestamp) object).toLocalDateTime();
                }
                m.invoke(model, object);
            }
        }
    }
}
