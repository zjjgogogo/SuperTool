package com.jamesjaw.supertool;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.jamesjaw.supertool.SuperToolsActivity.DummySectionFragment;

public class ToolsPagerAdapter extends FragmentPagerAdapter {

	Context context;

	public ToolsPagerAdapter(FragmentManager fm, Context context) {
		super(fm);
		this.context = context;
	}

	@Override
	public Fragment getItem(int position) {
		// getItem is called to instantiate the fragment for the given page.
		// Return a DummySectionFragment (defined as a static inner class
		// below) with the page number as its lone argument.

		if (position == 0) {
			
			Fragment fragment = new LockScreenFragment();
			return fragment;
			
		}
		
		else if(position == 1)
		{
			Fragment fragment = new MessageBoxFragement();
			return fragment;
		}
		else if(position == 2)
		{
			Fragment fragment = new NetWorkFragment();
			return fragment;
		}
		else {
			Fragment fragment = new DummySectionFragment();
			Bundle args = new Bundle();
			args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
			fragment.setArguments(args);
			return fragment;
		}
	}

	@Override
	public int getCount() {
		// Show 3 total pages.
		return 3;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		switch (position) {
		case 0:
			return context.getString(R.string.title_section1).toUpperCase();
		case 1:
			return context.getString(R.string.title_section2).toUpperCase();
		case 2:
			return context.getString(R.string.title_section3).toUpperCase();
		}
		return null;
	}
}
