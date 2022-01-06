package chow.dao;

import com.chow.domain.Order;
import com.chow.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDao extends JpaRepository<Order, Integer> {
}
