package com.slickdeals.slickdealsApp.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;import org.springframework.web.bind.annotation.RestController;

import com.slickdeals.slickdealsApp.Deal;
import com.slickdeals.slickdealsApp.DealService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/deals")
@Tag(name = "Deals API", description = "API for managing deals")
public class SlickdealsController {
    private static final Logger logger = LoggerFactory.getLogger(DealService.class);

    @Autowired
    private DealService dealService;

    @GetMapping
    @Operation(summary = "Get all deals")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deals retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public List<Deal> getDeals() {
        return dealService.getDeals();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get deal by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deal retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Deal not found")
    })
    public Deal getDealById(@PathVariable Long id) {
        return dealService.getDealById(id);
    }

    @PostMapping
    @Operation(summary = "Create new deal")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Deal created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public ResponseEntity<?> createDeal(@RequestBody Deal deal) {
        try {
            Deal createdDeal = dealService.createDeal(deal);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdDeal);
        } catch (RuntimeException e) {
            logger.error("An error occurred while creating the deal", e);
            if (e.getMessage().equals("Deal already exists")) {
                return ResponseEntity.badRequest().body("Deal already exists");
            } else {
                return ResponseEntity.internalServerError().body("An error occurred while creating the deal");
            }
        } catch (Exception e) {
            logger.error("An unexpected error occurred while creating the deal", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }


    @PutMapping("/{id}")
    @Operation(summary = "Update existing deal")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deal updated successfully"),
            @ApiResponse(responseCode = "404", description = "Deal not found")
    })
    public Deal updateDeal(@PathVariable Long id, @RequestBody Deal deal) {
        return dealService.updateDeal(id, deal);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete deal by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Deal deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Deal not found")
    })
    public void deleteDeal(@PathVariable Long id) {
        dealService.deleteDeal(id);
    }
}