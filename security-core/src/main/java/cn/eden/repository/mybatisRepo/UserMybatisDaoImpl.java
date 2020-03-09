package cn.eden.repository.mybatisRepo;

import cn.eden.repository.entity.User;
import cn.eden.repository.mybatisRepo.base.MBUtils;
import cn.eden.repository.mybatisRepo.base.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Component
public class UserMybatisDaoImpl implements UserMybatisDao {

    @Autowired
    BaseDao baseDao;

    public static final String FIND_BY_PHONE="FIND_BY_PHONE";

    @Override
    public User findByUsername(String phone) {
            Map<String,Object> paramMap=new HashMap<>();
            paramMap.put("phone",phone);
            List<Map<String, Object>> list = baseDao.queryListBySqlId(FIND_BY_PHONE, paramMap);
            if(list.size()==0){
                System.out.println("查不到数据");
            }
        return (User) MBUtils.transMap2Bean(list.get(0), User.class);
    }
}
