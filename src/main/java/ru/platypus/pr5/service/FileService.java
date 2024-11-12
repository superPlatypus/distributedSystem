package ru.platypus.pr5.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.platypus.pr5.entity.FileEntity;
import ru.platypus.pr5.repository.FileRepository;

import java.io.IOException;

@Service
public class FileService {

    private final FileRepository fileRepository;

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public FileEntity saveFile(MultipartFile file) throws IOException {
        FileEntity fileEntity = new FileEntity();
        fileEntity.setFilename(file.getOriginalFilename());
        fileEntity.setFileType(file.getContentType());
        fileEntity.setData(file.getBytes());
        return fileRepository.save(fileEntity);
    }

    public FileEntity getFile(Long id) {
        return fileRepository.findById(id).orElse(null);
    }

    public FileEntity getFileByName(String filename) {
        return fileRepository.findByFilename(filename);
    }
}