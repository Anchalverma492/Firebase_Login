package com.yoekisoft.myapplication;
import com.yoekisoft.myapplication.nepro.ModelRetrofit;
import com.yoekisoft.myapplication.nepro.StickerNo;
import com.yoekisoft.myapplication.nepro.WareSite;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Api {

  //  String BASE_URL="https://simplifiedcoding.net/demos/";
     String BASE_URL="http://10.10.10.238:8012/api/Nepro/";



  @POST("scanLookupStillage")
    Call<ModelRetrofit> getStickerData(@Body StickerNo insertUser);
  @POST("WareHouseData")
  Call<WareSite> getWareHouse(@Body StickerNo insertUser);
//  @POST("")
//  Call<ModelRetrofit> getStickerData(@Body);

    @POST("marvel")
    Call<List<Hero>> getHeroes();


}
