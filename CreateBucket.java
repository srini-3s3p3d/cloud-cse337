package javaapplication4;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.Bucket;
import java.io.IOException;
import java.util.List;

public class JavaApplication4 {

    public static BasicAWSCredentials creds = null;

    public static Bucket getBucket(String bucket_name) {

        AmazonS3 s3 = AmazonS3Client.builder().withRegion("us-east-1").withCredentials(new AWSStaticCredentialsProvider(creds)).build();
        Bucket named_bucket = null;
        List<Bucket> buckets = s3.listBuckets();
        for (Bucket b : buckets) {
            if (b.getName().equals(bucket_name)) {
                named_bucket = b;
            }
        }
        return named_bucket;
    }

    private static final String bucketName = "bucketname";

    public static void main(String[] args) throws IOException {
        creds = new BasicAWSCredentials("key1", "key2");

        AmazonS3 s3 = AmazonS3Client.builder().withRegion(Regions.US_EAST_1).withCredentials(new AWSStaticCredentialsProvider(creds)).build();
        Bucket b = null;
        if (s3.doesBucketExistV2(bucketName)) {
            System.out.format("Bucket %s already exists.\n", bucketName);
            b = getBucket(bucketName);
        } else {
            try {
                b = s3.createBucket(bucketName);
            } catch (AmazonS3Exception e) {
                System.err.println(e.getErrorMessage());
            }
        }

        if (b == null) {
            System.out.println("Error creating bucket!\n");
        } else {
            System.out.println("Done!\n");
        }

    }
}
