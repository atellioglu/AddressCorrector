package com.infinidium;

import com.infinidium.model.City;
import com.infinidium.model.State;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AddressHandler {
    private static AddressHandler singleton = null;
    public static AddressHandler getInstance(){
        if(singleton == null)
            singleton = new AddressHandler();
        return singleton;
    }
    private AddressHandler(){
        rightKeywords = new HashMap<>();
        allkeywords = new HashMap<>();
        cityHashMap = new HashMap<>();
        statesHashMap = new HashMap<>();
    }
    //otomatik duzeltmeler icin yapildi!
    private HashMap<String,String> rightKeywords;
    private HashMap<Integer,List<String>> allkeywords;
    private HashMap<String,City> cityHashMap;
    private HashMap<String,State> statesHashMap;// burasi liste olabilir. birden fazla state olabilir dikkat et!
    private City[] mCities;

    public void addRightKeyword(String wrong,String correct){
        rightKeywords.put(wrong,correct);
    }
    public void setCities(City[] cities){
        mCities = cities;
        for(int i =0;i<cities.length;i++) {
            cityHashMap.put(cities[i].getCityName(),cities[i]);
            addKeyword(cities[i].getCityName());
            if(cities[i].getStates()!=null){
                for(int j =0;j<cities[i].getStates().length;j++){
                    State state = cities[i].getStates()[j];
                    addKeyword(state.getState());
                    statesHashMap.put(state.getState(),state);
                }
            }
        }
        System.out.println("TEST");
    }
    private void addKeyword(String str){
        String lowered = Util.lowerAll(str);
        addToMap(Hasher.firstAndLastCharacterString(lowered),lowered);
    }
    private void addToMap(int id,String str){
        if(!allkeywords.containsKey(id)){
            allkeywords.put(id,new ArrayList<>());
        }
        allkeywords.get(id).add(str);
    }
    public void setPlainAddress(String address) {
        String loweredAddress = Util.lowerAll(address);
        System.out.println(loweredAddress);
        String[] splitted = loweredAddress.split(" ");
        for(int i =0;i<splitted.length;i++){
            String str = splitted[i];
            applyEditDistance(str);
        }
    }
    public void applyEditDistance(String str){
        //hash fonksiyonu arttikca score degiskeni de artacak.
        int[] scores = new int[1];
        int[] maximumIndexes = new int[1];
        for(int i =0;i<scores.length;i++){
            scores[i] = Integer.MAX_VALUE;
        }
        int hash1 = Hasher.firstAndLastCharacterString(str);
        List<String> possibilities = allkeywords.get(hash1);
        if(possibilities!=null){
            for(int i =0;i<possibilities.size();i++){
                int editScore = editDistance(possibilities.get(i),str);
                if(editScore<scores[0]){
                    scores[0] = editScore;
                    maximumIndexes[0] = i;
                }
                //System.out.println(editScore + " "+possibilities.get(i));
            }
            System.out.println("Current string :"+str);
            System.out.println("Minimum score :"+scores[0]);
            System.out.println("Minimum score value :"+possibilities.get(maximumIndexes[0]));
            System.out.println("----------");

        }else{
            System.out.println("Has no possibility :"+str);
            System.out.println("-----------");
        }

    }
    public static class Hasher{
        /**
         * hashing function,, multiply first character with 61 and sum it with last character.
         * @param str
         * @return
         */
        public static int firstAndLastCharacterString(String str){
            if(str==null){
                return 0;
            }else{
                return (str.charAt(str.length()-1) + str.charAt(0)*61);
            }
        }

    }
    private int editDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();

        int[][] dp = new int[len1 + 1][len2 + 1];

        for (int i = 0; i <= len1; i++) {
            dp[i][0] = i;
        }

        for (int j = 0; j <= len2; j++) {
            dp[0][j] = j;
        }

        //iterate though, and check last char
        for (int i = 0; i < len1; i++) {
            char c1 = word1.charAt(i);
            for (int j = 0; j < len2; j++) {
                char c2 = word2.charAt(j);

                //if last two chars equal
                if (c1 == c2) {
                    //update dp value for +1 length
                    dp[i + 1][j + 1] = dp[i][j];
                } else {
                    int replace = dp[i][j] + 1;
                    int insert = dp[i][j + 1] + 1;
                    int delete = dp[i + 1][j] + 1;

                    int min = replace > insert ? insert : replace;
                    min = delete > min ? min : delete;
                    dp[i + 1][j + 1] = min;
                }
            }
        }

        return dp[len1][len2];
    }
}
