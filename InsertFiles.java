/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insertfiles;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
//Insert files to s3 bucket
public class InsertFiles {

    public static BasicAWSCredentials creds = null;

    private static final String bucketName = "srinievalkerala";

    public static void main(String[] args) throws IOException {
        creds = new BasicAWSCredentials("key1", "key2");
        String path = "C:\\Users\\Viswanathan\\Documents\\xyz.txt";
        String foldername = "data/requests.txt";
        AmazonS3 s3 = AmazonS3Client.builder().withRegion(Regions.US_EAST_1).withCredentials(new AWSStaticCredentialsProvider(creds)).build();
        PutObjectResult res = s3.putObject(new PutObjectRequest(bucketName, foldername, new File(path)));
        System.out.println(res.toString());
    }
}
