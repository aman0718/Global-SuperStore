package com.learn.globalsuperstore.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.learn.globalsuperstore.Item;
import com.learn.globalsuperstore.Service.StoreService;

import jakarta.validation.Valid;


@Controller
public class StoreController {

    @Autowired
    StoreService storeService; 

    // Another way of registering Dependency Injection
    // public StoreController(StoreService storeService){
    //     this.storeService = storeService;
    // }


    @GetMapping("/")
    public String getForm(Model model, @RequestParam(required = false) String id){

        model.addAttribute("item", storeService.getItemFromId(id));
        return "form";
    }


    @PostMapping("/submitItem")
    public String handleSubmit(@Valid Item item, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if(item.getPrice() < item.getDiscount()){
            bindingResult.rejectValue("price", "", "Price cannot be less than discount");
        }

        if(bindingResult.hasErrors())return "form";

        String status = storeService.handleSubmit(item);

        //save flash attribute to flash status of data --- Status: Success
        redirectAttributes.addFlashAttribute("status", status);
        return "redirect:/inventory";
    }
    
 
    @GetMapping("/inventory")
    public String getInventory(Model model){
        model.addAttribute("items", storeService.getAllItems());
        return "inventory";
    }
    
}
