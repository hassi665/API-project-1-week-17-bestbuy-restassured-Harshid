package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StoresExtractionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {

        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/stores")
                .then().statusCode(200);
    }

    //1. Extract the limit
    @Test
    public void test001() {
        int limit = response.extract().path("limit");

        // response.log().all(); for printing

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + limit);
        System.out.println("------------------End of Test---------------------------");
    }

    //2. Extract the total
    @Test
    public void test002() {
        int total = response.extract().path("total");


        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of total is : " + total);
        System.out.println("------------------End of Test---------------------------");
    }

    //3. Extract the name of 5th store
    @Test
    public void test003() {
        String name = response.extract().path("data[4].name");


        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of 5th store is : " + name);
        System.out.println("------------------End of Test---------------------------");
    }

    //4. Extract the names of all the store
    @Test
    public void test004() {

        List<String> nameOfAllStores = response.extract().path("data.name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Names of all the stores are : " + nameOfAllStores);
        System.out.println("------------------End of Test---------------------------");
    }

    //5. Extract the storeId of all the store
    @Test
    public void test005() {

        List<Integer> listOfIds = response.extract().path("data.id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("IDs of all the stores are : " + listOfIds);
        System.out.println("------------------End of Test---------------------------");
    }

    //6. Print the size of the data list
    @Test
    public void test006() {

        List<Integer> listOfData= response.extract().path("data");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Size of data list is : " + listOfData.size());
        System.out.println("------------------End of Test---------------------------");
    }



    //7. Get all the value of the store where store name = St Cloud
    @Test
    public void test007() {

        List<HashMap<String, ?>> valueOfStore= response.extract().path("data.findAll{it.name == 'St Cloud'}");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Values stores in the store name - St Cloud : " + valueOfStore);
        System.out.println("------------------End of Test---------------------------");
    }

    //8. Get the address of the store where store name = Rochester
    @Test
    public void test008() {

        List<String> address = response.extract().path("data.findAll{it.name == 'Rochester'}.address");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The address of the store where store name is 'Rochester' : " + address);
        System.out.println("------------------End of Test---------------------------");
    }

    //9. Get all the services of 8th store
    @Test
    public void test009() {
        List<String> services= response.extract().path("data[7].services");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All Services of 8th stores are : " + services);
        System.out.println("------------------End of Test---------------------------");
    }

    //10. Get storeservices of the store where service name = Windows Store
    @Test
    public void test010() {

        List<String> servicesText = response.extract().path("data.find{it.name == 'Rochester'}.services");


        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Value of storeservices of the store where service name = Windows Store : " + servicesText );
        System.out.println("------------------End of Test---------------------------");
    }
    //11. Get all the storeId of all the store

    @Test
    public void test011() {

        List<Integer> listOfStoreIds = response.extract().path("data.services.storeservices.storeId");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("IDs of all the stores are : " + listOfStoreIds);
        System.out.println("------------------End of Test---------------------------");
    }

    //12. Get id of all the store
    @Test
    public void test012() {

        List<Integer> listOfIds = response.extract().path("data.id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("IDs of all the stores are : " + listOfIds);
        System.out.println("------------------End of Test---------------------------");
    }

    //13. Find the store names Where state = ND
    @Test
    public void test013() {

        List<HashMap<String, ?>> storesName= response.extract().path("data.findAll{it.state == 'ND'}.name");


        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Store name where state = ND is : " + storesName);
        System.out.println("------------------End of Test---------------------------");
    }

    //14. Find the Total number of services for the store where store name = Rochester
    @Test
    public void test014() {

        List<HashMap<Integer, ?>> services= response.extract().path("data.find{it.name == 'Rochester'}.services");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Total number of services for the store name = Rochester : " + services.size());
        System.out.println("------------------End of Test---------------------------");
    }

    //15. Find the createdAt for all services whose name = “Windows Store”
    @Test
    public void test015() {

        List<HashMap<String, ?>> createdAtFind= response.extract().path("data*.services*.findAll{it.name == 'Windows Store'}.createdAt");


        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of CreatedAt for all services which name has = Windows Store : " + createdAtFind);
        System.out.println("------------------End of Test---------------------------");
    }

    //16. Find the name of all services Where store name = “Fargo”
    @Test
    public void test016() {

        List<HashMap<String, ?>> services= response.extract().path("data.findAll{it.name == 'Fargo'}.services.name");


        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All services list for store name of 'Fargo' : " + services);
        System.out.println("------------------End of Test---------------------------");
    }

    //17. Find the zip of all the store
    @Test
    public void test017() {

        List<Integer> zips = response.extract().path("data.zip");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Zip of all the stores : " + zips);
        System.out.println("------------------End of Test---------------------------");
    }

    //18. Find the zip of store name = Roseville
    @Test
    public void test018() {

        List<HashMap<String, ?>> zipOfStore= response.extract().path("data.findAll{it.name == 'Roseville'}.zip");


        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Zip of the store name = Roseville : " + zipOfStore);
        System.out.println("------------------End of Test---------------------------");
    }

    //19. Find the storeservices details of the service name = Magnolia Home Theater
    @Test
    public void test019() {

        List<HashMap<String, ?>> storeService= response.extract().path("data.services*.findAll{it.name== 'Magnolia Home Theater'}.storeservices");


        System.out.println("------------------StartingTest---------------------------");
        System.out.println("storeservices details : " + storeService);
        System.out.println("------------------End of Test---------------------------");
    }

    //20. Find the lat of all the stores
    @Test
    public void test020() {

        List<Integer> lats = response.extract().path("data.lat");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Lat of all the stores : " + lats);
        System.out.println("------------------End of Test---------------------------");
    }


}
