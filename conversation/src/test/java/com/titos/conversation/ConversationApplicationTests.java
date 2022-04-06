package com.titos.conversation;

import com.titos.tool.token.CustomStatement;
import com.titos.tool.token.TokenContent;
import com.titos.tool.token.TokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ConversationApplication.class)
class ConversationApplicationTests {

    @Test
    public void test01() {
        CustomStatement customStatement = new CustomStatement();
        customStatement.setId(10001);
        customStatement.setUsername("ddgo");
        customStatement.setRole(1);
        TokenContent tokenContent = new TokenContent(customStatement, "YUNKE");
        String token = TokenUtil.buildToken(tokenContent);
        System.out.println(token);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            CustomStatement customStatement1 = TokenUtil.getMsgFromToken(token, "YUNKE");
        }catch (ExpiredJwtException e) {
            System.out.println("失效了");
        }
    }

    @Test
    public void test02() {

    }

    @Test
    public void test03() {

    }

}
