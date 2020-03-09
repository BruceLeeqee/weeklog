package cn.eden.repository.mybatisRepo.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 一个类封装一个组件，功能
 */
@Service
public class SqlCache {
    @Autowired
    private SqlTemplate template;
    public static final Long EXPIRE = 30000L;

    private ConcurrentMap<String, String> sqlConfig = new ConcurrentHashMap<>();//sql缓存
    private ConcurrentMap<String, Long> sqlTimeConfig = new ConcurrentHashMap<>(); //sql时间缓存

    public String findSqlById(String sqlId) {
        if (sqlConfig.get(sqlId) == null || sqlTimeConfig.get(sqlId + "CACHE_TIME") == null ||
                System.currentTimeMillis() - Long.parseLong(sqlConfig.get(sqlId + "CACHE_TIME")) > 30000) {
            Map<String, Object> map = new HashMap<>();
            map.put("sqlId", sqlId);
            map.put("sql", "select sql_context from sql_config where sql_id=#{sqlId}");
            List<Map<String, Object>> list = template.executeSql(map);
            if(list.size()==0){
               return "zzzz";
            }
            sqlConfig.put(sqlId,(String) list.get(0).get(sqlId));
            sqlTimeConfig.put(sqlId+"CACHE_TIME",System.currentTimeMillis());
        }
        return sqlConfig.get(sqlId);
    }
}
