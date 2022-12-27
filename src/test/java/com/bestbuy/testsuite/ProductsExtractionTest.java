package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ProductsExtractionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {

        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/products")
                .then().statusCode(200);
    }

    //21. Extract the limit
    @Test
    public void test021() {
        int limit = response.extract().path("limit");

        // response.log().all(); for printing

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + limit);
        System.out.println("------------------End of Test---------------------------");
    }

    //22. Extract the total
    @Test
    public void test022() {
        int total = response.extract().path("total");


        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of total is : " + total);
        System.out.println("------------------End of Test---------------------------");
    }

    //23. Extract the name of 5th product
    @Test
    public void test023() {
        String name = response.extract().path("data[4].name");


        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of 5th product is : " + name);
        System.out.println("------------------End of Test---------------------------");
    }

    //24. Extract the names of all the products
    @Test
    public void test024() {

        List<String> nameOfAllProducts = response.extract().path("data.name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Names of all the products are : " + nameOfAllProducts);
        System.out.println("------------------End of Test---------------------------");
    }

    //25. Extract the productId of all the products
    @Test
    public void test025() {

        List<Integer> listOfProductIds = response.extract().path("data.id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("IDs of all the products are : " + listOfProductIds);
        System.out.println("------------------End of Test---------------------------");
    }
    //26. Print the size of the data list
    @Test
    public void test026() {

        List<Integer> listOfData= response.extract().path("data");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Size of data list is : " + listOfData.size());
        System.out.println("------------------End of Test---------------------------");
    }

    //27. Get all the value of the product where product name = Energizer - MAX Batteries AA (4-Pack)
    @Test
    public void test027() {

        List<HashMap<String, ?>> valueOfProduct= response.extract().path("data.findAll{it.name == 'Energizer - MAX Batteries AA (4-Pack)'}");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Values of Energizer - MAX Batteries AA (4-Pack) product is : " + valueOfProduct);
        System.out.println("------------------End of Test---------------------------");
    }
    //28. Get the model of the product where product name = Energizer - N Cell E90 Batteries (2- Pack)
    @Test
    public void test028() {

        List<String> modelName = response.extract().path("data.findAll{it.name == 'Energizer - N Cell E90 Batteries (2-Pack)'}.model");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Model name of the product : " + modelName);
        System.out.println("------------------End of Test---------------------------");
    }

    //29. Get all the categories of 8th products
    @Test
    public void test029() {
        List<String> categoriesList= response.extract().path("data[7].categories");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Categories of 8th products are : " + categoriesList);
        System.out.println("------------------End of Test---------------------------");
    }

    //30. Get categories of the store where product id = 150115
    @Test
    public void test030() {

        List<HashMap<String, ?>> categoriesId = response.extract().path("data.findAll{it.id == 150115}.categories");


        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Categories of product id = 150115 is : " + categoriesId);
        System.out.println("------------------End of Test---------------------------");
    }

    //31. Get all the descriptions of all the products
    @Test
    public void test031() {
        List<String> descriptionList= response.extract().path("data.description");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Description list of all products : " + descriptionList);
        System.out.println("------------------End of Test---------------------------");
    }

    //32. Get id of all the all categories of all the products
    @Test
    public void test032() {
        List<String> idList= response.extract().path("data.categories.id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of all IDs  : " + idList);
        System.out.println("------------------End of Test---------------------------");
    }

    //33. Find the product names Where type = HardGood
    @Test
    public void test033() {
        List<String> productsName = response.extract().path("data.findAll{it.type == 'HardGood'}.name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All products name  : " + productsName);
        System.out.println("------------------End of Test---------------------------");
    }

    //34. Find the Total number of categories for the product where product name = Duracell - AA 1.5V CopperTop Batteries (4-Pack)
    @Test
    public void test034() {

        List<String> numberOfCategories= response.extract().path("data.findAll{it.name == 'Duracell - AA 1.5V CopperTop Batteries (4-Pack)'}.categories");
        int size = numberOfCategories.size();

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Size of data list is : " + size);
        System.out.println("------------------End of Test---------------------------");
    }

    //35. Find the createdAt for all products whose price < 5.49
    @Test
    public void test035() {

        List<String> numberOfCreatedAt= response.extract().path("data.findAll{it.price < 5.49}.createdAt");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of Products which prices are less then 5.49 price : " + numberOfCreatedAt);
        System.out.println("------------------End of Test---------------------------");
    }
    //36. Find the name of all categories Where product name = “Energizer - MAX Batteries AA (4-Pack)”
    @Test
    public void test036() {

        List<String> allCategoriesProd= response.extract().path("data.findAll{it.name == 'Energizer - MAX Batteries AA (4-Pack)'}.categories.name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All categories name : " + allCategoriesProd);
        System.out.println("------------------End of Test---------------------------");
    }

    //37. Find the manufacturer of all the products
    @Test
    public void test037() {

        List<String> allManufac= response.extract().path("data.manufacturer");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All categories name : " + allManufac);
        System.out.println("------------------End of Test---------------------------");
    }

    //38. Find the imge of products whose manufacturer is = Energizer
    @Test
    public void test038() {

        List<String> imageList= response.extract().path("data.findAll{it.manufacturer == 'Energizer'}.image");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Image list of products : " + imageList);
        System.out.println("------------------End of Test---------------------------");
    }

    //39. Find the createdAt for all categories products whose price > 5.99
    @Test
    public void test039() {

        List<String> listOfCategoriesCreatedAt= response.extract().path("data.findAll{it.price > 5.99}.categories.createdAt");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of Products which prices are more then 5.99 price : " + listOfCategoriesCreatedAt);
        System.out.println("------------------End of Test---------------------------");
    }

    //40. Find the uri of all the products
    @Test
    public void test040() {

        List<String> listOfAllURL= response.extract().path("data.url");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of URL of all the products : " + listOfAllURL);
        System.out.println("------------------End of Test---------------------------");
    }


}
