package com.devf.designpatternsproject.ui.promodescription;

import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
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
import com.devf.designpatternsproject.ui.main.MainActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PromoDescriptionActivity extends AppCompatActivity {

    private Promotion mPromotion;

    @BindView(R.id.toolbar)
    public Toolbar toolbar;

    @BindView(R.id.description_tv_title)
    TextView tvTitle;

    @BindView(R.id.description_tv_promo)
    TextView tvPromo;

    @BindView(R.id.tv_cosete)
    TextView tvCoste;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promo_description);
        ButterKnife.bind(this);
        getData();
        settingsToolbar(mPromotion.getStoreName());

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void getData() {
        Bundle extra = getIntent().getExtras();
        mPromotion = (Promotion) extra.get("promo");

        tvCoste.setText("Precio total: \n" + mPromotion.getPrice());
        tvTitle.setText(mPromotion.getTitle());
        tvPromo.setText(mPromotion.getDiscount() + "%");
    }


    protected void settingsToolbar(String title) {


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setTitle(title);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @OnClick(R.id.description_tv_obtener)
    public void obtener() {
        new MaterialDialog.Builder(this)

                .customView(R.layout.dialog_user_data, true)
                .positiveText(R.string.dialog_aceptar)
                .positiveColorRes(R.color.colorPrimary)
                .negativeColorRes(R.color.color_text_promotion)
                .negativeText(R.string.dialog_cancelar)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        EditText inputPais = (EditText) dialog.findViewById(R.id.input_pais);
                        EditText inputCp = (EditText) dialog.findViewById(R.id.input_cp);

                        FabricaCodigosPostales fabrica = new FabricaCodigosPostales();

                        CodigoPostal codigo = fabrica.creaCodigoPostal(
                                inputPais.getText().toString(),
                                inputCp.getText().toString());
                        ColoniaCodigoPostal obtenerColonia = new ColoniaCodigoPostalImplementacionArchivos(PromoDescriptionActivity.this);
                        Colonia coloniaTemp = obtenerColonia.colonia(codigo);
                        System.out.println(coloniaTemp);
                        DescuentosServiciosDBImplementacionArchivos todos = new DescuentosServiciosDBImplementacionArchivos();
                        try {
                            List<DescuentosServicios> descuentos = todos.leerDescuentos(PromoDescriptionActivity.this);
                            for (DescuentosServicios descuento : descuentos) {
                                System.out.println("Todos los Descuentos:");
                                System.out.println(descuento);
                            }
                            System.out.println("\n\n");
                            DescuentosServicios s = descuentos.get(0);
                            DescuentosAdicionalesCodigoPostal desAd = new DescuentosAdicionalesCodigoPostal();
                            if (coloniaTemp != null) {

                                if (DescuentosAdicionalesCodigoPostal.tieneDescuentoAdiccional(PromoDescriptionActivity.this, coloniaTemp.getCodigoPostal().getCodigoPostal(), s.getServicio())) {
                                    System.out.print("El servicio " + s.getServicio().getNombreServicio() + " Tiene descuento Adicional y el total es :");
                                    DescuentoAdicional descuentoFinal = desAd.regresaDescuentoAdicional(PromoDescriptionActivity.this, coloniaTemp.getCodigoPostal().getCodigoPostal(), s.getServicio(), s);
                                    showDialog("El servicio " + s.getServicio().getNombreServicio() + " Tiene descuento Adicional y el total es :" + descuentoFinal.precioFinal());
                                    System.out.println(descuentoFinal.precioFinal());
                                } else {
                                    System.out.println("No tiene descuento Adicional pero el total es de" + s.precioFinal());

                                    showDialog2();
                                }
                            } else {
                                System.out.println("El codigo Postal no concuerda con alguan colonia registrada");
                                showDialog3();
                            }

                        } catch (ServicioInexistenteExcepcion ex) {
                            showDialog3();
                            System.out.println(ex);
                        }

                    }
                })
                .show();
    }

    private void showDialog3() {
        new MaterialDialog.Builder(this)
                .title("Upss..")
                .content("Lo sentimos esta promoción no esta disponible en tu colonia")
                .positiveText(R.string.dialog_aceptar)
                .positiveColorRes(R.color.colorPrimary)
                .negativeColorRes(R.color.color_text_promotion)
                .negativeText(R.string.dialog_cancelar)
                .show();
    }

    private void showDialog2() {
        new MaterialDialog.Builder(this)
                .title("Felicidades")
                .content("Has obtenido esta promoción")
                .positiveText(R.string.dialog_aceptar)
                .positiveColorRes(R.color.colorPrimary)
                .negativeColorRes(R.color.color_text_promotion)
                .negativeText(R.string.dialog_cancelar)
                .show();
    }

    private void showDialog(String descuento) {
        new MaterialDialog.Builder(this)
                .title("Felicidades")
                .content(descuento)
                .positiveText(R.string.dialog_aceptar)
                .positiveColorRes(R.color.colorPrimary)
                .negativeColorRes(R.color.color_text_promotion)
                .negativeText(R.string.dialog_cancelar)
                .show();
    }
}
