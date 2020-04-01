package org.ruqinhu.mq.client;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * @author zhaorunze
 * @since 2020/1/6
 */
public class RocketMqProducerTest {

    public static void main(String[] args) throws Exception {
        SyncProducer.send();
        AsyncProducer.send();
        OnewayProducer.send();
    }

    static class SyncProducer {
        public static void send() throws Exception {
            //Instantiate with a producer group name.
            DefaultMQProducer producer = new
                    DefaultMQProducer("test_basic_group");
            // Specify name server addresses.
            producer.setNamesrvAddr("114.67.108.91:9876");
            //Launch the instance.
            producer.start();
            for (int i = 0; i < 100; i++) {
                //Create a message instance, specifying topic, tag and message body.
                Message msg = new Message("TopicTest" /* Topic */,
                        "TagA" /* Tag */,
                        ("Hello RocketMQ " +
                                i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
                );
                //Call send message to deliver message to one of brokers.
                SendResult sendResult = producer.send(msg);
                System.out.printf("%s%n", sendResult);
            }
            //Shut down once the producer instance is not longer in use.
            producer.shutdown();
        }
    }

     static class AsyncProducer {
        public static void send() throws Exception {
            //Instantiate with a producer group name.
            DefaultMQProducer producer = new DefaultMQProducer("test_basic_group");
            // Specify name server addresses.
            producer.setNamesrvAddr("114.67.108.91:9876");
            //Launch the instance.
            producer.start();
            producer.setRetryTimesWhenSendAsyncFailed(0);
            for (int i = 0; i < 100; i++) {
                final int index = i;
                //Create a message instance, specifying topic, tag and message body.
                Message msg = new Message("TopicTest",
                        "TagA",
                        "OrderID188",
                        "Hello world".getBytes(RemotingHelper.DEFAULT_CHARSET));
                producer.send(msg, new SendCallback() {
                    public void onSuccess(SendResult sendResult) {
                        System.out.printf("%-10d OK %s %n", index,
                                sendResult.getMsgId());
                    }
                    public void onException(Throwable e) {
                        System.out.printf("%-10d Exception %s %n", index, e);
                        e.printStackTrace();
                    }
                });
            }
            //Shut down once the producer instance is not longer in use.
            producer.shutdown();
        }
    }

    static class OnewayProducer {
        public static void send() throws Exception{
            //Instantiate with a producer group name.
            DefaultMQProducer producer = new DefaultMQProducer("test_basic_group");
            // Specify name server addresses.
            producer.setNamesrvAddr("114.67.108.91:9876");
            //Launch the instance.
            producer.start();
            for (int i = 0; i < 100; i++) {
                //Create a message instance, specifying topic, tag and message body.
                Message msg = new Message("TopicTest" /* Topic */,
                        "TagA" /* Tag */,
                        ("Hello RocketMQ " +
                                i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
                );
                //Call send message to deliver message to one of brokers.
                producer.sendOneway(msg);

            }
            //Shut down once the producer instance is not longer in use.
            producer.shutdown();
        }
    }

}
