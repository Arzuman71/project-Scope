package com.projectScope.projectScope.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


@Service
@RequiredArgsConstructor
public class MainService {

    @Value("${file.upload.dir}")
    private String uploadDir;


    public byte[] getImageOrNull(String imageName) {
        InputStream in = null;
        try {
            in = new FileInputStream(uploadDir + File.separator + imageName);

            return IOUtils.toByteArray(in);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
