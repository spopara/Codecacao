package resources;

public class AvatarDto {
	private long id;
	private String url;

	public AvatarDto() {

	}

	public AvatarDto(long id, String url) {
		super();
		this.id = id;
		this.url = url;
	}

	public long getId() {
		return id;
	}

	public String getUrl() {
		return url;
	}

}
