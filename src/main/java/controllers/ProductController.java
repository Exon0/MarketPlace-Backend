package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import services.ProductInterface;

@Controller

public class ProductController {

    @Autowired
    ProductInterface productInterface;
}