package com.rick.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import com.datastax.driver.core.Row;
import com.datastax.driver.core.ResultSet;

import org.json.JSONArray;
import org.json.JSONObject;

public class slaveData{
    MyModel mymodel = null;
    public slaveData(){
        mymodel = new MyModel("slaveData");
    }
    public Object readMaxTime(){
        ResultSet t = mymodel.readRecord("SELECT MAX(toTimestamp(id)) FROM slaveData;");
        // System.out.println(t.one());
        return t.one().getObject(0);
    }

    public List readMaxRecord(){
        List<Object> re = new ArrayList<>();
        // System.out.println(new Timestamp(Calendar.getInstance(TimeZone.getTimeZone("GMT")).getTimeInMillis()));
        String now = new Timestamp(System.currentTimeMillis()-(8*3600*1000)-1000).toString();
        ResultSet t = mymodel.readRecord("SELECT * FROM slaveData WHERE id>maxTimeuuid('"+now+"+0000') LIMIT 1 ALLOW FILTERING");
        String data = t.one().getString("data");
        try{
            JSONObject json = new JSONObject(data);
            System.out.println(json);
            System.out.println(json.getInt("19"));
            // System.out.println(json.getValueType("19"));
        }catch(Exception err){
            err.printStackTrace();
        }

        // for(Row row : t){
        //     System.out.println(row.getString("data"));
        // }
        return re;
    }

}
