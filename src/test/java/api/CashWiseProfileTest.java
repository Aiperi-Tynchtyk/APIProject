package api;

import org.junit.Assert;
import org.junit.Test;
import utilities.APIRunner;

public class CashWiseProfileTest {
    @Test
    public void verifyProfileTest(){
        String path="/api/myaccount/editors/get/profile";
        APIRunner.runGet(path);

        System.out.println(APIRunner.getCustomResponses().getEmail());
        System.out.println(APIRunner.getCustomResponses().getAddress());
       System.out.println(APIRunner.getCustomResponses().getBusiness_area().getEnTitle());
       System.out.println(APIRunner.getCustomResponses().getCompany_name().getCompanyName());

        Assert.assertNotNull(APIRunner.getCustomResponses().getBusiness_area().getEnTitle());
        Assert.assertNotNull(APIRunner.getCustomResponses().getBusiness_area().getRuTitle());

        Assert.assertFalse(APIRunner.getCustomResponses().getBusiness_area().getEnTitle().isEmpty());
        Assert.assertFalse(APIRunner.getCustomResponses().getBusiness_area().getRuTitle().isEmpty());
    }






    
}
