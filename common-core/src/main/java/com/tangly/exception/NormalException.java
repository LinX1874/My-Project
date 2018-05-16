package com.tangly.exception;

/**
 * @author: tangly
 * @time：
 * @Discription: 所有的业务逻辑有问题需手动抛出该异常
 */
public class NormalException extends Exception {

    private static final long serialVersionUID = 1364225358754654702L;
    /**
     * 服务端错误异常码
     */
    private int errorCode;

    public NormalException(){
        super("业务逻辑异常");
    }

    public NormalException(String message){
        super(message);
        this.errorCode = 10001;
    }

    public NormalException(int errorCode,String message){
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}