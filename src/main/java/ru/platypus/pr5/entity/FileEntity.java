package ru.platypus.pr5.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class FileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String filename;

    private String fileType;

    @Lob
    private byte[] data;
}
