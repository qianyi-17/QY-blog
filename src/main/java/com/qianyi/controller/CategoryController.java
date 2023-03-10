package com.qianyi.controller;

import com.alibaba.excel.EasyExcel;
import com.qianyi.domain.ResponseResult;
import com.qianyi.domain.entity.Category;
import com.qianyi.domain.vo.CategoryVo;
import com.qianyi.service.CategoryService;
import com.qianyi.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author 千亦
 * @create 2022-11-16-16:18
 */
@RestController
@RequestMapping("/content/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    /**
     * 查询所有分类接口
     * @return
     */
    @GetMapping("/listAllCategory")
    public ResponseResult listAllCategory(){
        ResponseResult result = categoryService.listAllCategory();
        return result;
    }
    @GetMapping("/export")
    public void export(HttpServletResponse response){
        try {
            //设置下载文件请求头
            WebUtils.setDownLoadHeader("分类.xlsx",response);
            //获取需要导出的数据
            List<CategoryVo> categoryVos = (List<CategoryVo>) categoryService.listAllCategory();
            //把数据写入到excel中
//            EasyExcel.write(response.getOutputStream(), DownloadData.class).autoCloseStream(Boolean.FALSE).sheet("模板")
//                    .doWrite(data());
        } catch (UnsupportedEncodingException e) {

        }
        //把数据写入到excel中
        //如果出现异常也要响应json数据
    }
}
