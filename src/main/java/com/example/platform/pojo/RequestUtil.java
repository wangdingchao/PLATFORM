package com.example.platform.pojo;

import com.example.platform.pojo.enums.TerminalType;

import javax.servlet.http.HttpServletRequest;

public class RequestUtil {

    public static String getIP(HttpServletRequest request) {
//        String ip = request.getHeader("X-Forwarded-For");
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            //X-Real-IP：nginx服务代理
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    //获取当前登陆的终端
    //todo 暂时先区分小程序和PC
    public static TerminalType getTerminal(HttpServletRequest request) {
        String agent = request.getHeader("user-agent");
        if (agent == null || agent == "" || "unknown".equalsIgnoreCase(agent)) {
            return TerminalType.UNION;
        }
        agent = agent.toLowerCase();
        //在微信端访问
        if (agent.indexOf("micromessenger") >= 0) {
            //小程序
            return TerminalType.MINAPP;
            //公众号
        }

        if (agent.indexOf("dingtalk") >= 0) {
            return TerminalType.DING;
        }
//        //安卓
//        if (agent.indexOf("Android") >= 0) {
//            return TerminalType.ANDROID;
//        }
//        //苹果
//        if ((agent.indexOf("iPhone") >= 0) || (agent.indexOf("ipad") >=  0) || (agent.indexOf("ipod") >= 0)) {
//            return TerminalType.IOS;
//        }
        return TerminalType.WEB;
    }
}
