package com.example.springblog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MathController {
    @GetMapping("/add/{num1}/and/{num2}")
    @ResponseBody()
    public String add(@PathVariable double num1, @PathVariable double num2) {
        double sum = num1 + num2;
        return num1 + " + " + num2 + " is " + sum;
    }

    @GetMapping("/subtract/{num1}/from/{num2}")
    @ResponseBody()
    public String subtract(@PathVariable double num1, @PathVariable double num2) {
        double difference = num1 - num2;
        return num1 + " - " + num2 + " is " + difference;
    }

    @GetMapping("/multiply/{num1}/and/{num2}")
    @ResponseBody()
    public String multiply(@PathVariable double num1, @PathVariable double num2) {
        double multiplied = num1 * num2;
        return num1 + " * " + num2 + " is " + multiplied;
    }

    @GetMapping("/divide/{num1}/by/{num2}")
    @ResponseBody()
    public String divide(@PathVariable double num1, @PathVariable double num2) {
        double divide = num1 / num2;
        return num1 + " / " + num2 + " is " + divide;
    }
}
