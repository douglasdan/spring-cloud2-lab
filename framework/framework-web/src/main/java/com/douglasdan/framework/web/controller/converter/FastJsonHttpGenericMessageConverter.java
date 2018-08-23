package com.douglasdan.framework.web.controller.converter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.douglasdan.framework.common.constants.HttpConstants;
import com.douglasdan.framework.common.model.Model;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractGenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

/**
 * @author Douglas.Dan
 * @date 2018-08-21
 */
@SuppressWarnings("ALL")
public class FastJsonHttpGenericMessageConverter extends AbstractGenericHttpMessageConverter {

    public final static Charset UTF8     = Charset.forName(HttpConstants.HTTP_CHARSET);

    private Charset             charset  = UTF8;

    private SerializerFeature[] features = new SerializerFeature[0];

    public FastJsonHttpGenericMessageConverter(){
        this(new MediaType("application", "json", UTF8), new MediaType("application", "*+json", UTF8));
    }

    public FastJsonHttpGenericMessageConverter(MediaType... supportedMediaTypes) {
        super(supportedMediaTypes);
    }

    @Override
    protected boolean supports(Class clazz) {
        return true;
    }

    public Charset getCharset() {
        return this.charset;
    }

    public void setCharset(Charset charset) {
        this.charset = charset;
    }

    public SerializerFeature[] getFeatures() {
        return features;
    }

    public void setFeatures(SerializerFeature... features) {
        this.features = features;
    }

    @Override
    public Object read(Type type, Class clazz,
                       HttpInputMessage inputMessage) throws IOException,
            HttpMessageNotReadableException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        InputStream in = inputMessage.getBody();

        byte[] buf = new byte[1024];
        for (;;) {
            int len = in.read(buf);
            if (len == -1) {
                break;
            }

            if (len > 0) {
                baos.write(buf, 0, len);
            }
        }

        byte[] bytes = baos.toByteArray();
        if (logger.isDebugEnabled()) {
            logger.debug(new String(bytes));
        }
        return JSON.parseObject(bytes, 0, bytes.length, charset.newDecoder(), type);
    }

    @Override
    protected Object readInternal(Class clazz, HttpInputMessage inputMessage)
            throws IOException, HttpMessageNotReadableException {

        return null;
    }

    @Override
    protected void writeInternal(Object obj, Type type,
                                 HttpOutputMessage outputMessage) throws IOException,
            HttpMessageNotWritableException {
        OutputStream out = outputMessage.getBody();

        String text = null;
        if (obj instanceof Model)
        {
            text = obj.toString();
        }
        else
        {
            text = JSON.toJSONString(obj, features);
        }
        byte[] bytes = text.getBytes(charset);
        out.write(bytes);
    }

}
