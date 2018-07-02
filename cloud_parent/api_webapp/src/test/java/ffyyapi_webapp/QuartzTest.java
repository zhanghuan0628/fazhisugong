package ffyyapi_webapp;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;

public class QuartzTest {
    @Scheduled(cron = "0/2 * * * * *")  
    public void process() {  
        System.out.println("job run");  
    }  
  
    public static void main(String[] args) throws InterruptedException {  
//        while (true) {  
//            System.out.println("main running...");  
//            Thread.sleep(10000);  
//        }  
    }  

}
