package com.jamesjaw.supertool;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.jamesjaw.supertool.receiver.LockScreenReceiver;

public class LockScreenFragment extends Fragment implements OnClickListener {

	private DevicePolicyManager policyManager;

	private ComponentName componentName;

	public LockScreenFragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		LinearLayout layout = new LinearLayout(getActivity());
		layout.setOrientation(LinearLayout.VERTICAL);
		Button btn_lock = new Button(getActivity());
		btn_lock.setText("Lock");
		btn_lock.setId(0);
		btn_lock.setOnClickListener(this);
		Button btn_unlock = new Button(getActivity());
		btn_unlock.setText("Unlock");
		btn_unlock.setId(1);
		btn_unlock.setOnClickListener(this);
		
		Button btn_closeScreen= new Button(getActivity());
		btn_closeScreen.setText("Switch");
		btn_closeScreen.setId(2);
		btn_unlock.setOnClickListener(this);
		
		layout.addView(btn_lock);
		layout.addView(btn_unlock);
		layout.addView(btn_closeScreen);
		return layout;
	}

	@Override
	public void onClick(View v) {

		policyManager = (DevicePolicyManager) getActivity().getSystemService(
				Context.DEVICE_POLICY_SERVICE);
		componentName = new ComponentName(getActivity(),
				LockScreenReceiver.class);

		if (v.getId() == 1) {
			
			LockScreenManager.cancelAuth(getActivity()); 
			
		} else if (v.getId() == 0) {
			
			LockScreenManager.getAuth(getActivity()); 
		}
		else if(v.getId() == 2)
		{
			NotificationCenter center = new NotificationCenter(getActivity());
			center.notification();
		}
	}
 
}
