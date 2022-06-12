package com.example.a11stapp;

public class Movie {
    String rnum;
    String movieNm;
    String rank;
    String rankInten;
    String rankOldAndNew;
    String movieCd;
    String openDt;
    String salesAnt;
    String salesShare;
    String salesInten;
    String salesChange;
    String audiCnt;
    String salesAcc;
    String audiChange;
    String audiAcc;
    String scrnCnt;
    String showCnt;

    public Movie(String movieNm,String audiCnt,String rank, String salesAcc){
        this.movieNm=movieNm;
        this.rank=rank;
        this.audiCnt=audiCnt;
        this.salesAcc=salesAcc;


}

    public String getMovieNm(){return movieNm;}
    public String getRank(){return rank;}
    public String getAudiCnt(){return audiCnt;}
    public String getSalesAcc(){return salesAcc;};


}


