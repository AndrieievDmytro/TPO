package provider;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.naming.Context;

import common.Common;
import messages.requests.DefaultRequest;
import provider.handlers.HandlerProvider;
import provider.handlers.IRequestHandler;

public class DefaultProvider implements Runnable {

    protected static int COUNTER = 0;
    protected static final Logger LOGGER = Logger.getLogger(DefaultProvider.class.getName());
    protected int id = ++COUNTER;

    public int getId() {
        return id;
    }

    public void run() {
        Connection connection = null;
        int randomTime = (int) (Math.random() * (5000 - 3000)) + 3000;
        try {
            Context context = Common.getContext();
            connection = Common.createAndStartConnection(context);
            Session session = Common.createSession(connection);
            MessageConsumer messageConsumer = Common.createRequestConsumer(context, session);
            LOGGER.info(String.format("PROVIDER-%d: STARTED", id));

            while (true) {
                try {
                    Thread.sleep(randomTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                ObjectMessage objectMessage = (ObjectMessage) messageConsumer.receive();
                DefaultRequest request = (DefaultRequest) objectMessage.getObject();
                LOGGER.info(String.format("PROVIDER-%d: REQUEST RECEIVED FROM REQUESTOR-%d", id, request.getSenderId()));

                IRequestHandler handler = HandlerProvider.get(request.getClass());
                Object response = handler.handle(request);
                ObjectMessage responseMessage = session.createObjectMessage();
                responseMessage.setObject((Serializable) response);

                Destination destination = (Queue) context.lookup("Queue" + request.getSenderId());
                MessageProducer messageProducer = session.createProducer(destination);
                messageProducer.send(responseMessage);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException exception) {
                    LOGGER.severe(exception.getMessage());
                }
            }
        }
    }
}
