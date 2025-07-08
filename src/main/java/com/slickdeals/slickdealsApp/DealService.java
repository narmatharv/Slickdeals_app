package com.slickdeals.slickdealsApp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DealService {
    @Autowired
    private DealRepository dealRepository;

    public List<Deal> getDeals() {
        return dealRepository.findAll();
    }
}