package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
//스프링 빈에 등록해야하는데 AOP는 @Component로 어노테이션을 사용하기 보다는 설정파일에서 직접 넣어주는게 좋다.
public class TimeTraceAop {

    @Around("execution(* hello.hellospring..*(..))") //hello.hellospring패키지 하위에 모두 적용하라는 의미
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());

        try {
            return joinPoint.proceed();
        } finally {

            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString()+ " " + timeMs + "ms");
        }
    }
}