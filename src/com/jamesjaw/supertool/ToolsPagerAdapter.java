package com.jamesjaw.supertool;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

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
			
			Fragment fragment = new SwticherFragment();
			return fragment; 
		} 
		else if(position == 1)
		{
			Fragment fragment = new MessageBoxFragement();
			return fragment;
		}
		else if(position == 2)
		{
			Fragment fragment = new NumberListFragment();
			return fragment;
		}
		return null;
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
			return context.getString(R.string.title_section1);
		case 1:
			return context.getString(R.string.title_section2);
		case 2:
			return context.getString(R.string.title_section3);
		}
		return null;
	}
}
