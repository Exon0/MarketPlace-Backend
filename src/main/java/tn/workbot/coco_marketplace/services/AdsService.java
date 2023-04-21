package tn.workbot.coco_marketplace.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.workbot.coco_marketplace.configuration.SessionService;
import tn.workbot.coco_marketplace.entities.Ads;
import tn.workbot.coco_marketplace.entities.LastVued;
import tn.workbot.coco_marketplace.entities.Product;
import tn.workbot.coco_marketplace.entities.User;
import tn.workbot.coco_marketplace.entities.enmus.BudgetType;
import tn.workbot.coco_marketplace.entities.enmus.ObjectiveType;
import tn.workbot.coco_marketplace.repositories.AdsRepository;
import tn.workbot.coco_marketplace.repositories.LastVuedRepository;
import tn.workbot.coco_marketplace.repositories.ProductRepository;
import tn.workbot.coco_marketplace.repositories.UserrRepository;
import tn.workbot.coco_marketplace.services.interfaces.AdsInterface;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AdsService implements AdsInterface {
    @Autowired
    AdsRepository ar;
    @Autowired
    ProductRepository pr;
    @Autowired
    SessionService ss;
    @Autowired
    LastVuedRepository lvr;
    @Autowired
    UserrRepository ur;
    @Override
    public String assignAdsToProduct(Ads ads, Long idProduct) {
        Product product = pr.findById(idProduct).get();
        User user = ss.getUserBySession();
        if ((user.getAdsPoints() - ads.getAdsPoints()) > 0) {
            ads.setProduct(product);
            ads.setEnabled(true);
            user.setAdsPoints(user.getAdsPoints() - ads.getAdsPoints());
            ur.save(user);
            ar.save(ads);
            return null; // return null since the operation was successful
        } else {
            return "Insufficient Ads points"; // return an error message
        }
    }

    @Override
    public Ads descativatedAds(Long idAds) {
        Ads ads=ar.findById(idAds).get();
        ads.setEnabled(false);
        ads.setProduct(ads.getProduct());
        return ar.save(ads);
    }

    @Override
    public List<Ads> retrieveAllAds() {
        return (List<Ads>) ar.findAll();
    }

    @Override
    public Ads retrieveAdsById(Long idAds) {
        Ads ads=ar.findById(idAds).get();
        return ads;
    }

    @Override
    public List<Ads> retrieveAdsTOInterestedBuyerObSales() {
        User u=ss.getUserBySession();
        LocalDate currentDate = LocalDate.now();
        int years= Period.between(u.getBirthDate(), currentDate).getYears();
        List<Ads> adsList= (List<Ads>) ar.findAll();
        List<LastVued> lastVuedList=lvr.findAll();
        List<Ads> productList=new ArrayList<>();
        for (Ads a:adsList) {
            for (LastVued lv:lastVuedList) {
                if((lv.getUser().getId().equals(u.getId()))&&(a.getGender().equals(u.getGender()))&&(a.getEnabled().equals(true))
                        &&(a.getObjectiveType().equals(ObjectiveType.SALES))&&(a.getAudiencesAgeMin()<=years)&&(a.getAudiencesAgeMax()>=years)){
                    if(lv.getProductVued().getProductCategory().getName().equals(a.getProduct().getProductCategory().getName())){
                          productList.add(a);
                    }
                }
            }
        }
        return productList;
    }

    @Override
    public List<Ads> retrieveAdsTOInterestedBuyerObTraffic() {
        User u=ss.getUserBySession();
        List<Ads> adsList= (List<Ads>) ar.findAll();
        List<LastVued> lastVuedList=lvr.findAll();
        LocalDate currentDate = LocalDate.now();
        int years= Period.between(u.getBirthDate(), currentDate).getYears();
        List<Ads> productList=new ArrayList<>();
        for (Ads a:adsList) {
            for (LastVued lv:lastVuedList) {
                if((lv.getUser().getId().equals(u.getId()))&&(a.getGender().equals(u.getGender()))&&(a.getEnabled().equals(true))
                        &&(a.getObjectiveType().equals(ObjectiveType.TRAFFIC))&&(a.getAudiencesAgeMin()<=years)&&(a.getAudiencesAgeMax()>=years)){
                    if(lv.getProductVued().getProductCategory().getName().equals(a.getProduct().getProductCategory().getName())){
                        productList.add(a);
                    }
                }
            }
        }
        return productList;
    }

    @Override
    public Integer retrieveHMAwRWithAds(float adsPoints, Date startDate, Date expiredDate, BudgetType budgetType) {
        int reach = 0;
        if (adsPoints == 1) {
            if(budgetType.equals(BudgetType.DAILYBUDGET)){
                reach = 100;
            }
            else{


            }

        } else if (adsPoints > 1){
            if(budgetType.equals(BudgetType.DAILYBUDGET)){
                int adsPointsIncrement = (int) (adsPoints - 1);
                reach = 100 + adsPointsIncrement * 200;
            }
            else{

            }
        }
        return reach;
    }
}
