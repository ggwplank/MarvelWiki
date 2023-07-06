package com.LCDP.marvelwiki;

import java.util.List;

import retrofit2.Call;

public interface MyApiTest {
    Call<List<testCharacter>> getModels();
}
