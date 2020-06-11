package net.codejava;
//Imports
import java.text.*;
import java.util.*;
/**
 * 
 * @author Kalin
 *
 */
public class Menu {
	//Projects Array
	public static ArrayList<Project> projects = ProjectsFile.getProjects();
	//Person Variable
	private static String name;
	private static String phoneNum;
	private static String emailAddress;
	private static String physicalAddress;
	//Methods
	
	/** Project Options Menu */
	public static void printMenu() {
		System.out.println("\n"
				+ "1 - Print Project\n"
				+ "2 - Change the Projects Due Date\n" 
				+ "3 - Change the Total Amount Paid to Date\n"
				+ "4 - Update the Contractors Details\n"
				+ "5 - Finalize the Project\n"
				+ "0 - Exit\n");
	}
	/** Main Menu */
	public static void printMainMenu() {
		System.out.println("\n"
				+ "1 - Pick a Project\n"
				+ "2 - Create a New Project\n"
				+ "3 - All Projects\n"
				+ "4 - All Unfinished Projects\n"
				+ "5 - All Overdue Projects\n"
				+ "0 - Exit\n");
	}
	public static int selectedOption() {
		Scanner input = new Scanner(System.in);
		
		String choiceStr = input.nextLine();
		int choice = 1;
		try {
			choice = Integer.parseInt(choiceStr);
			if(choice < 0) {
				throw new Exception();
			}
			return choice;
		} catch (Exception e) {
			System.out.println("Invalid Option Selected!");
			
			return selectedOption();
		}
	}
	//Display All Project by their Names
	public static void printAllProjectsNames() {
		for(int i =0;i < projects.size();i++) {
			System.out.println((i+1) + " " + projects.get(i).projectName);
		}
	}
	//Display All Projects
	public static void printAllProjects() {
		for(int i = 0;i < projects.size();i++) {
			System.out.println("Projects " + (i+1));
			projects.get(i).printProject();
		}
	}
	//Displays Selected Project
	public static void printSelectedProject(int i) {
		projects.get(i).printProject();
	}
	//Display Unfinished Projects
	public static void printUnfinishedProjects() {
		for(int i = 0;i < projects.size();i++) {
			if(projects.get(i).projectFinalised.equals("No")) {
				projects.get(i).printProject();
			}
		}
	}
	//Display Overdue Projects
	public static void printOverdueProjects() {
		for(int i = 0;i < projects.size();i++) {
			try {
				Date date = new SimpleDateFormat("yyyy-MM-dd").parse(projects.get(i).deadline);
				Date now = new Date();
				if(projects.get(i).projectFinalised.equals("No") && now.compareTo(date) > 0) {
					projects.get(i).printProject();
				}
			} catch (Exception e) {
				System.out.println("\nProject " + (i + 1) + " Invalid Date Format!");
			}
		}
	}
	/**
	 * 
	 * @return New Project
	 */
	public static Project createProject() {
		//Project Variables
		String projectName = "";
		String projectNum = "";
		String buildType = "";
		String buildAddress = "";
		String ERFNum = "";
		String deadline = "";
		double totalFee = 0.0;
		double amountPaidDate = 0.0;
		String projectFinalised = "No";
		
		Scanner input = new Scanner(System.in);
		
		do { //Project Name
			System.out.println("Project Name: ");
			projectName = input.nextLine();
			if(projectName.equals("")) {
				System.out.println("Please Re-enter!");
			}
		} while (projectName.equals(""));
		
		do { //Project Number
			System.out.println("Project Number: ");
			projectNum = input.nextLine();
			if(projectNum.equals("")) {
				System.out.println("Please Re-enter!");
			}
		} while (projectNum.equals(""));
		
		do { //Building Type
			System.out.println("Building Type: ");
			buildType = input.nextLine();
			if(buildType.equals("")) {
				System.out.println("Please Re-enter!");
			}
		} while (buildType.equals(""));
		
		do { //Building Address
			System.out.println("Project Address: ");
			buildAddress = input.nextLine();
			if(buildAddress.equals("")) {
				System.out.println("Please Re-enter!");
			}
		} while (buildAddress.equals(""));
		
		do { //ERF Number
			System.out.println("Project Name: ");
			ERFNum = input.nextLine();
			if(ERFNum.equals("")) {
				System.out.println("Please Re-enter!");
			}
		} while (ERFNum.equals(""));
		
		while(true) { //Deadline
			try {
				System.out.print("Project Deadline: ");
				String temp = input.nextLine();
				Date date = new SimpleDateFormat("yyyy-MM-dd").parse(temp);
				deadline = new SimpleDateFormat("yyyy-MM-dd").format(date);
				break;
			} catch (Exception e) {
				System.out.println("Invalid Date Format! Example. (2020-05-07),Please Re-enter: ");
			}
		}
		while(true) { //Total Fee
			try {
				System.out.print("Projects Total Fee: ");
				String temp = input.nextLine();
				totalFee = Double.valueOf(temp);
				break;
			} catch (Exception e) {
				System.out.println("Invalid Number! Please Re-enter: ");
			}
		}
		while(true) { //Amount Paid to Date
			try {
				System.out.print("Project Amount Paid to Date: ");
				String temp = input.nextLine();
				amountPaidDate = Double.valueOf(temp);
				break;
			} catch (Exception e){
				System.out.println("Invalid Number! Please Re-enter: ");
			}
		}
		//Person Objects
		Person architect = createPerson("Architect");
		Person customer = createPerson("Customer");
		Person contractor = createPerson("Contractor");
		
		if(projectName.equalsIgnoreCase("")) {
			projectName = customer.name;
		}
		
		//Project Object
		Project project = new Project(projectName,
									  projectNum,
									  buildType,
									  buildAddress,
									  ERFNum,
									  amountPaidDate,
									  totalFee,
									  deadline,
									  architect,
									  customer,
									  contractor,
									  projectFinalised);
		
		System.out.println("New Project Successfully Created! ");
		return project;
	}
	/**
	 * 
	 * @param ID
	 * @return Person Object
	 */
	public static Person createPerson(String ID) {
		Scanner input = new Scanner(System.in);
		
		//Getting info, checking it and using to Create a Person Object
		System.out.println("\nThe " + ID + "s" + " Details:");
		
		do {
			System.out.print("Name: ");
			name = input.nextLine();
			if(name.equals("")) {
				System.out.println("Invalid Input! Please Re-enter: ");
			}
		} while (name.equals(""));
		
		do {
			System.out.print("Contact Number: ");
			phoneNum = input.nextLine();
			if(phoneNum.equals("")) {
				System.out.println("Invalid Input! Please Re-enter: ");
			}
		} while (phoneNum.equals(""));
		
		do {
			System.out.print("Email Address: ");
			emailAddress = input.nextLine();
			if(emailAddress.equals("")) {
				System.out.println("Invalid Input! Please Re-enter: ");
			}
		} while (emailAddress.equals(""));
		
		do {
			System.out.print("Physical Address: ");
			physicalAddress = input.nextLine();
			if(physicalAddress.equals("")) {
				System.out.println("Invalid Input! Please Re-enter: ");
			}
		} while (physicalAddress.equals(""));
		
		//Creating Person Object
		Person person = new Person(ID,name,phoneNum,emailAddress,physicalAddress);
		
		return person;
	}
}
