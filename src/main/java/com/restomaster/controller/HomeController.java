package com.restomaster.controller;

import com.restomaster.service.MenuService;
import com.restomaster.repository.TableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MenuService menuService;
    private final TableRepository tableRepository;


    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("items", menuService.getAllItems());
        model.addAttribute("tables", tableRepository.findAll());
        return "index";
    }

    @GetMapping("/item/{id}")
    public String itemDetail(@PathVariable Long id, Model model) {
        model.addAttribute("selectedItem", menuService.getItemById(id));
        model.addAttribute("suggestions", menuService.getSmartSuggestions(id));
        return "detail";
    }
}