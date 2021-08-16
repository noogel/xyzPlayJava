package noogel.xyz;

import noogel.xyz.dao.BaseLineDao;
import noogel.xyz.model.BaseLineModel;
import noogel.xyz.orm.MapperProxyFactory;

public class Demo1 {

    public static void main(String[] args) {
        MapperProxyFactory mapperProxyFactory = new MapperProxyFactory(BaseLineDao.class);
        BaseLineDao baseLineDao = (BaseLineDao) mapperProxyFactory.newInstance();
        BaseLineModel test1 = baseLineDao.select("TEST1");
        System.out.println(test1);
    }
}
