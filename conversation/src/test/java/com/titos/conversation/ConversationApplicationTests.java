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
@SpringBootTest(classes = ConversationApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ConversationApplicationTests {

    @Test
    public void test01() {
        CustomStatement customStatement = new CustomStatement();
        customStatement.setId(10002);
        customStatement.setUsername("ddgo");
        customStatement.setRole(1);
        TokenContent tokenContent = new TokenContent(customStatement, "YUNKE");
        String token = TokenUtil.buildToken(tokenContent);
        System.out.println(token);
    }

    @Test
    public void test02() {

    }

    @Test
    public void test03() {

    }

}
