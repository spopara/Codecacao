package resources;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.fasterxml.jackson.annotation.JsonView;

import rest_response.Views;

public class UserDto {
	@JsonView(Views.Basic.class)
	private long id;
	@JsonView(Views.Extended.class)
	private String title;
	@JsonView(Views.Basic.class)
	private String firstName;
	@JsonView(Views.Basic.class)
	private String lastName;
	@JsonView(Views.Basic.class)
	private String email;
	@JsonView(Views.Extended.class)
	private String phoneCode;
	@JsonView(Views.Extended.class)
	private String phoneNumber;
	@JsonView(Views.Extended.class)
	private String password;
	private boolean tos;
	@JsonView(Views.Extended.class)
	private List<RoleDto> roles;
	@JsonView(Views.Summary.class)
	private boolean active;
	@JsonView(Views.Extended.class)
	private AvatarDto avatar;
	@JsonView(Views.Extended.class)
	private List<CompanyDto> companies;
	@JsonView(Views.Extended.class)
	private AddressDto billingAddress;

	private static AtomicLong counter = new AtomicLong();

	public UserDto() {
		this.id = counter.incrementAndGet();
	}

	public UserDto(String title, String firstName, String lastName, String email, String phoneCode, String phoneNumber,
			String password, boolean tos, List<RoleDto> roles, AvatarDto avatar, List<CompanyDto> companies,
			AddressDto billingAddress) {
		this.id = counter.incrementAndGet();
		this.title = title;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneCode = phoneCode;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.tos = tos;
		this.roles = roles;
		this.active = true;
		this.avatar = avatar;
		this.companies = companies;
		this.billingAddress = billingAddress;
	}

	public long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public boolean isActive() {
		return active;
	}

	public String getTitle() {
		return title;
	}

	public String getPhoneCode() {
		return phoneCode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public boolean isTos() {
		return tos;
	}

	public List<RoleDto> getRoles() {
		return roles;
	}

}
