package etec.com.ewen.matheus.appplantae.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import etec.com.ewen.matheus.appplantae.MenusActivity;
import etec.com.ewen.matheus.appplantae.R;
import etec.com.ewen.matheus.appplantae.VPAdapter;

public class TelaGuias extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_guias);

        getSupportActionBar().hide();


        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewpager);

        tabLayout.setupWithViewPager(viewPager);

        VPAdapter vpAdapter = new VPAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpAdapter.addFragment(new fragment1(), "INICIO");
        vpAdapter.addFragment(new fragment2(),"ADUBO");
        vpAdapter.addFragment(new fragment3(),"CARNÍVORAS");
        vpAdapter.addFragment(new fragment4(),"PRAGAS");
        vpAdapter.addFragment(new fragment5(),"INCIDÊNCIA");
        vpAdapter.addFragment(new fragment6()," VASOS");
        viewPager.setAdapter(vpAdapter);

    }

    @Override
    public void onBackPressed() {
        Intent it = new Intent(TelaGuias.this, MenusActivity.class);
        startActivity(it);
    }
}