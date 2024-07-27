package com.snakes.snake_validator.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class State {
    private String gameId;
    private int width;
    private int height;
    private int score;
    private Fruit fruit;
    private Snake snake;
}
