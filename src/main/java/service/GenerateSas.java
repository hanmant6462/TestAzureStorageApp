//package service;
//
//import com.azure.storage.blob.BlobClientBuilder;
//import com.azure.storage.blob.BlobContainerClient;
//import com.azure.storage.blob.sas.BlobContainerSasPermission;
//import com.azure.storage.blob.sas.BlobSasPermission;
//import com.azure.storage.blob.sas.BlobServiceSasSignatureValues;
//import com.azure.storage.common.sas.AccountSasPermission;
//import com.azure.storage.common.sas.AccountSasResourceType;
//import com.azure.storage.common.sas.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.time.OffsetDateTime;
//
//@Component
//public class GenerateSas {
//
//
//
//     @Autowired
//     private final BlobContainerClient blobContainerClient ;
//
//
//     public GenerateSas(BlobContainerClient blobContainerClient){
//         this.blobContainerClient = blobContainerClient;
//     }
//
//
//
//
//
////    /*
////     * Generate an account sas. Other samples in this file will demonstrate how to create a client with the sas
////     * token.
////     */
////// Configure the sas parameters. This is the minimal set.
////    OffsetDateTime expiryTime = OffsetDateTime.now().plusDays(1);
////    AccountSasPermission accountSasPermission = new AccountSasPermission().setReadPermission(true);
////    AccountSasService services = new AccountSasService().setBlobAccess(true);
////    AccountSasResourceType resourceTypes = new AccountSasResourceType().setObject(true);
////
////    // Generate the account sas.
////    AccountSasSignatureValues accountSasValues =
////            new AccountSasSignatureValues(expiryTime, accountSasPermission, services, resourceTypes);
////    //String sasToken = blobServiceClient.generateAccountSas(accountSasValues);
////
////    // Generate a sas using a container client
////    BlobContainerSasPermission containerSasPermission = new BlobContainerSasPermission().setCreatePermission(true);
////    BlobServiceSasSignatureValues serviceSasValues =
////            new BlobServiceSasSignatureValues(expiryTime, containerSasPermission);
//        blobContainerClient.generateSas();
//
//    // Generate a sas using a blob client
//    BlobSasPermission blobSasPermission = new BlobSasPermission().setReadPermission(true);
//    serviceSasValues = new BlobServiceSasSignatureValues(expiryTime, blobSasPermission);
//blobClient.generateSas(serviceSasValues);
//}
