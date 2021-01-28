package org.apache.rocketmq.sampler;

import org.apache.jmeter.testbeans.BeanInfoSupport;
import org.apache.jmeter.testbeans.gui.TypeEditor;

import java.beans.PropertyDescriptor;

/**
 * @ClassName RocketmqSamplerBeanInfo
 * @Description TODO
 * @Author 兴盛优选研发中心 技术质量部 杨杰
 * @Date 2021/1/26 16:22
 * @Version 1.0
 */
public class RocketmqSamplerBeanInfo extends BeanInfoSupport {
    /**
     * Construct a BeanInfo for the given class.
     */
    public RocketmqSamplerBeanInfo() {
        super(RocketmqSampler.class);

        PropertyDescriptor p;

        p = property("rocketName");//默认指定该元件为编辑框
        p.setValue(NOT_UNDEFINED, Boolean.TRUE);//是否必填项
        p.setValue(DEFAULT, "pool1");//默认值

        p = property("body", TypeEditor.TextAreaEditor);//指定该元件为多行文本框
        p.setValue(NOT_UNDEFINED, Boolean.FALSE);
        p.setValue(MULTILINE, Boolean.TRUE);
        p.setValue(DEFAULT, "message can't be empty");

/*        p = property("body2", TypeEditor.TextAreaEditor);//指定该元件为多行文本框
        p.setValue(NOT_UNDEFINED, Boolean.FALSE);
        p.setValue(MULTILINE, Boolean.TRUE);
        p.setValue(DEFAULT, "消息体可以为空");*/

        createPropertyGroup("name", new String[]{"rocketName"});

        //    createPropertyGroup("消息体",new String[]{"body","body2"});
        createPropertyGroup("message", new String[]{"body"});
    }
}
