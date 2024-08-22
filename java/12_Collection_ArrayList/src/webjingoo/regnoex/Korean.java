package webjingoo.regnoex;

import java.util.Objects;

public class Korean {
	private String regNo;
	private String name;

	Korean(String regNo, String name) {
		super(); // 모든 생성자는 부모 생성자를 호출, 모든 객체는 Object 객체의 자손
		this.regNo = regNo;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegNo() {
		return regNo;
	}

	@Override
	public boolean equals(Object obj) {
		Korean temp = (Korean) obj;

		if (this.regNo.equals(temp.regNo) && this.name.equals(temp.name)) {
			return true;
		}
		;
		return false;
	}
	
	@Override
	public int hashCode() {
		return (this.regNo + this.name).hashCode();
		//return Objects.hash(regNo, name);
	}
	

	@Override
	public String toString() {
		return "Korean [regNo=" + regNo + ", name=" + name + "]";
	}

}
