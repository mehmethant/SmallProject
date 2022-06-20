package Actors;



import javax.swing.JSlider;
import javax.swing.JTextArea;

import Run.Main;

public class Factory implements Runnable{
    private boolean exit = false;
    private int sleepTime;
    JTextArea area_CurrentInfo;
    Object lock = new Object();
    public Factory(JSlider productionSpeed, JTextArea area_CurrentInfo){
    	sleepTime = productionSpeed.getValue();
    	this.area_CurrentInfo = area_CurrentInfo;
    }

    @Override
    public void run() {
        while(!exit){
            try {
                Thread.sleep(sleepTime/2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Baloon baloon = new Baloon(BaloonColor.getRandomColor());
            if(Storage.baloons.size() >= 99){
                break;
            }
            System.out.println(Thread.currentThread().getName() + " PRODUCED: " + baloon.getNumber());
            Storage.addBaloon(baloon);
            
            updateCurrentInfo(area_CurrentInfo);
        }
    }

    void updateCurrentInfo(JTextArea area_CurrentInfo) {
		StringBuilder stringBuilder = new StringBuilder();
	
		stringBuilder.append("Active Factories: " + Main.factoryList.size() + "\n");
		stringBuilder.append("Active Transporters: " + Main.transporters.size() + "\n");
		stringBuilder.append("Baloon Count: " + Storage.baloons.size() + "\n");

		area_CurrentInfo.setText(stringBuilder.toString());
	}
    
    // for stopping the thread
    public void stop()
    {
        exit = true;
    }
}
