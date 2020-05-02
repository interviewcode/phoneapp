export interface PageInfo {
  totalResults: number;
  pageNumber: number;
  pageSize: number;
}

export interface AlphanumericResults {
  phoneNumbers: string[];
  pageInfo: PageInfo;
}

export const EmptyAlphanumericResult = {
  phoneNumbers: [],
  pageInfo: {
    totalResults: 0,
    pageNumber: 0,
    pageSize: 0
  },
};
