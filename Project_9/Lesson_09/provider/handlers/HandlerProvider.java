package provider.handlers;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import common.exceptions.CustomException;
import messages.requests.DefaultRequest;


public class HandlerProvider {

    private final static Map<Class<? extends DefaultRequest>, Class<? extends IRequestHandler>> m_map = new HashMap<>();

    public synchronized static void bind(Class<? extends DefaultRequest> key, Class<? extends IRequestHandler> handler) throws CustomException {
        if (m_map.containsKey(key)) {
            throw new CustomException("Service already mapped for the given key");
        }

        m_map.put(key, handler);
    }

    public static synchronized IRequestHandler get(Class<? extends DefaultRequest> key) throws CustomException, InstantiationException, IllegalAccessException,NoSuchMethodException,SecurityException, InvocationTargetException {
        if (m_map.containsKey(key) == false) {
            throw new CustomException("No service for entered key was found");
        }

        return m_map.get(key).getDeclaredConstructor().newInstance();
    }
}
