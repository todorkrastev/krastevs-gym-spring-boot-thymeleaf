package com.todorkrastev.krastevsgym.web;

import com.todorkrastev.krastevsgym.exception.ApiObjectNotFoundException;
import com.todorkrastev.krastevsgym.model.dto.ConversionResultDTO;
import com.todorkrastev.krastevsgym.service.ExRateService;
import com.todorkrastev.krastevsgym.web.aop.WarnWhenExecutionExceeds;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
public class CurrencyController {
    private final ExRateService exRateService;

    public CurrencyController(ExRateService exRateService) {
        this.exRateService = exRateService;
    }

    @WarnWhenExecutionExceeds(timeInMilliseconds = 1000)
    @GetMapping("/api/convert")
    public ResponseEntity<ConversionResultDTO> convert(
            @RequestParam("from") String from,
            @RequestParam("to") String to,
            @RequestParam("amount") BigDecimal amount) {
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

        BigDecimal result = exRateService.convert(from, to, amount);

        return ResponseEntity.ok(new ConversionResultDTO(
                from,
                to,
                amount,
                result
        ));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ApiObjectNotFoundException.class)
    @ResponseBody
    public NotFoundErrorInfo handleApiObjectNotFoundException(ApiObjectNotFoundException apiObjectNotFoundException) {
        return new NotFoundErrorInfo("NOT FOUND", apiObjectNotFoundException.getId());
    }

    public record NotFoundErrorInfo(String code, Object id) {

    }
}
