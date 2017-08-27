package rest_response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

import resources.UserDto;

public class UsersResponse {
	@JsonView(Views.Summary.class)
	private final int totalElements;
	@JsonView(Views.Summary.class)
	private final int totalPages;
	@JsonView(Views.Summary.class)
	private final List<UserDto> content;

	public UsersResponse(int totalElements, int totalPages, List<UserDto> list) {
		this.totalElements = totalElements;
		this.totalPages = totalPages;
		this.content = list;
	}

	public int getTotalElements() {
		return totalElements;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public List<UserDto> getContent() {
		return content;
	}

}
