package Operations;

import Actors.Baloon;

import java.util.HashMap;
import java.util.Map;

public class Transporter implements Runnable{
    public HashMap<Long, Baloon> transporterStorage;

    public Transporter(HashMap<Long, Baloon> transporterStorage){
        this.transporterStorage = transporterStorage;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " is carrying goods!");
        for(Map.Entry<Long,Baloon> currentBaloon : transporterStorage.entrySet()){
            System.out.println("\tRecipient got the Baloon-"+currentBaloon.getValue().getNumber());
        }
    }
}
