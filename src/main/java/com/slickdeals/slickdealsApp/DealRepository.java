package com.slickdeals.slickdealsApp;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DealRepository extends JpaRepository<Deal, Long> {
    List<Deal> findByNameContaining(String name);

    List<Deal> findByNameAndDescriptionAndPrice(String name, String description, double price);


    @Query("SELECT d FROM Deal d WHERE d.price < :price")
    List<Deal> findDealsByPriceLessThan(@Param("price") double price);

}
