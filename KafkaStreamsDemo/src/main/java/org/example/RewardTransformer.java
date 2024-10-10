package org.example;

import com.demo.invoice.types.Notification;
import com.demo.invoice.types.PosInvoice;
import com.demo.invoice.types.RewardNotification;
import org.apache.kafka.streams.kstream.ValueTransformer;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.state.KeyValueStore;

public class RewardTransformer implements ValueTransformer<PosInvoice, RewardNotification> {

    private KeyValueStore<String , Double> store;

    @Override
    public void init(ProcessorContext context) {
        this.store = context.getStateStore(AppConfigs.REWARDS_STORE_NAME);
    }

    @Override
    public RewardNotification transform(PosInvoice invoice) {

        RewardNotification notification = new RewardNotification()
                .withInvoiceNumber(invoice.getInvoiceNumber())
                .withCustomerCardNo(invoice.getCustomerCardNo())
                .withTotalAmount(invoice.getTotalAmount())
                .withEarnedLoyaltyPoints(invoice.getTotalAmount() * AppConfigs.LOYALTY_FACTOR)
                .withTotalLoyaltyPoints(0.0);


        Double accumulatedRewards = store.get(notification.getCustomerCardNo());
        Double totalRewards = 0.0;
        if(accumulatedRewards != null){
            totalRewards = accumulatedRewards + notification.getEarnedLoyaltyPoints();
        }
        else{
            totalRewards = notification.getEarnedLoyaltyPoints();
        }
        store.put(notification.getCustomerCardNo(), totalRewards);
        notification.setTotalLoyaltyPoints(totalRewards);
        return notification;
    }

    @Override
    public void close() {

    }
}
