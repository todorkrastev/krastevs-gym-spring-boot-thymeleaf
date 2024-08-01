package com.todorkrastev.krastevsgym.web.controlleradvice;

import com.todorkrastev.krastevsgym.exception.PriceRangeNotFoundException;
import com.todorkrastev.krastevsgym.exception.DepartmentIdNotFoundException;
import com.todorkrastev.krastevsgym.exception.ProductByCategoryIdAndDepartmentIdNotFoundException;
import com.todorkrastev.krastevsgym.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ModelAndView handleObjectNotFound(ResourceNotFoundException resourceNotFoundException) {
        ModelAndView modelAndView = new ModelAndView("error/resource-not-found");
        modelAndView.addObject("message", resourceNotFoundException.getMessage());
        LOGGER.warn("{} {}", resourceNotFoundException.getMessage(), resourceNotFoundException.getStackTrace());

        return modelAndView;
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(DepartmentIdNotFoundException.class)
    public ModelAndView handleByPriceRangeNotFound(DepartmentIdNotFoundException departmentIdNotFoundException) {
        ModelAndView modelAndView = new ModelAndView("error/department-id-not-found");
        modelAndView.addObject("message", departmentIdNotFoundException.getMessage());
        LOGGER.warn("{} {}", departmentIdNotFoundException.getMessage(), departmentIdNotFoundException.getStackTrace());

        return modelAndView;
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(PriceRangeNotFoundException.class)
    public ModelAndView handlePriceRangeNotFound(PriceRangeNotFoundException priceRangeNotFoundException) {
        ModelAndView modelAndView = new ModelAndView("error/price-range-not-found");
        modelAndView.addObject("message", priceRangeNotFoundException.getMessage());
        LOGGER.warn("{} {}", priceRangeNotFoundException.getMessage(), priceRangeNotFoundException.getStackTrace());

        return modelAndView;
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductByCategoryIdAndDepartmentIdNotFoundException.class)
    public ModelAndView handleProductByCategoryIdAndDepartmentIdNotFound(ProductByCategoryIdAndDepartmentIdNotFoundException productByCategoryIdAndDepartmentIdNotFoundException) {
        ModelAndView modelAndView = new ModelAndView("error/product-by-category-id-and-department-id-not-found");
        modelAndView.addObject("message", productByCategoryIdAndDepartmentIdNotFoundException.getMessage());
        LOGGER.warn("{} {}", productByCategoryIdAndDepartmentIdNotFoundException.getMessage(), productByCategoryIdAndDepartmentIdNotFoundException.getStackTrace());

        return modelAndView;
    }
}
