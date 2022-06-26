import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//A java POJO/json object for use in project
@JsonPropertyOrder({"userId","firstName","lastName","version","insuranceCompany"})
public class Enrollee {
    @JsonProperty("userId")
    private String userId;
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("version")
    private int version;
    @JsonProperty("insuranceCompany")
    private String insuranceCompany;

    public String getUserid() {
        return userId;
    }

    public void setUserid(String userid) {
        this.userId = userid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String fullName) {
        this.firstName = fullName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getInsuranceCompany() {
        return insuranceCompany;
    }

    public void setInsuranceCompany(String insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
    }

//    Here I built out acouple of different methods for retrieving the data in various forms.
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n") ;

        sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
        sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
        sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
        sb.append("    version: ").append(toIndentedString(version)).append("\n");
        sb.append("    insuranceCompany: ").append(toIndentedString(insuranceCompany)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    public String[] toStringArray(){
        return new String[]
                { userId, firstName, lastName, String.valueOf(this.userId), insuranceCompany };
    }

    public Map<String, String> toMap() {
        return new HashMap<>() {
            {
                put("userId", userId);
                put("firstName", firstName);
                put("lastName", lastName);
                put("version", String.valueOf(version));
                put("insuranceCompany", insuranceCompany);
            }
        };
    }

    public List<String> getValues() {
        List<String> strings = new ArrayList<>();
        strings.add(userId);
        strings.add(firstName);
        strings.add(lastName);
        strings.add(String.valueOf(userId));
        strings.add(insuranceCompany);
        return strings;
    }

    public JSONObject getEnrolleeAsJson () {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId",String.valueOf(this.userId));
        jsonObject.put("firstName",this.firstName);
        jsonObject.put("lastName",this.lastName);
        jsonObject.put("version",this.version);
        jsonObject.put("insuranceCompany",this.insuranceCompany);

        return jsonObject;
    }

    private String toIndentedString(Object o) {
        if (o == null) {
            return null;
        }
        return o.toString().replace("\n","\n    ");
    }
}
