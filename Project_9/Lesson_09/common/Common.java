package common;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Common {

	public static enum Operation {
		Random(0), 
		Addition(1), 
		Subtraction(2), 
		Multiplication(3), 
		Division(4);

		private final int m_value;

		Operation(int operation) {
			m_value = operation;
		}

		public int getValue() {
			return m_value;
		}
	}

	private static final String MAIN_QUEUE_NAME = "MainQueue";
	private static final String CONNECTION_FACTORY = "ConnectionFactory";

	public static final int REQUESTER_POOL_SIZE = 10;
	public static final int PROVIDER_POOL_SIZE = 5;

	public static Context getContext() throws NamingException {
		Context context = new InitialContext();
		return context;
	}

	public static Connection createAndStartConnection(Context context) throws NamingException, JMSException {
		Connection connection = createConnection(context);
		connection.start();
		return connection;
	}

	public static Session createSession(Connection connection) throws JMSException {
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		return session;
	}

	public static MessageProducer createRequestProducer(Context context, Session session)
			throws NamingException, JMSException {
		Destination destination = (Queue) context.lookup(Common.MAIN_QUEUE_NAME);
		return session.createProducer(destination);
	}

	public static MessageConsumer createRequestConsumer(Context context, Session session)
			throws NamingException, JMSException {
		Destination destination = (Queue) context.lookup(Common.MAIN_QUEUE_NAME);
		return session.createConsumer(destination);
	}

	private static Connection createConnection(Context context) throws NamingException, JMSException {
		ConnectionFactory factory = connectionFactory(context);
		Connection connection = factory.createConnection();
		return connection;
	}

	private static ConnectionFactory connectionFactory(Context context) throws NamingException {
		ActiveMQConnectionFactory factory = (ActiveMQConnectionFactory) context.lookup(Common.CONNECTION_FACTORY);
		factory.setTrustAllPackages(true);
		return factory;
	}
}