package com.rakhmat.setorkost;

import java.util.ArrayList;

public class PenghuniData {
    public static String[][] data = new String[][]{
            {
                    "Saykoji", "22", "Penyanyi", "Kamar 2", "1 Januari 2013"
            },
            {
                    "Ferguso", "24", "Artis", "Kamar 3", "1 Januari 2014"
            },
            {
                    "Saykoji", "22", "Penyanyi", "Kamar 2", "1 Januari 2013"
            },
            {
                    "Ferguso", "24", "Artis", "Kamar 3", "1 Januari 2014"
            },
            {
                    "Saykoji", "22", "Penyanyi", "Kamar 2", "1 Januari 2013"
            },
            {
                    "Ferguso", "24", "Artis", "Kamar 3", "1 Januari 2014"
            },
            {
                    "Saykoji", "22", "Penyanyi", "Kamar 2", "1 Januari 2013"
            },
            {
                    "Ferguso", "24", "Artis", "Kamar 3", "1 Januari 2014"
            }
    };

    public static ArrayList<Penghuni> getListData(){
        Penghuni penghuni = null;
        ArrayList<Penghuni> list = new ArrayList<>();
        for (String[] kData : data){
            penghuni = new Penghuni();
            penghuni.setNama(kData[0]);
            penghuni.setUmur(kData[1]);
            penghuni.setPekerjaan(kData[2]);
            penghuni.setNomorKamar(kData[3]);
            penghuni.setTanggalMasuk(kData[4]);
            list.add(penghuni);
        }

        return list;
    }
}
