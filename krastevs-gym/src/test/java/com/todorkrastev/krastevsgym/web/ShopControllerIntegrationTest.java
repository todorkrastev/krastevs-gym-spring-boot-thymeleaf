package com.todorkrastev.krastevsgym.web;

import com.todorkrastev.krastevsgym.service.DepartmentCategoryService;
import com.todorkrastev.krastevsgym.service.PriceFilterService;
import com.todorkrastev.krastevsgym.service.ProductCategoryService;
import com.todorkrastev.krastevsgym.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ShopControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    DepartmentCategoryService departmentCategoryService;

    @Autowired
    ProductService productService;

    @Autowired
    ProductCategoryService productCategoryService;

    @Autowired
    PriceFilterService priceFilterService;

    @Test
    @WithMockUser(username = "user", password = "topsecret")
    void testShop() throws Exception {
//        List<DepartmentCategoryDTO> categories = departmentCategoryService.findAll();
        mockMvc.perform(get("/shop"))
                .andExpect(status().isOk())
                .andExpect(view().name("shop"))
                .andExpect(model().attributeExists("categories"))
                .andExpect(model().size(1));
    }

    @Test
    @WithMockUser(username = "user", password = "topsecret")
    void testProductsByDepartment() throws Exception {
//        List<DepartmentCategoryDTO> departmentCategories = departmentCategoryService.findAll();
//        List<ProductShortInfoDTO> products = productService.findAllByDepartmentId(1L);
//        List<ProductCategoryDTO> categories = productCategoryService.findAll();
//        List<PriceFilterDTO> priceFilters = priceFilterService.findAll();
        mockMvc.perform(get("/shop/products-by-department/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("products-by-department"))
                .andExpect(model().attributeExists("departmentCategories"))
                .andExpect(model().attributeExists("products"))
                .andExpect(model().attributeExists("categories"))
                .andExpect(model().attributeExists("priceFilters"))
                .andExpect(model().size(4));
    }

    @Test
    @WithMockUser(username = "user", password = "topsecret")
    void testProductsByCategory() throws Exception {
//        List<DepartmentCategoryDTO> departmentCategories = departmentCategoryService.findAll();
//        List<ProductShortInfoDTO> products = productService.findAllByCategoryId(1L, 1L);
//        List<ProductCategoryDTO> categories = productCategoryService.findAll();
//        List<PriceFilterDTO> priceFilters = priceFilterService.findAll();
        mockMvc.perform(get("/shop/products-by-department/{id}/products-by-category/{categoryId}", 1, 1))
                .andExpect(status().isOk())
                .andExpect(view().name("products-by-department"))
                .andExpect(model().attributeExists("departmentCategories"))
                .andExpect(model().attributeExists("products"))
                .andExpect(model().attributeExists("categories"))
                .andExpect(model().attributeExists("priceFilters"))
                .andExpect(model().size(4));
    }

    @Test
    @WithMockUser(username = "user", password = "topsecret")
    void testProductsByPriceRange() throws Exception {
//        List<DepartmentCategoryDTO> departmentCategories = departmentCategoryService.findAll();
//        List<ProductShortInfoDTO> products = productService.findByPriceRange(1L, "from-0-to-10");
//        List<ProductCategoryDTO> categories = productCategoryService.findAll();
//        List<PriceFilterDTO> priceFilters = priceFilterService.findAll();
        mockMvc.perform(get("/shop/products-by-department/{id}/products-by-price-range/{fromTo}", 1, "from-0-to-10"))
                .andExpect(status().isOk())
                .andExpect(view().name("products-by-department"))
                .andExpect(model().attributeExists("departmentCategories"))
                .andExpect(model().attributeExists("products"))
                .andExpect(model().attributeExists("categories"))
                .andExpect(model().attributeExists("priceFilters"))
                .andExpect(model().size(4));
    }

    @Test
    @WithMockUser(username = "user", password = "topsecret")
    void testProduct() throws Exception {
//        ProductDetailsDTO product = productService.findById(1L);
        mockMvc.perform(get("/shop/product/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("product"))
                .andExpect(model().attributeExists("product"))
                .andExpect(model().size(1));
    }

    @Test
    @WithMockUser(username = "admin@admin.com", password = "admin", roles = {"ADMIN"})
    void testDeleteProduct() throws Exception {
//        Long departmentId = productService.deleteById(1L);

        mockMvc.perform(delete("/shop/product/{id}", 1)
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/shop/products-by-department/" + 1));
    }
}