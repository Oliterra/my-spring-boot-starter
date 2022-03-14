package com.anecdotestarter.advice;

import com.anecdotestarter.service.AnecdoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Log4j2
@RequiredArgsConstructor
public class GetMethodInfoAdvice {

    private final AnecdoteService anecdoteService;

    @Pointcut("@annotation(com.anecdotestarter.annotation.GetMethodInfo)")
    public void getMethodInfoAdvice() {
    }

    @Around("getMethodInfoAdvice()")
    public Object getMethodInfo(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long stratTime = System.currentTimeMillis();
        Object obj = proceedingJoinPoint.proceed();
        long endTime = System.currentTimeMillis();
        log.info("Был вызван метод класса {} - \"{}\"\nЕго аргументы: {}\nОн вернул: {}\nПо времени он отработал {} ms",
                proceedingJoinPoint.getSourceLocation().getWithinType().getName(),
                proceedingJoinPoint.getSignature().getName(),
                proceedingJoinPoint.getArgs(),
                obj,
                (endTime - stratTime)
        );
        return obj;
    }

    @AfterThrowing(value = "getMethodInfoAdvice()", throwing = "exception")
    public void getMethodInfoFailedExecution(JoinPoint joinPoint, Exception exception) {
        log.warn("Не удалось получить информацию о методе \"{}\" класса {} из-за исключения {}" +
                        "\nНо не расстраивайтесь, держите лучше анекдот:\n{}",
                joinPoint.getSignature().getName(),
                joinPoint.getSourceLocation().getWithinType().getName(),
                exception,
                anecdoteService.getAnecdote()
        );
    }
}
