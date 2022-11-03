package org.springframework.samples.petclinic.product;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

@Component
public class ProductTypeFormatter implements Formatter<ProductType>{

    private final ProductService proService;

	@Autowired
	public ProductTypeFormatter(ProductService proService) {
		this.proService = proService;
	}

    @Override
    public String print(ProductType object, Locale locale) {
        return object.getName();
    }

    @Override
    public ProductType parse(String text, Locale locale) throws ParseException {
        ProductType productType=proService.getProductType(text);
        if(productType==null)
            throw new ParseException("type not found: " + text, 0);
        return productType;
    }
    
}
