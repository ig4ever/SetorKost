package com.rakhmat.setorkost.model;

import java.util.ArrayList;

public class KamarData {
    public static String[][] data = new String[][]{
            {
                "Rumah 30/17C", "Kamar 1", "Rp 350.000"
            },
            {
                "Rumah 31/17C", "Kamar 2", "Rp 450.000"
            },
            {
                    "Rumah 30/17C", "Kamar 1", "Rp 350.000"
            },
            {
                    "Rumah 31/17C", "Kamar 2", "Rp 450.000"
            },
            {
                    "Rumah 30/17C", "Kamar 1", "Rp 350.000"
            },
            {
                    "Rumah 31/17C", "Kamar 2", "Rp 450.000"
            },
            {
                    "Rumah 30/17C", "Kamar 1", "Rp 350.000"
            },
            {
                    "Rumah 31/17C", "Kamar 2", "Rp 450.000"
            }
    };

    public static ArrayList<Kamar> getListData(){
        Kamar kamar = null;
        ArrayList<Kamar> list = new ArrayList<>();
        for (String[] kData : data){
            kamar = new Kamar();
            kamar.setTipeKamar(kData[0]);
            kamar.setNomorKamar(kData[1]);
            kamar.setHargaKamar(kData[2]);
            list.add(kamar);
        }

        return list;
    }
}
