package com.snakes.snake_validator.service;

import com.snakes.snake_validator.dto.ValidatorDto;
import com.snakes.snake_validator.entity.Fruit;
import com.snakes.snake_validator.entity.Snake;
import com.snakes.snake_validator.entity.State;
import com.snakes.snake_validator.entity.Tick;
import com.snakes.snake_validator.exception.APIException;
import com.snakes.snake_validator.helper.NumberGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

public class ValidatorTests {

    @InjectMocks
    private ValidatorService validatorService;

    @Mock
    private NumberGenerator numberGenerator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testValidateMoves_success() {
        Tick tick = new Tick(1, 0);
        Tick tick2 = new Tick(0, 1);
        List<Tick> ticks = Arrays.asList(tick, tick2);
        Fruit fruit = new Fruit(1, 1);
        Snake snake = new Snake(0, 0, 1, 0);
        ValidatorDto validatorDto = new ValidatorDto("123", 10, 10, 0, fruit, snake, ticks);
        Mockito.when(numberGenerator.generate()).thenReturn(5);

        State result = validatorService.validateMoves(validatorDto);

        //Assert
        assertNotNull(result.getFruit());
        assertNotNull(result.getSnake());
        assertEquals(1, result.getScore());
        assertEquals(1, result.getSnake().getX());
        assertEquals(1, result.getSnake().getY());
    }


    @Test
    void testValidateMoves_outOfBounds() {
        Tick tick = new Tick(1, 0);
        Tick tick2 = new Tick(1, 0);
        List<Tick> ticks = Arrays.asList(tick, tick2);
        Fruit fruit = new Fruit(1, 1);
        Snake snake = new Snake(0, 0, 1, 0);
        ValidatorDto validatorDto = new ValidatorDto("123", 1, 1, 0, fruit, snake, ticks);
        Mockito.when(numberGenerator.generate()).thenReturn(5);
        APIException exception = assertThrows(APIException.class, () -> {
            validatorService.validateMoves(validatorDto);
        });

        assertEquals(HttpStatus.I_AM_A_TEAPOT, exception.getStatus());
        assertEquals("Game is over, snake went out of bounds or made an invalid move.", exception.getMessage());
    }


    @Test
    void testValidateMoves_invalidMove_wrongValue() {
        Tick tick = new Tick(2, 0);
        List<Tick> ticks = List.of(tick);
        Fruit fruit = new Fruit(2, 0);
        Snake snake = new Snake(0, 0, 1, 0);
        ValidatorDto validatorDto = new ValidatorDto("123", 10, 10, 0, fruit, snake, ticks);
        Mockito.when(numberGenerator.generate()).thenReturn(5);
        APIException exception = assertThrows(APIException.class, () -> {
            validatorService.validateMoves(validatorDto);
        });

        assertEquals(HttpStatus.I_AM_A_TEAPOT, exception.getStatus());
        assertEquals("Game is over, snake went out of bounds or made an invalid move.", exception.getMessage());
    }

    @Test
    void testValidateMoves_invalidMove_diagonal() {
        Tick tick = new Tick(1, 1);
        List<Tick> ticks = List.of(tick);
        Fruit fruit = new Fruit(1, 0);
        Snake snake = new Snake(0, 0, 1, 0);
        ValidatorDto validatorDto = new ValidatorDto("123", 10, 10, 0, fruit, snake, ticks);
        Mockito.when(numberGenerator.generate()).thenReturn(5);
        APIException exception = assertThrows(APIException.class, () -> {
            validatorService.validateMoves(validatorDto);
        });

        assertEquals(HttpStatus.I_AM_A_TEAPOT, exception.getStatus());
        assertEquals("Game is over, snake went out of bounds or made an invalid move.", exception.getMessage());
    }


    @Test
    void testValidateMoves_invalidMove_reverse() {
        Tick tick = new Tick(-1, 0);
        List<Tick> ticks = List.of(tick);
        Fruit fruit = new Fruit(1, 0);
        Snake snake = new Snake(0, 0, 1, 0);
        ValidatorDto validatorDto = new ValidatorDto("123", 10, 10, 0, fruit, snake, ticks);
        Mockito.when(numberGenerator.generate()).thenReturn(5);
        APIException exception = assertThrows(APIException.class, () -> {
            validatorService.validateMoves(validatorDto);
        });

        assertEquals(HttpStatus.I_AM_A_TEAPOT, exception.getStatus());
        assertEquals("Game is over, snake went out of bounds or made an invalid move.", exception.getMessage());
    }


    @Test
    void testValidateMoves_fruitNotFound() {
        Tick tick = new Tick(1, 0);
        List<Tick> ticks = List.of(tick);
        Fruit fruit = new Fruit(2, 0);
        Snake snake = new Snake(0, 0, 1, 0);
        ValidatorDto validatorDto = new ValidatorDto("123", 10, 10, 0, fruit, snake, ticks);
        Mockito.when(numberGenerator.generate()).thenReturn(5);
        APIException exception = assertThrows(APIException.class, () -> {
            validatorService.validateMoves(validatorDto);
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
        assertEquals("Fruit not found, the ticks do not lead the snake to the fruit position.", exception.getMessage());
    }


}
