package com.rick.model;

import java.util.List;
import java.util.ArrayList;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Host;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.ResultSetFuture;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.SimpleStatement;
import com.datastax.driver.core.Statement;

public class MyModel{
    private String ip = "127.0.0.1";
	private String keyspace = "demo";
	private int port = 9042;
	private String table = null;
	private PreparedStatement insertModbus = null;
	private Cluster cluster = null;
	private Session session = null;

    public MyModel(String table){
        this.table = table;
        cluster = Cluster.builder().addContactPoints(ip).withPort(port).build();
		session = cluster.connect(keyspace);
    }

    public ResultSet readRecord(String statement,Object... param){
        // List<Object> re = new ArrayList<>();
        PreparedStatement IM = session.prepare(statement);
        ResultSet rs = session.execute(IM.bind(param));
		//String cqlStatement = "SELECT * FROM local";
        // for (Row row : session.execute(IM.bind(param))) {
        //     // System.out.println(row.getString(0));
        //     // re.add(row);
		// }
        return rs;
	}

}
