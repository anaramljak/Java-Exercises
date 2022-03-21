package vjezba3;

import org.eclipse.paho.client.mqttv3.MqttException;

public class Main {
	public static void main(String [] args) throws InterruptedException, MqttException {
		WaterFlow wf = WaterFlow.readJSON("JSONSensor.json");
		wf.publishing();
	
	}

}

