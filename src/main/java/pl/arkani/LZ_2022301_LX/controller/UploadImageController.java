package pl.arkani.LZ_2022301_LX.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

///todo:https://www.baeldung.com/spring-file-upload -  w trakcie ale cos nie dziala
///todo:https://spring.io/guides/gs/uploading-files/ -  w trakcie ale cos nie dziala
///todo:https://www.bezkoder.com/spring-boot-image-upload-thymeleaf/  - wykorzystanie fragmentów w thymeleaf
@Controller
public class UploadImageController {

    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/uploads";

    @GetMapping("/imageupload") public String displayUploadForm() {
        return "upload/imageupload/imageupload";
    }

    @PostMapping("/upload") public String uploadImage(Model model, @RequestParam("image") MultipartFile file) throws IOException {
        StringBuilder fileNames = new StringBuilder();
        Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());
        fileNames.append(file.getOriginalFilename());
        Files.write(fileNameAndPath, file.getBytes());
        model.addAttribute("msg", "Uploaded images: " + fileNames.toString());
        return "upload/imageupload/imageupload";
    }
}