import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MqttMessagePrinter implements MqttCallback {
    @Override
    public void connectionLost(Throwable throwable) {

    }

    public void messageArrived(String topic, MqttMessage message) throws MqttException {
        System.out.println("Received message: "+ "Topic= " + topic + new String(message.getPayload()));
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }
}