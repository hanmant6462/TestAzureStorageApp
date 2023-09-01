package com.example.demo2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.AzureBlobStorageService;
import utils.Utils;

import java.io.File;
import java.io.FileWriter;
import java.nio.charset.Charset;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
public class TestApiController {


    private final AzureBlobStorageService blobStorageService;

    private static final Logger LOG = LoggerFactory.getLogger(TestApiController.class);


    @Autowired
    public TestApiController(AzureBlobStorageService blobStorageService) {
        this.blobStorageService = blobStorageService;
    }

    @GetMapping("/list")
    public String testApi() {
        return "list";
    }

    @PostMapping("/upload")
    public ResponseEntity<Object> testApipost(@RequestBody Object data) {
        try {
            File file = (File) createFile(data);
            String result = blobStorageService.uploadFile(file);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file: " + e.getMessage());
        }
    }

    private Object createFile(Object data) {
        File result = null;
        try {
            result = File.createTempFile("data_", ".json");
            LOG.info("Temporary file created : " + result.getAbsolutePath());
            FileWriter writer = new FileWriter(result.getAbsolutePath(), Charset.forName("UTF-8"));
            writer.write(Utils.convertToString(data));
            writer.close();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return result;
    }


    @GetMapping("/listFiles")
    public ResponseEntity<String> listBlobs() {
        try {

            List listOfBlob = blobStorageService.listBlobContainer();
            return ResponseEntity.ok(listOfBlob.toString());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to list blobs: " + e.getMessage());
        }
    }

    @GetMapping("/download")
    public ResponseEntity<String> downloadBlob(@RequestParam("fileName") String fileName, @RequestParam("localPath") String localPath) {
        try {
            blobStorageService.downloadFile(fileName, localPath);
            return ResponseEntity.ok("Blob downloaded successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to download blob: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteContainerAndResources(@RequestParam("localPath") String localPath,
                                                              @RequestParam("fileName") String fileName,
                                                              @RequestParam("downloadFileName") String downloadFileName) {
        try {
            blobStorageService.deleteContainerAndResources(localPath, fileName, downloadFileName);
            return ResponseEntity.ok("Container and resources deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete container and resources: " + e.getMessage());
        }
    }
}







