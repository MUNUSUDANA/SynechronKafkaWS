package org.example.serdes;

import com.demo.invoice.types.HadoopRecord;
import com.demo.invoice.types.Notification;
import com.demo.invoice.types.PosInvoice;
import com.demo.invoice.types.RewardNotification;
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

    static final class PosInvoiceSerde extends Serdes.WrapperSerde<PosInvoice> {
        PosInvoiceSerde() {
            super(new JsonSerializer<>(), new JsonDeserializer<>());
        }
    }

    public static Serde<PosInvoice> PosInvoice() {
        PosInvoiceSerde serde = new PosInvoiceSerde();

        Map<String, Object> serdeConfigs = new HashMap<>();
        serdeConfigs.put(JsonDeserializer.VALUE_CLASS_NAME_CONFIG, PosInvoice.class);
        serde.configure(serdeConfigs, false);

        return serde;
    }

    static final class RewardNotificationSerde extends Serdes.WrapperSerde<RewardNotification> {
        RewardNotificationSerde() {
            super(new JsonSerializer<>(), new JsonDeserializer<>());
        }
    }

    public static Serde<RewardNotification> RewardNotification() {
        RewardNotificationSerde serde = new RewardNotificationSerde();

        Map<String, Object> serdeConfigs = new HashMap<>();
        serdeConfigs.put(JsonDeserializer.VALUE_CLASS_NAME_CONFIG, RewardNotificationSerde.class);
        serde.configure(serdeConfigs, false);

        return serde;
    }

    static final class NotificationSerde extends Serdes.WrapperSerde<Notification> {
        NotificationSerde() {
            super(new JsonSerializer<>(), new JsonDeserializer<>());
        }
    }

    public static Serde<Notification> Notification() {
        NotificationSerde serde = new NotificationSerde();

        Map<String, Object> serdeConfigs = new HashMap<>();
        serdeConfigs.put(JsonDeserializer.VALUE_CLASS_NAME_CONFIG, Notification.class);
        serde.configure(serdeConfigs, false);

        return serde;
    }

    static final class HadoopRecordSerde extends Serdes.WrapperSerde<HadoopRecord> {
        HadoopRecordSerde() {
            super(new JsonSerializer<>(), new JsonDeserializer<>());
        }
    }

    public static Serde<HadoopRecord> HadoopRecord() {
        HadoopRecordSerde serde = new HadoopRecordSerde();

        Map<String, Object> serdeConfigs = new HashMap<>();
        serdeConfigs.put(JsonDeserializer.VALUE_CLASS_NAME_CONFIG, HadoopRecord.class);
        serde.configure(serdeConfigs, false);

        return serde;
    }
}
