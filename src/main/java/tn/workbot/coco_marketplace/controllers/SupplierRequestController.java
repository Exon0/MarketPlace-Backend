package tn.workbot.coco_marketplace.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tn.workbot.coco_marketplace.entities.Product;
import tn.workbot.coco_marketplace.entities.SupplierRequest;
import tn.workbot.coco_marketplace.services.interfaces.SupplierRequestInterface;

import javax.mail.MessagingException;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("supplier")
public class SupplierRequestController {

    @Autowired
    SupplierRequestInterface supplierRequestInterface;

    @PostMapping("createRequest")
    SupplierRequest create(@RequestBody SupplierRequest s, @RequestParam Long productId) throws Exception {
        return supplierRequestInterface.create(s, productId);
    }
    @PreAuthorize(" hasAuthority('SELLER') || hasAuthority('SUPPLIER') || hasAuthority('ADMIN') ")

    @PutMapping("updateRequest")
    SupplierRequest update(@RequestBody SupplierRequest s) {
        return supplierRequestInterface.update(s);
    }
    @PreAuthorize(" hasAuthority('SELLER') || hasAuthority('SUPPLIER') || hasAuthority('ADMIN') ")

    @GetMapping("retrieveRequests")
    List<SupplierRequest> retrieveAll() {
        return supplierRequestInterface.retrieveAll();
    }
    @PreAuthorize(" hasAuthority('SELLER') || hasAuthority('SUPPLIER') || hasAuthority('ADMIN') ")

    @GetMapping("getRequestById")
    SupplierRequest getById(@RequestParam Long id) {
        return supplierRequestInterface.getById(id);
    }
    @PreAuthorize(" hasAuthority('SELLER') || hasAuthority('SUPPLIER') || hasAuthority('ADMIN') ")

    @DeleteMapping("DeleteRequest")
    void delete(@RequestParam Long id) throws Exception {
        supplierRequestInterface.deleteById(id);
    }
    @PreAuthorize(" hasAuthority('SELLER') || hasAuthority('SUPPLIER') || hasAuthority('ADMIN') ")

    @GetMapping("ProductsOutOfStock")
    List<Product> retriveProductsOutOfStock() {
        return supplierRequestInterface.retriveProductsOutOfStock();
    }



    @PutMapping("acceptRequest")
    public SupplierRequest accpetRequestBySeller(@RequestBody SupplierRequest supplierRequest) {
      return  supplierRequestInterface.accpetRequestBySeller(supplierRequest);

    }
    @PutMapping("ConfirmDelivery")
    void ConfirmDelivery(@RequestParam Long supplierRequestId) throws Exception {
        supplierRequestInterface.confirmRequestDelivery(supplierRequestId);

    }
}
