package com.lhf.springboot.repository.secondary;

import com.lhf.springboot.model.Girl;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @ClassName: SecondaryRepository
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/7/31 10:08
 */
public interface SecondaryRepository extends MongoRepository<Girl, Integer> {
}
