package net.codejava;
//Imports
import java.util.*;
import java.io.*;
/**
 * 
 * @author Kalin
 *
 */
public class ProjectsFile {
	//Methods
	/** Open the File & Read the File
	 * 
	 * @return Scanner Object for Projects.txt File 
	 */
	public static Scanner openFile() {
		try {
			File f = new File("src/Projects.txt");
			Scanner fsc = new Scanner(f);
			
			return fsc;
		} catch (FileNotFoundException e) {
			return null;
		}
	}
	/** Close the File
	 * 
	 * @param sc Scanner of the File
	 */
	public static void closeFile(Scanner sc) {
		sc.close();
	}
	/** Reads Projects from Projects.txt File into a ArrayList
	 * 
	 * @return ArrayList of Project Objects
	 */
	public static ArrayList<Project> getProjects() {
		//Variables
		ArrayList<Project> projects = new ArrayList<Project>();
		String lines = "";
		
		//Reading file
		Scanner sc = openFile();
		
		if(sc == null) {
			System.out.println("No Projects Found!");
			ArrayList<Project> empty = new ArrayList<Project>();
			return empty;
		}
		
		//Get the Projects
		while(sc.hasNext()){
			lines += sc.nextLine();
		}
		
		//Array of Lines
		String[] linesArr = lines.split("#");
		
		try {
			//Populating Projects Array
			for(int i = 0;i < linesArr.length;i++) {
				//Splitting Lines into Array
				String[] projectInfo = linesArr[i].split("~");
				Project project = new Project(lines, lines, lines, lines, lines, i, i, lines, null, null, null, lines);
				//Project Values
				project.projectName = projectInfo[0];
				project.projectNum = projectInfo[1];
				project.buildType = projectInfo[2];
				project.buildAddress = projectInfo[3];
				project.ERFNum = projectInfo[4];
				project.deadline = projectInfo[5];
				
				//Checking for Valid Values
				try {
					project.totalFee = Double.valueOf(projectInfo[6]);
				} catch(Exception e) {
					System.out.println("Total Fee " + i + " is Invalid!");
					}
				try {
					project.amountPaidDate = Double.valueOf(projectInfo[7]);
				} catch(Exception e) {
					System.out.println("Total Paid to Date " + i + " is Invalid!");
					}
				
				//Assigning Project People 
				Person architect = new Person("Architect", projectInfo[8], projectInfo[9],projectInfo[10],projectInfo[11]);
				project.architect = architect;
				Person customer = new Person("Customer", projectInfo[12], projectInfo[13], projectInfo[14], projectInfo[15]);
				project.customer = customer;
				Person contractor = new Person("Contractor", projectInfo[16], projectInfo[17], projectInfo[18],projectInfo[19]);
				project.contractor = contractor;
				
				//Assigning Finalized Status
				project.projectFinalised = projectInfo[20];
				//Adding new project to list
				projects.add(project);
			}
		} catch (Exception e) {
			System.out.println("No Projects Found!");
			ArrayList<Project> empty = new ArrayList<Project>();
			return empty;
		}
		closeFile(sc);
		
		return projects;
	}
	
	/** Writes Project to Projects.txt file */
	public static void writeProjectToFile() {
		try {
		Formatter f = new Formatter("src/Projects.txt");
		//Writing Project to File:
		for(int i = 0; i < Menu.projects.size(); i++) {
			f.format(Menu.projects.get(i).projectName + "~");
			f.format(Menu.projects.get(i).projectNum + "~");
			f.format(Menu.projects.get(i).buildType + "~");
			f.format(Menu.projects.get(i).buildAddress + "~");
			f.format(Menu.projects.get(i).ERFNum + "~");
			f.format(Menu.projects.get(i).deadline + "~");
			f.format(Menu.projects.get(i).totalFee + "~");
			f.format(Menu.projects.get(i).amountPaidDate + "~");
			f.format(Menu.projects.get(i).architect.name + "~");
			f.format(Menu.projects.get(i).architect.phoneNum + "~");
			f.format(Menu.projects.get(i).architect.emailAddress + "~");
			f.format(Menu.projects.get(i).architect.physicalAddress + "~");
			f.format(Menu.projects.get(i).customer.name + "~");
			f.format(Menu.projects.get(i).customer.phoneNum + "~");
			f.format(Menu.projects.get(i).customer.emailAddress + "~");
			f.format(Menu.projects.get(i).customer.physicalAddress + "~");
			f.format(Menu.projects.get(i).contractor.name + "~");
			f.format(Menu.projects.get(i).contractor.phoneNum + "~");
			f.format(Menu.projects.get(i).contractor.emailAddress + "~");
			f.format(Menu.projects.get(i).contractor.physicalAddress + "~");
			f.format(Menu.projects.get(i).projectFinalised + "#");
			f.format("\n");
		}
		f.close();
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found!");
		}
	}
}