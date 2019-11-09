 package newpackage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

@WebServlet({"/StudentControllerServlet"})
public class StudentControllerServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
   private StudentDbUtil studentDbUtil;
   private HttpSession session;
   @Resource(
      name = "jdbc/web_student_tracker"
   )
   private DataSource dataSource;

   public void init() throws ServletException {
      super.init();

      try {
         this.studentDbUtil = new StudentDbUtil(this.dataSource);
      } catch (Exception exception) {
         throw new ServletException(exception);
      }
   }
//doGet method which "chooses" which method to use basing on received parameter
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      try {
         String theCommand = request.getParameter("command");
         if (theCommand == null) {
            theCommand = "LIST";
         }

         switch(theCommand) {
         
         case "LOGOUT":
         logout(request, response);
          break;
            
           
         case "ADDSEMESTER":
            addSemester(request, response);
             break;
          
         case "SEARCH":
              searchStudent(request, response);
              break;
            
         case "UPDATE":
             upadteStudent(request, response);
              break;
           
         case "ADD":
              addStudent(request, response);
               break;
         
         case "LIST":
              listStudents(request, response);
              break;
           
         case "LOAD":
        	 loadStudent(request, response);
               break;
           
         case "LOGIN":
             loginTeacher(request, response);
               break;
           
         case "LOADSTUDENTDATA":
           
               loadStudentData(request, response);
               break;
            
         case "DELETE":
              deleteStudent(request, response);
               break;
            }
         
      } catch (Exception exception) {
         throw new ServletException(exception);
      }
   }
   //Adding semester, parameters passed from form add-semester.jsp
   private void addSemester(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
      String id = request.getParameter("id");
      int Id = Integer.parseInt(id);
      String math = request.getParameter("math");
      String phisics = request.getParameter("phisics");
      String english = request.getParameter("english");
      String biology = request.getParameter("biology");
      String art = request.getParameter("art");
      String history = request.getParameter("history");
      String schoolYear = request.getParameter("schoolYear");
      SemesterResults semester = new SemesterResults(schoolYear, Id, math, phisics, english, biology, art, history);
      studentDbUtil.addSemester(semester);
      //Sending user back to main page(list-students2.jsp)
      response.sendRedirect(request.getContextPath() + "/StudentControllerServlet?command=LIST");
   }
   		//Passing semester data  as an attribute and sending it as a dispatcher to view-student.jsp
   private void loadStudentData(HttpServletRequest request, HttpServletResponse response) throws Exception {
      String id = request.getParameter("studentId");
      List<SemesterResults> result = studentDbUtil.getSemesterData(id);
      request.setAttribute("RESULTS", result);
      RequestDispatcher dispatcher = request.getRequestDispatcher("/view-student.jsp");
      dispatcher.forward(request, response);
   }
   		//Loging out by removing attribute email1 and redirecting to login page
   private void logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
      session.removeAttribute("email1");
      response.sendRedirect("index.jsp");
   }
   		
   private void loginTeacher(HttpServletRequest request, HttpServletResponse response) throws Exception {
	   //Getting parameters from login form
      String email = request.getParameter("email");
      String password = request.getParameter("password");
      String status = request.getParameter("choice");
      //Checking if given password, status and email exist in database. If true adding attribute and sending user to Servlet default method, else to login page
      if (studentDbUtil.checkPassword(email, password, status)) {
         session = request.getSession();
         session.setAttribute("email1", email);
         response.sendRedirect(request.getContextPath() + "/StudentControllerServlet");
      } else {
         response.sendRedirect("index.jsp");
      }

   }
   	//Searching student using value passed in parameter from search window
   private void searchStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
      String theSearchName = request.getParameter("theSearchName");
      //Return list of students from database basing on the theSearchName parameter
      List<Student> students = studentDbUtil.searchStudent(theSearchName);
      //Set an attribute and pass it to jsp page
      request.setAttribute("STUDENT_LIST", students);
      RequestDispatcher dispatcher = request.getRequestDispatcher("/list-students2.jsp");
      dispatcher.forward(request, response);
   }
   	//Delete student basing on studentId parameter passed by delete link, it also deletes all his semesters
   private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
      int id = Integer.parseInt(request.getParameter("studentId"));
      studentDbUtil.deleteStudent(id);
      studentDbUtil.deleteSemester(id);
      listStudents(request, response);
   }
   //Updating student basing on parameters passed by update-student-form
   private void upadteStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
      String firstName = request.getParameter("firstName");
      String lastName = request.getParameter("lastName");
      String email = request.getParameter("email");
      String password = request.getParameter("password");
      String idString = request.getParameter("studentId");
      int id = Integer.parseInt(idString);
      Student student = new Student(id, firstName, lastName, email, password);
      studentDbUtil.updateStudent(student);
      //List students again, after they are updated
      listStudents(request, response);
   }
   //Method used to fill up the update form, basing on parameter studentId passed by update link in list-students2.jsp
   private void loadStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
      String studentId = request.getParameter("studentId");
      Student student = studentDbUtil.getStudent(studentId);
      request.setAttribute("THE_STUDENT", student);
      RequestDispatcher dispatcher = request.getRequestDispatcher("/update-student-form.jsp");
      dispatcher.forward(request, response);
   }
   //Add student basing on parameters passed by add-student-form.jsp
   private void addStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
      String firstName = request.getParameter("firstName");
      String lastName = request.getParameter("lastName");
      String email = request.getParameter("email");
      String password = request.getParameter("password");
    //Create new student object and add it to database via addStudent method
      Student student = new Student(firstName, lastName, email, password);
      studentDbUtil.addStudent(student);
      //Redirect user to default method LIST
      response.sendRedirect(request.getContextPath() + "/StudentControllerServlet?command=LIST");
   }
   //Method to get students from database and display on list-students2.jsp
   private void listStudents(HttpServletRequest request, HttpServletResponse response) throws Exception {
      List<Student> students = studentDbUtil.getStudents();
      request.setAttribute("STUDENT_LIST", students);
      RequestDispatcher dispatcher = request.getRequestDispatcher("/list-students2.jsp");
      dispatcher.forward(request, response);
   }
}