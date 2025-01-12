package com.example.demo.controllers;

import com.example.demo.domain.Part;
import com.example.demo.repositories.PartRepository;
import com.example.demo.domain.InhousePart;
import com.example.demo.service.InhousePartService;
import com.example.demo.service.InhousePartServiceImpl;
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
public class AddInhousePartController{
    @Autowired
    private InhousePartService inhouserepo;

    @Autowired
    private ApplicationContext context;

    @GetMapping("/showFormAddInPart")
    public String showFormAddInhousePart(@RequestParam(value = "partID", required = false) Long theId, Model theModel) {
        InhousePart inhousepart;

        if (theId != null) {
            inhousepart = inhouserepo.findById(theId);
            if (inhousepart == null) {
                throw new RuntimeException("Inhouse Part not found for ID: " + theId);
            }
        } else {
            inhousepart = new InhousePart();
        }

        theModel.addAttribute("inhousepart", inhousepart);
        return "InhousePartForm";
    }


    @PostMapping("/showFormAddInPart")
    public String submitForm(@Valid @ModelAttribute("inhousepart") InhousePart part, BindingResult theBindingResult, Model theModel){
        theModel.addAttribute("inhousepart",part);

        if(theBindingResult.hasErrors()){
            return "InhousePartForm";
        }

        //Inv below minimum
        if (part.getInv() < part.getMinInv()) {
            theBindingResult.rejectValue("inv", "error.inv", "Inventory cannot be less than the minimum.");
            return "InhousePartForm";
        }

        if (part.getInv() > part.getMaxInv()) {
            theBindingResult.rejectValue("inv", "error.inv", "Inventory cannot exceed the maximum.");
            return "InhousePartForm";
        }

        InhousePartService repo = context.getBean(InhousePartServiceImpl.class);
        repo.save(part);

        return "confirmationaddpart";
    }

    @PostMapping("/updateInhousePart")
    public String updateInhousePart(@Valid @ModelAttribute("inhousepart") InhousePart part, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "InhousePartForm"; // Return form with errors
        }

        if (part.getId() == null) {
            throw new RuntimeException("Part ID is missing");
        }

        InhousePartService inhousePartService = context.getBean(InhousePartServiceImpl.class);
        inhousePartService.save(part); // Save updated part to the database

        return "redirect:/mainscreen"; // Redirect to main screen after successful update
    }


}
