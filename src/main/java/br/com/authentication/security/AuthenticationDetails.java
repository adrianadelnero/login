package br.com.authentication.security;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

public class AuthenticationDetails extends WebAuthenticationDetails  {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -6460246283659159109L;
	private final String from;

	public AuthenticationDetails(HttpServletRequest request) {
        super(request);
        from = request.getHeader("from");
	}

	   public String getFrom() {
	        return from;
	    }
	    
	    @Override
	    public String toString() {
	        return super.toString() + " header from = " + from;
	    }
	    
	    @Override
	    public boolean equals(Object obj) {
	        if (obj == null) {
	            return false;
	        } else if (obj == this) {
	            return true;
	        } else if (obj instanceof AuthenticationDetails) {
	        	AuthenticationDetails other = (AuthenticationDetails) obj;
	            return new EqualsBuilder()
	                    .appendSuper(super.equals(obj))
	                    .append(this.from, other.from)
	                    .isEquals();
	        } else {
	            return false;
	        }
	    }

	    @Override
	    public int hashCode() {
	        return new HashCodeBuilder(5, 7)
	                .appendSuper(super.hashCode())
	                .append(from)
	                .toHashCode();
	    }
}
