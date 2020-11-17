package com.example.location_places.model;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class LocalViewModel extends AndroidViewModel {
    private LocalRepository localRepository;
    private LiveData<List<Local>> locaisResponseLiveData;
    private LiveData<Boolean> salvoComSucessoLiveData;
    public LocalViewModel(@NonNull Application application) {
        super(application);
        Log.d("RESPOSTA","CRIACAO DA VIEWMODEL");
        localRepository = new LocalRepository();
        locaisResponseLiveData = localRepository.getAllLocais();
        salvoComSucessoLiveData = localRepository.getSalvoSucesso();
    }
    public void getLocais() {
        localRepository.getLocais();
    }
    public LiveData<List<Local>> getLocaisResponseLiveData() {
        return locaisResponseLiveData;
    }
    public LiveData<Boolean> getSalvoSucesso() {
        return salvoComSucessoLiveData;
    }
    public void salvarLocal(Local local){
        localRepository.salvarLocal(local);
    }
   // public void alterarLocal(Local local){
   //     localRepository.alterarLocal(local);
   // }
}
