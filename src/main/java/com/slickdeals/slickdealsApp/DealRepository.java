package com.slickdeals.slickdealsApp;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DealRepository extends JpaRepository<Deal, Long> {
    List<Deal> findByNameContaining(String name);

    @Query("SELECT d FROM Deal d WHERE d.price < :price")
    List<Deal> findDealsByPriceLessThan(@Param("price") double price);

}
