package com.jamesjaw.supertool.receiver;

import android.app.admin.DeviceAdminReceiver;
import android.content.Context;
import android.content.Intent;

import com.jamesjaw.supertool.R;
import com.jamesjaw.supertool.file.PreferencesSave;
import com.jamesjaw.supertool.manager.LockScreenManager;
import com.jamesjaw.supertool.utils.Debug;
 

public class LockScreenReceiver extends DeviceAdminReceiver {

	@Override 
    public void onReceive(Context context, Intent intent) { 
        super.onReceive(context, intent); 
        
        catchActionAndDoSomething(context, intent);
        
    } 
   
    @Override 
    public void onEnabled(Context context, Intent intent) { 
        
        super.onEnabled(context, intent);
        
        Debug.show("LockScreenReceiver : onEnabled");
        
        PreferencesSave.saveBoolean(context, context.getString(R.string.lock_screen_preferences_tag), true);
         
    } 
   
    @Override 
    public void onDisabled(Context context, Intent intent) { 
        
        super.onDisabled(context, intent); 
         
        Debug.show("LockScreenReceiver : onDisabled");
        
        PreferencesSave.saveBoolean(context, context.getString(R.string.lock_screen_preferences_tag), false);
        
        
    } 
	
    
    protected void catchActionAndDoSomething(Context context,Intent intent)
    {
    	 	if(intent != null)
    	 	{
    	 		String action = intent.getAction();
    	 		Debug.show("LockScreenReceiver : "  + action);
    	 		if(action != null && action.equals(context.getString(R.string.lock_screen_action)))
    	 		{
    	 			LockScreenManager.lockScreen(context);
    	 		}
    	 	}
    }
}
