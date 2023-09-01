package config;


import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;
import com.microsoft.azure.storage.blob.ListBlobItem;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.OffsetDateTime;

@Configuration
public class AzureBlobPropertiesConfig {

    @Value("${storage.connectionString}")
    private String storageConnectionString;
    @Value("${storage.containerName}")
    private String containerName;


    @Bean
    public CloudBlobContainer containerClient() {

        CloudBlobContainer container = null;
        try {
            // Retrieve storage account from connection-string.
            CloudStorageAccount storageAccount = CloudStorageAccount.parse(storageConnectionString);

            // Create the blob client.
            CloudBlobClient blobClient = storageAccount.createCloudBlobClient();

            // Retrieve reference to a previously created container.
            container = blobClient.getContainerReference(containerName);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return container;
    }
}


