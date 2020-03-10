package com.lhf.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @ClassName: CardDTO
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/7/24 9:35
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardDTO {
    @NotBlank
    private String cardId;

    @Size(min = 10, max = 10)
    @NotNull
    private String cardNum;  //卡号

    @Range(max = 3)
    private String cardType;

    @Past
    @NotNull
    private Date createDate;
}
