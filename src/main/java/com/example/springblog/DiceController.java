package com.example.springblog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DiceController {
    @GetMapping("/roll-dice")
    public String roll() {
        return "roll";
    }
    
    @GetMapping("/roll-dice/{num}")
    public String rollGuess(@PathVariable int num, Model model) {
        int rolled_number = (int) Math.floor(Math.random() * 6) + 1;
        model.addAttribute("gen_num", rolled_number);
        model.addAttribute("num_guessed", num);
        return "roll";
    }
}