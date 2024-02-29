package com.hailei.mapper;

import com.hailei.pojo.Commodity;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CommodityMapper {

    /**
     * 查询所有
     * @return
     */
    @Select("select * from tb_commodity")
    @ResultMap("commodityResultMap")
    List<Commodity> selectAll();

    /**
     * 添加数据
     * @param commodity
     */
    @Insert("insert into tb_commodity values(null,#{commodityName},#{publisherName},#{price},#{description},#{status})")
    void add(Commodity commodity);


    /**
     * 删除
     * @param id
     */
    @Delete("delete from tb_commodity where id=#{id}")
    void deleteById(int id);

    /**
     * 修改
     * @param commodity
     */
    @Update("update tb_commodity set commodity_name = #{commodityName},publisher_name = #{publisherName},price = #{price},description = #{description},status = #{status} where id = #{id}")
    void updateById(Commodity commodity);

    /**
     * 批量删除
     * @param ids
     */
    void deleteByIds(@Param("ids") int[] ids);

    /**
     * 分页查询
     * @param begin
     * @param size
     * @return
     */
    @Select("select * from tb_commodity limit #{begin} , #{size}")
    @ResultMap("commodityResultMap")
    List<Commodity> selectByPage(@Param("begin") int begin, @Param("size") int size);

    /**
     * 查询总记录数
     * @return
     */
    @Select("select count(*) from tb_commodity ")
    int selectTotalCount();



    /**
     * 分页条件查询
     * @param begin
     * @param size
     * @return
     */
    List<Commodity> selectByPageAndCondition(@Param("begin") int begin, @Param("size") int size, @Param("commodity") Commodity commodity);

    /**
     * 根据条件查询总记录数
     * @return
     */
    int selectTotalCountByCondition(Commodity commodity);
}
