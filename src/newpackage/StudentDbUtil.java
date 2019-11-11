 package newpackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

public class StudentDbUtil {
   private DataSource dataSource;

   public StudentDbUtil(DataSource theDataSource) {
      this.dataSource = theDataSource;
   }
//Method to get students from database
   public List<Student> getStudents() throws Exception {
      List<Student> students = new ArrayList();
      Connection myConn = null;
      Statement myStmt = null;
      ResultSet myRs = null;

      try {
         myConn = this.dataSource.getConnection();
         String sql = "select * from student order by last_name";
         myStmt = myConn.createStatement();
         myRs = myStmt.executeQuery(sql);

         while(myRs.next()) {
            int id = myRs.getInt("id");
            String firstName = myRs.getString("first_name");
            String lastName = myRs.getString("last_name");
            String email = myRs.getString("email");
            String password = myRs.getString("pass_word");
            Student tempStudent = new Student(id, firstName, lastName, email, password);
            students.add(tempStudent);
         }

         
         return students;
      } finally {
         this.close(myConn, myStmt, myRs);
      }
   }
//Method to close java database connection
   private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
      try {
         if (myRs != null) {
            myRs.close();
         }

         if (myStmt != null) {
            myStmt.close();
         }

         if (myConn != null) {
            myConn.close();
         }
      } catch (Exception exception) {
         exception.printStackTrace();
      }

   }
//Adding new student to database
   public void addStudent(Student student) throws Exception {
      Connection myConn = null;
      PreparedStatement mySt = null;

      try {
         myConn = this.dataSource.getConnection();
         String sql = "insert into student (first_name, last_name, email, pass_word) values(?, ?, ?,?)";
         mySt = myConn.prepareStatement(sql);
         mySt.setString(1, student.getFirstName());
         mySt.setString(2, student.getLastName());
         mySt.setString(3, student.getEmail());
         mySt.setString(4, student.getPassword());
         mySt.execute();
      } finally {
         this.close(myConn, mySt, (ResultSet)null);
      }

   }
//Getting student from database basing on their id
   public Student getStudent(String studentId) throws Exception {
      Connection myConn = null;
      ResultSet myRs = null;
      PreparedStatement mySt = null;

      Student student;
      try {
         int theStudentId = Integer.parseInt(studentId);
         myConn = this.dataSource.getConnection();
         String sql = "select * from student where id=?";
         mySt = myConn.prepareStatement(sql);
         mySt.setInt(1, theStudentId);
         myRs = mySt.executeQuery();
         if (!myRs.next()) {
            throw new Exception("Could not find student id: " + theStudentId);
         }

         String firstName = myRs.getString("first_name");
         String lastName = myRs.getString("last_name");
         String email = myRs.getString("email");
         String password = myRs.getString("pass_word");
         student = new Student(theStudentId, firstName, lastName, email, password);
         
      } finally {
         this.close(myConn, mySt, myRs);
      }

      return student;
   }
//Changing data of a given student basing on their id
   public void updateStudent(Student student) throws Exception {
      Connection myConn = null;
      PreparedStatement mySt = null;

      try {
         myConn = this.dataSource.getConnection();
         String sql = "update student set first_name=?, last_name=?, email=?, pass_word=? where id=?";
         mySt = myConn.prepareStatement(sql);
         mySt.setString(1, student.getFirstName());
         mySt.setString(2, student.getLastName());
         mySt.setString(3, student.getEmail());
         mySt.setString(4, student.getPassword());
         mySt.setInt(5, student.getId());
         mySt.execute();
      } finally {
         this.close(myConn, mySt, (ResultSet)null);
      }

   }
//Deleting student
   public void deleteStudent(int id) throws Exception {
      Connection myConn = null;
      PreparedStatement mySt = null;

      try {
         myConn = this.dataSource.getConnection();
         String sql = "delete from student where id=? ";
         mySt = myConn.prepareStatement(sql);
         mySt.setInt(1, id);
         mySt.execute();
      } finally {
         this.close(myConn, mySt, (ResultSet)null);
      }

   }
