package com.example.demo.controllers;

import com.example.demo.domain.InhousePart;
import com.example.demo.domain.OutsourcedPart;
import com.example.demo.domain.Part;
import com.example.demo.repositories.PartRepository;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 *
 *
 *
 *
 */
@Controller
public class AddPartController {
    @Autowired
    private ApplicationContext context;

    @GetMapping("/showPartFormForUpdate")
    public String showPartFormForUpdate(@RequestParam("partID") Long theId, Model theModel) {
        OutsourcedPartService outsourcedrepo = context.getBean(OutsourcedPartServiceImpl.class);
        InhousePartService inhouserepo = context.getBean(InhousePartServiceImpl.class);

        // Try to fetch the part as an InhousePart
        InhousePart inhousePart = inhouserepo.findById(theId);
        if (inhousePart != null) {
            theModel.addAttribute("inhousepart", inhousePart);
            return "InhousePartForm";
        }

        // If not found, try to fetch the part as an OutsourcedPart
        OutsourcedPart outsourcedPart = outsourcedrepo.findById(theId);
        if (outsourcedPart != null) {
            theModel.addAttribute("outsourcedpart", outsourcedPart);
            return "OutsourcedPartForm";
        }

        // If the part is not found, throw an error
        throw new RuntimeException("Part not found for ID: " + theId);
    }


    @GetMapping("/deletepart")
    public String deletePart(@RequestParam("partID") Long theId) {
        PartService repo = context.getBean(PartServiceImpl.class);
        Part part = repo.findById(theId);
        if (part.getProducts().isEmpty()) {
            repo.deleteById(theId);
            return "confirmationdeletepart";
        } else {
            return "negativeerror";
        }
    }

}
