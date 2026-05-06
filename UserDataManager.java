
package projectt;
import java.io.*;
import java.util.*;

public class UserDataManager {

    public static String currentUserPhone = null;

    // اسم الملف لتخزين بيانات المستخدمين
    private static final String FILE_NAME = "users.txt";
    static User readUserData;

    // دالة لحفظ بيانات المستخدم في الملف
    public static void saveUserData(User user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(user.getFirstName() + "," + user.getLastName() + "," + user.getPhone() + "," +
                         user.getEmail() + "," + user.getPassword() + "," + user.getAge() + "," + user.getGender());
            writer.newLine();
            System.out.println("User data saved successfully: " + user.getPhone());
        } catch (IOException e) {
            System.out.println("Error saving user data: " + e.getMessage());
        }
    }

    // دالة للتحقق من البريد الإلكتروني وكلمة المرور عند تسجيل الدخول
    public static boolean validateLogin(String phone, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userDetails = line.split(",");
                String storedPhone = userDetails[2]; // الرقم المخزن
                String storedPassword = userDetails[4]; // كلمة المرور المخزنة

                // التحقق من تطابق الرقم وكلمة المرور
                if (storedPhone.equals(phone) && storedPassword.equals(password)) {
                    return true; // تطابق صحيح
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading user data.");
        }
        return false; // إذا لم يتم العثور على تطابق
    }

    // دالة لتحديث العضوية للمستخدم
    public static boolean updateMembership(String phone, String membershipType, String membershipDuration) {
        File file = new File(FILE_NAME);
        File tempFile = new File("temp_users.txt");
        boolean isUpdated = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(file));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] userDetails = line.split(",");
                String storedPhone = userDetails[2]; // الهاتف المخزن

                // التحقق من المستخدم
                if (storedPhone.equals(phone)) {
                    // تحديث معلومات العضوية
                    String updatedLine = String.join(",", userDetails[0], userDetails[1], userDetails[2],
                            userDetails[3], userDetails[4], userDetails[5], userDetails[6], membershipType, membershipDuration);
                    writer.write(updatedLine);
                    isUpdated = true;
                } else {
                    writer.write(line);
                }
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error updating membership data.");
        }

        // استبدال الملف الأصلي بالملف المؤقت
        if (file.delete()) {
            if (!tempFile.renameTo(file)) {
                System.out.println("Error renaming temp file.");
            }
        } else {
            System.out.println("Error deleting original file.");
        }

        return isUpdated;
    }

    // دالة لحذف بيانات المستخدم من الملف
    public static boolean deleteUserData(String phone) {
        File inputFile = new File(FILE_NAME);
        File tempFile = new File("temp_users.txt");
        boolean isDeleted = false;

        try (
                
             BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))
                ) 
        {
            
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userDetails = line.split(",");
                String storedPhone = userDetails[2]; // الهاتف المخزن

                // إذا كان الرقم المدخل يطابق الرقم المخزن، لا نقوم بكتابته
                if (!storedPhone.equals(phone)) {
                    writer.write(line); // كتابة السطر الذي لا يحتوي على الرقم المدخل
                    writer.newLine();
                } else {
                    isDeleted = true; // تم الحذف بنجاح
                }
            }
        } catch (IOException e) {
            System.out.println("Error deleting user data.");
        }

        // استبدال الملف الأصلي بالملف المؤقت
        if (inputFile.delete()) {
            if (!tempFile.renameTo(inputFile)) {
                System.out.println("Error renaming temp file.");
            }
        } else {
            System.out.println("Error deleting original file.");
        }

        return isDeleted;
    }

    // دالة لقراءة بيانات المستخدم من الملف
    public static User readUserData(String phone) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userDetails = line.split(",");
                if (userDetails.length >= 7) {
                    String storedPhone = userDetails[2];
                    if (storedPhone.equals(phone)) {
                        return new User(userDetails[0], userDetails[1], userDetails[2],
                                        userDetails[3], userDetails[4], userDetails[5], userDetails[6]);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // دالة لتحديث بيانات المستخدم
    public static boolean updateUserData(User updatedUser) {
        File file = new File(FILE_NAME);
        File tempFile = new File("temp_users.txt");
        boolean isUpdated = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(file));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] userDetails = line.split(",");
                String storedPhone = userDetails[2]; // الرقم المخزن

                // إذا كان الرقم المدخل يطابق الرقم المخزن، قم بتحديث البيانات
                if (storedPhone.equals(updatedUser.getPhone())) {
                    String updatedLine = updatedUser.getFirstName() + "," +
                                          updatedUser.getLastName() + "," +
                                          updatedUser.getPhone() + "," +
                                          updatedUser.getEmail() + "," +
                                          updatedUser.getPassword() + "," +
                                          updatedUser.getAge() + "," +
                                          updatedUser.getGender() + "," +
                                          updatedUser.getMembershipType() + "," +
                                          updatedUser.getMembershipDuration();
                    writer.write(updatedLine);
                    isUpdated = true;
                } else {
                    writer.write(line);
                }
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error updating user data.");
        }

        if (file.delete()) {
            tempFile.renameTo(file);
        }

        return isUpdated;
    }
}
