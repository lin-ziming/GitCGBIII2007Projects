package com.cy.pj.common.util;

import com.cy.pj.common.exception.ServiceException;


public class ValidUtils {
    public static void isArgsValid(boolean statement,String msg){
        if(statement) throw new IllegalArgumentException(msg);
    }
    public static void isResultValid(boolean statement,String msg){
        if(statement) throw new ServiceException(msg);
    }
}
