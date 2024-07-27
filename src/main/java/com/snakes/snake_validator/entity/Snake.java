package com.snakes.snake_validator.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Snake {
    private int x;
    private int y;
    private int velX;
    private int velY;
}
