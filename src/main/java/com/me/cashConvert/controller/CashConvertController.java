package com.me.cashConvert.controller;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.me.cashConvert.service.CurrencyService;

@Controller
public class CashConvertController {

    private final CurrencyService currencyService;

    public CashConvertController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/")
    public String showForm(Model model) {
        Map<String, String> currencies = currencyService.getAvailableCurrencies();
        model.addAttribute("currencies", currencies);
        return "index";
    }

    @PostMapping("/convert")
    public String convert(@RequestParam String from, 
                          @RequestParam String to, 
                          @RequestParam BigDecimal amount, 
                          Model model) {
        BigDecimal result;

        if (from.equals(to)) {
            result = amount; // Pas de conversion nécessaire
        } else {
            BigDecimal rate = currencyService.getExchangeRate(from, to);
            result = rate.multiply(amount);
        }

        model.addAttribute("result", result);
        model.addAttribute("currencies", currencyService.getAvailableCurrencies()); // Recharger la liste des devises
        return "index";
    }
}
