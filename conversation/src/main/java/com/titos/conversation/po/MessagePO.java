package com.titos.conversation.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * @Author: ddgo
 * @DateTime: 2022/4/1 20:54
 * @Version: 1.0.0
 * @Description: 对应数据库中的消息表
 */
@Data
public class MessagePO {
    private Long id;
    private Long sendId;
    private Long receiveId;
    private String content;
    private String imageAddr;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime releaseTime;
    private Long isComplete;
}
