package cn.eden.repository.mybatisRepo.base;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface SqlTemplate {
    List<Map<String,Object>> executeSql(Map<String,Object> map);
}
