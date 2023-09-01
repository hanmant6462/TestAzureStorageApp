//package service;
//
//import com.azure.storage.blob.BlobClient;
//import com.azure.storage.blob.BlobContainerClient;
//import com.azure.storage.blob.BlobContainerClientBuilder;
//import com.azure.storage.blob.models.BlobItem;
//
//public class BlobContainerClientWrapperBuilder {
//    private BlobContainerClientBuilder blobContainerClient;
//
//    public BlobContainerClientWrapperBuilder setBlobContainerClient(BlobContainerClientBuilder blobContainerClient) {
//        this.blobContainerClient = blobContainerClient;
//        return this;
//    }
//
//    public BlobContainerClientWrapper createBlobContainerClientWrapper() {
//        return new BlobContainerClientWrapper(blobContainerClient) {
//            @Override
//            public BlobClient getBlobClient(String fileName) {
//                return null;
//            }
//
//            @Override
//            public void deleteIfExists() {
//
//            }
//
//            @Override
//            public BlobItem[] listBlobs() {
//                return new BlobItem[0];
//            }
//        };
//    }
//}