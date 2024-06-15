package com.freedom.wishlist;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public abstract class MongoBaseTest {
    protected final String URL_BASE = "http://localhost:8080";

    static MongoDBContainer mongoDBContainer;

    static {
        Map<String, String> env = new HashMap<>();
        env.put("MONGO_REPLICA_SET_NAME", "rs0");
        mongoDBContainer = new MongoDBContainer("mongo:latest")
                .withEnv(env);
        mongoDBContainer.start();
    }

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    public static void wishlistInitialize(MongoTemplate mongoTemplate) {
        MongoCollection<Document> wishlistCollection = mongoTemplate.getCollection("wishlist");
        wishlistCollection.insertMany(Arrays.asList(
                        Document.parse("{_id: \"1\",\n" +
                                "\"customerId\": \"1\",\n" +
                                "\"idProducts\": [\"1\",\"2\",\"3\",\"4\",\"5\",\"6\",\"7\",\"8\",\"9\",\"10\",\n" +
                                "\"11\",\"12\",\"13\",\"14\",\"15\",\"16\",\"17\",\"18\",\"19\",\"20\"]}"),
                        Document.parse("{_id: \"2\",\n" +
                                "\"customerId\": \"1\",\n" +
                                "\"idProducts\": []}")
                )
        );
    }

    public static void dropAllWishlist(MongoTemplate mongoTemplate) {
        MongoCollection<Document> collection = mongoTemplate.getCollection("wishlist");
        collection.drop();
    }

    public static void productInitialize(MongoTemplate mongoTemplate) {
        MongoCollection<Document> produtoCollection = mongoTemplate.getCollection("product");
        produtoCollection.insertMany(Arrays.asList(
                Document.parse("{_id:\"1\"  ,\"name\": \"Product 1\" }"),
                Document.parse("{_id:\"2\"  ,\"name\": \"Product 2\" }"),
                Document.parse("{_id:\"3\"  ,\"name\": \"Product 3\" }"),
                Document.parse("{_id:\"4\"  ,\"name\": \"Product 4\" }"),
                Document.parse("{_id:\"5\"  ,\"name\": \"Product 5\" }"),
                Document.parse("{_id:\"6\"  ,\"name\": \"Product 6\" }"),
                Document.parse("{_id:\"7\"  ,\"name\": \"Product 7\" }"),
                Document.parse("{_id:\"8\"  ,\"name\": \"Product 8\" }"),
                Document.parse("{_id:\"9\"  ,\"name\": \"Product 9\" }"),
                Document.parse("{_id:\"10\" ,\"name\": \"Product 10\" }"),
                Document.parse("{_id:\"11\" ,\"name\": \"Product 11\" }"),
                Document.parse("{_id:\"12\" ,\"name\": \"Product 12\" }"),
                Document.parse("{_id:\"13\" ,\"name\": \"Product 13\" }"),
                Document.parse("{_id:\"14\" ,\"name\": \"Product 14\" }"),
                Document.parse("{_id:\"15\" ,\"name\": \"Product 15\" }"),
                Document.parse("{_id:\"16\" ,\"name\": \"Product 16\" }"),
                Document.parse("{_id:\"17\" ,\"name\": \"Product 17\" }"),
                Document.parse("{_id:\"18\" ,\"name\": \"Product 18\" }"),
                Document.parse("{_id:\"19\" ,\"name\": \"Product 19\" }"),
                Document.parse("{_id:\"20\" ,\"name\": \"Product 20\" }"),
                Document.parse("{_id:\"21\" ,\"name\": \"Product 21\" }")
        ));
    }

    public static void dropAllProduct(MongoTemplate mongoTemplate) {
        MongoCollection<Document> collection = mongoTemplate.getCollection("product");
        collection.drop();
    }

    public static void customerInitialize(MongoTemplate mongoTemplate) {
        MongoCollection<Document> clienteCollection = mongoTemplate.getCollection("customer");
        clienteCollection.insertMany(Arrays.asList(
                Document.parse("{_id:\"1\", \"name\": \"Customer 1\" }"),
                Document.parse("{_id:\"2\", \"name\": \"Customer 2\" }"),
                Document.parse("{_id:\"3\", \"name\": \"Customer 3\" }"),
                Document.parse("{_id:\"4\", \"name\": \"Customer 4\" }")
        ));
    }

    public static void dropAllCustomer(MongoTemplate mongoTemplate) {
        MongoCollection<Document> clienteCollection = mongoTemplate.getCollection("customer");
        clienteCollection.drop();
    }


}
