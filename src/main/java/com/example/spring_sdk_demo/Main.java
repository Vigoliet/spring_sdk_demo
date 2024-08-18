package com.example.spring_sdk_demo;

import com.example.spring_sdk_demo.s3bucket.S3Example;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static String bucketName;
    private static S3Example s3Example = new S3Example();

    public static void main(String[] args) {
        while (true) {
            try {
                runS3Demo();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    // todo - case 3: list files in chosen bucket
    // todo - case 4: show all files in chosen bucket and delete specific file
    // todo - try to incorporate into GUI
    private static void runS3Demo() throws IOException {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("---------------------\n" +
                    "Enter the operation:\n" +
                    "1- Create Bucket\n" +
                    "2- Upload File\n" +
                    "3- Read File\n" +
                    "4- Delete Bucket Contents\n" +
                    "5- Delete Bucket\n" +
                    "6- List buckets\n" +
                    "7- Exit Program\n" +
                    "---------------------");
            int operation = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            String fileName = "file1.txt";

            switch (operation) {
                case 1:
                    userInputBucket();
                    System.out.println("Creating bucket: " + bucketName);
                    s3Example.createBucket(bucketName);
                    break;
                case 2:
                    if (bucketName == null) {
                        System.out.println("Bucket not created yet. Please create a bucket first.");
                    } else {
                        System.out.println("Uploading file " + fileName + " to bucket " + bucketName);
                        s3Example.uploadFile(fileName, fileName);
                    }
                    break;
                case 3:
                    s3Example.listBuckets();
                    userInputBucket();
                    if (bucketName == null) {
                        System.out.println("Bucket not created yet. Please create a bucket first.");
                    } else {
                        System.out.println("Reading file " + fileName + " from bucket " + bucketName);
                        s3Example.readFile(fileName);
                    }
                    break;
                case 4:
                    s3Example.listBuckets();
                    System.out.println("Select bucket to delete contents");
                    userInputBucket();
                    if (bucketName == null) {
                        System.out.println("Bucket not created yet. Please create a bucket first.");
                    } else {
                        System.out.println("Deleting file 'file1.txt' from bucket: " + bucketName);
                        System.out.println("Bucket Name: " + bucketName);
                        System.out.println("File Name: file1.txt");
                        s3Example.setBucketName(bucketName); // Ensure bucketName is set in S3Example instance
                        s3Example.deleteObject("file1.txt");
                    }
                    break;
                case 5:
                    s3Example.listBuckets();
                    userInputBucket();
                    if (bucketName == null) {
                        System.out.println("Bucket not created yet. Please create a bucket first.");
                    } else {
                        System.out.println("Deleting bucket " + bucketName);
                        s3Example.setBucketName(bucketName); // Ensure bucketName is set in S3Example instance
                        s3Example.deleteBucket();
                        bucketName = null; // Reset bucketName after deletion
                    }
                    break;
                case 6:
                    System.out.println("Listing buckets");
                    s3Example.listBuckets();
                    break;
                case 7:
                    System.out.println("Exiting program");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid operation");
                    break;
            }
        }
    }

    private static void userInputBucket() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter bucket name:");
        bucketName = scanner.nextLine();
    }
}