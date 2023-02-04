package io.reflectoring.resilience4j.springboot;

import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.RetryRegistry;
import io.reflectoring.resilience4j.springboot.model.Flight;
import io.reflectoring.resilience4j.springboot.model.SearchRequest;
import io.reflectoring.resilience4j.springboot.services.FlightSearchService;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class RateLimitingService {
  @Autowired
  private FlightSearchService remoteSearchService;

  @Autowired
  private RPMRateLimitedFlightSearchSearch rpmRateLimitedFlightSearchSearch;

  @Autowired
  private RateLimiterRegistry registry;

  @Autowired
  private RetryRegistry retryRegistry;

  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss SSS");

  @RateLimiter(name = "basicExample")
  List<Flight> basicExample(SearchRequest request) {
    return remoteSearchService.searchFlights(request);
  }

  @RateLimiter(name = "timeoutExample")
  List<Flight> timeoutExample(SearchRequest request) {
    return remoteSearchService.searchFlights(request);
  }

  @RateLimiter(name = "multipleRateLimiters_rps_limiter")
  List<Flight> multipleRateLimitsExample(SearchRequest request) {
    return rpmRateLimitedFlightSearchSearch.searchFlights(request, remoteSearchService);
  }



  @RateLimiter(name = "rateLimiterEventsExample")
  public List<Flight> rateLimiterEventsExample(SearchRequest request) {
    return remoteSearchService.searchFlights(request);
  }


  @PostConstruct
  public void postConstruct() {

    io.github.resilience4j.ratelimiter.RateLimiter.EventPublisher eventPublisher = registry
        .rateLimiter("rateLimiterEventsExample")
        .getEventPublisher();

    eventPublisher.onSuccess(System.out::println);
    eventPublisher.onFailure(System.out::println);
  }
}

@Component
class RPMRateLimitedFlightSearchSearch {
  @RateLimiter(name = "multipleRateLimiters_rpm_limiter")
  List<Flight> searchFlights(SearchRequest request, FlightSearchService remoteSearchService) {
    return remoteSearchService.searchFlights(request);
  }
}