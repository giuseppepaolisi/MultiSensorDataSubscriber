import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.ArrayList;
import java.util.List;

public class MqttConnection {

    public static void main(String[] args) {
        String brokerUrl = "tcp://localhost:1883";
        String clientId = "mySubscriberClientId";

        //List of sensors
        List<String> sensors = new ArrayList<>();
        sensors.add("oximeters/2");
        sensors.add("oximeters/3");
        sensors.add("oximeters/4");
        sensors.add("oximeters/5");
        sensors.add("oximeters/6");
        sensors.add("oximeters/7");
        sensors.add("oximeters/8");
        sensors.add("oximeters/9");
        sensors.add("oximeters/10");
        sensors.add("alerts/2");
        /**
         * MemoryPersistence is an implementation of the MqttClientPersistence interface.
         * It stores the in-flight and pending messages in memory.
         * It is useful for clients that do not need to persist the messages when the client disconnects.
         */
        MemoryPersistence persistence = new MemoryPersistence();

        try {

            // create the MQTT client
            MqttClient client = new MqttClient(brokerUrl, clientId, persistence);

            // create the MQTT connection options
            MqttConnectOptions options = new MqttConnectOptions();
            options.setAutomaticReconnect(true);
            options.setCleanSession(true);

            // connect to the MQTT broker
            client.connect(options);

            // print the received messages
            MqttMessagePrinter printer = new MqttMessagePrinter();
            client.setCallback(printer);

            // start the message processing loop
            for (String sensor : sensors) {
                // subscribe to the MQTT topic
                client.subscribe(sensor);
            }
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
