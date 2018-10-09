package org.axle.example.request;

import org.axle.core.annotation.Parameter;
import org.axle.core.request.Request;

public class GetExampleRequest extends Request{
	@Parameter(required=true,fromUri=true,regular="^[0-9]*[1-9][0-9]*$")
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
