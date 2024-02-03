package com.example.coconuts.service;

import com.example.coconuts.dto.s3.S3FileDTO;
import org.springframework.web.multipart.MultipartFile;

public interface AmazonS3Service {
    S3FileDTO uploadFile(MultipartFile multipartFile); // 1개의 파일 업로드

    String deleteFile(Integer userId); // 파일 삭제

    String getUuidFileName(String fileName); // uuid 파일명 변환
    String getFolderName(); // 년월일 폴더명 변환

}
