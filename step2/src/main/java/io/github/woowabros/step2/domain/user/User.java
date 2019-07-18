package io.github.woowabros.step2.domain.user;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@DynamoDBTable(tableName = "User")
public class User {
    @Id
    @DynamoDBHashKey
    private String id;

    private String name;
}
