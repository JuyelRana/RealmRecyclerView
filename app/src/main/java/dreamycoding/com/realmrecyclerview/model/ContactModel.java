package dreamycoding.com.realmrecyclerview.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Juyel on 10/18/2017.
 */

public class ContactModel extends RealmObject {
    @PrimaryKey
    private String name;
    private String email;
    private String phone;
    private String age;

    public ContactModel() {
    }

    public ContactModel(String name, String email, String phone, String age) {

        this.name = name;
        this.email = email;
        this.phone = phone;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
