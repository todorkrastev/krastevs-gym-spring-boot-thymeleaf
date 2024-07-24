package com.todorkrastev.krastevsgym.web;


import com.todorkrastev.krastevsgym.model.dto.DepartmentCategoryDTO;
import com.todorkrastev.krastevsgym.model.dto.ProductCategoryDTO;
import com.todorkrastev.krastevsgym.model.dto.ProductShortInfoDTO;
import com.todorkrastev.krastevsgym.service.DepartmentCategoryService;
import com.todorkrastev.krastevsgym.service.ProductCategoryService;
import com.todorkrastev.krastevsgym.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/shop")
public class ShopController {

    private final DepartmentCategoryService departmentCategoryService;
    private final ProductService productService;
    private final ProductCategoryService productCategoryService;

    public ShopController(DepartmentCategoryService departmentCategoryService, ProductService productService, ProductCategoryService productCategoryService) {
        this.departmentCategoryService = departmentCategoryService;
        this.productService = productService;
        this.productCategoryService = productCategoryService;
    }

    @GetMapping
    public String shop(Model model) {
        List<DepartmentCategoryDTO> categories = departmentCategoryService.findAll();
        model.addAttribute("categories", categories);

        return "shop";
    }

    @GetMapping("/products-by-department/{id}")
    public String productsByDepartment(@PathVariable("id") Long id, Model model) {
        List<ProductShortInfoDTO> products = productService.findAllByDepartmentId(id);
        model.addAttribute("products", products);

        List<ProductCategoryDTO> categories = productCategoryService.findAll();
        model.addAttribute("categories", categories);

        return "products-by-department";
    }
}
