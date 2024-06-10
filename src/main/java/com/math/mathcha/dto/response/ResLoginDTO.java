package com.math.mathcha.dto.response;


import com.math.mathcha.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResLoginDTO extends User {
    private String token;

}
