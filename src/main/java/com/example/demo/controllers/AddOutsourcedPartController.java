package com.example.demo.controllers;

import com.example.demo.domain.InhousePart;
import com.example.demo.domain.OutsourcedPart;
import com.example.demo.domain.Part;
import com.example.demo.service.OutsourcedPartService;
import com.example.demo.service.OutsourcedPartServiceImpl;
import com.example.demo.service.PartService;
import com.example.demo.service.PartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/**
 *
 *
 *
 *
 */
@Controller
public class AddOutsourcedPartController {
    @Autowired
    private ApplicationContext context;

    @GetMapping("/showFormAddOutPart")
    public String showFormAddOutsourcedPart(Model theModel){
        OutsourcedPart outsourcedpart = new OutsourcedPart();
        outsourcedpart.setPrice(null);
        outsourcedpart.setInv(null);
        outsourcedpart.setMinInv(null);
        outsourcedpart.setMaxInv(null);
        theModel.addAttribute("outsourcedpart", outsourcedpart);
        return "OutsourcedPartForm";
    }

    @PostMapping("/showFormAddOutPart")
    public String submitOutsourcedPart(@Valid @ModelAttribute("outsourcedpart") OutsourcedPart part, BindingResult bindingResult, Model theModel) {
        theModel.addAttribute("outsourcedpart", part);

        if (bindingResult.hasErrors()) {
            return "OutsourcedPartForm";
        }

        if (part.getInv() < part.getMinInv()) {
            bindingResult.rejectValue("inv", "error.inv", "Inventory cannot be less than the minimum.");
            return "OutsourcedPartForm";
        }

        if (part.getInv() > part.getMaxInv()) {
            bindingResult.rejectValue("inv", "error.inv", "Inventory cannot exceed the maximum.");
            return "OutsourcedPartForm";
        }

        OutsourcedPartService repo = context.getBean(OutsourcedPartServiceImpl.class);
        repo.save(part);

        return "confirmationaddpart";

//        if (part.getCompanyName() == null || part.getCompanyName().isEmpty()) {
//            bindingResult.rejectValue("companyName", "error.companyName", "Company Name is required");
//            return "OutsourcedPartForm";
//        }

//        // Save part to the database
//        OutsourcedPartService partService = context.getBean(OutsourcedPartServiceImpl.class);
//        partService.save(part);
//        return "confirmationaddpart";
    }




}
