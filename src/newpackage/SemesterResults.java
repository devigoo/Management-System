package newpackage;

public class SemesterResults {
   private int id;
   private String schoolYear;
   private String math;
   private String phisics;
   private String english;
   private String biology;
   private String art;
   private String history;

   public String toString() {
      return "SemesterResults [id=" + this.id + ", schoolYear=" + this.schoolYear + ", math=" + this.math + ", phisics=" + this.phisics + ", english=" + this.english + ", biology=" + this.biology + ", art=" + this.art + ", history=" + this.history + "]";
   }

   public String getSchoolYear() {
      return this.schoolYear;
   }

   public void setSchoolYear(String schoolYear) {
      this.schoolYear = schoolYear;
   }

   public SemesterResults(String schoolYear, int id, String math, String phisics, String english, String biology, String art, String history) {
      this.id = id;
      this.schoolYear = schoolYear;
      this.math = math;
      this.phisics = phisics;
      this.english = english;
      this.biology = biology;
      this.art = art;
      this.history = history;
   }

   public int getId() {
      return this.id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getMath() {
      return this.math;
   }

   public void setMath(String math) {
      this.math = math;
   }

   public String getPhisics() {
      return this.phisics;
   }

   public void setPhisics(String phisics) {
      this.phisics = phisics;
   }

   public String getEnglish() {
      return this.english;
   }

   public void setEnglish(String english) {
      this.english = english;
   }

   public String getBiology() {
      return this.biology;
   }

   public void setBiology(String biology) {
      this.biology = biology;
   }

   public String getArt() {
      return this.art;
   }

   public void setArt(String art) {
      this.art = art;
   }

   public String getHistory() {
      return this.history;
   }

   public void setHistory(String history) {
      this.history = history;
   }
}