package cn.eden.repository.mybatisRepo.base;

import java.util.List;
import java.util.Map;

public interface BaseDao {
     List<Map<String,Object>> queryListBySqlId(String sqlId,Map<String,Object> paramMap);

     List<Map<String,Object>> queryListBySql(String sql,Map<String,Object> paramMap);
}
