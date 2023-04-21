package tn.workbot.coco_marketplace.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.workbot.coco_marketplace.entities.Ads;
import tn.workbot.coco_marketplace.entities.Product;
import tn.workbot.coco_marketplace.entities.enmus.BudgetType;
import tn.workbot.coco_marketplace.services.interfaces.AdsInterface;

import java.util.Date;
import java.util.List;

@RestController
public class AdsController {
    @Autowired
    AdsInterface ai;
    @PostMapping ("AssignAdsToProduct")
    public String assignAdsToProduct(@RequestBody Ads ads,@RequestParam Long idProduct) {
        return ai.assignAdsToProduct(ads,idProduct);
    }
    @PutMapping ("descativatedAds")
    public Ads descativatedAds(@RequestParam Long idAds) {
        return ai.descativatedAds(idAds);
    }
    @GetMapping("retrieveAllAds")
    public List<Ads> retrieveAllAds() {
        return ai.retrieveAllAds();
    }

    @GetMapping("retrieveAdsById")
    public Ads retrieveAdsById(@RequestParam Long idAds) {
        return ai.retrieveAdsById(idAds);
    }
    @GetMapping("retrieveProductAdsTOInterestedBuyer")
    public List<Ads> retrieveProductAdsTOInterestedBuyer() {
        return  ai.retrieveAdsTOInterestedBuyerObSales();
    }
    @GetMapping("retrieveAdsTOInterestedBuyerObTraffic")
    public List<Ads> retrieveAdsTOInterestedBuyerObTraffic() {
        return ai.retrieveAdsTOInterestedBuyerObTraffic();
    }
    @GetMapping("retrieveHMAwRWithAds")
    public Integer retrieveHMAwRWithAds(@RequestParam float adsPoints,
                                        @RequestParam(required = false) Date startDate,
                                        @RequestParam(required = false) Date expiredDate,
                                        @RequestParam(required = false) BudgetType budgetType) {
    return ai.retrieveHMAwRWithAds(adsPoints,startDate,expiredDate,budgetType);
    }
    }
