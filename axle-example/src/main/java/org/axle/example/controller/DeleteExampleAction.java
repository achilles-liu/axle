/**
 * 
 */
package org.axle.example.controller;

import org.axle.core.AbstractApiAction;
import org.axle.core.annotation.ApiAction;
import org.axle.core.annotation.IoParam;
import org.axle.example.cache.ExampleCache;
import org.axle.example.model.Example;
import org.axle.example.request.DeleteExampleRequest;
import org.axle.example.response.DeleteExampleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * axle-example
 * <description></description>
 * @author Johnny Liu
 * @date 2018-10-02
 */
@ApiAction(url="/example/delete/{id}",method=RequestMethod.DELETE,
		io=@IoParam(in=DeleteExampleRequest.class,out=DeleteExampleResponse.class))
public class DeleteExampleAction extends AbstractApiAction<DeleteExampleRequest, DeleteExampleResponse>{
	private @Autowired ExampleCache cache;

	@Override
	protected DeleteExampleResponse command(DeleteExampleRequest request) {
		Example example = this.cache.deleteById(request.getId());
		DeleteExampleResponse resp = new DeleteExampleResponse();
		resp.setId(example.getId());
		resp.setNickname(example.getNickname());
		return resp;
	}

}
