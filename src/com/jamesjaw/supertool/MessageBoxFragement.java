package com.jamesjaw.supertool;
 
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class MessageBoxFragement extends Fragment implements OnClickListener{

	
	public MessageBoxFragement()
	{
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		LinearLayout layout = new LinearLayout(getActivity());
		layout.setOrientation(LinearLayout.VERTICAL);
		Button btn_notifi = new Button(getActivity());
		btn_notifi.setText("Notification");
		btn_notifi.setId(0);
		btn_notifi.setOnClickListener(this);
	  
		layout.addView(btn_notifi);
		return layout;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		NotificationCenter center = new NotificationCenter(getActivity());
		center.notification();
	}
}
