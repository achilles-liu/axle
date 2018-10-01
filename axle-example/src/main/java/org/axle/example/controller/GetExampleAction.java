package org.axle.example.controller;

import org.axle.core.AbstractApiAction;
import org.axle.core.annotation.ApiAction;
import org.axle.core.annotation.IoParam;
import org.axle.example.cache.ExampleCache;
import org.axle.example.model.Example;
import org.axle.example.request.GetExampleRequest;
import org.axle.example.response.GetExampleResponse;
import org.springframework.beans.factory.annotation.Autowired;

@ApiAction(url="/example/{id}",io=@IoParam(in=GetExampleRequest.class,out=GetExampleResponse.class))
public class GetExampleAction extends AbstractApiAction<GetExampleRequest, GetExampleResponse>{
	
	private @Autowired ExampleCache cache;

	@Override
	protected GetExampleResponse command(GetExampleRequest request) {
		System.out.println("example's id  => "+request.getId());
		Example entity = this.cache.getById(request.getId());
		GetExampleResponse resp = new GetExampleResponse();
		resp.setUsername(entity.getUsername());
		resp.setAge(entity.getAge());
		return resp;
	}

}
