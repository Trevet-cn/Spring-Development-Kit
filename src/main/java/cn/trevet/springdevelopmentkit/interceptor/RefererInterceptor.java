package cn.trevet.springdevelopmentkit.interceptor;

import cn.trevet.springdevelopmentkit.swagger2.DataResponseResult;
import cn.trevet.springdevelopmentkit.swagger2.ResponseResult;
import com.alibaba.fastjson.JSON;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * RefererInterceptor
 *
 * @author Trevet
 * 2021-12-02 12:19
 */
public class RefererInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String referer = request.getHeader("referer");
        String host = request.getServerName();

        // 如果不为空，则认为可能存在页面访问，或跨站访问
        if (referer != null) {
            URL url;
            try {
                url = new URL(referer);
            } catch (MalformedURLException e) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return this.handleError(response, "Referer Content Error!");
            }
            if (!host.equals(url.getHost())) {
                return this.handleError(response, "Referer Error!");
            }
        }

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    private boolean handleError(HttpServletResponse response, String errorMsg) throws IOException {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        ResponseResult responseResult = new DataResponseResult<>();
        responseResult.setCode(HttpServletResponse.SC_BAD_REQUEST);
        responseResult.setMessage(errorMsg);
        PrintWriter printWriter = response.getWriter();
        printWriter.write(JSON.toJSONString(responseResult));
        printWriter.flush();
        printWriter.close();
        return false;
    }
}
