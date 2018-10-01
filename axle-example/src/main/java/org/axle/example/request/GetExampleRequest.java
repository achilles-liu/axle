package org.axle.example.request;

import org.axle.core.request.Request;

public class GetExampleRequest extends Request{
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
