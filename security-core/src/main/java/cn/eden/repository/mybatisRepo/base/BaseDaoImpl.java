package cn.eden.repository.mybatisRepo.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
/**
 * 1. 抽象类可以有构造方法，接口不能有构造方法
 * 2. 抽象类可以有普通成员变量
 * 3. 抽象类可以包含静态方法
 * 4. 一个类可以实现多个接口，但只能继承一个抽象类
 * 抽象案例：员工，经理，老总都是人，但是工作方式不同
 *
 * 抽象类实现 大部分持久层操作
 * 抽象类实现 所有持久层操作(查询),且返回List<Bean>
 */
@Service
public class BaseDaoImpl implements BaseDao {
    @Autowired
    SqlCache sqlCache;
    @Autowired
    SqlTemplate template;
    /**
     * 通过sqlid查询sql进行执行
     * @param sqlId
     * @param paramMap
     * @return
     */
    @Override
    public List<Map<String, Object>> queryListBySqlId(String sqlId, Map<String, Object> paramMap) {
        String sql=sqlCache.findSqlById(sqlId);
        if("zzzz".equals(sql)){
            return null;
        }else{
            paramMap.put("sql",sql);
            return template.executeSql(paramMap);
        }
    }
    /**
     * 直接传入sql语句进行执行
     * @param sql
     * @param paramMap
     * @return
     */
    @Override
    public List<Map<String, Object>> queryListBySql(String sql, Map<String, Object> paramMap) {
        paramMap.put("sql",sql);
        return template.executeSql(paramMap);
    }
}
