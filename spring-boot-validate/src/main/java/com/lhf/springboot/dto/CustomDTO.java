package com.lhf.springboot.dto;

import com.lhf.springboot.validate.annotation.CaseMode;
import com.lhf.springboot.validate.annotation.CheckCase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: CustomDTO
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/7/24 9:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomDTO {

    @CheckCase(value = CaseMode.UPPER)
    private String word;
}
