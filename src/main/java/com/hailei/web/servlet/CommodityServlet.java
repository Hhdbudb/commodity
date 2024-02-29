package com.hailei.web.servlet;


import com.alibaba.fastjson.JSON;
import com.hailei.pojo.Commodity;
import com.hailei.pojo.PageBean;
import com.hailei.service.CommodityService;
import com.hailei.service.impl.CommodityServiceImpl;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/commodity/*")
public class CommodityServlet extends BaseServlet{
    private CommodityService commodityService = new CommodityServiceImpl();

    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 调用service查询
        List<Commodity> commodities = commodityService.selectAll();

        //2. 转为JSON
        String jsonString = JSON.toJSONString(commodities);
        //3. 写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }


    /**
     * 新增
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1. 接收商品数据
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串

        //转为Commodity对象
        Commodity commodity = JSON.parseObject(params, Commodity.class);

        //2. 调用service添加
        commodityService.add(commodity);

        //3. 响应成功的标识
        response.getWriter().write("success");
    }

    /**
     * 删除
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void deleteById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1. 接收数据
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串

        //转为 int
        int id = JSON.parseObject(params,int.class);


        //2. 调用service添加
        commodityService.deleteById(id);

        //3. 响应成功的标识
        response.getWriter().write("success");
    }

    /**
     * 修改
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void updateById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1. 接收数据
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串

        //转为 int
        Commodity commodity = JSON.parseObject(params, Commodity.class);


        //2. 调用service添加
        commodityService.updateById(commodity);

        //3. 响应成功的标识
        response.getWriter().write("success");
    }

    /**
     * 批量删除
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void deleteByIds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1. 接收数据  [1,2,3]
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串

        //转为 int[]
        int[] ids = JSON.parseObject(params, int[].class);

        //2. 调用service添加
        commodityService.deleteByIds(ids);

        //3. 响应成功的标识
        response.getWriter().write("success");
    }

    /**
     * 分页查询
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */

    public void selectByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 接收 当前页码 和 每页展示条数    url?currentPage=1&pageSize=5
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");

        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        //2. 调用service查询
        PageBean<Commodity> pageBean = commodityService.selectByPage(currentPage, pageSize);

        //2. 转为JSON
        String jsonString = JSON.toJSONString(pageBean);
        //3. 写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }




    /**
     * 分页条件查询
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */

    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 接收 当前页码 和 每页展示条数    url?currentPage=1&pageSize=5
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");

        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        // 获取查询条件对象
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串

        //转为 Commodity
        Commodity commodity = JSON.parseObject(params, Commodity.class);


        //2. 调用service查询
        PageBean<Commodity> pageBean = commodityService.selectByPageAndCondition(currentPage,pageSize, commodity);

        //2. 转为JSON
        String jsonString = JSON.toJSONString(pageBean);
        //3. 写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }



}
