package com.xxxx.jd.controller.product;

import com.alibaba.fastjson.JSON;
import com.xxxx.jd.base.Result;
import com.xxxx.jd.base.ResultInfo;
import com.xxxx.jd.service.ProductService;
import com.xxxx.jd.vo.Product;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@WebServlet("/product/update")
public class update extends HttpServlet {
    private ProductService productService = new ProductService();
    @SneakyThrows
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json; charset=utf-8");
        Product product = new Product();
        product.setId(Integer.valueOf(req.getParameter("id")));
        product.setName(req.getParameter("name"));
        product.setMerchant(req.getParameter("merchant"));
        product.setType(req.getParameter("type"));
        if (!Objects.equals(req.getParameter("createDate"), "")) {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date createDate = df.parse(req.getParameter("createDate"));
            product.setCreateDate(createDate);
        }
        Object r = productService.updateProduct(product);
        ResultInfo resultInfo;
        if (r instanceof String) {
            resultInfo = Result.fail((String) r);
        } else {
            resultInfo = Result.success("更新产品成功！");
        }
        resp.getWriter().write(JSON.toJSONString(resultInfo));

    }
}