package com.vic.sprincloud.service.impl;

import com.vic.sprincloud.service.IMessageProvider;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author：vic
 * @date： 2020/4/19 17:52
 * @desc:
 */
//定义消息的推送管道
@EnableBinding(Source.class)
public class IMessageProviderImpl implements IMessageProvider {

    @Resource
    private MessageChannel outChannel;

    @Override
    public String send() {
        String message = UUID.randomUUID().toString();
        outChannel.send(MessageBuilder.withPayload(message).build());
        System.out.println("--output---message--" + message);
        return message;
    }
}
