package demo.code.phone.model.springdata;

import java.util.List;

/**
 * If we were using SpringData this would be their page object.
 * As this isn't we're only defining the parts we need, perserving the interface/look & feel.
 *
 * @link https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/domain/Page.html
 */
public class Page<T> {

  private Pageable pageable;
  private List<T> results;
  private long totalElements;

  public Page(List<T> results, Pageable pageable, long totalElements) {
    this.pageable = pageable;
    this.results = results;
    this.totalElements = totalElements;
  }

  public Pageable getPageable() {
    return pageable;
  }

  public long getTotalElements() {
    return totalElements;
  }

  public List<T> getResults() {
    return results;
  }
}
