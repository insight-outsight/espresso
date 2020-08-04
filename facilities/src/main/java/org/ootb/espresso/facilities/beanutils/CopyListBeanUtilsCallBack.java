package org.ootb.espresso.facilities.beanutils;

@FunctionalInterface
public interface CopyListBeanUtilsCallBack <S, T> {
    /**
     * 定义默认回调方法
     * @param t
     * @param s
     */
    void callBack(S t, T s);
}