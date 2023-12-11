package edu.augustana.cards;

import com.opencsv.bean.CsvBindAndSplitByName;
import com.opencsv.bean.CsvBindByName;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Card implements java.io.Serializable{

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

    @CsvBindAndSplitByName(elementType = String.class, column = "Level", required = true)
    private ArrayList<String> level;

    @CsvBindAndSplitByName(elementType = String.class, splitOn = "[,/]", column = "Equipment", required = true)
    private ArrayList<String> equipment;

    @CsvBindAndSplitByName(elementType = String.class, column = "Keywords", splitOn = "\\,", required = true)
    private ArrayList<String> keyWords;


    /**
     *
     * @return image of the current card
     */
    public String getImage(){
        return image;
    }

    /**
     *
     * @return string including all elements of the card
     */
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
    public String getCode() {
        return code;
    }

    public String getEvent() {
        return event;
    }

    public String getCategory() {
        return category;
    }

    public String getTitle() {
        return title;
    }

    public String getPackFolder() {
        return packFolder;
    }

    public char getGender() {
        return gender;
    }

    public char getModelSex() {
        return modelSex;
    }

    public ArrayList<String> getLevel() {
        return level;
    }

    public ArrayList<String> getEquipment() {
        return equipment;
    }

    public ArrayList<String> getKeyWords() {
        return keyWords;
    }


}
