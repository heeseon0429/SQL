package c_info2;

public class InfoVO {

	private String name;
	private String id;
	private String tel;
	private String gender;
	private int age;
	private String home;
	
	
	
	@Override
	public String toString() {
		return "InfoVO [name=" + name + ", id=" + id + ", tel=" + tel + ", gender=" + gender + ", age=" + age
				+ ", home=" + home + "] \n";
	}
	

	// constructor 생성자 함수
	public InfoVO() {}
	
	public InfoVO(String name, String id, String tel, String gender, int age, String home) {
		super();
		this.name = name;
		this.id = id;
		this.tel = tel;
		this.gender = gender;
		this.age = age;
		this.home = home;
	}
	
	// setter, getter
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getHome() {
		return home;
	}
	public void setHome(String home) {
		this.home = home;
	}
	
	
}
