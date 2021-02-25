package boot.spring.pagemodel;

import java.util.HashMap;


/**
 * 搜索请求参数
 * @author Wujun
 *
 */
public class ElasticSearchRequest {
	// 查询条件
	private QueryCommand query;
	// 过滤条件
	private FilterCommand filter;
	
	public QueryCommand getQuery() {
		return query;
	}
	public void setQuery(QueryCommand query) {
		this.query = query;
	}
	public FilterCommand getFilter() {
		return filter;
	}
	public void setFilter(FilterCommand filter) {
		this.filter = filter;
	}
}
