/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reactiveweb;

////import org.junit.jupiter.api.Test;
////import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.reactive.server.WebTestClient;
//
///**
// *
// * @author woohoo
// */
//@ExtendWith(SpringExtension.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class GreetingRouterTest {
//
//    @Autowired
//    private WebTestClient webTestClient;
//
//    @Test
//    public void testHello() {
//        webTestClient
//                // Create a GET request to test an endpoint
//                .get().uri("/hello")
//                .accept(MediaType.TEXT_PLAIN)
//                .exchange()
//                // and use the dedicated DSL to test assertions against the response
//                .expectStatus().isOk()
//                .expectBody(String.class).isEqualTo("Hello, Spring!");
//
//    }
//}
