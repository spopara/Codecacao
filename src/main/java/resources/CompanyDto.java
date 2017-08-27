package resources;

public class CompanyDto {
	private long id;
	private String name;
	private long role_id;

	public CompanyDto() {
	}

	public CompanyDto(long id, String name, long role_id) {
		super();
		this.id = id;
		this.name = name;
		this.role_id = role_id;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public long getRole_id() {
		return role_id;
	}

}
