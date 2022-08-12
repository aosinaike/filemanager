package com.mytest.filemanager;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;


public interface FileService {
    public void init();
    public void save(MultipartFile file);
    public void deleteAll();
    public Resource load(String filename);
    public Stream<Path> loadAll();
    public StreamingResponseBody downloadZipFile(HttpServletResponse response, List<FileModel> fileModelList);
}