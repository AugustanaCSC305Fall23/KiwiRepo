package edu.augustana;

import com.opencsv.bean.CsvBindByName;
import java.io.FileNotFoundException;

public class Card {

    @CsvBindByName(column = "CODE", required = true)
    private String code;

    @CsvBindByName(column = "Event", required = true )
    private String event;

    @CsvBindByName(column = "Category", required = true)
    private String category;

    @CsvBindByName(column = "Title", required = true)
    private String title;

    @CsvBindByName(column = "Pack Folder", required = true)
    private String packFolder;

    @CsvBindByName(column = "Image", required = true)
    private String image;

    @CsvBindByName(column = "Gender", required = true)
    private char gender;

    @CsvBindByName(column = "Model Sex", required = true)
    private char modelSex;

    @CsvBindByName(column = "Level", required = true)
    private String level;

    @CsvBindByName(column = "Equipment", required = true)
    private String equipment;

    @CsvBindByName(column = "Keywords", required = true)
    private String keyWords;

    public String getImage(){
        return image;
    }

    @Override
    public String toString(){
        return "Code: "+code+"\n " +
                "Event: "+event+"\n " +
                "Category: "+category+"\n " +
                "Title: "+title+"\n " +
                "PackFolder: "+packFolder+"\n " +
                "Image: "+image+"\n " +
                "Gender: "+gender+"\n "+
                "Model sex: "+modelSex+"\n " +
                "Level: "+level+"\n " +
                "Equipment: "+equipment+"\n " +
                "Keyword: "+keyWords+"\n \n";
    }

}
