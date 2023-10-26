package entities;

import lombok.Data;

@Data

public class RequestBody {

    private String email;
    private String password;

    //get all seller
    private String category_title;
    private String category_description;
    private boolean flag;

    //create seller seller-controller swagger documentation
    private String company_name;
    private String seller_name;
    private String phone_number;
    private String address;



}
