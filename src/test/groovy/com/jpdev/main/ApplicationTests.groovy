package com.jpdev.main

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput
import com.jpdev.main.domain.Product
import com.jpdev.main.repository.ProductRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ApplicationTests {

    @Test
    void contextLoads() {
    }

    private DynamoDBMapper dynamoDBMapper;

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;

    @Autowired
    ProductRepository repository;

    private static final String EXPECTED_COST = "20";

    @BeforeEach
    public void setup() {
        dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB)

        CreateTableRequest tableRequest = dynamoDBMapper.generateCreateTableRequest(Product.class)
        tableRequest.setProvisionedThroughput(
                new ProvisionedThroughput(1L, 1L)
        )
        amazonDynamoDB.createTable(tableRequest);

        dynamoDBMapper.batchDelete(
                (List<Product>)repository.findAll()
        )
    }

    @Test
    public void givenItemWithExpectedCost_whenRunFindAll_thenItemIsFound() {
        Product productInfo = new Product();
        productInfo.setCost(EXPECTED_COST);
        println(productInfo.getCost());

        repository.save(productInfo);
        List<Product> result = (List<Product>) repository.findAll();

        assert (result.size() == 1)
        assert (result.get(0).getCost() == EXPECTED_COST)
    }
}
