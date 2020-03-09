package cn.eden.repository.hibernateRepo;

import cn.eden.repository.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu,Long> {

    public Menu findMenuByUrl(String url);

}
