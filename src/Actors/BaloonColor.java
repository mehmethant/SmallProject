package Actors;

import java.util.Random;

public enum BaloonColor {
    GREEN,BLUE,RED;

    public static BaloonColor getRandomColor() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];//1
    }
}
