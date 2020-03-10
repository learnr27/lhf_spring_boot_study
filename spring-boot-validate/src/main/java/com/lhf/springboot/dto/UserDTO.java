package com.lhf.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @ClassName: UserDTO
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/7/24 9:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @NotBlank
    private String userId;

    @NotBlank
    private String username;

    private String password;

    @Valid
    private List<CardDTO> cardList;
}
