package com.example.akshatsharma.medifinder;

/**
 * Created by akshatSharma on 29-Sep-15.
 */
public class navList1 {

    private String name;
    private int iconId;

    public navList1(String name,int iconId) {
        this.iconId = iconId;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getIconId() {
        return iconId;
    }
}
