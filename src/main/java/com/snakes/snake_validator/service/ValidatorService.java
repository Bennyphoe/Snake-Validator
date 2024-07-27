package com.snakes.snake_validator.service;

import com.snakes.snake_validator.dto.ValidatorDto;
import com.snakes.snake_validator.entity.Fruit;
import com.snakes.snake_validator.entity.Snake;
import com.snakes.snake_validator.entity.State;
import com.snakes.snake_validator.entity.Tick;
import com.snakes.snake_validator.exception.APIException;
import com.snakes.snake_validator.helper.NumberGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ValidatorService {
    public State validateMoves(ValidatorDto validatorDto) {
        List<Tick> tickList = validatorDto.getTicks();
        int[] fruitPos = new int[]{validatorDto.getFruit().getX(), validatorDto.getFruit().getY()};
        int[] snakePos = new int[]{validatorDto.getSnake().getX(), validatorDto.getSnake().getY()};
        int[] currentVelocity = new int[]{validatorDto.getSnake().getVelX(), validatorDto.getSnake().getVelY()};
        int height = validatorDto.getHeight();
        int width = validatorDto.getWidth();
        for (Tick tick: tickList) {
            //check validity of current tick
            if (!isValidMove(currentVelocity, tick)) throw new APIException(HttpStatus.I_AM_A_TEAPOT, "Game is over, snake went out of bounds or made an invalid move.");
            //update snake position based on the current tick values
            snakePos[0] += tick.velX();
            snakePos[1] += tick.velY();
            //check if snake pos is out of bounds
            if (snakePos[0] < 0 || snakePos[0] > width || snakePos[1] < 0 || snakePos[1] > height) throw new APIException(HttpStatus.I_AM_A_TEAPOT, "Game is over, snake went out of bounds or made an invalid move.");
            //update the currentVelocity to the latest tick
            currentVelocity[0] = tick.velX();
            currentVelocity[1] = tick.velY();
        }
        if (!Arrays.equals(fruitPos, snakePos)) throw new APIException(HttpStatus.NOT_FOUND, "Fruit not found, the ticks do not lead the snake to the fruit position.");
        int fruitXPosition = new NumberGenerator(0, validatorDto.getWidth()).generate();
        int fruitYPosition = new NumberGenerator(0, validatorDto.getHeight()).generate();
        //Create new Fruit
        Fruit newFruit = new Fruit(fruitXPosition, fruitYPosition);

        //Update Snake
        Snake currentSnake = validatorDto.getSnake();
        currentSnake.setX(snakePos[0]);
        currentSnake.setY(snakePos[1]);
        currentSnake.setVelX(currentVelocity[0]);
        currentSnake.setVelY(currentVelocity[1]);

        return new State(validatorDto.getGameId(), validatorDto.getWidth(), validatorDto.getHeight(), validatorDto.getScore() + 1, newFruit, currentSnake);
    }

    private boolean isValidMove(int[] velocity, Tick tick) {
        List<Integer> validMoves = Arrays.asList(-1, 0, 1);
        //ticks contains values other than -1, 0, 1
        if (!validMoves.contains(tick.velX()) || !validMoves.contains(tick.velY())) return false;
        //tick contains values that result in snake not going in odd direction
        if (tick.velX() != 0 && tick.velY() != 0) return false;
        //check for 180 degrees turns
        if (velocity[0] == 1 && tick.velX() == -1) return false;
        if (velocity[0] == -1 && tick.velX() == 1) return false;
        if (velocity[1] == 1 && tick.velY() == -1) return false;
        if (velocity[1] == -1 && tick.velY() == 1) return false;
        return true;
    }
}


