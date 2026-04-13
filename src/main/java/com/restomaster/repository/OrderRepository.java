package com.restomaster.repository;

import com.restomaster.model.Order;
import com.restomaster.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {


    @Query("SELECT oi2.menuItem FROM Order o " +
            "JOIN o.orderItems oi1 " +
            "JOIN o.orderItems oi2 " +
            "WHERE oi1.menuItem.id = :itemId AND oi2.menuItem.id <> :itemId " +
            "GROUP BY oi2.menuItem " +
            "ORDER BY COUNT(oi2.menuItem) DESC")
    List<MenuItem> findTopSuggestions(@Param("itemId") Long itemId);
}