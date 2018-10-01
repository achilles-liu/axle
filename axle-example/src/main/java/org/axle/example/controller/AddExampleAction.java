package org.axle.example.controller;

import org.axle.core.AbstractApiAction;
import org.axle.core.annotation.ApiAction;
import org.axle.core.annotation.IoParam;
import org.axle.example.cache.ExampleCache;
import org.axle.example.model.Example;
import org.axle.example.request.AddExampleRequest;
import org.axle.example.response.AddExampleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;

@ApiAction(url="/example/add",method=RequestMethod.POST,
	io=@IoParam(in=AddExampleRequest.class,out=AddExampleResponse.class)
)
public class AddExampleAction extends AbstractApiAction<AddExampleRequest, AddExampleResponse>{
	private @Autowired ExampleCache cache;
	protected AddExampleResponse command(AddExampleRequest request) {
		Example newExample = new Example();
		newExample.setId(cache.generateId());
		newExample.setUsername(request.getUsername());
		newExample.setNickname(request.getNickname());
		newExample.setAge(request.getAge());
		cache.insert(newExample);
		AddExampleResponse resp = new AddExampleResponse();
		resp.setId(newExample.getId());
		return resp;
	}

}
