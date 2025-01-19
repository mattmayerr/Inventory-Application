package com.example.demo.validators;

import com.example.demo.domain.Part;
import com.example.demo.domain.Product;
import com.example.demo.service.ProductService;
import com.example.demo.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EnufPartsValidator implements ConstraintValidator<ValidEnufParts, Product> {
    @Autowired
    private ApplicationContext context;

    @Override
    public void initialize(ValidEnufParts constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Product product, ConstraintValidatorContext constraintValidatorContext) {
        if (context == null) return true;

        ProductService repo = context.getBean(ProductServiceImpl.class);

        if (product.getId() != 0) { // Existing product
            Product existingProduct = repo.findById(product.getId());

            // Calculate inventory difference
            int inventoryDifference = product.getInv() - existingProduct.getInv();

            // Validate associated parts
            if (inventoryDifference > 0) { // Only check if inventory increases
                for (Part part : existingProduct.getParts()) {
                    int adjustedInventory = part.getInv() - inventoryDifference;

                    // Check if adjusted inventory falls below the minimum
                    if (adjustedInventory < part.getMinInv()) {
                        // Customize the error message
                        constraintValidatorContext.disableDefaultConstraintViolation();
                        constraintValidatorContext.buildConstraintViolationWithTemplate(
                                "Part '" + part.getName() + "' will fall below its minimum inventory. Current: " +
                                        part.getInv() + ", Required: " + inventoryDifference + ", Minimum: " + part.getMinInv()
                        ).addConstraintViolation();
                        return false; // Validation failed
                    }
                }
            }
        }
        return true; // Validation passed
    }
}
