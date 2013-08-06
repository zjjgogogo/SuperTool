package com.jamesjaw.supertool;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.jamesjaw.supertool.file.PreferencesSave;
import com.jamesjaw.supertool.manager.LockScreenManager;

public class SwticherFragment extends Fragment implements OnCheckedChangeListener {
  
	public SwticherFragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
 
		View view = inflater.inflate(R.layout.switchers_fragment, null);
		
		Switch mWifi = (Switch)view.findViewById(R.id.switch_wifi);
		Switch mLockscreen = (Switch)view.findViewById(R.id.switch_lockscreen);
		Switch mSms = (Switch)view.findViewById(R.id.switch_sms);
		
		mWifi.setChecked(PreferencesSave.getBoolean(getActivity(),
				getActivity().getString(R.string.network_preferences_tag), false));
		mWifi.setOnCheckedChangeListener(this);
		mLockscreen.setChecked(PreferencesSave.getBoolean(getActivity(),
				getActivity().getString(R.string.lock_screen_preferences_tag), false));
		mLockscreen.setOnCheckedChangeListener(this);
		mSms.setChecked(PreferencesSave.getBoolean(getActivity(),
				getActivity().getString(R.string.sms_receive_preferences_tag), false));
		mSms.setOnCheckedChangeListener(this);
		
		return view;
	}
 

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
	
		if (buttonView.getId() == R.id.switch_lockscreen) {

			PreferencesSave.saveBoolean(getActivity(), getActivity().getString(R.string.lock_screen_preferences_tag), isChecked);

		} else if (buttonView.getId() == R.id.switch_wifi) {

			PreferencesSave.saveBoolean(getActivity(), getActivity().getString(R.string.network_preferences_tag), isChecked);
			
		} else if (buttonView.getId() == R.id.switch_sms) {
		
			PreferencesSave.saveBoolean(getActivity(), getActivity().getString(R.string.sms_receive_preferences_tag), isChecked);
		}
		
		NotificationCenter center = new NotificationCenter(getActivity());
		center.notification();
		
	}

}
