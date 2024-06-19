package com.yz.controller;

import com.alibaba.fastjson.JSON;
import com.yz.entity.Category;
import com.yz.entity.Page;
import com.yz.entity.Product;
import com.yz.service.ProductService;
import com.yz.service.impl.ProductServiceImpl;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.SQLException;
import java.util.*;


@WebServlet( "/product")
public class ProductController extends HttpServlet {
    ProductService productService = new ProductServiceImpl();
    @Override
    public void init() throws ServletException {
        super.init();
        // 获取热门商品列表
        List<Product> hotProductList = productService.findHotProductList();
        // 将热门商品列表添加到Servlet的上下文属性中
        getServletContext().setAttribute("hotProductList", hotProductList);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        if("list".equals(type)){
            list(request,response);
            //查询商品列表 type.equals(“list”)可能出现空指针异常
        }else if("add".equals(type)){
            add(request,response);
        }else if("del".equals(type)){
           del(request,response);
        } else if ("update".equals(type)) {
            update(request,response);
        } else if ("updateProduct".equals(type)) {
            updateProduct(request,response);
        } else if ("productByCid".equals(type)) {
            productByCid(request,response);
        } else if ("findByProduct".equals(type)) {
            findByProduct(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
    protected void productByCid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cid = request.getParameter("cid");
        String page = request.getParameter("page");
        int pageNo = page==""||page==null?1:Integer.parseInt(page);
        Page<Product> pageList = productService.findPage(pageNo,cid);
        response.setContentType("application/json;charset=utf-8");
        String json = JSON.toJSONString(pageList);
        response.getWriter().write(json);
    }
    protected void findByProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cid = request.getParameter("cid");
        request.setAttribute("cid",cid);
        request.getRequestDispatcher("/product_list.jsp").forward(request,response);
    }
    protected void updateProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //根据id修改商品信息
        try {
            // 获取表单各元素值，封装成Product实体，插入到底层数据
            // 1.创建工厂
            DiskFileItemFactory factory = new DiskFileItemFactory();
            // 2.创建核心类
            ServletFileUpload upload = new ServletFileUpload(factory);
            Product product = new Product();
            Map<String, Object> map = new HashMap<String,Object>();
            // 3.解析request的内容
            List<FileItem> parseRequest = upload.parseRequest(request);
            //遍历集合
            for(FileItem item : parseRequest){
                //判断是不是普通表单
                if(item.isFormField())
                {
                    //是普通表单,获取表单中name属性值，获取表单对应的输入值
                    String fieldName = item.getFieldName();
                    String fieldValue = item.getString("utf-8");
                    //放入map集合中
                    map.put(fieldName, fieldValue);
                }else{
                    //文件上传
                    //获取文件名，文件内容，写入服务器
                    String fileName = item.getName();
                    //文件上传的路径
                    String path =this.getServletContext().getRealPath("products");
                    InputStream in = item.getInputStream();
                    OutputStream out = new FileOutputStream(path+"/" + fileName);
                    IOUtils.copy(in, out);
                    in.close();
                    out.close();
                    //文件的路径放入map集合中
                    map.put("pimage","products"+ File.separator + fileName);
                }
            }
            //将map中的属性映射到product实体中
            BeanUtils.populate(product, map);
            //product.setPid(UUID.randomUUID().toString());
            //product.setPdate(new Date());
            //product.setPflag(0);
            Category c = new Category();
            c.setCid(map.get("cid").toString());
            product.setCategory(c);
            //保存商品
            boolean flag=productService.updateProduct(product);
            //保存成功后跳转到列表页，列表页可以直接看新增的数据
            if(flag){
                response.sendRedirect(request.getContextPath()+"/admin/product/list.jsp");
            }

        } catch (FileUploadException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pid=request.getParameter("pid");
        //根据商品id查询商品
        Product product = productService.findProductById(pid);
        request.setAttribute("product",product);
        request.getRequestDispatcher("/admin/product/edit.jsp").forward(request,response);

    }
    protected void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pid = request.getParameter("pid");
        boolean flag = productService.delProduct(pid);
        response.getWriter().print(flag);

    }
    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //查询商品列表
        List<Product> productList = productService.findAllProductList();
        String json = JSON.toJSONString(productList);//将list对象转成json字符串
        response.setContentType("application/json;charset=utf-8");//设置响应类型编码
        response.getWriter().write(json);
    }
    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 获取表单各元素值，封装成Product实体，插入到底层数据
            // 1.创建工厂
            DiskFileItemFactory factory = new DiskFileItemFactory();
            // 2.创建核心类
            ServletFileUpload upload = new ServletFileUpload(factory);
            Product product = new Product();
            Map<String, Object> map = new HashMap<String,Object>();
            // 3.解析request的内容
            List<FileItem> parseRequest = upload.parseRequest(request);
            //遍历集合
            for(FileItem item : parseRequest){
                //判断是不是普通表单
                if(item.isFormField())
                {
                    //是普通表单,获取表单中name属性值，获取表单对应的输入值
                    String fieldName = item.getFieldName();
                    String fieldValue = item.getString("utf-8");
                    //放入map集合中
                    map.put(fieldName, fieldValue);
                }else{
                    //文件上传
                    //获取文件名，文件内容，写入服务器
                    String fileName = item.getName();
                    //文件上传的路径
                    String path =this.getServletContext().getRealPath("products");
                    InputStream in = item.getInputStream();
                    OutputStream out = new FileOutputStream(path+"/" + fileName);
                    IOUtils.copy(in, out);
                    in.close();
                    out.close();
                    //文件的路径放入map集合中
                    map.put("pimage","products"+ File.separator + fileName);
                }
            }
            //将map中的属性映射到product实体中
            BeanUtils.populate(product, map);
            product.setPid(UUID.randomUUID().toString());
            product.setPdate(new Date());
            product.setPflag(0);
            Category c = new Category();
            c.setCid(map.get("cid").toString());
            product.setCategory(c);
            //保存商品
            boolean flag=productService.addProduct(product);
            //保存成功后跳转到列表页，列表页可以直接看新增的数据
            if(flag){
                response.sendRedirect(request.getContextPath()+"/admin/product/list.jsp");
            }

        } catch (FileUploadException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
