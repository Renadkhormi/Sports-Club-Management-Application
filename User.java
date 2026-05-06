
package projectt;
public class User {
    
    
     
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String password;
    private String age;
    private String gender;
    private String membershipType;  // إضافة نوع العضوية
    private String membershipDuration;  // إضافة مدة العضوية

    // إنشاء الكائن باستخدام كافة الخصائص بما في ذلك العضوية
    public User(String firstName, String lastName, String phone, String email, String password, String age, String gender, String membershipType, String membershipDuration) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.age = age;
        this.gender = gender;
        this.membershipType = membershipType;
        this.membershipDuration = membershipDuration;
    }
    
    public User(String firstName, String lastName, String phone, String email, String password, String age, String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.age = age;
        this.gender = gender;
        this.membershipType = membershipType;
        this.membershipDuration = membershipDuration;
    }
    
    
    
 public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getAge() { return age; }
    public String getGender() { return gender; }
    


    // الدوال getter و setter الخاصة بكل الخصائص
    public String getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }

    public String getMembershipDuration() {
        return membershipDuration;
    }

    public void setMembershipDuration(String membershipDuration) {
        this.membershipDuration = membershipDuration;
    }
}