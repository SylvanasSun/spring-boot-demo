package cn.sun.springmvc.converter;

import cn.sun.springmvc.pojo.DemoObj;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Created by sylvanasp on 2016/7/24.
 */
public class MyMessageConverter extends AbstractHttpMessageConverter<DemoObj> {

    public MyMessageConverter() {
        super(new MediaType("application", "x-wisely", Charset.forName("UTF-8")));
    }

    /**
     * 处理由 "-"分隔的数据,并转换成DemoObj对象
     * 例如: 1-jack
     */
    @Override
    protected DemoObj readInternal(Class<? extends DemoObj> aClass,
                                   HttpInputMessage httpInputMessage)
            throws IOException, HttpMessageNotReadableException {
        String temp = StreamUtils.copyToString(httpInputMessage.getBody(), Charset.forName("UTF-8"));
        String[] tempArr = temp.split("-");
        return new DemoObj(new Long(tempArr[0]), tempArr[1]);
    }


    @Override
    protected boolean supports(Class<?> aClass) {
        return DemoObj.class.isAssignableFrom(aClass);
    }

    /**
     * 处理如何输出数据到response
     */
    @Override
    protected void writeInternal(DemoObj demoObj, HttpOutputMessage httpOutputMessage)
            throws IOException, HttpMessageNotWritableException {
        String out = "hello:" + demoObj.getId() + "-" + demoObj.getName();
        httpOutputMessage.getBody().write(out.getBytes());
    }

}
