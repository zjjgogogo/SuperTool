package com.jamesjaw.supertool;
 
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class MessageBoxFragementTemp extends Fragment implements OnClickListener{

	
	public MessageBoxFragementTemp()
	{
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		LinearLayout layout = new LinearLayout(getActivity());
		layout.setOrientation(LinearLayout.VERTICAL);
		
		Button btn_add = new Button(getActivity());
		btn_add.setText("Add Num");
		btn_add.setId(0);
		btn_add.setOnClickListener(this); 
		layout.addView(btn_add);
		
		Button btn_notifi = new Button(getActivity());
		btn_notifi.setText("Message Swtich");
		btn_notifi.setId(1);
		btn_notifi.setOnClickListener(this); 
		layout.addView(btn_notifi);
		return layout;
	}

	@Override
	public void onClick(View v) {
		
		if(v.getId() == 1)
		{
			NotificationCenter center = new NotificationCenter(getActivity());
			center.notification();
		}
		else if(v.getId() == 0)
		{
			Intent intent = new Intent(getActivity(), ContactListActivity.class);
			startActivity(intent);
			getActivity().overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
		}
	}
}
