package com.darkthor.User.Response;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRespose {
    private String message;
    private boolean status;
}
