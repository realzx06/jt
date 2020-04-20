package cn.jt.aop;

import cn.jt.vo.SysResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//表示全出异常处理
//      controler通知 当程序运行过程中出现异常则会执行通知具体方法
@RestControllerAdvice
public class SystemException {


    //处理异常
    @ExceptionHandler(RuntimeException.class)
    public SysResult sysResult(Exception e){
        e.printStackTrace();
        return SysResult.fail();

    }



}
