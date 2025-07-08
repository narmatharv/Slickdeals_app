package com.slickdeals.slickdealsApp.Controller;

import org.springframework.ui.Model;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.slickdeals.slickdealsApp.Deal;
import com.slickdeals.slickdealsApp.DealService;

@Controller
public class SlickdealsController {
    @Autowired
    private DealService dealService;

    @GetMapping("/slickdeals")
    public String getSlickdealsPage(Model model) {
        List<Deal> deals = dealService.getDeals();
        model.addAttribute("deals", deals);
        return "slickdeals";
    }
}