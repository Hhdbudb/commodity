package com.hailei.service.impl;

import com.hailei.mapper.CommodityMapper;
import com.hailei.pojo.Commodity;
import com.hailei.pojo.PageBean;
import com.hailei.service.CommodityService;
import com.hailei.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class CommodityServiceImpl implements CommodityService {
    //1. 创建SqlSessionFactory 工厂对象
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();


    @Override
    public List<Commodity> selectAll() {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取CommodityMapper
        CommodityMapper mapper = sqlSession.getMapper(CommodityMapper.class);

        //4. 调用方法
        List<Commodity> commodities = mapper.selectAll();

        //5. 释放资源
        sqlSession.close();

        return commodities;
    }

    @Override
    public void add(Commodity commodity) {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取CommodityMapper
        CommodityMapper mapper = sqlSession.getMapper(CommodityMapper.class);

        //4. 调用方法
        mapper.add(commodity);
        sqlSession.commit();//提交事务

        //5. 释放资源
        sqlSession.close();
    }

    @Override
    public void deleteById(int id) {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取CommodityMapper
        CommodityMapper mapper = sqlSession.getMapper(CommodityMapper.class);

        //4. 调用方法
        mapper.deleteById(id);

        sqlSession.commit();//提交事务

        //5. 释放资源
        sqlSession.close();
    }

    @Override
    public void updateById(Commodity commodity) {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取CommodityMapper
        CommodityMapper mapper = sqlSession.getMapper(CommodityMapper.class);

        //4. 调用方法
        mapper.updateById(commodity);

        sqlSession.commit();//提交事务

        //5. 释放资源
        sqlSession.close();
    }

    @Override
    public void deleteByIds(int[] ids) {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取CommodityMapper
        CommodityMapper mapper = sqlSession.getMapper(CommodityMapper.class);

        //4. 调用方法
        mapper.deleteByIds(ids);

        sqlSession.commit();//提交事务

        //5. 释放资源
        sqlSession.close();
    }

    @Override
    public PageBean<Commodity> selectByPage(int currentPage, int pageSize) {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取CommodityMapper
        CommodityMapper mapper = sqlSession.getMapper(CommodityMapper.class);


        //4. 计算开始索引
        int begin = (currentPage - 1) * pageSize;
        // 计算查询条目数
        int size = pageSize;

        //5. 查询当前页数据
        List<Commodity> rows = mapper.selectByPage(begin, size);

        //6. 查询总记录数
        int totalCount = mapper.selectTotalCount();

        //7. 封装PageBean对象
        PageBean<Commodity> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);


        //8. 释放资源
        sqlSession.close();

        return pageBean;
    }

    @Override
    public PageBean<Commodity> selectByPageAndCondition(int currentPage, int pageSize, Commodity commodity) {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取CommodityMapper
        CommodityMapper mapper = sqlSession.getMapper(CommodityMapper.class);


        //4. 计算开始索引
        int begin = (currentPage - 1) * pageSize;
        // 计算查询条目数
        int size = pageSize;

        // 处理commodity条件，模糊表达式
        String commodityName = commodity.getCommodityName();
        if (commodityName != null && commodityName.length() > 0) {
            commodity.setCommodityName("%" + commodityName + "%");
        }

        String publisherName = commodity.getPublisherName();
        if (publisherName != null && publisherName.length() > 0) {
            commodity.setPublisherName("%" + publisherName + "%");
        }


        //5. 查询当前页数据
        List<Commodity> rows = mapper.selectByPageAndCondition(begin, size, commodity);

        //6. 查询总记录数
        int totalCount = mapper.selectTotalCountByCondition(commodity);

        //7. 封装PageBean对象
        PageBean<Commodity> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);


        //8. 释放资源
        sqlSession.close();

        return pageBean;
    }


}
