package com.jamesjaw.supertool;
 
import com.jamesjaw.supertool.manager.MessageManager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class MessageBoxFragement extends Fragment implements OnClickListener{

	
	public MessageBoxFragement()
	{
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Button btn = new Button(getActivity());
		btn.setText("Delete 10086");
		btn.setOnClickListener(this);
		return btn;
	}

	@Override
	public void onClick(View v) {
		 
		MessageManager mm = new MessageManager();
		mm.deleteSMS(getActivity(), "", "10086");
	}
}
