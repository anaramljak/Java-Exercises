package vjezba2;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class WaterFlow {

	private String topic;
	private String broker;
	private List<Sensor> sensors = new ArrayList<>();

	WaterFlow(String topic, String broker) {
		this.topic = topic;
		this.broker = broker;
	}

	public void addSensors(Sensor sensor) {
		sensors.add(sensor);
	}

	public void publishing() throws InterruptedException, MqttException {

		for (var s : sensors) {
			MemoryPersistence persistence = new MemoryPersistence();
			MqttClient client = new MqttClient(broker, "sample", persistence);
			try {
				MqttConnectOptions connOpts = new MqttConnectOptions();
				connOpts.setCleanSession(true);
				client.connect(connOpts);
				MqttMessage message = new MqttMessage(s.toString().getBytes());
				System.out.println(message);
				client.publish(topic, message);

			} catch (MqttException e) {
				System.out.println("Error\n");
				e.printStackTrace();
			}

			finally {
				client.disconnect();
				client.close();
			}

		}
	}

}
