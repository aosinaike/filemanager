package com.mytest.filemanager;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class FileServiceImpl implements FileService{

    private final Path root = Paths.get("uploads");

    @Override
    public void init() {
        try {
            Files.createDirectory(root);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    @Override
    public void save(MultipartFile file) {
        try {
            Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
        } catch (Exception e) {
            throw new RuntimeException("Error occurred saving file "+file.getName()+". Error: " + e.getMessage());
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(root.toFile());
    }

    @Override
    public Resource load(String filename) {
        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Could not load the files!");
        }
    }

    @Override
    public StreamingResponseBody downloadZipFile(HttpServletResponse response, List<FileModel> fileModels) {
        response.setContentType("application/zip");
        response.setHeader("Content-Disposition", "attachment; filename=download.zip");
        File rootFile = root.toFile();
        StreamingResponseBody streamResponseBody = out -> {
            try (ZipOutputStream zipOutputStream = new ZipOutputStream(response.getOutputStream())) {
                for (FileModel fileModel : fileModels) {
                    FileSystemResource fileSystemResource = new FileSystemResource(rootFile.getName() + "/" + fileModel.getName());
                    ZipEntry zipEntry = new ZipEntry(fileSystemResource.getFilename());
                    zipEntry.setSize(fileSystemResource.contentLength());
                    zipEntry.setTime(System.currentTimeMillis());

                    zipOutputStream.putNextEntry(zipEntry);

                    StreamUtils.copy(fileSystemResource.getInputStream(), zipOutputStream);
                    zipOutputStream.closeEntry();
                }
                zipOutputStream.finish();
            } catch (IOException e) {
//            logger.error(e.getMessage(), e);
            }
        };
        return streamResponseBody;
    }

}
