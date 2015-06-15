package Sponsors;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import Sponsors.Newinfo;

public class MySQLAccess {
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	private Newinfo newinfo = new Newinfo();
	public String loginSuccess = "";
	
	public void logIn() throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager
			          .getConnection("jdbc:mysql://localhost/feedback?"
			              + "user=denimalpaca&password=1423qrwe");
			newinfo.loginInfo();
			statement = connect.createStatement();
			resultSet = statement.
					executeQuery("select email, pass from team");
		    String userEmail = resultSet.getString("email");
		    String userPass = resultSet.getString("pass");
		    if(userEmail == newinfo.teamEmail && userPass == newinfo.teamPass){
		    	this.loginSuccess = "t";
		    }else {
		    	this.loginSuccess = "f";
		    }
		} catch (Exception e) {
			throw e;
		} //finally {
		//	close();
		//}
		
	}

	public void updateSponsors() throws Exception {
	    try {
	      // This will load the MySQL driver, each DB has its own driver
	      //Class.forName("com.mysql.jdbc.Driver");
	      // Setup the connection with the DB
	      //connect = DriverManager
	      //    .getConnection("jdbc:mysql://localhost/feedback?"
	      //        + "user=denimalpaca&password=1423qrwe");

	      // Statements allow to issue SQL queries to the database
	      statement = connect.createStatement();
	      // Result set get the result of the SQL query
	      //resultSet = statement
	      //   .executeQuery("select * from sbhacks_sponsorship.sponsors");
	      //writeResultSet(resultSet);

	      newinfo.makeNewInfo();
	      
	      // PreparedStatements can use variables and are more efficient
	      preparedStatement = connect
	          .prepareStatement("insert into sbhacks_sponsorship.sponsors values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
	      // "company, contact, phone, email, email2, sponsoring, tier, amount, contactee"
	      preparedStatement.setString(0, newinfo.newCompany);
	      preparedStatement.setString(1, newinfo.newContact);
	      preparedStatement.setString(2, newinfo.newPhone);
	      preparedStatement.setString(3, newinfo.newEmail);
	      preparedStatement.setString(4, newinfo.newEmail2);
	      preparedStatement.setString(5, newinfo.newSponsoring);
	      preparedStatement.setString(6, newinfo.newTier);
	      preparedStatement.setInt(7, newinfo.newAmount);
	      preparedStatement.setString(8, newinfo.newContactee);
	      preparedStatement.executeUpdate();

	      preparedStatement = connect
	          .prepareStatement("SELECT company, contact, phone, email, email_secondary, sponsoring, tier, amount, contactee from sbhacks_sponsorship.sponsors");
	      resultSet = preparedStatement.executeQuery();
	      writeResultSet(resultSet);

	      // Remove again the insert comment
	      //preparedStatement = connect
	      //.prepareStatement("delete from feedback.comments where myuser= ? ; ");
	      //preparedStatement.setString(1, "Test");
	      //preparedStatement.executeUpdate();
	      
	      resultSet = statement
	      .executeQuery("select * sbhacks_sponsorship.sponsors");
	      writeMetaData(resultSet);
	      
	    } catch (Exception e) {
	      throw e;
	    } //finally {
	      //close();
	    //}

	  }
	
	public void updateTeam() throws Exception {
	    try {
	      // This will load the MySQL driver, each DB has its own driver
	      //Class.forName("com.mysql.jdbc.Driver");
	      // Setup the connection with the DB
	      //connect = DriverManager
	      //    .getConnection("jdbc:mysql://localhost/feedback?"
	      //        + "user=denimalpaca&password=1423qrwe");

	      // Statements allow to issue SQL queries to the database
	      statement = connect.createStatement();
	      // Result set get the result of the SQL query
	      //resultSet = statement
	      //   .executeQuery("select * from sbhacks_sponsorship.sponsors");
	      //writeResultSet(resultSet);

	      newinfo.makeNewTeamInfo();
	      
	      // PreparedStatements can use variables and are more efficient
	      preparedStatement = connect
	          .prepareStatement("insert into sbhacks_sponsorship.team values (?, ?, ?, ?)");
	      // "name (first and last), email, pass, admin"
	      preparedStatement.setString(0, newinfo.memberName);
	      preparedStatement.setString(1, newinfo.memberEmail);
	      preparedStatement.setString(2, newinfo.memberPass);
	      preparedStatement.setInt(3, newinfo.admin);
	      preparedStatement.executeUpdate();

	      preparedStatement = connect
	          .prepareStatement("SELECT name, email, pass, admin FROM sbhacks_sponsorship.team");
	      resultSet = preparedStatement.executeQuery();
	      writeResultSet(resultSet);

	      // Remove again the insert comment
	      //preparedStatement = connect
	      //.prepareStatement("delete from feedback.comments where myuser= ? ; ");
	      //preparedStatement.setString(1, "Test");
	      //preparedStatement.executeUpdate();
	      
	      resultSet = statement
	      .executeQuery("select * sbhacks_sponsorship.team");
	      writeMetaData(resultSet);
	      
	    } catch (Exception e) {
	      throw e;
	    } //finally {
	      //close();
	    //}

	  }

	  private void writeMetaData(ResultSet resultSet) throws SQLException {
	    //   Now get some metadata from the database
	    // Result set get the result of the SQL query
	    
	    System.out.println("The columns in the table are: ");
	    
	    System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
	    for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
	      System.out.println("Column " +i  + " "+ resultSet.getMetaData().getColumnName(i));
	    }
	  }

	  private void writeResultSet(ResultSet resultSet) throws SQLException {
	    // ResultSet is initially before the first data set
	    while (resultSet.next()) {
	      // It is possible to get the columns via name
	      // also possible to get the columns via the column number
	      // which starts at 1
	      // e.g. resultSet.getSTring(2);
	      String company = resultSet.getString("company");
	      String contact = resultSet.getString("contact");
	      String phone = resultSet.getString("phone");
	      String email = resultSet.getString("email");
	      String email2 = resultSet.getString("email_secondary");
	      String sponsoring = resultSet.getString("sponsoring");
	      String tier = resultSet.getString("tier");
	      Integer amount = resultSet.getInt("amount");
	      String contactee = resultSet.getString("contactee");
	      System.out.println("Company: " + company);
	      System.out.println("Contact: " + contact);
	      System.out.println("Phone: " + phone);
	      System.out.println("Email: " + email);
	      System.out.println("Email2: " + email2);
	      System.out.println("Sponsoring: " + sponsoring);
	      System.out.println("Tier: " + tier);
	      System.out.println("Amount: " + amount);
	      System.out.println("Contactee: " + contactee);
	    }
	  }

	  // You need to close the resultSet
	  public void close() {
	    try {
	      if (resultSet != null) {
	        resultSet.close();
	      }

	      if (statement != null) {
	        statement.close();
	      }

	      if (connect != null) {
	        connect.close();
	      }
	    } catch (Exception e) {

	    }
	  }
}
