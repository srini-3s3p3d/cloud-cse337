/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retrievefiles;


import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.S3Object;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class RetrieveFiles {

    public static BasicAWSCredentials creds=null; 
      
   
    private static final String bucketName = "bucketname";

    public static void main(String[] args) throws IOException {
      creds = new BasicAWSCredentials("key1", "key2");
      String path="C:\\Users\\Viswanathan\\Downloads\\S3 Steps.docx";
      String foldername="semanticweb/S3 Steps.docx";
      AmazonS3 s3 = AmazonS3Client.builder().withRegion(Regions.US_EAST_1).withCredentials(new AWSStaticCredentialsProvider(creds)).build();
      S3Object s3object = s3.getObject(bucketName, foldername);
        try (InputStream inputStream = s3object.getObjectContent()) {
            Files.copy(inputStream, Paths.get("C:\\Users\\Viswanathan\\Downloads\\sample.docx"));
        }
    }
}

    
    

