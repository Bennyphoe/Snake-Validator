package com.snakes.snake_validator.helper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NumberGenerator {
    private int rangeStart;
    private int rangeEnd;

    public int generate() {
        return (int)(Math.random() * (rangeEnd - rangeStart + 1)) + rangeStart;
    }
}
