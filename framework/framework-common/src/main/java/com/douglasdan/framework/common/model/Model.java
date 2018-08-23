package com.douglasdan.framework.common.model;

import java.io.Serializable;

import org.springframework.data.annotation.Transient;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * @author Douglas.Dan
 */
public class Model implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -7532190300864165247L;

    @Transient
    @JSONField(serialize=false)
    private SerializerFeature[] features = new SerializerFeature[3];

    public Model()
    {
        features[0] = SerializerFeature.QuoteFieldNames;
        features[1] = SerializerFeature.WriteMapNullValue;
        features[2] = SerializerFeature.WriteNullStringAsEmpty;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this, features);
    }

    public String toString(SerializeFilter... filters) {
        if(filters==null){
            return JSON.toJSONString(this, features);
        }
        return JSON.toJSONString(this, filters, features);
    }

    @JSONField(serialize=false)
    public SerializerFeature[] getFeatures() {
        return features;
    }

    public void setFeatures(SerializerFeature[] features) {
        this.features = features;
    }

}
