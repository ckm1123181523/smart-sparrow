package com.jtfr.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class ParameterRequestWrapper extends HttpServletRequestWrapper {

    private Map<String, String[]> params = new HashMap<String, String[]>();

    private Object object;

    @SuppressWarnings("unchecked")
    public ParameterRequestWrapper(HttpServletRequest request) {

        super(request);

        this.params.putAll(request.getParameterMap());

    }

    public ParameterRequestWrapper(HttpServletRequest request, JSONObject object) {
        this(request);
        this.object = object;
        addAllParameters(object.getInnerMap());
    }

    public ParameterRequestWrapper(HttpServletRequest request, String jsonStr) {
        this(request);
        // Json 字符串 转换成 JSONObject 对象
        JSONObject object = JSON.parseObject(jsonStr);
        this.object = object;
        addAllParameters(object.getInnerMap());
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        return Collections.unmodifiableMap(this.params);
    }

    @Override
    public String getParameter(String name) {
        String[] values = params.get(name);
        if (values == null || values.length == 0) {
            return null;
        }
        return values[0];
    }

    @Override
    public String[] getParameterValues(String name) {//同上
        return params.get(name);
    }


    @Override
    public Enumeration<String> getParameterNames() {
        return Collections.enumeration(this.params.keySet());
    }

    @Override
    public ServletInputStream getInputStream() {
        final ByteArrayInputStream in = new ByteArrayInputStream(object.toString().getBytes(StandardCharsets.UTF_8));
        return new ServletInputStream() {
            public int read() {
                return in.read();
            }

            public void setReadListener(ReadListener listener) {
            }

            public boolean isReady() {
                return true;
            }

            public boolean isFinished() {
                return false;
            }
        };
    }

    public final void addAllParameters(Map<String, Object> otherParams) {
        for (Map.Entry<String, Object> entry : otherParams.entrySet()) {
            addParameter(entry.getKey(), entry.getValue());
        }
    }

    public void addParameter(String name, Object value) {
        if (value != null) {
            if (value instanceof String[]) {
                params.put(name, (String[]) value);
            } else if (value instanceof String) {
                params.put(name, new String[]{(String) value});
            } else {
                params.put(name, new String[]{String.valueOf(value)});
            }
        }
    }

}