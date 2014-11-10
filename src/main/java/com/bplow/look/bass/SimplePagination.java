package com.bplow.look.bass;

import java.io.Serializable;
import java.util.List;

public class SimplePagination implements Serializable, IPagination{

	public static final long serialVersionUID = 20080219L;
    private int firstResult;  //起始行
    private int maxResults;  //每页显示的行数
    private List results;   //结果集
    private int allCount;  //总记录数
    private String querySQL;
    private String queryCountSQL;
    private int curPageIndex;  //当前页
    private int allPageCount;   //总页数
    
	
	public SimplePagination(int firstResult, int maxResults)
    {
        this.firstResult = firstResult;
        this.maxResults = maxResults;
    }

    public int getAllPageCount()
    {
        if(allCount == 0 || maxResults == 0)
            return 0;
        else
            return allCount / maxResults + (allCount % maxResults <= 0 ? 0 : 1);
    }

    public int getCurPageCount()
    {
        if(results != null)
            return results.size();
        else
            return 0;
    }

    public int getCurPageIndex()
    {
        int i = firstResult/maxResults;
        return i+1;
        /*
        int allPageCount = getAllPageCount();
        for(int i = 0; i < allPageCount; i++)
            if(maxResults * (i + 1) > firstResult)
                return i;

        return 0;*/
    }

    public int getAllCount()
    {
        return allCount;
    }

    public int getFirstResult()
    {
        return firstResult;
    }

    public int getMaxResults()
    {
        return maxResults;
    }

    public void setResults(List results)
    {
        this.results = results;
    }

    public List getResults()
    {
        return results;
    }

    public void setAllCount(int allCount)
    {
        this.allCount = allCount;
    }

    public void setFirstResult(int firstResult)
    {
        this.firstResult = firstResult;
    }

    public void setMaxResults(int maxResults)
    {
        this.maxResults = maxResults;
    }

	public String getQuerySQL() {
		return querySQL;
	}

	public void setQuerySQL(String querySQL) {
		this.querySQL = querySQL;
	}

	public String getQueryCountSQL() {
		return queryCountSQL;
	}

	public void setQueryCountSQL(String queryCountSQL) {
		this.queryCountSQL = queryCountSQL;
	}

	public void setCurPageIndex(int curPageIndex) {
		this.curPageIndex = curPageIndex;
	}

	public void setAllPageCount(int allPageCount) {
		this.allPageCount = allPageCount;
	}
    
    
}
