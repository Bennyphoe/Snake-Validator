package com.snakes.snake_validator.controller;

import com.snakes.snake_validator.dto.ValidatorDto;
import com.snakes.snake_validator.entity.State;
import com.snakes.snake_validator.service.InitService;
import com.snakes.snake_validator.service.ValidatorService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class SnakeController {

    private InitService initService;
    private ValidatorService validatorService;

    @GetMapping("/new")
    public State createNewGame(@RequestParam("w") int width, @RequestParam("h") int height) {
        return initService.initGame(width, height);
    }

    @PostMapping("/validate")
    public State validate(@RequestBody ValidatorDto validatorDto) {
        return validatorService.validateMoves(validatorDto);
    }
}
