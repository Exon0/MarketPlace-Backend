package tn.workbot.coco_marketplace.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.workbot.coco_marketplace.entities.Product;
import tn.workbot.coco_marketplace.entities.ProductCategory;
import tn.workbot.coco_marketplace.entities.Store;
import tn.workbot.coco_marketplace.repositories.ProductCategoryRepository;
import tn.workbot.coco_marketplace.repositories.ProductRepository;
import tn.workbot.coco_marketplace.services.interfaces.ProductInterface;

import java.util.List;

@Service
@Slf4j
public class ProductService implements ProductInterface {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    StoreService storeService;

    @Autowired
    ProductCategoryService productCategoryService;

    @Autowired
    ProductCategoryRepository productCategoryRepository;


    @Override
    public Product create(Product p) {
        return productRepository.save(p);
    }


    @Override
    public Product update(Product p) {
        return productRepository.save(p);
    }


    @Override
    public List<Product> retrieveAll() {
        return productRepository.findAll();
    }

    @Override
    public Product getById(Long id) {
        if (productRepository.findById(id).isPresent()) {
            return productRepository.findById(id).get();
        }

        return new Product();
    }

    @Override
    public void delete(Product p) {
        productRepository.delete(p);
    }

    @Override
    public Product createAndAssignToStore(Product p, Long idStore) {
        Store store = storeService.getById(idStore);
        p.setStore(store);
        productRepository.save(p);
        return p;
    }

    @Override
    public Product createAndAssignCategoryAndSubCategory(Product p, String categoryName, String subCatName) {

        ProductCategory category = productCategoryRepository.findByName(categoryName);
        ProductCategory subCategory = productCategoryRepository.findByNameAndCategoryName(subCatName, categoryName);
        if (category == null && subCategory == null) {
            ProductCategory category1 = new ProductCategory();
            category1.setName(categoryName);
            subCategory = new ProductCategory();
            subCategory.setName(subCatName);
            subCategory.setCategory(category1);
            productCategoryRepository.save(subCategory);

        }
        if (category != null && subCategory == null) {
            subCategory = new ProductCategory();
            subCategory.setName(subCatName);
            subCategory.setCategory(category);
            productCategoryRepository.save(subCategory);


        }

        p.setProductCategory(subCategory);

        productRepository.save(p);
        return p;
    }
}




