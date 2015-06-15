package Sponsors;

import java.io.Console;

public class Newinfo {
	String newCompany;
	String newContact;
	String newPhone;
	String newEmail;
	String newEmail2;
	String newSponsoring;
	String newTier;
	Integer newAmount;
	String newContactee;
	
	String memberName;
	String memberEmail;
	String memberPass;
	Integer admin;
	
	String teamEmail;
	String teamPass;

	public void makeNewInfo() {
		Console console = System.console();
		newCompany = console.readLine("Company name:");
		newContact = console.readLine("Company Contact:");
		newPhone = console.readLine("Phone:");
		newEmail = console.readLine("Email:");
		newEmail2 = console.readLine("Email 2:");
		newSponsoring = console.readLine("Sponsoring? (Y/N):");
		newTier = console.readLine("Tier:");
		newAmount = Integer.parseInt(console.readLine("Amount:"));
		newContactee = console.readLine("New contactee (first and last)");
	}
	
	public void makeNewTeamInfo(){
		Console console = System.console();
		memberName = console.readLine("First and last name:");
		memberEmail = console.readLine("Email:");
		memberPass = console.readLine("Temp password:");
		admin = Integer.parseInt(console.readLine("Admin? (0/1):"));
	}
	
	public void loginInfo() {
		Console console = System.console();
		teamEmail = console.readLine("Your Email:");
		teamPass = console.readLine("Your Password:");
	}
}
