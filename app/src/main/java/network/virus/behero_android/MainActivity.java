package network.virus.behero_android;

import android.app.usage.UsageEvents;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
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
import android.util.Base64;
import android.util.EventLog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    private static final int SYSTEM_ALERT_WINDOW_PERMISSION = 2084;

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

        /* for Android Floating Widget */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {
            askPermission();
        }

        /* for Lock Screen */
        startService(new Intent(MainActivity.this, LockScreenService.class));
    }

    /* for Android Floating Widget */
    private void askPermission() {
        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                Uri.parse("package:" + getPackageName()));
        startActivityForResult(intent, SYSTEM_ALERT_WINDOW_PERMISSION);
    }

    /* for Android Floating Widget */
    public void onClick_buttonCreateWidget(View v) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            startService(new Intent(MainActivity.this, FloatingViewService.class));
            finish();
        } else if (Settings.canDrawOverlays(this)) {
            startService(new Intent(MainActivity.this, FloatingViewService.class));
            finish();
        } else {
            askPermission();
            Toast.makeText(this, "You need System Alert Window Permission to do this", Toast.LENGTH_SHORT).show();
        }
    }

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

        WebView tab2_webview;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            // For First tab
            ImageView advertise = (ImageView) rootView.findViewById(R.id.advertise);
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
//            LinearLayout people_1_layout = (LinearLayout) rootView.findViewById(R.id.people_1_layout);
//            LinearLayout people_2_layout = (LinearLayout) rootView.findViewById(R.id.people_2_layout);
            ImageButton btn_location = (ImageButton) rootView.findViewById(R.id.btn_location);
            tab2_webview = (WebView) rootView.findViewById(R.id.tab2_webview);

            // For Second tab - WebView
            // Enable Javascript
            tab2_webview.getSettings().setJavaScriptEnabled(true);
            // Add a WebViewClient
            tab2_webview.setWebViewClient(new WebViewClient() {

                @Override
                public void onPageFinished(WebView view, String url) {
                    // Inject CSS when page is done loading
                    injectCSS();
                    super.onPageFinished(view, url);
                }
            });

            // Load a webpage
            tab2_webview.loadUrl("http://www.safe182.go.kr/api/lcm/findChildListT.do?esntlId=10000190&authKey=f1fd6f8d88b34dcd&rowSize=6");

            // For Third tab
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
//            people_1_layout.setVisibility(View.GONE);
//            people_2_layout.setVisibility(View.GONE);
            btn_location.setVisibility(View.GONE);
            tab2_webview.setVisibility(View.GONE);

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
//                people_1_layout.setVisibility(View.VISIBLE);
//                people_2_layout.setVisibility(View.VISIBLE);
                btn_location.setVisibility(View.VISIBLE);
                tab2_webview.setVisibility(View.VISIBLE);
            }

            // Only for Third tab
            if (getArguments().getInt(ARG_SECTION_NUMBER) == 3) {
                tabLayout.setVisibility(View.VISIBLE);
            }

            return rootView;
        }

        // Inject CSS method: read style.css from assets folder
        // Append stylesheet to document head
        private void injectCSS() {
            try {
                InputStream inputStream = getContext().getAssets().open("style.css");
                byte[] buffer = new byte[inputStream.available()];
                inputStream.read(buffer);
                inputStream.close();
                String encoded = Base64.encodeToString(buffer, Base64.NO_WRAP);
                tab2_webview.loadUrl("javascript:(function() {" +
                        "var parent = document.getElementsByTagName('head').item(0);" +
                        "var style = document.createElement('style');" +
                        "style.type = 'text/css';" +
                        // Tell the browser to BASE64-decode the string into your script !!!
                        "style.innerHTML = window.atob('" + encoded + "');" +
                        "parent.appendChild(style)" +
                        "})()");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void onClick_thanks_more(View view) {
        Intent intent = new Intent(this, ThanksActivity.class);
        startActivity(intent);
    }

    public void onClick_people_1_layout(View view) {
        Intent intent = new Intent(this, ListPopupActivity.class);
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
