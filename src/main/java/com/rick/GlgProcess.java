package com.rick;

import java.util.Random;
import java.util.TimerTask;
import javax.swing.*;
import java.util.Timer;
import java.awt.event.*;
import com.genlogic.*;

import com.rick.model.slaveData;

class GlgProcess{
    Timer timer = null;
    TimerTask task = null;
    GlgJBean glg_bean = null;
    Random ran = new Random();

    slaveData sd = new slaveData();

    public GlgProcess(){

        timer = new Timer();
        //make sure GUI thread and Logic thread is separate
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                startGui();
                setValue();
            }
        });

    }
    public void startGui(){
        sd.readMaxRecord();

        JFrame frame = new JFrame();
    	frame.setResizable( true );
    	frame.setSize( 600, 400 );

    	// Create a GlgBean component
    	glg_bean = new GlgJBean();

    	// Set a GLG drawing name to be displayed in a GLG bean
    	glg_bean.SetDrawingName( "template/tmp.g" );

    	// Add glg_bean component to a frame
    	frame.getContentPane().add( glg_bean );
    	frame.show();
    }

    public void setValue(){
        task = new TimerTask(){
            @Override
            public void run(){
                Double v = (Double)((ran.nextInt(10)+1)/10.0);
                // System.out.println(v);
                glg_bean.SetDResource( "$Widget/DataGroupOne/EntryPoint",v );
                glg_bean.Update();
            }
        };
        timer.schedule(task,1000,2000);
    }


}
