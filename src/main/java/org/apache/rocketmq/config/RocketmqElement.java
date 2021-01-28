package org.apache.rocketmq.config;

import org.apache.jmeter.config.ConfigElement;
import org.apache.jmeter.testbeans.TestBean;
import org.apache.jmeter.testbeans.TestBeanHelper;
import org.apache.jmeter.testelement.AbstractTestElement;
import org.apache.jmeter.testelement.TestStateListener;
import org.apache.jmeter.threads.JMeterContextService;
import org.apache.jmeter.threads.JMeterVariables;
import org.apache.jorphan.util.JMeterException;
import org.apache.jorphan.util.JOrphanUtils;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.utils.RocketMQUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName RocketmqElement
 * @Description TODO
 * @Author 兴盛优选研发中心 技术质量部 杨杰
 * @Date 2021/1/26 16:21
 * @Version 1.0
 */
public class RocketmqElement extends AbstractTestElement implements ConfigElement, TestStateListener, TestBean {
    private static final Logger log = LoggerFactory.getLogger(RocketmqElement.class);

    private transient String rocketName;
    private transient String nameAddr;
    private transient String producerGroup;
    private transient String topic;
    private transient String tag;

    private transient RocketMQUtils rocketMQUtils;

    @Override
    public void addConfigElement(ConfigElement config) {
    }

    @Override
    public boolean expectsModification() {
        return false;
    }

    @Override
    public void testStarted() {
        testStarted("localhost");
    }

    @Override
    public void testStarted(String host) {
        this.setRunningVersion(true);
        TestBeanHelper.prepare(this);
        JMeterVariables variables = getThreadContext().getVariables();
        String poolName = getRocketName();
        if (JOrphanUtils.isBlank(poolName)) {
            throw new IllegalArgumentException("pool Name must not be empty for element:" + getName());
        } else if (variables.getObject(poolName) != null) {
            log.error("rocketmq pool already defined for: {}", poolName);
        } else {
            synchronized (this) {
                try {
                    DefaultMQProducer producer = new DefaultMQProducer(getProducerGroup());
                    producer.setNamesrvAddr(getNameAddr());
                    producer.start();
                    rocketMQUtils = new RocketMQUtils(getTopic(), getTag(), producer, getNameAddr(), getProducerGroup());
                    variables.putObject(poolName, rocketMQUtils);
                } catch (MQClientException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void testEnded() {
        testEnded("localhost");
    }

    @Override
    public void testEnded(String host) {
        if (rocketMQUtils != null) {
            DefaultMQProducer producer = rocketMQUtils.getProducer();
            if (producer != null) {
                producer.shutdown();
            }
        }
    }

    public static RocketMQUtils getRocketMQDetail(String poolName) throws JMeterException {
        Object object = JMeterContextService.getContext().getVariables().getObject(poolName);
        if (object == null) {
            throw new JMeterException("未找到" + poolName + "对应的RocketMQUtils对象");
        } else {
            if (object instanceof RocketMQUtils) {
                return (RocketMQUtils) object;
            } else {
                throw new JMeterException("找到" + poolName + "对应的对象不是RocketMQUtils类型");
            }
        }
    }

    @Override
    public Object clone() {
        RocketmqElement el = (RocketmqElement) super.clone();
        synchronized (this) {
            el.rocketMQUtils = rocketMQUtils;
        }
        return el;
    }

    public String getRocketName() {
        return rocketName;
    }

    public void setRocketName(String rocketName) {
        this.rocketName = rocketName;
    }

    public String getNameAddr() {
        return nameAddr;
    }

    public void setNameAddr(String nameAddr) {
        this.nameAddr = nameAddr;
    }

    public String getProducerGroup() {
        return producerGroup;
    }

    public void setProducerGroup(String producerGroup) {
        this.producerGroup = producerGroup;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
