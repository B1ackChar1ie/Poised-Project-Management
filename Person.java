package net.codejava;
/**
 * 
 * @author Kalin
 *
 */
public class Person {
	//Variables|Attributes
	String name;
	String phoneNum;
	String emailAddress;
	String physicalAddress;
	String ID;
	
	//Constructors
	public Person(String ID,String name,String phoneNum,String emailAddress,String physicalAddress) {
		this.ID = ID;
		this.name = name;
		this.phoneNum = phoneNum;
		this.emailAddress = emailAddress;
		this.physicalAddress = physicalAddress;
	}
	//Methods
	public void printPerson() {
		String personDetails = "\n" + ID;
		personDetails += "\nName: " + name;
		personDetails += "\nPhone Number: " + phoneNum;
		personDetails += "\nEmail Address: " + emailAddress;
		personDetails += "\nPhysical Address: " + physicalAddress;
		
		System.out.println(personDetails);
	}
}
