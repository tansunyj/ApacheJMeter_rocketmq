package org.apache.rocketmq.sampler;

import org.apache.jmeter.samplers.Entry;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.samplers.Sampler;
import org.apache.jmeter.testbeans.TestBean;
import org.apache.jmeter.testelement.AbstractTestElement;
import org.apache.jorphan.util.JOrphanUtils;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.config.RocketmqElement;
import org.apache.rocketmq.utils.RocketMQUtils;

/**
 * @ClassName RocketmqSampler
 * @Description TODO
 * @Author 兴盛优选研发中心 技术质量部 杨杰
 * @Date 2021/1/26 16:22
 * @Version 1.0
 */
public class RocketmqSampler extends AbstractTestElement implements Sampler, TestBean {
    private transient String rocketName;
    private transient RocketMQUtils rocketMQUtils;
    private transient String body;
    //   private transient String body2;
    private transient String[] topics;

    @Override
    public SampleResult sample(Entry e) {
        SampleResult sampleResult = new SampleResult();
        sampleResult.sampleStart();
        try {
            rocketMQUtils = RocketmqElement.getRocketMQDetail(getRocketName());
            topics = rocketMQUtils.getTopic().split(";");
            DefaultMQProducer producer = rocketMQUtils.getProducer();
            StringBuilder stringBuilder = new StringBuilder(512);
            if (!JOrphanUtils.isBlank(body)) {
                for (String topic : topics) {
                    SendResult sendResult = producer.send(new Message(topic, rocketMQUtils.getTag(), body.getBytes("UTF-8")), 30000);
                    if (sendResult != null && sendResult.getSendStatus() == SendStatus.SEND_OK) {
                        stringBuilder.append(sendResult.toString()).append("\n");
                    }
                }
            }

            //        String res2 = "";
/*            if (!JOrphanUtils.isBlank(body2)) {
                for (String topic : topics) {
                    SendResult sendResult = producer.send(new Message(topic, rocketMQUtils.getTag(), body2.getBytes("UTF-8")), 30000);
                    res2 = sendResult.toString();
                }
            }*/
            sampleResult.setSampleLabel(getName());
            sampleResult.setSamplerData(requestBody());
            //       sampleResult.setResponseData((res1 + res2).getBytes());
            sampleResult.setResponseData((stringBuilder.toString()).getBytes());
            // 请求默认success为true，是否正确的工作交给断言来完成
            sampleResult.setSuccessful(true);
        } catch (Exception ex) {
            ex.printStackTrace();
            sampleResult.setSuccessful(false);
            sampleResult.setResponseData(ex.toString().getBytes());
        }
        sampleResult.setDataType(SampleResult.TEXT);
        sampleResult.sampleEnd();
        return sampleResult;
    }

    public String getRocketName() {
        return rocketName;
    }

    public void setRocketName(String rocketName) {
        this.rocketName = rocketName;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

/*
    public String getBody2() {
        return body2;
    }

    public void setBody2(String body1) {
        this.body2 = body1;
    }
*/

    private String requestBody() {
        StringBuilder devolver = new StringBuilder();
        devolver.append("cluster Addr: " + rocketMQUtils.getNameAddr());
        devolver.append("\nproducer group: " + rocketMQUtils.getProducerGroup());
        devolver.append("\ntopic: " + rocketMQUtils.getTopic());
        devolver.append("\ntags: " + rocketMQUtils.getTag());
        //    devolver.append("\ndata: " + body + "\n" + body2);
        devolver.append("\nbody: " + body);
        return devolver.toString();
    }
}
