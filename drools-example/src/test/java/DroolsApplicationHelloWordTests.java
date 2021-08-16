import noogel.xyz.drools.config.DroolsAutoConfiguration;
import noogel.xyz.drools.entity.PromotionCtx;
import noogel.xyz.drools.entity.PurchaseItem;
import noogel.xyz.drools.entity.User;
import org.drools.core.base.RuleNameEndsWithAgendaFilter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * https://www.cnblogs.com/zhixiang-org-cn/p/10106551.html
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DroolsAutoConfiguration.class)
public class DroolsApplicationHelloWordTests {

    @Autowired
    KieSession kieSession;

    @Test
    public void testHelloWord() {
        kieSession.fireAllRules();
    }

    @Test
    public void testUser() {
        User user=new User("张三",18);
        kieSession.insert(user);
        kieSession.fireAllRules(new RuleNameEndsWithAgendaFilter("user"));
        System.err.println("规则执行完毕后张三变为了："+user.getName());
    }

    @Test
    public void testPromotion() {
        PurchaseItem item = new PurchaseItem();
        item.setSkuId("123123");
        item.setQuantity(10);
        item.setPrice(5);
        PurchaseItem item2 = new PurchaseItem();
        item2.setSkuId("123124");
        item2.setQuantity(10);
        item2.setPrice(6);
        PromotionCtx ctx = new PromotionCtx();
        ctx.setPurchaseItemList(Stream.of(item, item2).collect(Collectors.toList()));
        kieSession.insert(ctx);
        kieSession.fireAllRules(new RuleNameEndsWithAgendaFilter("man_zhe"));
        System.out.println(ctx);
    }
}