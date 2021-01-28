package org.apache.rocketmq.utils;

import org.apache.rocketmq.client.producer.DefaultMQProducer;

/**
 * @ClassName RocketMQUtils
 * @Description 该对象用来存放mq的一些信息
 * @Author 兴盛优选研发中心 技术质量部 杨杰
 * @Date 2021/1/26 17:22
 * @Version 1.0
 */
public class RocketMQUtils {

    private String topic;
    private String tag;
    private DefaultMQProducer producer;

    public String getProducerGroup() {
        return producerGroup;
    }

    public void setProducerGroup(String producerGroup) {
        this.producerGroup = producerGroup;
    }

    private String producerGroup;

    public String getNameAddr() {
        return nameAddr;
    }

    public void setNameAddr(String nameAddr) {
        this.nameAddr = nameAddr;
    }

    private String nameAddr;


    public RocketMQUtils(String topic, String tag, DefaultMQProducer producer, String nameAddr, String producerGroup) {
        this.topic = topic;
        this.tag = tag;
        this.producer = producer;
        this.nameAddr = nameAddr;
        this.producerGroup = producerGroup;
    }

    public String getTopic() {
        return topic;
    }

    public String getTag() {
        return tag;
    }

    public DefaultMQProducer getProducer() {
        return producer;
    }
}
