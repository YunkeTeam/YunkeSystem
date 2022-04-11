package com.titos.conversation.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Titos
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReceiveMessageVO {
    private Integer toUserId;
    private String message;
}
