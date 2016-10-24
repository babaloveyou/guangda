package com.android.thinkive.invest_sd.activities;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.android.thinkive.invest_sd.R;
import com.android.thinkive.invest_sd.widget.MediaController;

public class VideoPlayerActivity extends Activity implements OnTouchListener {

	// private
	MediaController mController;
	VideoView viv;
	int progress = 0;

	private String mTitle="",mMediaUrl="";
	private TextView mTitleView;
	private ImageView mBack;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.player);
		viv = (VideoView) findViewById(R.id.videoView);
		try {
			mTitle = getIntent().getStringExtra("title");
			mMediaUrl = getIntent().getStringExtra("mediaUrl");
		}catch (Exception e){
			mTitle = "视频播放";
			mMediaUrl = "http://61.144.205.111:8800/upload/20150730/test1.mp4";
		}
		mTitleView = (TextView)findViewById(R.id.title);
		mTitleView.setText(mTitle);
		mBack = (ImageView)findViewById(R.id.back);
		mBack.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		mController = new MediaController(this);
		viv.setMediaController(mController);
		// String videopath=getIntent().getStringExtra("path");
		// if (videopath!=null)
		// {
		// viv.setVideoPath(videopath);
		// }
		//String url = "http://61.144.205.111:8800/upload/20150730/test1.mp4";
		viv.setVideoURI(Uri.parse(mMediaUrl));
//		viv.setVideoURI(Uri.parse("android.resource://" + getPackageName()
//				+ "/" + R.raw.videoviewdemo));
		viv.requestFocus();
		viv.start();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		progress = viv.getCurrentPosition();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		viv.seekTo(progress);
		viv.start();
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		return false;

	}


}
