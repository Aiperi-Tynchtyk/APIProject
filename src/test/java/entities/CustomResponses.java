package entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties (ignoreUnknown = true)
public class CustomResponses {

    private int category_id;
    private String created;
    private String seller_id;

    // to get the list of some information
    private List<CustomResponses> responses;

    private String email;
    private String seller_name;
    private  String responseBody;
    private String company_name;


}
