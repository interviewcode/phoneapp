package demo.code.phone.model.springdata;

/**
 * If we were using SpringData this would be their pageable.
 * As this isn't we're only defining the parts we need.
 *
 * @link https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/domain/Pageable.html
 */
public class Pageable {

  private Integer pageNumber;
  private Integer pageSize;

  public Pageable() {
  }

  public Pageable(int pageNumber, int pageSize) {
    this.pageNumber = pageNumber;
    this.pageSize = pageSize;
  }

  public int getPageNumber() {
    return pageNumber;
  }

  public void setPageNumber(Integer pageNumber) {
    this.pageNumber = pageNumber;
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }
}
