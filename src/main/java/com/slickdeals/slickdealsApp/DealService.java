package com.slickdeals.slickdealsApp;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DealService {
    
    private static final Logger logger = LoggerFactory.getLogger(DealService.class);

    @Autowired
    private DealRepository dealRepository;

    public List<Deal> getDeals() {
        return dealRepository.findAll();
    }

    public Deal getDealById(Long id) {
        return dealRepository.findById(id).orElse(null);
    }

    public Deal createDeal(Deal deal) {
        // Check if a deal with the same name, description, and price already exists
        List<Deal> existingDeals = dealRepository.findByNameAndDescriptionAndPrice(deal.getName(), deal.getDescription(), deal.getPrice());
        if (!existingDeals.isEmpty()) {
            logger.warn("Deal already exists with name: {}, description: {}, and price: {}", deal.getName(), deal.getDescription(), deal.getPrice());
            throw new RuntimeException("Deal already exists");
        }
        logger.info("Creating a new deal with name: {}, description: {}, and price: {}", deal.getName(), deal.getDescription(), deal.getPrice());

        Deal savedDeal = dealRepository.save(deal);
        logger.info("Deal created successfully with ID: {}", savedDeal.getId());
        return savedDeal;
    }

    public Deal updateDeal(Long id, Deal deal) {
        return dealRepository.findById(id)
                .map(existingDeal -> {
                    existingDeal.setName(deal.getName());
                    existingDeal.setDescription(deal.getDescription());
                    existingDeal.setPrice(deal.getPrice());
                    return dealRepository.save(existingDeal);
                })
                .orElseThrow();
    }

    public void deleteDeal(Long id) {
        if (dealRepository.existsById(id)) {
            dealRepository.deleteById(id);  
        }
    }
}