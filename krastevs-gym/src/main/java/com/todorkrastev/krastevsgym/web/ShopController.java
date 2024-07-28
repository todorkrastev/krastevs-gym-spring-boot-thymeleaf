package com.todorkrastev.krastevsgym.web;


import com.todorkrastev.krastevsgym.model.dto.*;
import com.todorkrastev.krastevsgym.service.DepartmentCategoryService;
import com.todorkrastev.krastevsgym.service.PriceFilterService;
import com.todorkrastev.krastevsgym.service.ProductCategoryService;
import com.todorkrastev.krastevsgym.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
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
    private final PriceFilterService priceFilterService;

    public ShopController(DepartmentCategoryService departmentCategoryService, ProductService productService, ProductCategoryService productCategoryService, PriceFilterService priceFilterService) {
        this.departmentCategoryService = departmentCategoryService;
        this.productService = productService;
        this.productCategoryService = productCategoryService;
        this.priceFilterService = priceFilterService;
    }

    @GetMapping
    public String shop(Model model) {
        List<DepartmentCategoryDTO> categories = departmentCategoryService.findAll();
        model.addAttribute("categories", categories);

        return "shop";
    }

    @GetMapping("/products-by-department/{id}")
    public String productsByDepartment(@PathVariable("id") Long id, Model model) {
        List<DepartmentCategoryDTO> departmentCategories = departmentCategoryService.findAll();
        model.addAttribute("departmentCategories", departmentCategories);

        List<ProductShortInfoDTO> products = productService.findAllByDepartmentId(id);
        model.addAttribute("products", products);

        List<ProductCategoryDTO> categories = productCategoryService.findAll();
        model.addAttribute("categories", categories);

        List<PriceFilterDTO> priceFilters = priceFilterService.findAll();
        model.addAttribute("priceFilters", priceFilters);

        return "products-by-department";
    }

    @GetMapping("/products-by-department/{id}/products-by-category/{categoryId}")
    public String productsByCategory(@PathVariable("id") Long departmentId, @PathVariable("categoryId") Long categoryId, Model model) {
        List<DepartmentCategoryDTO> departmentCategories = departmentCategoryService.findAll();
        model.addAttribute("departmentCategories", departmentCategories);

        List<ProductShortInfoDTO> products = productService.findAllByCategoryId(departmentId, categoryId);
        model.addAttribute("products", products);

        List<ProductCategoryDTO> categories = productCategoryService.findAll();
        model.addAttribute("categories", categories);

        List<PriceFilterDTO> priceFilters = priceFilterService.findAll();
        model.addAttribute("priceFilters", priceFilters);

        return "products-by-department";
    }

    @GetMapping("/products-by-department/{id}/products-by-price-range/{fromTo}")
    public String productsByPriceRange(@PathVariable("id") Long departmentId, @PathVariable("fromTo") String fromTo, Model model) {
        List<DepartmentCategoryDTO> departmentCategories = departmentCategoryService.findAll();
        model.addAttribute("departmentCategories", departmentCategories);

        List<ProductShortInfoDTO> products = productService.findByPriceRange(departmentId, fromTo);
        model.addAttribute("products", products);

        List<ProductCategoryDTO> categories = productCategoryService.findAll();
        model.addAttribute("categories", categories);

        List<PriceFilterDTO> priceFilters = priceFilterService.findAll();
        model.addAttribute("priceFilters", priceFilters);

        return "products-by-department";
    }

    @GetMapping("/product/{id}")
    public String product(@PathVariable("id") Long id, Model model) {
        ProductDetailsDTO product = productService.findById(id);
        model.addAttribute("product", product);

        return "product";
    }

    @DeleteMapping("/product/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        Long departmentId = productService.deleteById(id);

        return "redirect:/shop/products-by-department/" + departmentId;
    }
}