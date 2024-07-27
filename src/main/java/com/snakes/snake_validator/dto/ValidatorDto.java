package com.snakes.snake_validator.dto;

import com.snakes.snake_validator.entity.Fruit;
import com.snakes.snake_validator.entity.Snake;
import com.snakes.snake_validator.entity.State;
import com.snakes.snake_validator.entity.Tick;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ValidatorDto {
    private String gameId;
    private int width;
    private int height;
    private int score;
    private Fruit fruit;
    private Snake snake;
    private List<Tick> ticks;
}
