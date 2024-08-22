package teacher.treesetex2;

import java.util.Set;
import java.util.TreeSet;

public class StuClass {
	private int classNo;
	private Set<Student> stuSet;
	
	public StuClass(int classNo) {
		super();
		this.classNo = classNo;
		this.stuSet = new TreeSet<Student>();
	}

	public Set<Student> getStuSet() {
		return stuSet;
	}

	public void setStuSet(Set<Student> stuSet) {
		this.stuSet = stuSet;
	}

	public int getClassNo() {
		return classNo;
	}
	
	// 학생을 TreeSet에 추가
	public void addStudent(Student s) {
		boolean existStudent = false;
		for(Student student : stuSet) {
			if(student.getStuNo().equals(s.getStuNo())) {
				existStudent = true;
				break;
			}
		}
		
		if(!existStudent) {
			this.stuSet.add(s);
		} else {
			System.out.println("학번: " + s.getStuNo() + "인 학생이 이미 존재합니다.");
		}
	}
	
	public void outputEntrieStudent() {
		for(Student student : stuSet) {
			System.out.println(student);
		}
	}
}
