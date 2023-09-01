package service;


import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;
import com.microsoft.azure.storage.blob.ListBlobItem;
import config.AzureBlobPropertiesConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


@Service
public class AzureBlobStorageService {

    private AzureBlobPropertiesConfig azureBlobPropertiesConfig;

    private static final Logger LOG = LoggerFactory.getLogger(AzureBlobStorageService.class);

    @Value("${storage.folderName}")
    private String storageFolder;

    @Autowired
    public AzureBlobStorageService(AzureBlobPropertiesConfig azureBlobPropertiesConfig) {
        this.azureBlobPropertiesConfig = azureBlobPropertiesConfig;
    }


    public String uploadFile(File file) {
        LOG.info("Azure store file BEGIN {}", file);
        try {

            CloudBlobContainer blobContainer = azureBlobPropertiesConfig.containerClient();
            blobContainer.createIfNotExists();
            CloudBlockBlob blob = blobContainer.getBlockBlobReference(storageFolder + "/" + file.getName());
            blob.uploadFromFile(file.getAbsolutePath());
            LOG.info("File uploaded successfully");
            return "File uploaded with success!";
        } catch (Exception e) {
            LOG.error("Azure store file failed with error", e.getMessage());
            return "File uploaded Failed!";
        }
    }

    public List<String> listBlobContainer() throws StorageException, URISyntaxException {
        LOG.info("\nListing blobs...");
        // List the blob(s) in the container.
        CloudBlobContainer blobContainer = azureBlobPropertiesConfig.containerClient();
        CloudBlockBlob blob = blobContainer.getBlockBlobReference(storageFolder);
        List<String> listAllFiles = new ArrayList<>();
        for (ListBlobItem blobItem : blobContainer.listBlobs()) {
            LOG.info(String.valueOf(blobItem.getUri()));
            listAllFiles.add(blobItem.getUri().toString());

        }
        return listAllFiles;
    }


    public void downloadFile(String fileName, String localPath) throws Exception {
        // Download the blob to a local file
        // Append the string "DOWNLOAD" before the .txt extension for comparison purposes
        String downloadFileName = fileName.replace(".txt", "DOWNLOAD.txt");
        LOG.info("\nDownloading blob to\n\t " + localPath + downloadFileName);
        try {
            CloudBlobContainer blobContainer = azureBlobPropertiesConfig.containerClient();
            CloudBlockBlob blob = blobContainer.getBlockBlobReference(storageFolder + "/" + fileName);
            blob.downloadToFile(localPath);
            LOG.info("Downloaded");
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void deleteContainerAndResources(String localPath, String fileName, String downloadFileName) {
        File downloadedFile = new File(localPath + downloadFileName);
        File localFile = new File(localPath + fileName);

        // Clean up resources
        LOG.info("Deleting blob container files...");
        try {
            CloudBlobContainer blobContainer = azureBlobPropertiesConfig.containerClient();
            CloudBlockBlob blob = blobContainer.getBlockBlobReference(storageFolder + "/" + fileName);
            blob.delete();
            LOG.info("Deleting the storage file.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOG.info("Deleting the local source and downloaded files...");
        localFile.delete();
        downloadedFile.delete();
        LOG.info("Deleted");
    }


}




