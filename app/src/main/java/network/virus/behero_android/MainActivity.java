package network.virus.behero_android;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            // Create the adapter that will return a fragment for each of the three
            // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

    }


    /*@Override
    public boolean onCreateOption;
    public boolean onOptionsItemSelected(MenuItem item)sMenu(Menu menu); {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
*/
    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            // For First tab
            TextView advertise = (TextView) rootView.findViewById(R.id.advertise);
            LinearLayout thanks_textlayout = (LinearLayout) rootView.findViewById(R.id.thanks_textlayout);
            LinearLayout thanks_imagelayout_1 = (LinearLayout) rootView.findViewById(R.id.thanks_imagelayout_1);
            LinearLayout thanks_imagelayout_2 = (LinearLayout) rootView.findViewById(R.id.thanks_imagelayout_2);
            LinearLayout will_textlayout = (LinearLayout) rootView.findViewById(R.id.will_textlayout);
            LinearLayout will_imagelayout_1 = (LinearLayout) rootView.findViewById(R.id.will_imagelayout_1);
            LinearLayout will_imagelayout_2 = (LinearLayout) rootView.findViewById(R.id.will_imagelayout_2);
            LinearLayout bottom_padding = (LinearLayout) rootView.findViewById(R.id.bottom_padding);

            TextView will_more = (TextView) rootView.findViewById(R.id.will_more);
            will_more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // TODO
                    Toast.makeText(getActivity(), "내일은 다시 만날 수 있겠지 더보기", Toast.LENGTH_LONG).show();
                }
            });

            // For Second tab
            LinearLayout tab2_searchlayout = (LinearLayout) rootView.findViewById(R.id.tab2_searchlayout);
            LinearLayout people_1_layout = (LinearLayout) rootView.findViewById(R.id.people_1_layout);
            LinearLayout people_2_layout = (LinearLayout) rootView.findViewById(R.id.people_2_layout);
            ImageButton btn_location = (ImageButton) rootView.findViewById(R.id.btn_location);

            TabLayout tabLayout = (TabLayout) rootView.findViewById(R.id.btn_reward_tab);

            // For First tab
            advertise.setVisibility(View.GONE);
            thanks_textlayout.setVisibility(View.GONE);
            thanks_imagelayout_1.setVisibility(View.GONE);
            thanks_imagelayout_2.setVisibility(View.GONE);
            will_textlayout.setVisibility(View.GONE);
            will_imagelayout_1.setVisibility(View.GONE);
            will_imagelayout_2.setVisibility(View.GONE);
            bottom_padding.setVisibility(View.GONE);

            // For Second tab
            tab2_searchlayout.setVisibility(View.GONE);
            people_1_layout.setVisibility(View.GONE);
            people_2_layout.setVisibility(View.GONE);
            btn_location.setVisibility(View.GONE);

            // For Third tab
            tabLayout.setVisibility(View.GONE);

            // Only for First tab
            if (getArguments().getInt(ARG_SECTION_NUMBER) == 1) {
                advertise.setVisibility(View.VISIBLE);
                thanks_textlayout.setVisibility(View.VISIBLE);
                thanks_imagelayout_1.setVisibility(View.VISIBLE);
                thanks_imagelayout_2.setVisibility(View.VISIBLE);
                will_textlayout.setVisibility(View.VISIBLE);
                will_imagelayout_1.setVisibility(View.VISIBLE);
                will_imagelayout_2.setVisibility(View.VISIBLE);
                bottom_padding.setVisibility(View.VISIBLE);
            }

            // Only for Second tab
            if (getArguments().getInt(ARG_SECTION_NUMBER) == 2) {
                tab2_searchlayout.setVisibility(View.VISIBLE);
                people_1_layout.setVisibility(View.VISIBLE);
                people_2_layout.setVisibility(View.VISIBLE);
                btn_location.setVisibility(View.VISIBLE);
            }

            // Only for Third tab
            if (getArguments().getInt(ARG_SECTION_NUMBER) == 3) {
                tabLayout.setVisibility(View.VISIBLE);
            }

            return rootView;
        }
    }

    public void onClick_thanks_more(View view) {
        Intent intent = new Intent(this, ThanksActivity.class);
        startActivity(intent);
    }

    public void onClick_goto_openapi(View view) {
        Intent intent = new Intent(this, OpenapiActivity.class);
        startActivity(intent);
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
}

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }
}
}
