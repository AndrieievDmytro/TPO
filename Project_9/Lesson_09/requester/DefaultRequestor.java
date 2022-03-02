package requester;

import java.util.logging.Logger;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.naming.Context;

import common.Common;
import common.Common.Operation;
import messages.requests.AdditionRequest;
import messages.requests.DivisionRequest;
import messages.requests.MultiplicationRequest;
import messages.requests.RandomRequest;
import messages.requests.SubtractionRequest;
import messages.responses.ArithmeticResponse;
import messages.responses.RandomResponse;

public class DefaultRequestor implements Runnable {
	protected static int COUNTER = 0;
	protected static final Logger LOGGER = Logger.getLogger(DefaultRequestor.class.getName());
	protected int id = ++COUNTER;

	public int getId() {
		return id;
	}

	@Override
	public void run() {
		Connection connection = null;
		try {
			Context context = Common.getContext();
			connection = Common.createAndStartConnection(context);
			Session session = Common.createSession(connection);
			Queue responseQueue = (Queue) context.lookup("Queue" + id);
			MessageProducer messageProducer = Common.createRequestProducer(context, session);
			MessageConsumer messageConsumer = session.createConsumer(responseQueue);
			LOGGER.info(String.format("REQUESTOR-%d: STARTED", id));

			ObjectMessage responseMessage = null;
			for (int i = 0; i < 3; i++) {
				ObjectMessage requestMessage = session.createObjectMessage();
				int randomOperation = (int) (Math.random() * 5);
				switch (Operation.values()[randomOperation]) {
					case Random: {
						int min = 0;
						int max = 10;
						requestMessage.setObject(new RandomRequest(id, min, max));
						messageProducer.send(requestMessage);

						LOGGER.info(String.format("REQUESTOR-%d: MESSAGE PREPARED AND SENT", id));
						responseMessage = (ObjectMessage) messageConsumer.receive();
						RandomResponse randomResponse = (RandomResponse) responseMessage.getObject();
						LOGGER.info(
								String.format("REQUESTOR-%d: RANDOM RESPONSE = %d", id, randomResponse.getRandomNum()));

						break;
					}
					case Addition: {
						int number1 = 20;
						int number2 = 10;
						requestMessage.setObject(new AdditionRequest(id, number1, number2));
						messageProducer.send(requestMessage);

						LOGGER.info(String.format("REQUESTOR-%d: MESSAGE PREPARED AND SENT", id));
						responseMessage = (ObjectMessage) messageConsumer.receive();
						ArithmeticResponse arithmeticResponse = (ArithmeticResponse) responseMessage.getObject();
						LOGGER.info(String.format("REQUESTOR-%d: ADDITION RESPONSE = %f", id,
								arithmeticResponse.getResult()));
						break;
					}

					case Subtraction: {
						int number1 = 20;
						int number2 = 10;
						requestMessage.setObject(new SubtractionRequest(id, number1, number2));
						messageProducer.send(requestMessage);

						LOGGER.info(String.format("REQUESTOR-%d: MESSAGE PREPARED AND SENT", id));
						responseMessage = (ObjectMessage) messageConsumer.receive();
						ArithmeticResponse arithmeticResponse = (ArithmeticResponse) responseMessage.getObject();
						LOGGER.info(String.format("REQUESTOR-%d: SUBTRACTION RESPONSE = %f", id,
								arithmeticResponse.getResult()));
						break;
					}

					case Multiplication: {
						int number1 = 20;
						int number2 = 10;
						requestMessage.setObject(new MultiplicationRequest(id, number1, number2));
						messageProducer.send(requestMessage);

						LOGGER.info(String.format("REQUESTOR-%d: MESSAGE PREPARED AND SENT", id));
						responseMessage = (ObjectMessage) messageConsumer.receive();
						ArithmeticResponse arithmeticResponse = (ArithmeticResponse) responseMessage.getObject();
						LOGGER.info(String.format("REQUESTOR-%d: MULTIPLICATION RESPONSE = %f", id,
								arithmeticResponse.getResult()));
						break;
					}

					case Division: {
						int number1 = 20;
						int number2 = 10;
						requestMessage.setObject(new DivisionRequest(id, number1, number2));
						messageProducer.send(requestMessage);

						LOGGER.info(String.format("REQUESTOR-%d: MESSAGE PREPARED AND SENT", id));
						responseMessage = (ObjectMessage) messageConsumer.receive();
						ArithmeticResponse arithmeticResponse = (ArithmeticResponse) responseMessage.getObject();
						LOGGER.info(String.format("REQUESTOR-%d: DIVISION RESPONSE = %f", id,
								arithmeticResponse.getResult()));
						break;
					}

					default:
						break;
				}
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