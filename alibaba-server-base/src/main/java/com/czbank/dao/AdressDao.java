package com.czbank.dao;

import com.czbank.entity.Adress;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Adress)表数据库访问层
 *
 * @author makejava
 * @since 2020-04-21 11:35:57
 */
public interface AdressDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Adress queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Adress> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param adress 实例对象
     * @return 对象列表
     */
    List<Adress> queryAll(Adress adress);

    /**
     * 新增数据
     *
     * @param adress 实例对象
     * @return 影响行数
     */
    int insert(Adress adress);

    /**
     * 修改数据
     *
     * @param adress 实例对象
     * @return 影响行数
     */
    int update(Adress adress);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}