package org.tku.web.controller;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.tku.database.entity.Book;
import org.tku.database.repository.BookRepository;
import org.tku.web.entity.FileItem;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@Log4j2
public class FileController {
    private String folder = "D:/";

    public FileController() {

    }


    @RequestMapping(value = "/web/file")
    public String index(Model model) {
        String[] files = Paths.get(folder).toFile().list();
        assert files != null;
        List<FileItem> fileList = Arrays.stream(files).filter(name -> Paths.get(folder, name).toFile().isFile()).map(FileItem::new).toList();
        model.addAttribute("files", fileList);

        return "file/index";
    }

    @GetMapping(value = "/api/files")
    public ResponseEntity<byte[]> download(@RequestParam(value = "fileName", required = false) String fileName) throws IOException {
        byte[] content = IOUtils.toByteArray(Paths.get(folder, fileName).toUri());
        return ResponseEntity.ok().header("Content-Disposition", "attachment;filename="+fileName).body(content);
    }



    @PostMapping(value = "/api/files")
    public String upload(@RequestParam(name = "file") MultipartFile file) throws IOException {
        log.info(file.getOriginalFilename());
        FileUtils.copyInputStreamToFile(file.getInputStream(), Paths.get(folder, file.getOriginalFilename()).toFile());
        return "redirect:/web/file";
    }

}
