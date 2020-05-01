package demo.code.phone.model;

import demo.code.phone.model.springdata.Page;
import java.util.List;

final public class AlphanumericPhoneNumberPageResponse {

  private List<String> phoneNumbers;

  private PageInfo pageInfo;

  public AlphanumericPhoneNumberPageResponse(Page<String> pageResults) {
    this.phoneNumbers = pageResults.getResults();
    this.pageInfo = new PageInfo<>(pageResults);
  }

  public List<String> getPhoneNumbers() {
    return phoneNumbers;
  }

  public PageInfo getPageInfo() {
    return pageInfo;
  }
}
