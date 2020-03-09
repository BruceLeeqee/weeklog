package cn.eden.shop.dao;

import cn.eden.shop.domain.Good;
import cn.eden.shop.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsDao {

    public List<Good> getList();
}
