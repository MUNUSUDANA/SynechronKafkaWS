package invoice;

import invoice.datagenerator.InvoiceGenerator;
import invoice.types.PosInvoice;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.concurrent.atomic.AtomicBoolean;

public class RunnableProducer implements Runnable {
    private final AtomicBoolean stopper = new AtomicBoolean(false);
    private KafkaProducer<String, PosInvoice> producer;
    private String topicName;
    private InvoiceGenerator invoiceGenerator;
    private int produceSpeed;
    private int id;

    RunnableProducer(int id, KafkaProducer<String, PosInvoice> producer, String topicName, int produceSpeed) {
        this.id = id;
        this.producer = producer;
        this.topicName = topicName;
        this.produceSpeed = produceSpeed;
        this.invoiceGenerator = InvoiceGenerator.getInstance();
    }
    @Override
    public void run() {
        try {
            System.out.println("Starting producer thread - " + id);
            while (!stopper.get()) {
                PosInvoice posInvoice = invoiceGenerator.getNextInvoice();
                producer.send(new ProducerRecord<>(topicName, posInvoice.getStoreID(), posInvoice));
                Thread.sleep(produceSpeed);
            }
        } catch (Exception e) {
            System.out.println("Exception in Producer thread - " + id);
            throw new RuntimeException(e);
        }

    }

    void shutdown() {
        System.out.println("Shutting down producer thread - " + id);
        stopper.set(true);

    }
}
