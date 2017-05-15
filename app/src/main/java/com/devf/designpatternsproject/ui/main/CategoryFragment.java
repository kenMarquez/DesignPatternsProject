package com.devf.designpatternsproject.ui.main;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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


/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment implements OnItemSelected {

    @BindView(R.id.promo_rv)
    RecyclerView rvPromo;

    private List<Promotion> promotions = new ArrayList<>();

    public static CategoryFragment newInstance() {
        CategoryFragment fragment = new CategoryFragment();
        return fragment;
    }


    public CategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        ButterKnife.bind(this, view);
        getData();
        settingsRecyclerView();
        return view;
    }

    private void settingsRecyclerView() {

        PromoAdapter adapterPromo = new PromoAdapter(promotions, this);
        rvPromo.setAdapter(adapterPromo);
        rvPromo.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rvPromo.setHasFixedSize(true);
    }

    private void getData() {

        FabricaCodigosPostales fabrica = new FabricaCodigosPostales();
        CodigoPostal codigo = fabrica.creaCodigoPostal("Mexico", "55717");
        ColoniaCodigoPostal obtenerColonia = new ColoniaCodigoPostalImplementacionArchivos(getActivity());
        Colonia coloniaTemp = obtenerColonia.colonia(codigo);
        System.out.println(coloniaTemp);
        DescuentosServiciosDBImplementacionArchivos todos = new DescuentosServiciosDBImplementacionArchivos();

        try {
            List<DescuentosServicios> descuentos = todos.leerDescuentos(getActivity());
            for (DescuentosServicios descuento : descuentos) {
                System.out.println("Todos los Descuentos:");
                System.out.println(descuento);

            }

            for (int i = 0; i < descuentos.size(); i++) {
                promotions.add(new Promotion(
                        descuentos.get(i).getServicio().getNombreServicio(),
                        "url",
                        getString(R.string.item_promo_text_description),
                        descuentos.get(i).getServicio().getCosto(),
                        descuentos.get(i).getDescuento() + "",
                        "uslImage",
                        "Best Buy"));
            }
            System.out.println("\n\n");
            DescuentosServicios s = descuentos.get(0);
            DescuentosAdicionalesCodigoPostal desAd = new DescuentosAdicionalesCodigoPostal();
            if (coloniaTemp != null) {

                if (DescuentosAdicionalesCodigoPostal.tieneDescuentoAdiccional(getActivity(), coloniaTemp.getCodigoPostal().getCodigoPostal(), s.getServicio())) {
                    System.out.print("El servicio " + s.getServicio().getNombreServicio() + " Tiene descuento Adicional y el total es :");
                    DescuentoAdicional descuentoFinal = desAd.regresaDescuentoAdicional(getActivity(), coloniaTemp.getCodigoPostal().getCodigoPostal(), s.getServicio(), s);
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

    @Override
    public void onItemSelected(Promotion promo) {
        Intent intent = new Intent(getActivity(), PromoDescriptionActivity.class);
        intent.putExtra("promo", promo);
        startActivity(intent);
    }

}
