package org.example.serdes;

import com.demo.types.Product;
import com.demo.types.User;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;

import java.util.HashMap;
import java.util.Map;

public class AppSerde {
    static public final class UserSerde extends Serdes.WrapperSerde<User> {
        public UserSerde() {
            super(new JsonSerializer<>(), new JsonDeserializer<>());
        }
    }

    public static final Serde<User> User(){
        UserSerde userSerde =  new UserSerde();

        Map<String, Object> map = new HashMap<>();
        map.put(JsonDeserializer.VALUE_CLASS_NAME_CONFIG, User.class);
        userSerde.configure(map, false);
        return  userSerde;
    }

    static public final class ProductSerde extends Serdes.WrapperSerde<Product> {
        public ProductSerde() {
            super(new JsonSerializer<>(), new JsonDeserializer<>());
        }
    }

    public static final Serde<Product> Product(){
        ProductSerde userSerde =  new ProductSerde();

//        Map<String, Object> map = new HashMap<>();
//        map.put(JsonDeserializer.VALUE_CLASS_NAME_CONFIG, Product.class);
       // userSerde.configure(map, false);
        return  userSerde;
    }
}
