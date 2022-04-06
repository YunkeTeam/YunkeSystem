package com.titos.conversation.controller;

import com.sun.deploy.net.HttpResponse;
import com.titos.conversation.po.MessagePO;
import com.titos.conversation.service.impl.ConversationServiceImpl;
import com.titos.info.global.CommonResult;
import com.titos.info.global.enums.StatusEnum;
import com.titos.tool.token.TokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: ddgo
 * @DateTime: 2022/4/1 20:47
 * @Version: 1.0.0
 * @Description: TODO
 */
@Controller
@RequestMapping("/conversation")
public class ConversationController {

    @Autowired
    ConversationServiceImpl service;

    @GetMapping("/clickAvatar")
    @ResponseBody
    public String startDialog(ModelMap model, String token, String id) {
        CommonResult<List<MessagePO>> commonResult = null;
        try {
//            Integer userId = TokenUtil.verifyTokenAndGetUserId(token);
            Integer userId = (Integer) TokenUtil.getTokenValueByKey(token, "YUNKE", "id");
            Integer otherUserId = Integer.parseInt(id);
            commonResult = service.selectAllDialog(userId, otherUserId);
        }catch (ExpiredJwtException e) {
            commonResult = new CommonResult<>(StatusEnum.TOKEN_ERROR.getCode(), null, StatusEnum.TOKEN_ERROR.getMsg());
        }
        model.addAttribute("commonResult", commonResult);
        return commonResult.toString();
    }
}
