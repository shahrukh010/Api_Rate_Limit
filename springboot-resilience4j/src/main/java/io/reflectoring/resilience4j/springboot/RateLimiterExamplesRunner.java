package io.reflectoring.resilience4j.springboot;

import io.reflectoring.resilience4j.springboot.model.SearchRequest;
import java.time.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RateLimiterExamplesRunner {

  @Autowired
  private RateLimitingService service;

  public void run() {
    System.out.println("Running ratelimiter examples");

    System.out.println("----------------------------- basicExample ------------------------------------------");
    basicExample();
    System.out.println("-----------------------------------------------------------------------");

    System.out.println("----------------------------- timeoutExample ------------------------------------------");
    timeoutExample();
    System.out.println("-----------------------------------------------------------------------");

    System.out.println("------------------------------ multipleLimits_2rps_40rpm_sequential -----------------------------------------");
    multipleLimits_2rps_40rpm_sequential();
    System.out.println("-----------------------------------------------------------------------");


   System.out.println("------------------------------ rateLimiterEvents -----------------------------------------");
   rateLimiterEvents();
   System.out.println("-----------------------------------------------------------------------");

  }

  private void rateLimiterEvents() {
    SearchRequest request = new SearchRequest("NYC", "LAX", "08/15/2021");

    try {
      System.out.println(service.rateLimiterEventsExample(request));
      System.out.println(service.rateLimiterEventsExample(request));
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }


  private void multipleLimits_2rps_40rpm_sequential() {
    SearchRequest request = new SearchRequest("NYC", "LAX", "08/15/2021");
    for (int i=0; i<45; i++) {
      try {
        System.out.println(service.multipleRateLimitsExample(request));
      }
      catch (Exception e) {
        System.err.println("limit exceeded...!");
      }
    }
  }

  private void timeoutExample() {
    SearchRequest request = new SearchRequest("NYC", "LAX", "08/15/2021");
    try {
      for (int i=0; i<7; i++) {
        System.out.println(service.timeoutExample(request));
      }
    }
    catch (Exception e) {
      System.err.println("limit exceeded...!");


    }
  }

  private void basicExample() {
    SearchRequest request = new SearchRequest("NYC", "LAX", "08/15/2021");
    for (int i=0; i<4; i++) {
      try {
        System.out.println(service.basicExample(request));
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}