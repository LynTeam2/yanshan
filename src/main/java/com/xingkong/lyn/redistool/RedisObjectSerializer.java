package com.xingkong.lyn.redistool;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.serializer.support.DeserializingConverter;
import org.springframework.core.serializer.support.SerializingConverter;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

/**
 * Created by lyn on 2017/6/22.
 * redis 序列化对象
 */
public class RedisObjectSerializer implements RedisSerializer<Object> {
    private Converter<Object, byte[]> serializer = new SerializingConverter();
    private Converter<byte[], Object> deserializer = new DeserializingConverter();
    static final byte[] EMPTY_ARRAY = new byte[0];

    @Override
    public byte[] serialize(Object o) throws SerializationException {
        if(null == o){
            return EMPTY_ARRAY;
        }
        try{
            return serializer.convert(o);
        } catch (Exception e){
            return EMPTY_ARRAY;
        }
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        if(isEmpty(bytes)) {
            return null;
        }
        try{
            return deserializer.convert(bytes);
        } catch (Exception e) {
            throw new SerializationException("Cannot deserialize", e);
        }
    }

    private boolean isEmpty(byte[] data) {
        return (data == null || data.length == 0);
    }
}
