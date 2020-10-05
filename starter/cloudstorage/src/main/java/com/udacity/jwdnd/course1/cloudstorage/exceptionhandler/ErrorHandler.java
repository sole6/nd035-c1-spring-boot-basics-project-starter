package com.udacity.jwdnd.course1.cloudstorage.exceptionhandler;

import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@EnableWebMvc
@ControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(MultipartException.class)
    public String handleError1(MultipartException e, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("success", null);
        redirectAttributes.addFlashAttribute("failure", e.getCause().getMessage());
        return "result";
    }
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public String handleError2(MaxUploadSizeExceededException e, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("success", null);
        redirectAttributes.addFlashAttribute("failure", e.getCause().getMessage());
        return "result";

    }
    @ExceptionHandler(FileSizeLimitExceededException.class)
    public String handleError3(FileSizeLimitExceededException e, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("success", null);
        redirectAttributes.addFlashAttribute("failure", e.getCause().getMessage());
        return "result";

    }
    @ExceptionHandler(IllegalStateException.class)
    public String handleError4(IllegalStateException e, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("success", null);
        redirectAttributes.addFlashAttribute("failure", e.getCause().getMessage());
        return "result";

    }
}
