package com.mall.zhangheng.controller;

import com.mall.zhangheng.domain.Product;
import com.mall.zhangheng.dto.ResponseModel;
import com.mall.zhangheng.service.ProductService;
import com.mall.zhangheng.service.UserService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("dashboard")
public class ProductController {

    @Autowired
    ProductService productService;

    // 文件存放服务端的位置



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
    public ResponseModel<Product> saveProduct(@RequestBody Product product, @RequestParam(value = "image") MultipartFile file) {
        //MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        //MultipartFile file = request.getFile("image");
        try {
            String rootPath = ResourceUtils.getURL("classpath:").getPath();

            String uploadPath = rootPath + "/static/image/upload";
            File uploadFileFold = new File(uploadPath);
            if (!uploadFileFold.exists()) {
                uploadFileFold.mkdirs();
            }
            File uploadFile = new File(uploadPath, file.getOriginalFilename());
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(uploadFile));
            out.write(file.getBytes());
            out.flush();
            out.close();
            String imagePath = uploadFile.getAbsolutePath();
            product.setImage(imagePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("upload image success...");
        Product product1 = productService.saveProduct(product);
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
