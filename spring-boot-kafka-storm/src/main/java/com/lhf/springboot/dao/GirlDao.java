package com.lhf.springboot.dao;

import com.lhf.springboot.pojo.Girl;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName: GirlDao
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/11/6 15:59
 */
@Mapper
public interface GirlDao {

    /**
     * 批量新增插入数据
     * @param girlList
     * @return
     * @throws Exception
     */
    boolean insertBatch(List<Girl> girlList) throws Exception;

    List<Girl> findByGirl(Girl girl);

    public List<Girl> find(Integer index, Integer limit);
    public Integer count();
    public Integer save(Girl girl);
    public Integer deleteById(Integer id);
    public Girl findById(Integer id);
    public Integer update(Girl girl);
    public Girl findByIdAndName(Integer id, String name);


}
