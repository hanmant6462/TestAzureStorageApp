//package service;
//
//import com.azure.storage.blob.BlobContainerClient;
//import com.azure.storage.blob.BlobContainerClientBuilder;
//import org.springframework.stereotype.Component;
//
//@Component
//public abstract class BlobContainerClientWrapper  {
//    private final BlobContainerClientBuilder blobContainerClient;
//
//    public BlobContainerClientWrapper(BlobContainerClientBuilder blobContainerClient) {
//        this.blobContainerClient = blobContainerClient;
//    }
//
//
//    public BlobContainerClient getBlobContainerClient() {
//        return blobContainerClient.buildClient();
//    }
//}
