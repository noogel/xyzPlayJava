package noogel.xyz.dao;

import noogel.xyz.annotation.DaoMapper;
import noogel.xyz.annotation.Param;
import noogel.xyz.annotation.Selector;
import noogel.xyz.model.BaseLineModel;

@DaoMapper
public interface BaseLineDao {

    @Selector("select * from "+ BaseLineModel.TABLE +" where report_name = #{reportName}")
    BaseLineModel select(@Param("reportName") String report_name);
}
