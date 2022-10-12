package com.blog.services.implementServices;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
// import java.util.UUID;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.blog.services.FileServices;

@Service
public class FileServicesImpl implements FileServices {

    @Override
    public String uploadIMage(String path, MultipartFile file) throws IOException {
        // file Name
        String name = file.getOriginalFilename();
        // random file name
            String randomID = UUID.randomUUID().toString();
            String randomFileName = randomID.concat(name.substring(name.lastIndexOf('.')));
        // full path with name
        String filePath = path + File.separator + randomFileName;

        // create folder for images/resources
        File f = new File(path);
        if (!f.exists()) {
            f.mkdir();
        }

        Files.copy(file.getInputStream(), Paths.get(filePath));
        return randomFileName;
    }

    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {
        String fullPath = path + File.separator + fileName;
        InputStream obj = new FileInputStream(fullPath);
        return obj;
    }

}
