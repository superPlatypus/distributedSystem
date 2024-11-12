package ru.platypus.pr5.controller;


import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.platypus.pr5.entity.FileEntity;
import ru.platypus.pr5.service.FileService;

import java.io.IOException;


@RestController
@RequestMapping("/files")
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    // Загрузка файла
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        fileService.saveFile(file);
        return ResponseEntity.ok("Файл успешно загружен: " + file.getOriginalFilename());
    }

    // Выгрузка файла по ID
    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Long id) {
        FileEntity fileEntity = fileService.getFile(id);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(fileEntity.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileEntity.getFilename() + "\"")
                .body(fileEntity.getData());
    }

    // Выгрузка файла по имени
    @GetMapping("/download/name/{filename}")
    public ResponseEntity<byte[]> downloadFileByName(@PathVariable String filename) {
        FileEntity fileEntity = fileService.getFileByName(filename);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(fileEntity.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileEntity.getFilename() + "\"")
                .body(fileEntity.getData());
    }
}
