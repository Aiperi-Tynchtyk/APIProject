package entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties (ignoreUnknown = true)
public class CustomResponses {

    private int category_id;
    private String created;
    private int seller_id;

    // to get the list of some information
    private List<CustomResponses> responses;

    private String email;
    private String seller_name;
    private  String responseBody;

    @JsonPropertyOrder("company_name")
    private String companyName;
    private String address;
    private String phone_number;

    private CustomResponses business_area;
    private String ruTitle;
   private  String enTitle;

   private CustomResponses company_name;
   private String company_id;
   //private String companyName;
   private String currency;





}
