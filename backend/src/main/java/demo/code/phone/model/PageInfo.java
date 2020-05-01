package demo.code.phone.model;


import demo.code.phone.model.springdata.Page;

final public class PageInfo<T> {

  private long totalResults;
  private int pageNumber;
  private int pageSize;

  public PageInfo(Page<T> pageInfo) {
    this.totalResults = pageInfo.getTotalElements();
    this.pageSize = pageInfo.getPageable().getPageSize();
    this.pageNumber = pageInfo.getPageable().getPageNumber();
  }

  public long getTotalResults() {
    return totalResults;
  }

  public int getPageNumber() {
    return pageNumber;
  }

  public int getPageSize() {
    return pageSize;
  }
}
