package org.example.backend.service.impl;


import com.cloudinary.Cloudinary;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@Service
public class CloudinaryService {
    Cloudinary cloudinary;

    public CloudinaryService() {
        Map<String, String> valuesMap = new HashMap<>();
        valuesMap.put("cloud_name", "dbehok9oq");
        valuesMap.put("api_key", "362391269528773");
        valuesMap.put("api_secret", "_9kj3P2cnj5_7VGRGhW9WhlYoR4");
        cloudinary = new Cloudinary(valuesMap);
    }

    @Transactional
    public ResponseEntity<?> upload(List<MultipartFile>imgList) throws IOException {
        List<String> imgUrls = new ArrayList<>();
        for (MultipartFile multipartFile : imgList) {
            File file = convert(multipartFile);
            Map result = cloudinary.uploader().upload(file, Collections.emptyMap());
            imgUrls.add((String) result.get("url"));
        }
            return new ResponseEntity<>(imgUrls, HttpStatus.OK);

    }

    private File convert(MultipartFile multipartFile) throws IOException {
        File file = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        FileOutputStream fo = new FileOutputStream(file);
        fo.write(multipartFile.getBytes());
        fo.close();
        return file;
    }
}


