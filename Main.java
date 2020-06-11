package net.codejava;
//Import
import java.util.*;
/**
 * 
 * @author Kalin
 *
 */
public class Main {
	public static void main(String[] args) {
		//Project Variables
		int choice = 0;
		Scanner input = new Scanner(System.in);
		while(true) {
			//Variables
			int projectSelection = 0;
			boolean projectSelected = false;
			//Main Menu
			Menu.printMainMenu();
			//Handles Menu Selection
			choice = Menu.selectedOption();
			
			//Picking a Project
			if(choice == 1) {
				try {
						System.out.println("Select Project Number");
						Menu.printAllProjectsNames();
						projectSelection = Menu.selectedOption();
						if(projectSelection < Menu.projects.size() && projectSelection >= 0) {
								projectSelected = true;
								Menu.printMenu();
						} else {
								System.out.print("Project Number doesn't match any Projects!\n");
						}
				} catch (Exception e) {
						System.out.println("No Projects Exist Yet!");
				}
			}
			//Creating a Project
			if(choice == 2) {
				//Creating Project Object
				Project project = Menu.createProject();
				Menu.projects.add(project);
				//Updating Projects File
				ProjectsFile.writeProjectToFile();
			}
			//Display All Projects
			if(choice == 3) {
				try {
					Menu.printAllProjects();
				} catch (Exception e) {
					System.out.println("No Projects Exist Yet!");
				}
			}
			//Display Projects that are Unfinished
			if(choice == 4) {
				try {
					Menu.printUnfinishedProjects();
				} catch (Exception e) {
					System.out.println("No Projects Exist Yet!");
				}
			}
			//Display Overdue Projects
			if(choice == 5) {
				try {
					Menu.printOverdueProjects();
				} catch (Exception e) {
					System.out.println("No Projects Exist Yet!");
				}
			}
			//Exit Program
			if(choice == 0) {
				input.close();
				break;
			}
			while(projectSelected) {
				choice = Menu.selectedOption();
				if(choice == 1) {
					try {
						Menu.printSelectedProject(projectSelection);
					} catch (Exception e) {
						System.out.println("No Projects Exist Yet!");
					}
				}
				//Changes Projects Deadline
				if(choice == 2) {
					try {
						Menu.projects.get(projectSelection).changeDate();
						//Updating Project to Database
						ProjectsFile.writeProjectToFile();;
					} catch (Exception e) {
						System.out.println("No Projects Exist Yet!");
					}
				}
				//Change Total Paid to Date
				if(choice == 3) {
					try {
						Menu.projects.get(projectSelection).updateContractor();
						//Updating Project to Database
						ProjectsFile.writeProjectToFile();
					} catch (Exception e) {
						System.out.println("No Projects Exist Yet!");
					}
				}
				//Update Contractors Details
				if(choice == 4) {
					try {
						Menu.projects.get(projectSelection).updateContractor();
						//Updating Project to Database
						ProjectsFile.writeProjectToFile();
					} catch (Exception e) {
						System.out.println("No Projects Exist Yet!");
					}
				}
				//Finalize the Project
				if(choice == 5) {
					try {
						//Create Invoice
						String[] invoice = Menu.projects.get(projectSelection).projectFinalized(Menu.projects.get(projectSelection).totalFee,Menu.projects.get(projectSelection).amountPaidDate);
						//Update Finalized Status
						if(invoice[3] == "Finalised") {
							Menu.projects.get(projectSelection).projectFinalised = "Yes";
						}
						//Print Invoice
						for(int i = 0;i < 5;i++) {
							System.out.println(invoice[i]);
						}
						//Updating Project to Database
						ProjectsFile.writeProjectToFile();	
						} catch (Exception e) {
							e.printStackTrace();
							System.out.println("No Projects Exist Yet!");
						}
					}
				}
				//Exit
				if(choice == 0) {
					break;
				}
				Menu.printMenu();
			}
  }
}