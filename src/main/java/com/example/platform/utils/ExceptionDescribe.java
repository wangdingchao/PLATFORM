package com.example.platform.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by xiaoxuwang on 2018/2/6.
 */
public enum ExceptionDescribe {

    MESSAGE;

    ExceptionDescribe(){}

    public String getMessage(Exception e){
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw, true);
        e.printStackTrace(pw);
        pw.flush();
        sw.flush();
        return sw.toString();
    }



}
