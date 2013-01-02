package com.isep.arqam.voiceit;

import java.io.IOException;
import java.util.Random;

import com.isep.arqam.voiceit.dropbox.DropboxMain;

import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;


/****************************************************************************************
 * MemoRecord
 * - Faz a grava��o de um novo memo
 ***************************************************************************************/
public class MemoRecord extends Activity {

	/** Variaveis globais*/
	private static final String TAG = "MemoRecord";
	private MediaRecorder mRecorder = null;
    private static String mFileName = null;
	
	/************************************************************************************
	 * onCreate
	 ***********************************************************************************/
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_memo_record);
		
		Button btn_stopRecrod = (Button)findViewById(R.id.stopRecord);	
		startRecording();

		/**
		 * Termina a grava��o e inicia activity Save_Temp. Esta activity � temporaria e
		 * deve ser substituida por uma dialogbox nesta activity (RecordMemo) com os
		 * mesmos botoes sim e nao;
		 */
		btn_stopRecrod.setOnClickListener(new View.OnClickListener() {		
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				stopRecording();
				Intent myIntent = new Intent(MemoRecord.this, DropboxMain.class);
				myIntent.putExtra("memoName", mFileName);
				MemoRecord.this.startActivity(myIntent);
			}
		});
	}
	
	/************************************************************************************
	 * onResume
	 ***********************************************************************************/
    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume()" + this);
    }

	/************************************************************************************
	 * onStart
	 ***********************************************************************************/
    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart()" + this);
    }

	/************************************************************************************
	 * onPause
	 ***********************************************************************************/
    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause()" + this);
    }

	/************************************************************************************
	 * onStop
	 ***********************************************************************************/
    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop()" + this);
    }

	/************************************************************************************
	 * onDestroy
	 ***********************************************************************************/
    @Override
    public void onDestroy() {
    	super.onDestroy();
        Log.d(TAG, "onDestroy()" + this);
    }

    /************************************************************************************
	 * startRecording
	 ***********************************************************************************/
    public void startRecording() {
    	
    	Random rand = new Random();
    	int num = rand.nextInt(20);
    	mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        mFileName = Environment.getExternalStorageDirectory().getAbsolutePath();
        mFileName += "/audiorecordtest"+num+".3gp";
        mRecorder.setOutputFile(mFileName);

        
        try {
        	mRecorder.prepare();
        	mRecorder.start();
        } catch (IllegalStateException e) {
        	e.printStackTrace();
        } catch (IOException e) {
        	e.printStackTrace();
        }
    }

    /************************************************************************************
	 * stopRecording
	 ***********************************************************************************/
    private void stopRecording() {
    	mRecorder.stop();
    	mRecorder.reset();
    	mRecorder.release();
    	mRecorder = null;
    }

	/************************************************************************************
	 * onCreateOptionsMenu
	 ***********************************************************************************/
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_record_memo, menu);
		return true;
	}
}
