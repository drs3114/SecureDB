/**
 * 
 */
package http;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collection;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import play.mvc.Http;
import play.mvc.Http.Response;

/**
 * @author DeepakShankar
 *
 */
public class SecureDBHttpResponse implements HttpServletResponse {

	private Response response;
    private String characterEncoding;
    private int status;
    private ByteArrayOutputStream bos = new ByteArrayOutputStream();
    private PrintWriter pw = new PrintWriter(bos);
    
    public SecureDBHttpResponse(Response response) {
        this.response = response;
    }
    
    public ByteArrayOutputStream getBuffer(){
        return bos;
    }

    @Override
    public void flushBuffer() throws IOException {
        pw.flush();
    }

    @Override
    public int getBufferSize() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getCharacterEncoding() {
        if (characterEncoding != null) {
            return characterEncoding;
        }
        
        return getCharsetFromContentType(getContentType());
    }
    

    @Override
    public String getContentType() {
        return getHeader(Http.HeaderNames.CONTENT_TYPE);
    }

    @Override
    public Locale getLocale() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return new ServletOutputStream() {

            @Override
            public void write(int b) throws IOException {
                bos.write(b);
            }

			@Override
			public boolean isReady() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public void setWriteListener(WriteListener arg0) {
				// TODO Auto-generated method stub
				
			}
            
        };
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        return pw;
    }

    @Override
    public boolean isCommitted() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void reset() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void resetBuffer() {
        pw.flush();
        bos.reset();
    }

    @Override
    public void setBufferSize(int arg0) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setCharacterEncoding(String characterEncoding) {
        this.characterEncoding = characterEncoding;
    }

    @Override
    public void setContentLength(int length) {
        this.response.setHeader(Http.HeaderNames.CONTENT_LENGTH, Integer.toString(length));
    }

    @Override
    public void setContentType(String type) {
        this.response.setContentType(type);

    }

    @Override
    public void setLocale(Locale locale) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void addCookie(Cookie cookie) {
    	
    	Http.Cookie cky = new Http.Cookie(cookie.getName(), cookie.getValue(), cookie.getMaxAge(), cookie.getPath(), cookie.getDomain(), cookie.getSecure(), true);
    	response.setCookie(cky);
        //throw new UnsupportedOperationException();
    }

    @Override
    public void addDateHeader(String name, long date) {
        addHeader(name, String.valueOf(date));
    }

    @Override
    public void addHeader(String name, String value) {
        String head = this.response.getHeaders().get(name);
        String newValue;
        if(head == null || head == "") {
            newValue = value;
        } else {
            newValue = head + "," + value;
        }
        this.response.setHeader(name, newValue);
    }

    @Override
    public void addIntHeader(String name, int value) {
           addHeader(name, value +"");
    }

    @Override
    public boolean containsHeader(String name) {
        return this.response.getHeaders().containsKey(name);
    }

    @Override
    public String encodeRedirectURL(String url) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String encodeRedirectUrl(String url) {
        return encodeRedirectURL(url);
    }

    @Override
    public String encodeURL(String arg0) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String encodeUrl(String arg0) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getHeader(String headerName) {
        for(String h: response.getHeaders().keySet()) {
            if(headerName.toLowerCase().equals(h.toLowerCase())) {
                return  response.getHeaders().get(h);
            }
        }
        
        return null;
    }

    @Override
    public Collection<String> getHeaderNames() {
        return this.response.getHeaders().keySet();
    }

    @Override
    public Collection<String> getHeaders(String name) {
        return Arrays.<String>asList(this.response.getHeaders().get(name).split(","));
    }

    @Override
    public int getStatus() {
        return this.status;
    }

    @Override
    public void sendError(int statusCode) throws IOException {
        // FIXME response should be returned at this time.
        setStatus(statusCode);
    }

    @Override
    public void sendError(int statusCode, String msg) throws IOException {
        // FIXME response should be returned at this time.
        setStatus(statusCode);
        resetBuffer();
        if (msg != null) {
            pw.write(msg);
            response.setHeader(Http.HeaderNames.CONTENT_TYPE, "text/plain");
        } else {
            response.getHeaders().remove(Http.HeaderNames.CONTENT_TYPE);
        }
    }

    @Override
    public void sendRedirect(String location) throws IOException {
        // FIXME response should be returned at this time.
        response.setHeader(Http.HeaderNames.LOCATION, location);
        setStatus(Http.Status.FOUND);
    }

    @Override
    public void setDateHeader(String name, long date) {
        this.response.setHeader(name, String.valueOf(date));
    }

    @Override
    public void setHeader(String name, String value) {
        response.setHeader(name, value);
    }

    @Override
    public void setIntHeader(String name, int value) {
        response.setHeader(name, Integer.toString(value));
    }

    @Override
    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public void setStatus(int status, String msg) {
        throw new UnsupportedOperationException();
    }
    
    /**
     * Parse the character encoding from the specified content type header.
     * If the content type is null, or there is no explicit character encoding,
     * <code>null</code> is returned.
     *
     * @param contentType a content type header
     */
    private static String getCharsetFromContentType(String contentType) {

        if (contentType == null) {
            return (null);
        }
        int start = contentType.indexOf("charset=");
        if (start < 0) {
            return (null);
        }
        String encoding = contentType.substring(start + 8);
        int end = encoding.indexOf(';');
        if (end >= 0) {
            encoding = encoding.substring(0, end);
        }
        encoding = encoding.trim();
        if ((encoding.length() > 2) && (encoding.startsWith("\""))
            && (encoding.endsWith("\""))) {
            encoding = encoding.substring(1, encoding.length() - 1);
        }
        return (encoding.trim());

    }

	@Override
	public void setContentLengthLong(long arg0) {
		// TODO Auto-generated method stub
		
	}
    
}
