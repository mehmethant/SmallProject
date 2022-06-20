package Actors;

public class Baloon {
    private long number = 0;
    private BaloonColor color;
    private static Integer counter = 0;

    Baloon(BaloonColor color){
        synchronized (Baloon.counter){
            this.number = ++counter;
        }
        this.color = BaloonColor.getRandomColor();
    }

    public long getNumber() {
        return number;
    }

    public BaloonColor getColor() {
        return color;
    }

    public static Integer getCounter() {
        return counter;
    }
}
