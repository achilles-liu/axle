/**
 * 
 */
package org.axle.example.controller;

import org.axle.core.AbstractApiAction;
import org.axle.core.annotation.ApiAction;
import org.axle.core.annotation.IoParam;
import org.axle.example.cache.ExampleCache;
import org.axle.example.model.Example;
import org.axle.example.request.UpdateExampleRequest;
import org.axle.example.response.UpdateExampleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * axle-example
 * <description></description>
 * @author Johnny Liu
 * @date 2018-10-02
 */
@ApiAction(url="/example/update/{id}",method=RequestMethod.PUT,
	io=@IoParam(in=UpdateExampleRequest.class,out=UpdateExampleResponse.class))
public class UpdateExampleAction extends AbstractApiAction<UpdateExampleRequest, UpdateExampleResponse>{
	
	private @Autowired ExampleCache cache;

	@Override
	protected UpdateExampleResponse command(UpdateExampleRequest request) {
		int id = request.getId();
		Example example = this.cache.getById(id);
		example.setUsername(request.getUsername());
		example.setNickname(request.getNickname());
		example.setAge(request.getAge());
		this.cache.update(example);
		UpdateExampleResponse resp = new UpdateExampleResponse();
		resp.setId(id);
		resp.setUsername(example.getUsername());
		return resp;
	}

}
