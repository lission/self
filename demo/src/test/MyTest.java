import com.SelfApplication;
import com.Controller.InterviewTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author lisong@mimidai.com
 * @date 2019/11/27 10:54
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SelfApplication.class)
@Component
public class MyTest {
    @Autowired
    private InterviewTest interviewTest;
    @Autowired
    private RedisTemplate redisTemplate;
    @Test
    public void simple(){
        System.out.println("简单执行测试单元");
        Boolean flag = redisTemplate.delete("lissiondeshare1");
        if (flag){
            System.out.println("解锁成功");
        }else {
            System.out.println("解锁失败");
        }
        String str = "friend_";
        String friend = "";
        String resp;
        for (int i=1;i<15;i++){
            friend = str+i;
            redisTemplate.opsForSet().remove("lissiondeshare1set",friend);
        }
        System.out.println("解锁结束");
    }
    
    @Test
    public void simple1(){
        
        System.out.println(redisTemplate.opsForSet().members(
            "lissiondeshareset"));
    }
    
    @Test
    public void test(){
        System.out.println("start _______");
        String str = "friend_";
        String friend = "";
        String resp;
        for (int i=1;i<15;i++){
            friend = str+i;
            resp = interviewTest.disCount("lissiondeshare1","lission",friend);
            System.out.println(friend+"{"+resp+"}");
        }
    }

}
