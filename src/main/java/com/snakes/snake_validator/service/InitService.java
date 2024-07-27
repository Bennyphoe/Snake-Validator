package com.snakes.snake_validator.service;

import com.snakes.snake_validator.entity.Fruit;
import com.snakes.snake_validator.entity.Snake;
import com.snakes.snake_validator.entity.State;
import com.snakes.snake_validator.helper.NumberGenerator;
import org.springframework.stereotype.Service;

@Service
public class InitService {

    public State initGame(int width, int height) {
        String gameId = String.valueOf(new NumberGenerator(0, 10000000).generate());
        int fruitXPosition = new NumberGenerator(0, width).generate();
        int fruitYPosition = new NumberGenerator(0, height).generate();
        Fruit newFruit = new Fruit(fruitXPosition, fruitYPosition);
        Snake newSnake = new Snake(0, 0, 1, 0);
        return new State(gameId, width, height, 0, newFruit, newSnake);
    }
}
