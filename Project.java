package net.codejava;
//Import
import java.util.*;
import java.io.*;
import java.text.*;
import java.time.*;
/** 
 * 
 * @author Kalin
 *
 */
public class Project {
	//Variable|Attributes
	String projectNum;
	String projectName;
	String buildType;
	String buildAddress;
	String ERFNum;
	double totalFee;
	double amountPaidDate;
	String deadline; 
	//People
	Person architect;
	Person contractor;
	Person customer;
	
	String projectFinalised;
	//Constructor
	public Project(String projectNum,String projectName,String buildType,String buildAddress,String ERFNum,double totalFee,double amountPaidDate,
				   String deadline,Person architect,Person customer,Person contractor,String projectFinalised) {
		
		this.projectNum = projectNum;
		this.projectName = projectName;
		this.buildType = buildType;
		this.buildAddress = buildAddress;
		this.ERFNum = ERFNum;
		this.totalFee = totalFee;
		this.amountPaidDate = amountPaidDate;
		this.deadline = deadline;
		this.architect = architect;
		this.contractor = contractor;
		this.customer = customer;
		this.projectFinalised = projectFinalised;
	}
	//Methods
	Scanner input = new Scanner(System.in);
	//Print out Project Details Method
	public void printProject() {
		String projectDetails = "------------------------------------------------------------\n";
		projectDetails += "Project Details:";
		projectDetails += "\n------------------------------------------------------------\n";
		projectDetails += "\nProject Name: " + projectName;
		projectDetails += "Project Number: " + projectNum;
		projectDetails += "\nBuilding Type: " + buildType;
		projectDetails += "\nSite Address: " + buildAddress;
		projectDetails += "\nERF Number: " + ERFNum;
		projectDetails += "\nDeadline of Project: " + deadline;
		projectDetails += "\nTotal Fee: R" + totalFee;
		projectDetails += "\nTotal amount paid to date: R" + amountPaidDate;
		projectDetails += "\nProject Finalized: " + projectFinalised;
		projectDetails += "\n------------------------------------------------------------\n";
		
		//Output
		System.out.println(projectDetails);
		
		architect.printPerson();
		contractor.printPerson();
		customer.printPerson();
	}
	
	//Change the Due Date Method:
	public void changeDate() {
		while (true) {
			try {
				System.out.println("New Deadline:");
				String temp = input.nextLine();
				Date newDate = new SimpleDateFormat("YYYY-MM-DD").parse(temp);
				this.deadline = new SimpleDateFormat("yyy-MM-dd").format(newDate);
				break;
				
			} catch (Exception e) {
				System.out.println("Invalid Date Format! Example: (2020-05-07)");
			}
		}
		System.out.println("Deadline Updated!");
	}
	//Change the Total Paid to Date:
	public void changeAmountPaid() {
		System.out.println("How much has been paid to date? ");
		input.nextLine();
		double newAmount = input.nextDouble();
		this.amountPaidDate = newAmount;
		System.out.println("Amount Paid to Date Updated!");
	}
	//Update the Contractors Details
	public void updateContractor() {
		//Verify if the Phone Number needs to be Updated.
		System.out.println("Update the Contractors Phone Number? Yes/ No");
		String choice = input.nextLine();
		if(choice.equalsIgnoreCase("Yes")) {
			System.out.println("Phone Number: ");
			String newPhoneNum = input.next();
			this.contractor.phoneNum = newPhoneNum;
			System.out.println("Phone Number Updated!");
		}
		//Verify if the Email Address needs to be Updated.
		System.out.println("Update the Contractors Email Address? Yes/ No");
		choice = input.next();
		if(choice.equalsIgnoreCase("Yes")) {
			System.out.println("Email Address: ");
			String newEmail = input.next();
			this.contractor.emailAddress = newEmail;
			System.out.println("Email Address Updated!");
		}
	}
		//Finalize Project by Creating invoice
		public String[] projectFinalized(double total, double totalPaid) {
			String[] invoice = new String[5] ;
			double amountToPay = total - totalPaid;
			//Creating  Invoice:
			if(amountToPay > 0) {
				invoice[0] = "Customer Phone Number: " + this.customer.phoneNum;
				invoice[1] = "Customer Email Address: " + this.customer.emailAddress;
				invoice[2] = "Amount owed: " + amountToPay;
				invoice[3] = "Finalised";
				invoice[4] = "Date Finalised: " + LocalDateTime.now();
				this.projectFinalised = "Yes";
			}
			return invoice;
		}
}

