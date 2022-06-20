package Actors;
import java.util.HashMap;

import javax.swing.JTextArea;

public class Storage {
    public static HashMap<Long,Baloon> baloons = new HashMap<>();

    static void addBaloon(Baloon baloon){
        baloons.put(baloon.getNumber(),baloon);
    }
}