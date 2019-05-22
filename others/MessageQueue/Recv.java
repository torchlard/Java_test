package MessageQueue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

// fanout exchange
public class Recv {
  private final static String QUEUE_NAME = "test_queue_direct_1";
  // private final static String QUEUE_NAME = "test_queue_topic_1";
  private final static String EXCHANGE_NAME = "test_exchange_direct";

  public static void main(String[] args) throws Exception {
    Connection connection = ConnectionUtil.getConnection();
    Channel channel = connection.createChannel();

    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
    // bind to exchanger
    channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "");
    // channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "udpate");
    
    // channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "item.udpate");
    // channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "item.delete");
    // channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "item.#");

    channel.basicQos(1);
    QueueingConsumer consumer = new QueueingConsumer(channel);
    channel.basicConsume(QUEUE_NAME, false, consumer);

    while(true){
      QueueingConsumer.Delivery delivery = consumer.nextDelivery();
      String message = new String(delivery.getBody());
      System.out.println("frontend: " + message );
      Thread.sleep(10);

      channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
    }
    
  }
}











