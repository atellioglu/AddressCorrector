package com.infinidium;

import com.infinidium.model.City;
import com.infinidium.model.State;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader("TurkeyCities"));
        String str;
        List<String> cities = new ArrayList<>();
        while((str=reader.readLine())!=null){
            cities.add(str);
        }
        reader.close();
        AddressHandler addressHandler = AddressHandler.getInstance();
        City[] cities1 = new City[cities.size()];
        for(int i =0;i<cities.size();i++){
            if(cities.get(i).equals("İstanbul")){
                State[] states = new State[10];
                states[0] = new State("Beşiktaş",null);
                states[1] = new State("Mecidiyeköy",null);
                states[2] = new State("Şişli",null);
                states[3] = new State("Arnavutköy",null);
                states[4] = new State("Bebek",null);
                states[5] = new State("Halıcıoğlu",null);
                states[6] = new State("Gaziosmanpaşa",null);
                states[7] = new State("Taksim",null);
                states[8] = new State("Eminönü",null);
                states[9] = new State("Okmeydanı",null);
                cities1[i] = new City(cities.get(i),states);
            }else if(cities.get(i).equals("Ankara")){
                State[] states = new State[5];
                states[0] = new State("Kızılay",null);
                states[1] = new State("Sincan",null);
                states[2] = new State("Çankaya",null);
                states[3] = new State("Arnavutköy",null);
                states[4] = new State("Bebek",null);
                cities1[i] = new City(cities.get(i),states);
            }else{
                cities1[i] = new City(cities.get(i),null);
            }

        }
        addressHandler.setCities(cities1);
        addressHandler.setPlainAddress("Zambak sokak yesil kosk apt. no:1/19 İstanbul BesktsŞ");

    }
}
