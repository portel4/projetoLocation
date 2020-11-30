package com.example.location_places.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.location_places.R;
import com.example.location_places.model.Local;
import com.example.location_places.model.LocalViewModel;
import com.example.location_places.model.Usuario;
import com.example.location_places.model.UsuarioViewModel;
import com.example.location_places.util.ImageUtil;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.orhanobut.hawk.Hawk;

import java.util.List;

import static android.app.Activity.RESULT_OK;


public class CadastroLocalFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private LocalViewModel localViewModel;
    private Local localCorrente;
    private EditText editTextData;
    private EditText editTextDescricao;
    private EditText editTextLatitude;
    private EditText editTextLongitude;
    private Button buttonSalvar;
    private Button buttonVoltar;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private Local mParam2;

    public CadastroLocalFragment() {
        // Required empty public constructor
    }

    public static CadastroLocalFragment newInstance(String param1, Local param2) {
        CadastroLocalFragment fragment = new CadastroLocalFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putSerializable(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = (Local)getArguments().getSerializable(ARG_PARAM2);
        }
        localCorrente = new Local();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cadastro_local, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstaceState){

        editTextData = view.findViewById(R.id.editTextData);
        editTextDescricao = view.findViewById(R.id.editTextDescricao);
        editTextLatitude = view.findViewById(R.id.txtLat);
        editTextLongitude = view.findViewById(R.id.txtLong);
        buttonSalvar = view.findViewById(R.id.btnSalvar);
        buttonVoltar = view.findViewById(R.id.btnVoltar);



        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarLocal();
            }
        });

        buttonVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voltar();
            }
        });

        localViewModel = new ViewModelProvider(this).get(LocalViewModel.class);

        localViewModel.getSalvoSucesso().observe(getActivity(), new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable final Boolean sucesso) {
                if(sucesso){
                    Toast.makeText(getActivity(), "Local salvo com sucesso", Toast.LENGTH_SHORT).show();
                    limparCampos();
                }else{
                    Toast.makeText(getActivity(), "Falha ao salvar o local", Toast.LENGTH_SHORT).show();
                }
            }
        });

        if (mParam2!=null){
            localCorrente = mParam2;
            editTextData.setText(localCorrente.getData());
            editTextDescricao.setText(localCorrente.getDescricao());
            editTextLatitude.setText(localCorrente.getLatitude());
            editTextLongitude.setText(localCorrente.getLongitude());
        }



    }

    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    public void onActivityResult(int  requestCode , int  resultCode , Intent  data ){
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

        }
    }

    protected void replaceFragment(@IdRes int containerViewId,
                                   @NonNull Fragment fragment,
                                   @NonNull String fragmentTag,
                                   @Nullable String backStackStateName) {

        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(containerViewId, fragment, fragmentTag)
                .addToBackStack(backStackStateName)
                .commit();
    }


    public void voltar(){
        replaceFragment(R.id.frameLayoutMain,
                LocaisFragment.newInstance("",""),
                "LOCAISFRAGMENT",
                "LOCAIS");
    }

    public void salvarLocal() {

        if(localCorrente == null){
            localCorrente = new Local();
        }

        if (validarCampos()){
            localCorrente.setData(editTextData.getText().toString());
            localCorrente.setDescricao(editTextDescricao.getText().toString());
            localCorrente.setLatitude(editTextLatitude.getText().toString());
            localCorrente.setLongitude(editTextLongitude.getText().toString());

            if(mParam2==null){
                localViewModel.salvarLocal(localCorrente);
            }else {
                localViewModel.alterarLocal(localCorrente);
            }

        }

    }

    private void limparCampos(){
        editTextData.setText("");
        editTextDescricao.setText("");
        editTextLatitude.setText("");
        editTextLongitude.setText("");
    }

    public Boolean validarCampos(){
        boolean valido = true;
        if(editTextData.getText().toString().trim().length() ==0){
            valido = false;
            Toast.makeText(getActivity(), "Campo DATA está vazio!!!", Toast.LENGTH_SHORT).show();
        }
        if(editTextDescricao.getText().toString().trim().length() ==0){
            valido = false;
            Toast.makeText(getActivity(), "Campo DESCRIÇÃO está vazio!!!", Toast.LENGTH_SHORT).show();
        }
        if(editTextLatitude.getText().toString().trim().length() ==0){
            valido = false;
            Toast.makeText(getActivity(), "Campo LATITUDE está vazio!!!", Toast.LENGTH_SHORT).show();
        }
        if(editTextLongitude.getText().toString().trim().length() ==0){
            valido = false;
            Toast.makeText(getActivity(), "Campo LONGITUDE está vazio!!!", Toast.LENGTH_SHORT).show();
        }
        return valido;
    }
}