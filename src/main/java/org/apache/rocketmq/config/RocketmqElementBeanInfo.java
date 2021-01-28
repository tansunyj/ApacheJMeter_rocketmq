package org.apache.rocketmq.config;

import org.apache.jmeter.testbeans.BeanInfoSupport;

import java.beans.PropertyDescriptor;

/**
 * @ClassName RocketmqElementBeanInfo
 * @Description TODO
 * @Author 兴盛优选研发中心 技术质量部 杨杰
 * @Date 2021/1/26 16:21
 * @Version 1.0
 */
public class RocketmqElementBeanInfo extends BeanInfoSupport {
    /**
     * Construct a BeanInfo for the given class.
     */
    public RocketmqElementBeanInfo() {
        super(RocketmqElement.class);

        PropertyDescriptor p;

        p = property("rocketName");//默认指定该元件为编辑框
        p.setValue(NOT_UNDEFINED, Boolean.TRUE);//是否必填项
        p.setValue(DEFAULT, "pool1");//默认值

        p = property("nameAddr");//默认指定该元件为编辑框
        p.setValue(NOT_UNDEFINED, Boolean.TRUE);//是否必填项
        p.setValue(DEFAULT, "localhost:9876;localhost:9876");//默认值

        p = property("producerGroup");
        p.setValue(NOT_UNDEFINED, Boolean.TRUE);
        p.setValue(DEFAULT, "producer group can't be empty");

        p = property("topic");
        p.setValue(NOT_UNDEFINED, Boolean.TRUE);
        p.setValue(DEFAULT, "topic1;topic2");

        p = property("tag");
        p.setValue(NOT_UNDEFINED, Boolean.TRUE);
        p.setValue(DEFAULT, "tag can be empty");

        createPropertyGroup("name", new String[]{"rocketName"});

        createPropertyGroup("rocketmq connection configuration", new String[]{"nameAddr", "producerGroup", "topic", "tag"});
    }
}
