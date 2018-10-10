package com.gokhanaliccii.citylist.ui.cities.citylist.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.gokhanaliccii.citylist.data.model.City;
import com.gokhanaliccii.citylist.databinding.CardCityBinding;
import com.gokhanaliccii.citylist.util.ClickListener;

import java.util.List;

public class CityListAdapter extends RecyclerView.Adapter<CityListAdapter.ViewHolder> {

    private List<City> mCityList;
    private ClickListener<City> mClickListener;

    public CityListAdapter(List<City> mCityList, ClickListener<City> clickListener) {
        this.mCityList = mCityList;
        this.mClickListener = clickListener;
    }

    public void updateCities(List<City> cities) {
        mCityList = cities;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        CardCityBinding cityCardBinding = CardCityBinding.inflate(inflater, viewGroup, false);

        cityCardBinding.getRoot().setOnClickListener(
                v -> mClickListener.onClick(mCityList.get(position)));

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
            mCardBinding.executePendingBindings();
        }

    }


}
