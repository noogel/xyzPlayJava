package noogel.xyz;

import noogel.xyz.service.ActionService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AspectjTest {

    @Autowired
    private ActionService actionService;

    @Test
    public void testA() throws InterruptedException {
        Assert.assertEquals(actionService.say("hello world!"), "program say:hello world!");
        actionService.listen("hello world!");
        try {
            actionService.throwExc();
            Assert.fail();
        } catch (RuntimeException ignore) {
        }
    }
}
