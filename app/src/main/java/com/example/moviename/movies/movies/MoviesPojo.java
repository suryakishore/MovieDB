package com.example.moviename.movies.movies;

import java.util.ArrayList;

public class MoviesPojo {
    private String page;

    private String total_pages;

    private ArrayList<MoviesResults> results;

    private String total_results;

    public String getPage ()
    {
        return page;
    }

    public void setPage (String page)
    {
        this.page = page;
    }

    public String getTotal_pages ()
    {
        return total_pages;
    }

    public void setTotal_pages (String total_pages)
    {
        this.total_pages = total_pages;
    }

    public ArrayList<MoviesResults> getResults ()
    {
        return results;
    }

    public void setResults (ArrayList<MoviesResults> results)
    {
        this.results = results;
    }

    public String getTotal_results ()
    {
        return total_results;
    }

    public void setTotal_results (String total_results)
    {
        this.total_results = total_results;
    }
}
