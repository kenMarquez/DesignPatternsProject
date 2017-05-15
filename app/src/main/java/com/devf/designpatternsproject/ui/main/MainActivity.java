package com.devf.designpatternsproject.ui.main;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.devf.designpatternsproject.R;
import com.devf.designpatternsproject.contador.ColoniaCodigoPostal;
import com.devf.designpatternsproject.contador.ColoniaCodigoPostalImplementacionArchivos;
import com.devf.designpatternsproject.contador.DescuentosAdicionalesCodigoPostal;
import com.devf.designpatternsproject.contador.DescuentosServiciosDBImplementacionArchivos;
import com.devf.designpatternsproject.contador.FabricaCodigosPostales;
import com.devf.designpatternsproject.excepciones.ServicioInexistenteExcepcion;
import com.devf.designpatternsproject.models.CodigoPostal;
import com.devf.designpatternsproject.models.Colonia;
import com.devf.designpatternsproject.models.DescuentoAdicional;
import com.devf.designpatternsproject.models.DescuentosServicios;
import com.devf.designpatternsproject.models.Promotion;
import com.devf.designpatternsproject.ui.promodescription.PromoDescriptionActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    public Toolbar toolbar;

    @BindView(R.id.tabs)
    public TabLayout tabLayout;

    @BindView(R.id.viewpager)
    public ViewPager viewPager;
    private AppBarLayout appBarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        settingsToolbar("Promos");
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        algo();
    }

    private void algo() {

        FabricaCodigosPostales fabrica = new FabricaCodigosPostales();
        CodigoPostal codigo = fabrica.creaCodigoPostal("Mexico", "55717");
        ColoniaCodigoPostal obtenerColonia = new ColoniaCodigoPostalImplementacionArchivos(MainActivity.this);
        Colonia coloniaTemp = obtenerColonia.colonia(codigo);
        System.out.println(coloniaTemp);
        DescuentosServiciosDBImplementacionArchivos todos = new DescuentosServiciosDBImplementacionArchivos();
        try {
            List<DescuentosServicios> descuentos = todos.leerDescuentos(MainActivity.this);
            for (DescuentosServicios descuento : descuentos) {
                System.out.println("Todos los Descuentos:");
                System.out.println(descuento);
            }
            System.out.println("\n\n");
            DescuentosServicios s = descuentos.get(0);
            DescuentosAdicionalesCodigoPostal desAd = new DescuentosAdicionalesCodigoPostal();
            if (coloniaTemp != null) {

                if (DescuentosAdicionalesCodigoPostal.tieneDescuentoAdiccional(MainActivity.this, coloniaTemp.getCodigoPostal().getCodigoPostal(), s.getServicio())) {
                    System.out.print("El servicio " + s.getServicio().getNombreServicio() + " Tiene descuento Adicional y el total es :");
                    DescuentoAdicional descuentoFinal = desAd.regresaDescuentoAdicional(MainActivity.this, coloniaTemp.getCodigoPostal().getCodigoPostal(), s.getServicio(), s);
                    System.out.println(descuentoFinal.precioFinal());
                } else {
                    System.out.println("No tiene descuento Adicional pero el total es de" + s.precioFinal());
                }
            } else {
                System.out.println("El codigo Postal no concuerda con alguan colonia registrada");
            }

        } catch (ServicioInexistenteExcepcion ex) {
            System.out.println(ex);
        }
    }


    protected void settingsToolbar(String title) {
        appBarLayout = (AppBarLayout) findViewById(R.id.app_bar_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(title);
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(CategoryFragment.newInstance(), "Deportes");
        adapter.addFragment(CategoryFragment.newInstance(), "Tecnología");
        adapter.addFragment(CategoryFragment.newInstance(), "Restaurantes");
        viewPager.setAdapter(adapter);
        selectPage(1);
    }

    void selectPage(int pageIndex) {
        tabLayout.setScrollPosition(pageIndex, 0f, true);
        viewPager.setCurrentItem(pageIndex);
    }


    private class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager childFragmentManager) {
            super(childFragmentManager);
        }


        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }


}
