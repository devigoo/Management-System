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
      } catch (Exception var2) {
         throw new ServletException(var2);
      }
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      try {
         String theCommand = request.getParameter("command");
         if (theCommand == null) {
            theCommand = "LIST";
         }

         switch(theCommand.hashCode()) {
         case -2043999862:
            if (theCommand.equals("LOGOUT")) {
               this.logout(request, response);
               return;
            }
            break;
         case -1960252231:
            if (theCommand.equals("ADDSEMESTER")) {
               this.addSemester(request, response);
               return;
            }
            break;
         case -1853007448:
            if (theCommand.equals("SEARCH")) {
               this.searchStudent(request, response);
               return;
            }
            break;
         case -1785516855:
            if (theCommand.equals("UPDATE")) {
               this.upadteStudent(request, response);
               return;
            }
            break;
         case 64641:
            if (theCommand.equals("ADD")) {
               this.addStudent(request, response);
               return;
            }
            break;
         case 2336926:
            if (theCommand.equals("LIST")) {
               this.listStudents(request, response);
               return;
            }
            break;
         case 2342118:
            if (theCommand.equals("LOAD")) {
               this.loadStudent(request, response);
               return;
            }
            break;
         case 72611657:
            if (theCommand.equals("LOGIN")) {
               this.loginTeacher(request, response);
               return;
            }
            break;
         case 129550239:
            if (theCommand.equals("LOADSTUDENTDATA")) {
               this.loadStudentData(request, response);
               return;
            }
            break;
         case 2012838315:
            if (theCommand.equals("DELETE")) {
               this.deleteStudent(request, response);
               return;
            }
         }

         this.listStudents(request, response);
      } catch (Exception var5) {
         throw new ServletException(var5);
      }
   }

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
      this.studentDbUtil.addSemester(semester);
      response.sendRedirect(request.getContextPath() + "/StudentControllerServlet?command=LIST");
   }

   private void loadStudentData(HttpServletRequest request, HttpServletResponse response) throws Exception {
      String id = request.getParameter("studentId");
      List<SemesterResults> result = this.studentDbUtil.getSemesterData(id);
      request.setAttribute("RESULTS", result);
      RequestDispatcher dispatcher = request.getRequestDispatcher("/view-student.jsp");
      dispatcher.forward(request, response);
   }

   private void logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
      this.session.removeAttribute("email1");
      response.sendRedirect("index.jsp");
   }

   private void loginTeacher(HttpServletRequest request, HttpServletResponse response) throws Exception {
      String email = request.getParameter("email");
      String password = request.getParameter("password");
      String status = request.getParameter("choice");
      if (this.studentDbUtil.checkPassword(email, password, status)) {
         this.session = request.getSession();
         this.session.setAttribute("email1", email);
         response.sendRedirect(request.getContextPath() + "/StudentControllerServlet");
      } else {
         response.sendRedirect("index.jsp");
      }

   }

   private void searchStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
      String theSearchName = request.getParameter("theSearchName");
      List<Student> students = this.studentDbUtil.searchStudent(theSearchName);
      request.setAttribute("STUDENT_LIST", students);
      RequestDispatcher dispatcher = request.getRequestDispatcher("/list-students2.jsp");
      dispatcher.forward(request, response);
   }

   private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
      int id = Integer.parseInt(request.getParameter("studentId"));
      this.studentDbUtil.deleteStudent(id);
      this.studentDbUtil.deleteSemester(id);
      this.listStudents(request, response);
   }

   private void upadteStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
      String firstName = request.getParameter("firstName");
      String lastName = request.getParameter("lastName");
      String email = request.getParameter("email");
      String password = request.getParameter("password");
      String idString = request.getParameter("studentId");
      int id = Integer.parseInt(idString);
      Student student = new Student(id, firstName, lastName, email, password);
      this.studentDbUtil.updateStudent(student);
      this.listStudents(request, response);
   }

   private void loadStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
      String studentId = request.getParameter("studentId");
      Student student = this.studentDbUtil.getStudent(studentId);
      request.setAttribute("THE_STUDENT", student);
      RequestDispatcher dispatcher = request.getRequestDispatcher("/update-student-form.jsp");
      dispatcher.forward(request, response);
   }

   private void addStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
      String firstName = request.getParameter("firstName");
      String lastName = request.getParameter("lastName");
      String email = request.getParameter("email");
      String password = request.getParameter("password");
      Student student = new Student(firstName, lastName, email, password);
      this.studentDbUtil.addStudent(student);
      response.sendRedirect(request.getContextPath() + "/StudentControllerServlet?command=LIST");
   }

   private void listStudents(HttpServletRequest request, HttpServletResponse response) throws Exception {
      List<Student> students = this.studentDbUtil.getStudents();
      request.setAttribute("STUDENT_LIST", students);
      RequestDispatcher dispatcher = request.getRequestDispatcher("/list-students2.jsp");
      dispatcher.forward(request, response);
   }
}