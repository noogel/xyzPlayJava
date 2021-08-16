package noogel.xyz;

import noogel.xyz.model.BaseLineModel;
import noogel.xyz.utils.JdbcUtil;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class JdbcUtilTest {

    @Test
    public void testA() throws SQLException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        JdbcUtil jdbcUtil = new JdbcUtil();
        BaseLineModel test1 = jdbcUtil.query(BaseLineModel.class, "select * from baseline where report_name = ? and report_period = ?", "TEST1", 12);
        System.out.println(test1);
    }
}
