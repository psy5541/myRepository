package com.comtec.standard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.util.*;

import com.comtec.standard.member.vo.MemberVO;

@Component
public class CertificationInterceptor implements HandlerInterceptor{
 
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HttpSession session = request.getSession();
        MemberVO loginVO = (MemberVO) session.getAttribute("loginUser");
        
        if(ObjectUtils.isEmpty(loginVO)){
            response.sendRedirect("/login");
            return false;
        }else{
            session.setMaxInactiveInterval(30*60);
            return true;
        }
    }
 
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        // TODO Auto-generated method stub
        
    }
 
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // TODO Auto-generated method stub
        
    }
 
}
