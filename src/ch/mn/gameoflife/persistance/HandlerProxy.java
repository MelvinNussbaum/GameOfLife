/******************************************************************************
 *
 * [ Proxy.java ]
 *
 * COPYRIGHT (c) 2002 - 2018 by Allianz-Suisse, Zürich, Switzerland.
 * All rights reserved. This material contains unpublished, copyrighted
 * work including confidential and proprietary information of Allianz-Suisse.
 *
 ******************************************************************************/
package ch.mn.gameoflife.persistance;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HandlerProxy implements InvocationHandler {

    private AbstractSafeManager safeManager;

    public HandlerProxy(AbstractSafeManager safeManager) {
        this.safeManager = safeManager;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
        throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        method.invoke(safeManager, args);
        return null;
    }
}