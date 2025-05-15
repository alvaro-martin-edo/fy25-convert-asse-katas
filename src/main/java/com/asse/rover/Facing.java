package com.asse.rover;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Facing {
    N(0),
    E(1),
    S(2),
    W(3);

    private final int value;

    public static Facing fromValue(int value) {
        for (Facing facing : values()) {
            if (facing.value == value) {
                return facing;
            }
        }
        throw new IllegalArgumentException("Invalid value: " + value);
    }

    public static Facing fromString(String str) {
        for (Facing facing : values()) {
            if (facing.name().equalsIgnoreCase(str)) {
                return facing;
            }
        }
        throw new IllegalArgumentException("Invalid string: " + str);
    }

    public Facing turnLeft() {
        return fromValue((this.value + 3) % 4);
    }

    public Facing turnRight() {
        return fromValue((this.value + 1) % 4);
    }
}
