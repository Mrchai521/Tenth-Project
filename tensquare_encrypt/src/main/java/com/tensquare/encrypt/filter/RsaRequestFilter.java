package com.tensquare.encrypt.filter;

import com.google.common.base.Charsets;
import com.google.common.base.Strings;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.netflix.zuul.http.ServletInputStreamWrapper;
import com.tensquare.encrypt.service.RsaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

/**
 * @description:
 * @author:柴新峰
 * @create:2020/8/27
 */
public class RsaRequestFilter extends ZuulFilter {
    @Autowired
    private RsaService rsaService;

    @Override
    public String filterType() {
        return null;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return false;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        HttpServletResponse response = ctx.getResponse();
        try {
            String decryptData = null;
            HashMap dataMap = null;
            String token = null;
            String url = request.getRequestURL().toString();
            InputStream stream = ctx.getRequest().getInputStream();
            String requestParam = StreamUtils.copyToString(stream, Charsets.UTF_8);
            if (!Strings.isNullOrEmpty(requestParam)) {
                System.out.println(String.format("请求体中的密文: %s", requestParam));
//                decryptData = rsaService.RSADecryptDataPEM(requestParam, RsaKeys.getServerPrvKeyPkcs8());
                System.out.println(String.format("解密后的内容: %s", decryptData));
            }
            System.out.println(String.format("request: %s >>> %s, data=%s", request.getMethod(), url, decryptData));
            if (!Strings.isNullOrEmpty(decryptData)) {
                System.out.println("json字符串写入request body");
                final byte[] reqBodyBytes = decryptData.getBytes();
                ctx.setRequest(new HttpServletRequestWrapper(request) {
                    @Override
                    public ServletInputStream getInputStream() throws IOException {
                        return new ServletInputStreamWrapper(reqBodyBytes);
                    }

                    @Override
                    public int getContentLength() {
                        return reqBodyBytes.length;
                    }

                    @Override
                    public long getContentLengthLong() {
                        return reqBodyBytes.length;
                    }
                });
            }
            System.out.println("转发request");
            // 设置request请求头中的Content-Type为application/json，否则api接口模块需要进行url转码操作
            ctx.addZuulRequestHeader("Content-Type", String.valueOf(MediaType.APPLICATION_JSON) + ";charset=UTF-8");
        } catch (Exception e) {
            System.out.println(this.getClass().getName() + "运行出错" + e.getMessage());
        }
        return null;
    }
}
