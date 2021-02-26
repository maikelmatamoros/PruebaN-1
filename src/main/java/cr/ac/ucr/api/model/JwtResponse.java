package cr.ac.ucr.api.model;

import java.io.Serializable;

public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;
	private final int client_Id;

	public JwtResponse(String jwttoken,int id) {
		this.client_Id = id;
		this.jwttoken = jwttoken;
	}

	public String getToken() {
		return this.jwttoken;
	}

	public int getId() {
		return this.client_Id;
	}
}