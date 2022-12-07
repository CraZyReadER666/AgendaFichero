package AGENDA;
/**
 *
 * @author DAM2B-11
 */
public class Contacto {
    private String name;
    private String surname;
    private String address;
    private String phone;
    private int age;

    public Contacto(){}
    
    public Contacto(String name, String surname, String address, String telefono, int age) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phone = telefono;
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public void setSurname(String surname){
        this.surname = name;
    }
    
    public String getSurname(){
        return surname;
    }
    
    public void setAddress(String address){
        this.address = address;
    }
    
    public String getAdrress(){
        return address;
    }
    
    public void setPhone(String phone){
        this.phone = phone;
    }
    
    public String getPhone(){
        return phone;
    }
    
    public void setAge(int age){
        this.age = age;
    }
    
    public int getAge(){
        return age;
    }

    public String toString() {
        return "Contacto [name=" + name + ", surname=" + surname + ", address=" + address + ", phone=" + phone
                + ", age=" + age + "]";
    }

    public String toCSV(){
        return name + ", " + surname + ", " + address + ", " + phone + "+ " + age;
    }

    public String toTextFile(){
        return "Contacto" + "\nnombre: " + name + "\napellidos: " + surname + "\ndirección: " + address + "\nteléfono: " + phone + "\nedad: " + age;
    }
    
}