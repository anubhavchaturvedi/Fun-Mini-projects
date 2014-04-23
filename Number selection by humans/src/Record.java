
public class Record {
	private int age;
	private char gender;
	private String educationLevel;
	private int[] numbers;
	
	public Record(int age, char gender, String educationlevel, int[] numbers)
	{
		this.age = age;
		this.gender = gender;
		this.educationLevel = educationlevel;
		this.numbers = numbers;
	}

	public int getAge() {
		return age;
	}

	public char getGender() {
		return gender;
	}

	public String getEducationLevel() {
		return educationLevel;
	}

	public int[] getNumbers() {
		return numbers;
	}
}
