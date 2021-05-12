package encapsulation;
import java.util.Calendar;
import java.util.Date;

public class Person {
		
	private String name;
	private String email;
	private char gender;
	private Date birthdate;	
	
	public Person() {
	}
	
	public void setName(String given_name, String surname){
		if ((given_name.length()>=2) && (surname.length()>=2)) {
			this.name = given_name+" "+surname;
		}
		else {
			throw new IllegalArgumentException("Både fornavn og etternavn må inneholde minst to bokstaver.");
		}
		if ((!(given_name.matches("[a-zA-Z]*"))) || (!(surname.matches("[a-zA-Z]*")))){
			throw new IllegalArgumentException("Både fornavn og etternavn kan kun inneholde bokstaver.");
		}
	}
	
	public void setEmail(String email) {
		String name = getName();
		String[] list = name.split("\\s+");
		String landskoder = "(ad|ae|af|ag|ai|al|am|ao|aq|ar|as|at|au|aw|ax|az|ba|bb|bd|be|bf|bg|bh|bi|bj|bl|bm|bn|bo|bq|br|bs|bt|bv|bw|by|bz|ca|cc|cd|cf|cg|ch|ci|ck|cl|cm|cn|co|cr|cu|cv|cw|cx|cy|cz|de|dj|dk|dm|do|dz|ec|ee|eg|eh|er|es|et|fi|fj|fk|fm|fo|fr|ga|gb|gd|ge|gf|gg|gh|gi|gl|gm|gn|gp|gq|gr|gs|gt|gu|gw|gy|hk|hm|hn|hr|ht|hu|id|ie|il|im|in|io|iq|ir|is|it|je|jm|jo|jp|ke|kg|kh|ki|km|kn|kp|kr|kw|ky|kz|la|lb|lc|li|lk|lr|ls|lt|lu|lv|ly|ma|mc|md|me|mf|mg|mh|mk|ml|mm|mn|mo|mp|mq|mr|ms|mt|mu|mv|mw|mx|my|mz|na|nc|ne|nf|ng|ni|nl|no|np|nr|nu|nz|om|pa|pe|pf|pg|ph|pk|pl|pm|pn|pr|ps|pt|pw|py|qa|re|ro|rs|ru|rw|sa|sb|sc|sd|se|sg|sh|si|sj|sk|sl|sm|sn|so|sr|ss|st|sv|sx|sy|sz|tc|td|tf|tg|th|tj|tk|tl|tm|tn|to|tr|tt|tv|tw|tz|ua|ug|um|us|uy|uz|va|vc|ve|vg|vi|vn|vu|wf|ws|ye|yt|za|zm|zw)";
		if (email.matches(list[0].toLowerCase()+"\\."+list[1].toLowerCase()+"@[A-Za-z0-9]{1,}\\."+landskoder)) {
			this.email = email;
		}
		else {
			throw new IllegalArgumentException(email + " er ikke gyldig.");
		}
		if (email.length()!=0) {
			this.email = email;
		}
		else {
			throw new IllegalArgumentException(email + " er for kort.");
		}
	}
	
	public void setGender(char gender){
		if ((gender == 'F') || (gender == 'M') || (gender == '\0')) {
			this.gender = gender;
		}
		else {
			throw new IllegalArgumentException("Kjønn må angis som 'M', 'F', eller '\0'.");
		}
	}
	
	public void setBirthdate(Date birthdate) {
		Date today = Calendar.getInstance().getTime();
		if (today.after(birthdate)) {
			this.birthdate = birthdate;
		}
		else {
			throw new IllegalArgumentException("Datoen er i framtiden.");
		}
	}
	
	public String getName() {
		return name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public char getGender() {
		return gender;
	}
	
	public Date getBirthday() {
		return birthdate;
	}
	
	public static void main (String[] args) {
		Person person = new Person();
		person.setName("Karoline", "Bernklev");
		person.setEmail("karoline.bernklev@gmail.no");
		person.setGender('F');
		System.out.println(person.getName());
		System.out.println(person.getEmail());
		System.out.println(person.getGender());
	}
}
