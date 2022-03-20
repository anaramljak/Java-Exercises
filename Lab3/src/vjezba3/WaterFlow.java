package vjezba3;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.List;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import com.google.gson.Gson;

public class WaterFlow {
	private String topic;
	private String broker;
	private String clientId;
	List<Sensor> sensors;

	public WaterFlow(String topic, String broker, String clientId, List<Sensor> sensors) {
		this.topic = topic;
		this.broker = broker;
		this.clientId = clientId;
		this.sensors = sensors;
	}

	public static WaterFlow readJSON(String file) {

		Gson gson = new Gson();
		try {
			Reader json_file = new FileReader(file);
			WaterFlow obj = gson.fromJson(json_file, WaterFlow.class);
			return obj;
		}

		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	private String ConvertObjToJSON(Sensor sensor) {
		Gson gson = new Gson();
		sensor.generaterandom();
		String jsonString = gson.toJson(sensor);
		return jsonString;
	}

	public void publishing() throws InterruptedException, MqttException {

			MemoryPersistence persistence = new MemoryPersistence();
			MqttClient client = new MqttClient(broker, clientId, persistence);
			try {
				MqttConnectOptions connOpts = new MqttConnectOptions();
				connOpts.setCleanSession(true);
				client.connect(connOpts);

				String msg2 = "\nMeasuring data:\n";
				MqttMessage message2 = new MqttMessage(msg2.getBytes());
				client.publish(topic, message2);
				for(Sensor s: sensors) {
			
					MqttMessage message = new MqttMessage(ConvertObjToJSON(s).getBytes());
					System.out.println(message);
					client.publish(topic, message);
				}

				
			}

			catch (MqttException e) {
				System.out.println("Error\n");
				e.printStackTrace();
			}

			finally {
				client.disconnect();
				client.close();
			}
		}
	}
