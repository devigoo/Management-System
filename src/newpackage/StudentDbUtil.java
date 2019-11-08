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
      } catch (Exception var5) {
         var5.printStackTrace();
      }

   }

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

   public Student getStudent(String studentId) throws Exception {
      Connection myConn = null;
      ResultSet myRs = null;
      PreparedStatement mySt = null;

      Student var13;
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
         Student student = new Student(theStudentId, firstName, lastName, email, password);
         var13 = student;
      } finally {
         this.close(myConn, mySt, myRs);
      }

      return var13;
   }

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

   public List<Student> searchStudent(String theSearchName) throws Exception {
      Connection myConn = null;
      PreparedStatement mySt = null;
      ResultSet myRs = null;
      ArrayList students = new ArrayList();

      try {
         myConn = this.dataSource.getConnection();
         String sql;
         String firstName;
         if (theSearchName != null && theSearchName.trim().length() != 0) {
            sql = "select * from student where lower(first_name) like ? or lower(last_name) like ?";
            mySt = myConn.prepareStatement(sql);
            firstName = "%" + theSearchName.toLowerCase() + "%";
            mySt.setString(1, firstName);
            mySt.setString(2, firstName);
         } else {
            sql = "select * from student order by last_name";
            mySt = myConn.prepareStatement(sql);
         }

         myRs = mySt.executeQuery();

         while(myRs.next()) {
            int id = myRs.getInt("id");
            firstName = myRs.getString("first_name");
            String lastName = myRs.getString("last_name");
            String email = myRs.getString("email");
            Student student = new Student(id, firstName, lastName, email);
            students.add(student);
         }

         ArrayList var12 = students;
         return var12;
      } finally {
         this.close(myConn, mySt, (ResultSet)null);
      }
   }

   public boolean checkPassword(String email, String password, String status) throws Exception {
      Connection myConn = null;
      PreparedStatement myStmt = null;
      ResultSet myRs = null;

      try {
         myConn = this.dataSource.getConnection();
         String sql;
         if (status.contentEquals("teacher")) {
            sql = "select * from teacher  where email=? and pass_word=?";
         } else {
            sql = "select * from student  where email=? and pass_word=?";
         }

         myStmt = myConn.prepareStatement(sql);
         myStmt.setString(1, email);
         myStmt.setString(2, password);
         myRs = myStmt.executeQuery();
         if (!myRs.next()) {
            return false;
         }
      } finally {
         myConn.close();
         myStmt.close();
      }

      return true;
   }

   public List<SemesterResults> getSemesterResults(int id) {
      List<SemesterResults> semesterResults = new ArrayList();
      return semesterResults;
   }

   public List<SemesterResults> getSemesterData(String id) throws Exception {
      List<SemesterResults> results = new ArrayList();
      Connection myConn = null;
      PreparedStatement myStmt = null;
      ResultSet myRs = null;

      try {
         myConn = this.dataSource.getConnection();
         String sql = "select * from semester_results  where id=?";
         myStmt = myConn.prepareStatement(sql);
         myStmt.setString(1, id);
         myRs = myStmt.executeQuery();

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
}