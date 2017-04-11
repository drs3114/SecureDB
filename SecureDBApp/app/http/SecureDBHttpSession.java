/**
 * 
 */
package http;

import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

/**
 * @author DeepakShankar
 *
 */
public class SecureDBHttpSession implements HttpSession {

	private ServletContext context;

    public SecureDBHttpSession(ServletContext context) {
        this.context = context;
    }

    @Override
    public Object getAttribute(String arg0) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Enumeration<String> getAttributeNames() {
        throw new UnsupportedOperationException();
    }

    @Override
    public long getCreationTime() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getId() {
        throw new UnsupportedOperationException();
    }

    @Override
    public long getLastAccessedTime() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getMaxInactiveInterval() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ServletContext getServletContext() {
        return this.context;
    }

    @Override
    public HttpSessionContext getSessionContext() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object getValue(String arg0) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String[] getValueNames() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void invalidate() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isNew() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void putValue(String arg0, Object arg1) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void removeAttribute(String arg0) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void removeValue(String arg0) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setAttribute(String arg0, Object arg1) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setMaxInactiveInterval(int arg0) {
        throw new UnsupportedOperationException();
    }
}
