package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class FileController implements HandlerExceptionResolver {
    private final FileUploadService fileUploadService;
    private final UserService userService;
    private final ModelInitializerService modelInitializerService;

    public FileController(FileUploadService fileUploadService, UserService userService,
                           ModelInitializerService modelInitializerService) {
        this.fileUploadService = fileUploadService;
        this.userService = userService;
        this.modelInitializerService = modelInitializerService;
    }

    @GetMapping("/files")
    public String getFileUpload ( Model model) {
        modelInitializerService.initModels(model);
        return "home";
    }

    @PostMapping("/files/upload")
    public String uploadFile (@RequestParam("fileUpload") MultipartFile file,
                              Model model) {

        modelInitializerService.initModels(model);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUser(auth.getName());
        String page = "home";
        System.out.println(file.getSize());

        if (file.isEmpty()) {
            model.addAttribute("fileUploadErrMsg", "File is empty. Please upload a valid file");
            page = "home";

        }else if(!fileUploadService.isFileNameAvailable(file.getOriginalFilename())){
            model.addAttribute("success", null);
            model.addAttribute("failure", "File with this name is already saved.");
            model.addAttribute("route", "/files");
            page = "result";
        } else if (file.getSize() > 10485760) {
            System.out.println("file sieze");
            model.addAttribute("success", null );
            model.addAttribute("failure", "file max ");
            page = "result";
        }
        else{
            try {
                File file1 = new File();
                file1.setFilename(file.getOriginalFilename());
                file1.setFiledata(file.getBytes());
                file1.setFilesize(String.valueOf(file.getSize()));
                file1.setContenttype(file.getContentType());
                file1.setUserid(user.getUserid());
                int upload = fileUploadService.uploadFile(file1);
                if(upload>0){
                    model.addAttribute("listOfFiles", this.fileUploadService.getFiles());
                    model.addAttribute("success", "You successfully uploaded " + file.getOriginalFilename() + "'");
                    model.addAttribute("failure", null);
                    model.addAttribute("route", "/files");
                    page = "result";
                }else{
                    model.addAttribute("success", null);
                    model.addAttribute("failure", "Cant upload "+file.getOriginalFilename());
                    model.addAttribute("route", "/files");
                }

            } catch (IOException e) {
                model.addAttribute("success", null );
                model.addAttribute("failure", e.getMessage());
                page = "result";
            }
        }
        return page;
    }

    @RequestMapping(value="/files/download", method= RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<byte[]> getFile(@Param(value="fileId") int fileId,  Model model) throws Exception {
        modelInitializerService.initModels(model);
        File file = this.fileUploadService.getFile(fileId);
        byte[] output = file.getFiledata();

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("charset", "utf-8");
        responseHeaders.setContentType(MediaType.valueOf(file.getContenttype()));
        responseHeaders.setContentLength(output.length);
        responseHeaders.set("Content-disposition", "attachment; filename="+file.getFilename());

        return new ResponseEntity<byte[]>(output, responseHeaders, HttpStatus.OK);
    }
    @RequestMapping(value="/files/delete")
    public String handleDeleteUser(@Param(value="fileId") int fileId, Model model) {
        modelInitializerService.initModels(model);

        int deletestatus = fileUploadService.deleteFile(fileId);
        if(deletestatus>0){
            model.addAttribute("listOfFiles", this.fileUploadService.getFiles());
            model.addAttribute("success", "You have successfully delete the file");
            model.addAttribute("failure", null);
            model.addAttribute("route", "/files");
        }else{

            model.addAttribute("listOfFiles", this.fileUploadService.getFiles());
            model.addAttribute("success", null);
            model.addAttribute("failure", "File deletion failed!");
            model.addAttribute("route", "/files");
        }
        return "result";
    }

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        ModelAndView modelAndView = new ModelAndView("fileError");
        if (e instanceof MaxUploadSizeExceededException) {
            modelAndView.getModel().put("message", "File size exceeds limit!");
        }
        return modelAndView;
    }
}
