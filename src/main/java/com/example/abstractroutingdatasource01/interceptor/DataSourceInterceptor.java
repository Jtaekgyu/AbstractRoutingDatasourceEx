package com.example.abstractroutingdatasource01.interceptor;

import com.example.abstractroutingdatasource01.config.BranchContextHolder;
import com.example.abstractroutingdatasource01.config.BranchEnum;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class DataSourceInterceptor implements HandlerInterceptor {
    // 어플리케이션에 들어오는 요청의 branch 헤더 값을 확인하여 어떤 DataSource를 사용할지 결정한다.
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String branch = request.getHeader("branch");
        if (BranchEnum.KOREA.toString().equalsIgnoreCase(branch)) {
            System.out.println("~~~KOREA");
            BranchContextHolder.setBranchContext(BranchEnum.KOREA);
        } else {
            System.out.println("~~~JAPAN");
            BranchContextHolder.setBranchContext(BranchEnum.JAPAN);
        }
        return true;
    }
}
