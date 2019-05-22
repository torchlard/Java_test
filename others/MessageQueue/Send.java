package MessageQueue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class Send {
  private final static String EXCHANGE_NAME = "test_exchange_fanout";
  // private final static String EXCHANGE_NAME = "test_exchange_topic";

  public static void main(String[] args) {
    Connection connection = ConnectionUtil.getConnection();
    Channel channel = connection.createChannel();

    channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
    // channel.exchangeDeclare(EXCHANGE_NAME, "topic");
    // channel.exchangeDeclare(EXCHANGE_NAME, "direct");

    String msg = "good increased, id=1000";
    channel.basicPublish(EXCHANGE_NAME, "", null, msg.getBytes());
    // define msg topic as item.delete, subscriber looking for item.delete can get this
    // channel.basicPublish(EXCHANGE_NAME, "item.delete", null, msg.getBytes());
    // channel.basicPublish(EXCHANGE_NAME, "delete", null, msg.getBytes());

    System.out.println("Sned " + msg);
    channel.close();
    connection.close();

  }
  
  
}



