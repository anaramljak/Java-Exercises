package vjezba2;

import org.eclipse.paho.client.mqttv3.MqttException;
public class Main {

	public static void main(String[] args) throws InterruptedException, MqttException {
		WaterFlow wf = new WaterFlow("watermeter", "tcp://localhost:1833");
		Sensor s = new Sensor("Temperature:", "int16", 10, -3266.8, 3266.8, "°C");
		Sensor s1 = new Sensor("Water pressure:", "uint16", 1000, 0, 65.336, "bar");
		Sensor s2 = new Sensor("Consuption in last 1min 10min 1h 1d:", "uint16", 0, 0, 65.336, "liter");
		wf.addSensors(s);
		wf.addSensors(s1);
		wf.addSensors(s2);
		wf.publishing();
	}
}