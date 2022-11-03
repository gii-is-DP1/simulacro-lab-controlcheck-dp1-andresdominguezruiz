package org.springframework.samples.petclinic.product;



import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/product")
public class ProductController {
    private final String PRODUCT_CREATE_OR_UPDATE_FORM="products/createOrUpdateProductForm";
    private final String WELCOME_VIEW="welcome";
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService=productService;
    }

    @GetMapping("/create")
    public ModelAndView newProduct(){
        Product p=new Product();
        List<ProductType> types=productService.findAllProductTypes();
        ModelAndView result=new ModelAndView(PRODUCT_CREATE_OR_UPDATE_FORM);
        result.addObject("product", p);
        result.addObject("productTypes", types);
        return result;
    }

    @PostMapping("/create")
    public ModelAndView processCreatedProduct(@Valid Product newProduct,BindingResult result){
        ModelAndView res=null;
        if(result.hasErrors()){
            res= new ModelAndView(PRODUCT_CREATE_OR_UPDATE_FORM,result.getModel());
        }
        else{
            this.productService.save(newProduct);
            res=new ModelAndView(WELCOME_VIEW);
            res.addObject("message","Perfecto");
        }
        return res;

    }

    
}
