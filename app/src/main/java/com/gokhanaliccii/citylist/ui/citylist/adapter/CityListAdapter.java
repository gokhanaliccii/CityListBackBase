package com.gokhanaliccii.citylist.ui.citylist.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fasterxml.jackson.core.util.BufferRecycler;
import com.gokhanaliccii.citylist.data.model.City;
import com.gokhanaliccii.citylist.databinding.CardCityBinding;

import java.util.List;

public class CityListAdapter extends RecyclerView.Adapter<CityListAdapter.ViewHolder> {

    private List<City> mCityList;

    public CityListAdapter(List<City> mCityList) {
        this.mCityList = mCityList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        CardCityBinding cityCardBinding = CardCityBinding.inflate(inflater, viewGroup, false);

        return new ViewHolder(cityCardBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        City city = mCityList.get(position);
        viewHolder.bind(city);
    }

    @Override
    public int getItemCount() {
        return mCityList == null
                ? 0
                : mCityList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private CardCityBinding mCardBinding;

        public ViewHolder(@NonNull CardCityBinding cardBinding) {
            super(cardBinding.getRoot());

            this.mCardBinding = cardBinding;
        }

        public void bind(City city) {
            mCardBinding.setCity(city);
        }

    }

}
