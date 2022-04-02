package com.titos.conversation.vo;

import java.time.LocalDateTime;

/**
 * @Author: ddgo
 * @DateTime: 2022/4/1 20:54
 * @Version: 1.0.0
 * @Description:
 */

public class Message {
    private Long id;
    private Long sendId;
    private Long receiveId;
    private String content;
    private String imageAddr;
    private LocalDateTime releaseTime;
    private Long isComplete;
}
