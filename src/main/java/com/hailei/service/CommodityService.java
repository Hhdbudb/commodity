package com.hailei.service;

import com.hailei.pojo.Commodity;
import com.hailei.pojo.PageBean;

import java.util.List;

public interface CommodityService {

    /**
     * 查询所有
     * @return
     */
    List<Commodity> selectAll();

    /**
     * 添加数据
     * @param commodity
     */
    void add(Commodity commodity);

    /**
     * 删除
     * @param id
     */
    void deleteById(int id);

    /**
     * 修改
     * @param commodity
     */
    void updateById(Commodity commodity);

    /**
     * 批量删除
     * @param ids
     */
    void deleteByIds( int[] ids);

    /**
     * 分页查询
     * @param currentPage  当前页码
     * @param pageSize   每页展示条数
     * @return
     */
    PageBean<Commodity>  selectByPage(int currentPage, int pageSize);

    /**
     * 分页条件查询
     * @param currentPage
     * @param pageSize
     * @param commodity
     * @return
     */
    PageBean<Commodity>  selectByPageAndCondition(int currentPage, int pageSize, Commodity commodity);

}
