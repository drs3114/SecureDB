package http;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.AsyncContext;
import javax.servlet.DispatcherType;
import javax.servlet.ReadListener;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpUpgradeHandler;
import javax.servlet.http.Part;

import play.Play;
import play.i18n.Lang;
import play.mvc.Http;
import play.mvc.Http.RawBuffer;
import play.mvc.Http.Request;

public class SecureDBHttpRequest implements HttpServletRequest {

	private String characterEncoding;
	private final Request request;
	Map<String, Object> attributes = new HashMap<String, Object>();
	private final HttpSession httpSession;
	private final String pathInfo;

	/**
	 * The set of SimpleDateFormat formats to use in getDateHeader().
	 *
	 * Notice that because SimpleDateFormat is not thread-safe, we can't declare
	 * formats[] as a static variable.
	 */
	protected final SimpleDateFormat formats[] = { new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US),
			new SimpleDateFormat("EEEEEE, dd-MMM-yy HH:mm:ss zzz", Locale.US),
			new SimpleDateFormat("EEE MMMM d HH:mm:ss yyyy", Locale.US) };

	public SecureDBHttpRequest(Request request, HttpSession httpSession, String pathInfo) {
		this.request = request;
		this.httpSession = httpSession;
		this.pathInfo = pathInfo;
	}

	@Override
    public AsyncContext getAsyncContext() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object getAttribute(String name) {
        return attributes.get(name);
    }

    @Override
    public Enumeration<String> getAttributeNames() {
        return Collections.enumeration(attributes.keySet());
    }

    @Override
    public String getCharacterEncoding() {
        // request.headers.get("Content-Type").value()
        return this.characterEncoding;
    }

    @Override
    public int getContentLength() {
        String contentLength = request.getHeader(Http.HeaderNames.CONTENT_LENGTH);

        if (contentLength == null) {
            return -1;
        }

        return Integer.parseInt(contentLength);
    }

    @Override
    public String getContentType() {
        return request.getHeader(Http.HeaderNames.CONTENT_TYPE);
    }

    @Override
    public DispatcherType getDispatcherType() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        RawBuffer raw = request.body().asRaw();
        byte[] buf = raw.asBytes().toArray();
        final InputStream in;

        // If the content size is bigger than memoryThreshold,
        // which is defined as 100 * 1024 in play.api.mvc.BodyParsers trait,
        // the content is stored as a file.
        if (buf != null) {
            in = new ByteArrayInputStream(buf);
        } else {
            in = new FileInputStream(raw.asFile());
        }

        return new ServletInputStream() {
            @Override
            public int read() throws IOException {
                return in.read();
            }

            @Override
            protected void finalize() throws IOException {
                in.close();
            }

			@Override
			public boolean isFinished() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean isReady() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public void setReadListener(ReadListener arg0) {
				// TODO Auto-generated method stub
				
			}
        };
    }

    @Override
    public String getLocalAddr() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getLocalName() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getLocalPort() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Locale getLocale() {
        List<Lang> languages = request.acceptLanguages();
        if (languages.size() > 0) {
            return languages.get(0).toLocale();
        }

        return Locale.getDefault();
    }

    @Override
    public Enumeration<Locale> getLocales() {
        List<Locale> locales = new ArrayList<Locale>();
        for (Lang lang : request.acceptLanguages()) {
            locales.add(lang.toLocale());
        }
        return Collections.enumeration(locales);
    }

    @Override
    public String getParameter(String key) {
        String[] values = request.queryString().get(key);

        if (values.length > 0) {
            return values[0];

        } else {
            return null;
        }
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        return request.queryString();
    }

    @Override
    public Enumeration<String> getParameterNames() {
        return Collections.enumeration(request.queryString().keySet());
    }

    @Override
    public String[] getParameterValues(String key) {
        return request.queryString().get(key);
    }

    @Override
    public String getProtocol() {
        throw new UnsupportedOperationException();
    }

    @Override
    public BufferedReader getReader() throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getRealPath(String path) {
        return Play.application().getFile(path).getAbsolutePath();
    }

    @Override
    public String getRemoteAddr() {
        return request.remoteAddress();
    }

    @Override
    public String getRemoteHost() {
        // FIXME
        return null;
    }

    @Override
    public int getRemotePort() {
        throw new UnsupportedOperationException();
    }

    @Override
    public RequestDispatcher getRequestDispatcher(String arg0) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getScheme() {
        String scheme = null;

        try {
            scheme = new URI(request.uri()).getScheme();
        } catch (URISyntaxException e) {
        }

        if (scheme == null) {
            return "http";
        }

        return scheme;
    }

    @Override
    public String getServerName() {
        return request.host().split(":")[0];
    }

    @Override
    public int getServerPort() {
        try {
            return Integer.parseInt(request.host().split(":")[1]);
        } catch (Exception e) {
            if (getScheme().equals("https")) {
                return 443;
            } else {
                return 80;
            }
        }
    }

    @Override
    public ServletContext getServletContext() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isAsyncStarted() {
        return false;
    }

    @Override
    public boolean isAsyncSupported() {
        return false;
    }

    @Override
    public boolean isSecure() {
        // FIXME: Make this to work precisely after releasing Play 2.1.
        if (getScheme().equals("https")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void removeAttribute(String name) {
        attributes.remove(name);
    }

    @Override
    public void setAttribute(String name, Object value) {
        attributes.put(name, value);
    }

    @Override
    public void setCharacterEncoding(String characterEncoding) throws UnsupportedEncodingException {
        this.characterEncoding = characterEncoding;
    }

    @Override
    public AsyncContext startAsync() throws IllegalStateException {
        throw new UnsupportedOperationException();
    }

    @Override
    public AsyncContext startAsync(ServletRequest arg0, ServletResponse arg1)
            throws IllegalStateException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean authenticate(HttpServletResponse arg0) throws IOException, ServletException {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getAuthType() {
        // FIXME: should return appropriate auth type
        return null;
    }

    @Override
    public String getContextPath() {
    	System.out.println(request.remoteAddress());
        return request.path();
    }

    @Override
    public Cookie[] getCookies() {
    	List<Cookie> httpCokies = new ArrayList<>();
    	for(Http.Cookie cks : request.cookies()){
    		Cookie ck = new Cookie(cks.name(), cks.value());
    		httpCokies.add(ck);
    	}
    	Cookie[] newCookies = new Cookie[httpCokies.size()];
    	return  httpCokies.toArray(newCookies);
        //throw new UnsupportedOperationException();
    }

    @Override
    public long getDateHeader(String name) {
        String date = request.getHeader(name);

        if (date == null) {
            return -1;
        }

        return Date.parse(date);
    }

    @Override
    public String getHeader(String name) {
        return request.getHeader(name);
    }

    @Override
    public Enumeration<String> getHeaderNames() {
        return Collections.enumeration(request.headers().keySet());
    }

    @Override
    public Enumeration<String> getHeaders(String name) {
        String[] values = request.headers().get(name);

        if (values == null) {
            return Collections.enumeration(Collections.<String> emptyList());
        }

        return Collections.enumeration(Arrays.asList(request.headers().get(name)));
    }

    // same as org.apache.catalina.connector.Request.getHeaders
    @Override
    public int getIntHeader(String name) {
        String value = getHeader(name);
        if (value == null) {
            return (-1);
        }

        return Integer.parseInt(value);
    }

    @Override
    public String getMethod() {
        return request.method();
    }

    @Override
    public Part getPart(String arg0) throws IOException, ServletException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<Part> getParts() throws IOException, ServletException {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getPathInfo() {
        return pathInfo;
    }

    @Override
    public String getPathTranslated() {
        // FIXME: I'm not sure this works correctly.
        return getRealPath(getPathInfo());
    }

    @Override
    public String getQueryString() {
        String uri = request.uri();
        int index = uri.indexOf('?');

        if (index >= 0) {
            return uri.substring(index + 1);
        } else {
            return null;
        }
    }

    @Override
    public String getRemoteUser() {
        return null;
    }

    @Override
    public String getRequestURI() {
        return request.path();
    }

    @Override
    public StringBuffer getRequestURL() {
    	StringBuffer sb = new StringBuffer();
    	sb.append("http://").append(request.host()).append(request.path());
        return new StringBuffer(sb);
    }

    @Override
    public String getRequestedSessionId() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getServletPath() {
        String path = request.path();
        return path.substring(0, path.length() - pathInfo.length());
    }

    @Override
    public HttpSession getSession() {
        return httpSession;
    }

    @Override
    public HttpSession getSession(boolean create) {
        if (httpSession != null) {
            return httpSession;
        }

        if (create) {
            return null;
        } else {
            return null;
        }
    }

    @Override
    public Principal getUserPrincipal() {
        return null;
    }

    @Override
    public boolean isRequestedSessionIdFromCookie() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isRequestedSessionIdFromURL() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isRequestedSessionIdFromUrl() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isRequestedSessionIdValid() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isUserInRole(String role) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void login(String username, String password) throws ServletException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void logout() throws ServletException {
        throw new UnsupportedOperationException();
    }

	@Override
	public long getContentLengthLong() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String changeSessionId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends HttpUpgradeHandler> T upgrade(Class<T> arg0) throws IOException, ServletException {
		// TODO Auto-generated method stub
		return null;
	}


}
