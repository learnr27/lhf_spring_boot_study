package com.lhf.springboot.repository.primary;

import com.lhf.springboot.model.Girl;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @ClassName: PrimaryRepository
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/7/31 10:07
 */
public interface PrimaryRepository extends MongoRepository<Girl, Integer> {
}
