package com.tensquare.friend.interceptor;

import com.tensquare.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description:jwt的全局拦截器
 * @author:柴新峰
 * @create:2020/10/10
 */
@Component
public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("经过了拦截器");
        String header = request.getHeader("Authorization");

        if (header != null && !"".equals(header)) {
            //header信息不为空, 那么就进行解析的操作
            if (header.startsWith("Bearer")) {
                //得到token
                String token = header.substring(7);

                //对令牌进行验证
                //解析token的过程可能会抛出异常,因此使用try catch
                try {
                    //解析token
                    Claims claims = jwtUtil.parseJWT(token);

                    //获取角色信息
                    String roles = (String) claims.get("roles");

                    //判断角色是否为空,或者是不是admin角色
                    if (roles != null &&  "admin".equals(roles)) {
                        //如果角色不为空,并且是admin角色. 那么把token的信息,放入request域中,键为claims_admin, 值为token
                        request.setAttribute("claims_admin",token);
                    }

                    if (roles != null &&   "user".equals(roles)) {
                        //如果角色不为空,并且是user角色. 那么把token的信息,放入request域中,键为claims_user, 值为token
                        request.setAttribute("claims_user",token);
                    }

                } catch (RuntimeException e) {
                    //解析token的过程抛出异常, 也代表权限不足
                    //异常可能的原因是token过期等等
                    throw new RuntimeException("令牌不正确!");
                }
            }
        }
        //return true代表放行, return false 代表不放行
        //任何情况下都要放行, 最终具体的操作,要看其业务
        //拦截器只负责把请求头中包含token信息进行解析
        return true;
    }
}