//Search student
   public List<Student> searchStudent(String theSearchName) throws Exception {
      Connection myConn = null;
      PreparedStatement mySt = null;
      ResultSet myRs = null;
      ArrayList students = new ArrayList();

      try {
         myConn = this.dataSource.getConnection();
         String sql;
         String firstName;
         //Checking if name being searched is not null or has no length
         if (theSearchName != null && theSearchName.trim().length() != 0) {
            sql = "select * from student where lower(first_name) like ? or lower(last_name) like ?";
            mySt = myConn.prepareStatement(sql);
            firstName = "%" + theSearchName.toLowerCase() + "%";
            mySt.setString(1, firstName);
            mySt.setString(2, firstName);
         //If it is null or 0 in length, app just gives back all students from database, order by last_name   
         } else { 							
            sql = "select * from student order by last_name";
            mySt = myConn.prepareStatement(sql);
         }
         //Getting result set
         myRs = mySt.executeQuery();
         //While result set has next, create new student object and add it to students array list
         while(myRs.next()) {
            int id = myRs.getInt("id");
            firstName = myRs.getString("first_name");
            String lastName = myRs.getString("last_name");
            String email = myRs.getString("email");
            Student student = new Student(id, firstName, lastName, email);
            students.add(student);
         }

         
         return students;
      } finally {
         this.close(myConn, mySt, (ResultSet)null);
      }
   }
   //Check password 
   public boolean checkPassword(String email, String password, String status) throws Exception {
      Connection myConn = null;
      PreparedStatement myStmt = null;
      ResultSet myRs = null;
   //Checking if status checked is student or teacher, preparing sql statements
      try {
         myConn = this.dataSource.getConnection();
         String sql;
         if (status.contentEquals("teacher")) {
            sql = "select * from teacher  where email=? and pass_word=?";
         } 
         else if(status.contentEquals("student")){
        	 sql = "select * from student  where email=? and pass_word=?";
         }
        else {
        	 return false;
        }
    
         myStmt = myConn.prepareStatement(sql);
         myStmt.setString(1, email);
         myStmt.setString(2, password);
         myRs = myStmt.executeQuery();
   //Checking if password and email passed to the method don't exist in database
         if (!myRs.next()) {
            return false;
         }
      } finally {
         myConn.close();
         myStmt.close();
      }
      //If they exist, return true
      return true;
   }

   //Method to get marks of the given student, semester object id and student's id are the same. There can be many semesters with same id (they belong to the one student)
   public List<SemesterResults> getSemesterData(String id) throws Exception {
      List<SemesterResults> results = new ArrayList();
      Connection myConn = null;
      PreparedStatement myStmt = null;
      ResultSet myRs = null;
   //Return semester results with given id from database
      try {
         myConn = this.dataSource.getConnection();
         String sql = "select * from semester_results  where id=?";
         myStmt = myConn.prepareStatement(sql);
         myStmt.setString(1, id);
         myRs = myStmt.executeQuery();
   //Retrieve result set, create new semesterResult object and add it to results List
         while(myRs.next()) {
            int Id = Integer.parseInt(id);
            String math = myRs.getString("math");
            String phisics = myRs.getString("phisics");
            String english = myRs.getString("english");
            String biology = myRs.getString("biology");
            String art = myRs.getString("art");
            String history = myRs.getString("history");
            String schoolYear = myRs.getString("school_year");
            SemesterResults semesterResult = new SemesterResults(schoolYear, Id, math, phisics, english, biology, art, history);
            results.add(semesterResult);
         }
         return results;
      } finally {
         this.close(myConn, myStmt, myRs);
      }
   }
   //Add semester based on user input
   public void addSemester(SemesterResults semester) throws SQLException {
      Connection myConn = null;
      PreparedStatement mySt = null;

      try {
         myConn = this.dataSource.getConnection();
         String sql = "insert into semester_results (school_year, id, math, phisics, english, biology, art, history) values(?, ?, ?, ?,?,?,?,?)";
         mySt = myConn.prepareStatement(sql);
         mySt.setString(1, semester.getSchoolYear());
         mySt.setString(2, Integer.toString(semester.getId()));
         mySt.setString(3, semester.getMath());
         mySt.setString(4, semester.getPhisics());
         mySt.setString(5, semester.getEnglish());
         mySt.setString(6, semester.getBiology());
         mySt.setString(7, semester.getArt());
         mySt.setString(8, semester.getHistory());
         mySt.execute();
      } finally {
         this.close(myConn, mySt, (ResultSet)null);
      }

   }
   //Delete semesters of a given student(same id), all semester results are gone when student is deleted
   public void deleteSemester(int id) throws SQLException {
      Connection myConn = null;
      PreparedStatement mySt = null;

      try {
         myConn = this.dataSource.getConnection();
         String sql = "delete from semester_results where id=? ";
         mySt = myConn.prepareStatement(sql);
         mySt.setInt(1, id);
         mySt.execute();
      } finally {
         this.close(myConn, mySt, (ResultSet)null);
      }

   }
   
public Student getBasicInfo(String email) throws SQLException {

	      Connection myConn = null;
	      PreparedStatement myStmt = null;
	      ResultSet myRs = null;
	      int id = 0;
	      String firstName, lastName;
	      Student student = null;
	      
	      try {
	         myConn = this.dataSource.getConnection();
	         String sql = "select * from student where email=?";
	         myStmt = myConn.prepareStatement(sql);
	         myStmt.setString(1, email);
	         myRs = myStmt.executeQuery();

	         while(myRs.next()) {
	             id = myRs.getInt("id");
	             firstName = myRs.getString("first_name");
	             lastName = myRs.getString("last_name");
	             student = new Student(id, firstName, lastName);
	         }
	         
	         return student;
	      } finally {
	         this.close(myConn, myStmt, myRs);
	      }
	   }
	
	

}
   

   
   
   
   
   
   
   
   
   
