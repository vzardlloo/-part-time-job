package com.mall.zhangxuan.controller;

import com.mall.zhangxuan.domain.Product;
import com.mall.zhangxuan.dto.ResponseModel;
import com.mall.zhangxuan.service.ProductService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    public ResponseModel<Product> saveProduct(@RequestBody Product products) {

        Product product1 = productService.saveProduct(products);
        return ResponseModel.builder()
                .code(0)
                .data(product1)
                .build();
    }


    @RequestMapping(value = "/products", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseModel<Product> deleteProduct(@RequestBody Product product) {
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
                String rootPath = ResourceUtils.getURL("classpath:").getPath();

                String uploadPath = rootPath + "/static/image/upload";
                File uploadFile = new File(uploadPath);
                if (!uploadFile.exists()) {
                    uploadFile.mkdirs();
                }
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(uploadPath, file.getOriginalFilename())));
                out.write(file.getBytes());
                out.flush();
                out.close();
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

    //save file

    private void saveUploadedFiles(List<MultipartFile> files) throws IOException {
        String rootPath = ProductController.class.getResource("/static/upload").getPath();
        for (MultipartFile file : files) {

            if (file.isEmpty()) {
                continue; //next pls
            }
            byte[] bytes = file.getBytes();
            Path path = Paths.get(rootPath + file.getOriginalFilename());
            Files.write(path, bytes);

        }

    }


}
