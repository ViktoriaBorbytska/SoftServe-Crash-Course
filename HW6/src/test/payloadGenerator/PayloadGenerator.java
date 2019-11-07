package test.payloadGenerator;

public class PayloadGenerator {

    private int id;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private int userStatus;

    private String payload;

    public PayloadGenerator() {
        id = 123456;
        userName = "customUser1";
        firstName = "Custom";
        lastName = "User";
        email = "randomemail@abc.de";
        password = "qwert123";
        phone = "123456789";
        userStatus = 0;
    }

    public void generatePayload() {
        payload = "{\n" +
                "  \"id\": " + id + ",\n" +
                "  \"username\": \"" + userName + "\",\n" +
                "  \"firstName\": \"" + firstName + "\",\n" +
                "  \"lastName\": \"" + lastName + "\",\n" +
                "  \"email\": \"" + email + "\",\n" +
                "  \"password\": \"" + password + "\",\n" +
                "  \"phone\": \"" + phone + "\",\n" +
                "  \"userStatus\": " + userStatus +
                "}";
    }

    public String generateTriplePayload(String payload1, String payload2, String payload3){
        return "[" + payload1 + ", " + payload2 + ", " + payload3 + "]";
    }

    public String getPayload() {
        return payload;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName(){
        return userName;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public String getFirstName(){
        return firstName;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getPhone(){
        return phone;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public int getUserStatus(){
        return userStatus;
    }

    public void setUserStatus(int userStatus){
        this.userStatus = userStatus;
    }
}