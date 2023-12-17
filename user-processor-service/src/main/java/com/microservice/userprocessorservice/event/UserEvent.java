package com.microservice.userprocessorservice.event;

import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserEvent {
    private Long userId;
    private UserEventType eventType;
}
