package ru.platypus.pr5.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.platypus.pr5.entity.FileEntity;

public interface FileRepository extends JpaRepository<FileEntity, Long> {
    FileEntity findByFilename(String filename);
}
