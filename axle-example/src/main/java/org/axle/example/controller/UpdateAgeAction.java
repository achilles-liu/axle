/**
 * 
 */
package org.axle.example.controller;

import org.axle.core.AbstractApiAction;
import org.axle.core.annotation.ApiAction;
import org.axle.core.annotation.IoParam;
import org.axle.example.cache.ExampleCache;
import org.axle.example.model.Example;
import org.axle.example.request.UpdateAgeRequest;
import org.axle.example.response.UpdateAgeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * axle-example
 * <description></description>
 * @author Johnny Liu
 * @date 2018-10-02
 */
@ApiAction(url="/example/update/age/{id}",method=RequestMethod.PATCH,
	io=@IoParam(in=UpdateAgeRequest.class,out=UpdateAgeResponse.class))
public class UpdateAgeAction extends AbstractApiAction<UpdateAgeRequest, UpdateAgeResponse>{
	private @Autowired ExampleCache cache;
	
	@Override
	protected UpdateAgeResponse command(UpdateAgeRequest request) {
		Example example = this.cache.getById(request.getId());
		example.setAge(request.getAge());
		this.cache.update(example);
		UpdateAgeResponse resp = new UpdateAgeResponse();
		resp.setAge(example.getAge());
		return resp;
	}

}
