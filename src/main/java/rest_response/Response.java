package rest_response;

import com.fasterxml.jackson.annotation.JsonView;

public class Response {
	@JsonView(Views.Basic.class)
	private final Object data;

	public Response(Object data) {
		this.data = data;
	}

	public Object getData() {
		return data;
	}

}
