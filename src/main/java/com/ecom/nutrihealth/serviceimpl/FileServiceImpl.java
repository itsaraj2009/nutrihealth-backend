package com.ecom.nutrihealth.serviceimpl;

import com.ecom.nutrihealth.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {
//        file name of original file
        String originalFileName = file.getOriginalFilename();
//        Generate a unique file name
        String randomId = UUID.randomUUID().toString();
        String fileName = randomId.concat(originalFileName.substring(originalFileName.lastIndexOf('.'))); //images.jpg --> 1234.jpg
        String filePath = path + File.separator + fileName;
//        check if path exist and create
        File folder = new File(path);

        if (!folder.exists()) {
            boolean created = folder.mkdirs();

            if (!created) {
                throw new IOException("Failed to create directory: " + path);
            }
        }
//        upload to server
        Files.copy(file.getInputStream(), Paths.get(filePath));

//        returning file name
        return fileName;
    }
}
