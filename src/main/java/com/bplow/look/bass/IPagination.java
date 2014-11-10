package com.bplow.look.bass;

import java.util.List;

public interface IPagination {
	
	public abstract int getAllCount();

    public abstract void setAllCount(int i);

    public abstract int getAllPageCount();

    public abstract int getCurPageCount();

    public abstract int getCurPageIndex();

    public abstract List getResults();

    public abstract void setResults(List list);

    public abstract void setFirstResult(int i);

    public abstract int getFirstResult();

    public abstract void setMaxResults(int i);

    public abstract int getMaxResults();
    
    public static final int DEFAUL_PAGERESULTCOUNT = 12;
    
    public String getQuerySQL();

	public void setQuerySQL(String querySQL);

	public String getQueryCountSQL();

	public void setQueryCountSQL(String queryCountSQL);
	
	public void setCurPageIndex(int curPageIndex);

	public void setAllPageCount(int allPageCount);
	

}
