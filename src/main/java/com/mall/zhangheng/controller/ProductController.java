package com.mall.zhangheng.controller;

import com.mall.zhangheng.domain.Product;
import com.mall.zhangheng.dto.ResponseModel;
import com.mall.zhangheng.service.ProductService;
import com.mall.zhangheng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Controller
@RequestMapping("dashboard")
public class ProductController {

    @Autowired
    ProductService productService;

    @ResponseBody
    @RequestMapping(value = "/products",method = RequestMethod.GET)
    public ResponseModel<List<Product>> getAllProducts(){
        List<Product> productList = productService.getAllProducts();
        return ResponseModel.builder()
                .code(0)
                .data(productList)
                .build();

    }

    @RequestMapping(value = "/products",method = RequestMethod.POST)
    @ResponseBody
    public ResponseModel<Product> saveProduct(@RequestBody Product product){
        Product product1 = productService.saveProduct(product);
        return ResponseModel.builder()
                .code(0)
                .data(product1)
                .build();
    }

    @RequestMapping(value = "/products",method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseModel<Product> deleteProduct(@RequestBody Product product){
        productService.deleteProduct(product);
        return ResponseModel.builder()
                .code(0)
                .data(null)
                .build();
    }




    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    @ResponseBody
    public  ResponseModel uploadImage(@RequestBody MultipartFile file){
        if (!file.isEmpty()) {
            try {
                // 文件存放服务端的位置
                String rootPath = this.getClass().getResource("/upload").getPath();
                File dir = new File(rootPath + File.separator + "tmpFiles");
                if (!dir.exists())
                    dir.mkdirs();
                // 写文件到服务器
                File serverFile = new File(dir.getAbsolutePath() + File.separator + file.getOriginalFilename());
                file.transferTo(serverFile);
                return ResponseModel.builder()
                        .code(0)
                        .message("You successfully uploaded file=" +  file.getOriginalFilename())
                        .data(file)
                        .build();
            } catch (Exception e) {
                return ResponseModel.builder()
                        .code(500)
                        .message("You failed to upload " +  file.getOriginalFilename() + " => " + e.getMessage())
                        .data(null)
                        .build();
            }
        } else {
            return ResponseModel.builder()
                    .code(500)
                    .message("You failed to upload " +  file.getOriginalFilename() + " because the file was empty.")
                    .data(null)
                    .build();
        }
      }


}
