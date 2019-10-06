package com.example.katalogfilm.Class;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

public class Movies implements Parcelable {
    private String tittle, photo;
    private String original_language, overview;

    public Movies(JSONObject object) {
        try{
            String title = object.getString("title");
            String language = object.getString("original_language");
            String overview = object.getString("overview");
            String poster_path = object.getString("poster_path");

            this.tittle = title;
            this.original_language = language;
            this.overview = overview;
            this.photo = poster_path;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.tittle);
        dest.writeString(this.photo);
        dest.writeString(this.original_language);
        dest.writeString(this.overview);
    }

    protected Movies(Parcel in) {
        this.tittle = in.readString();
        this.photo = in.readString();
        this.original_language = in.readString();
        this.overview = in.readString();
    }

    public static final Creator<Movies> CREATOR = new Creator<Movies>() {
        @Override
        public Movies createFromParcel(Parcel source) {
            return new Movies(source);
        }

        @Override
        public Movies[] newArray(int size) {
            return new Movies[size];
        }
    };
}
