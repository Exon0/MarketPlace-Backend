package tn.workbot.coco_marketplace.controllers;

import com.google.maps.errors.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.workbot.coco_marketplace.entities.*;
import tn.workbot.coco_marketplace.services.interfaces.PickupIService;

import javax.swing.text.Position;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

@RestController
public class PickupController  {
    @Autowired
    PickupIService pis;


    @PostMapping("addPickup")
    public Pickup addPickup(@RequestBody Pickup pickup) {
        return pis.addPickup(pickup);
    }

    @DeleteMapping("RemovePickup")
    public void removePickup(@RequestParam Long id) {
             pis.removePickup(id);
    }

    @GetMapping("RetrievePickup")
    public Pickup RetrievePickup(@RequestParam Long id) {
        return pis.RetrievePickup(id);
    }

    @GetMapping("RetriveAllPickup")
    public List<Pickup> RetrievePickups() {
        return pis.RetrievePickups();
    }
    @PutMapping("UpdatePickup")
    public Pickup updatePickup(@RequestBody Pickup pickup){
        return  pis.updatePickup(pickup);
    }
    @PostMapping("AssignPickupByOder")
    public Pickup AssignPickupByOder(@RequestBody Pickup pickup, @RequestParam Long id) {
        return pis.AssignPickupByOder(pickup, id);
    }
    @GetMapping("RetrievePickupsByGovernoratBetweenPickupAndStoreAndDeliveryAgencyMen")
    public List<Pickup> RetrievePickupsByGovernoratBetweenPickupAndStoreAndDeliveryAgencyMen(@RequestParam Long id){
        return  pis.RetrievePickupsByGovernoratBetweenPickupAndStoreAndDeliveryAgencyMen(id);
    }
    @GetMapping("RetrievePickupsByGovernoratBetweenStoreAndDeliveryMenFreelancer")
    public List<Pickup> RetrievePickupsByGovernoratBetweenStoreAndDeliveryMenFreelancer() {
        return pis.RetrievePickupsByGovernoratBetweenStoreAndDeliveryMenFreelancer();
    }

    @GetMapping("RetrievePickupsbetweenAgencyBranchAndStoreInTheSomeGovernorat")
    public List<Pickup> RetrievePickupsbetweenAgencyBranchAndStoreInTheSomeGovernorat() {
        return pis.RetrievePickupsbetweenAgencyBranchAndStoreInTheSomeGovernorat();
    }
    @PostMapping("AssignPickupByStoreAndOrder")
    public Pickup AssignPickupByStoreAndOrder(@RequestBody Pickup pickup,@RequestParam Long id){
        return pis.AssignPickupByStoreAndOrder(pickup,id);
    }
    @PutMapping("ModifyStatusOfPickupByDelivery")
    public Pickup ModifyStatusOfPickupByDelivery(@RequestParam String Status,@RequestParam Long idPickup) {
        return pis.ModifyStatusOfPickupByDelivery(Status,idPickup);
    }
    @PostMapping("calculateDeliveryTime")
    public Duration calculateDeliveryTime(@RequestParam Long idPickup) throws IOException, InterruptedException, ApiException {
        return pis.calculateDeliveryTime(idPickup);
    }
    @PostMapping("hetcountt")
    public int test(@RequestParam Long id) {
        return pis.test(id);
    }
    @GetMapping("trakingbybuyer")
    public Pickup trakingbybuyer(@RequestParam String codePickup,@RequestParam Long idBuyer) {
        return pis.trakingbybuyer(codePickup, idBuyer);
    }
    @GetMapping("trakingbyseller")
    public Pickup trakingbyseller(@RequestParam String codePickup) {
        return pis.trakingbyseller(codePickup);
    }
    @GetMapping("retrievePickupByDeliveryMenFreelancer")
    public List<Pickup> retrievePickupByDeliveryMenFreelancer() {
        return pis.retrievePickupByDeliveryMenFreelancer();
    }


    @GetMapping("retrievePickupByAgence")
    public List<Pickup> retrievePickupByAgence() {
        return  pis.retrievePickupByAgence();
    }
    @GetMapping("retrievePickupByBranch")
    public List<Pickup> retrievePickupByBranch(Long idbranch) {
        return pis.retrievePickupByBranch(idbranch);
    }
    @GetMapping("retrieveOrderByseller")
    public List<Order> retrieveOrderByseller() {
        return pis.retrieveOrderByseller();
    }
    @GetMapping("retrievePickupBysellerAttent")
    public List<Pickup> retrievePickupBysellerAttent(){
              return pis.retrievePickupBysellerAttent();
    }

}

