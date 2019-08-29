package com.tsikalov.proj.dto.response;

import com.tsikalov.proj.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
    private Long id;

    private String nickname;

    private String mail;

    public UserResponse(User user) {
        id = user.getId();
        nickname = user.getNickname();
        mail = user.getMail();
    }
}
