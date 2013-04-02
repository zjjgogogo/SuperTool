package com.jamesjaw.supertool;

import com.jamesjaw.supertool.file.PreferencesSave;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class NetWorkFragment extends Fragment implements OnClickListener{

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		LinearLayout layout = new LinearLayout(getActivity());
		layout.setOrientation(LinearLayout.VERTICAL);
		Button btn_wifi_switch = new Button(getActivity());
		btn_wifi_switch.setText("Wifi Switch");
		btn_wifi_switch.setId(0);
		btn_wifi_switch.setOnClickListener(this);
		 
		layout.addView(btn_wifi_switch); 
		return layout;
	}

	@Override
	public void onClick(View v) {
	 
		if(v.getId() == 0)
		{
			boolean value = PreferencesSave.getBoolean(getActivity(),
					this.getString(R.string.network_preferences_tag), false);
			
			PreferencesSave.saveBoolean(getActivity(), this.getString(R.string.network_preferences_tag), !value);

			NotificationCenter center = new NotificationCenter(getActivity());
			center.notification();
		}
		
	}
	
}
