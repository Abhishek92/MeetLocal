package com.android.meetlocal.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.meetlocal.R;
import com.android.meetlocal.database.Interest;
import com.android.meetlocal.imageloader.AppImageLoader;
import com.android.meetlocal.viewmodel.InterestListViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>A fragment that shows a list of items as a modal bottom sheet.</p>
 * <p>You can show this modal bottom sheet from your activity like this:</p>
 * <pre>
 *     InterestListDialogFragment.newInstance(30).show(getSupportFragmentManager(), "dialog");
 * </pre>
 * <p>You activity (or fragment) needs to implement .</p>
 */
public class InterestListDialogFragment extends BottomSheetDialogFragment {

    // TODO: Customize parameter argument names
    private static final String ARG_ITEM_LIST = "ARG_ITEM_LIST";
    private InterestListViewModel interestListViewModel;
    private List<Interest> selectedInterestList = new ArrayList<>();

    // TODO: Customize parameters
    public static InterestListDialogFragment newInstance(ArrayList<Interest> interestList) {
        InterestListDialogFragment fragment = new InterestListDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(ARG_ITEM_LIST, interestList);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_item_list_dialog, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        final RecyclerView recyclerView = (RecyclerView) view;
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        interestListViewModel = ViewModelProviders.of(getActivity()).get(InterestListViewModel.class);
        if (null != getArguments()) {
            List<Interest> interestList = getArguments().getParcelableArrayList(ARG_ITEM_LIST);
            recyclerView.setAdapter(new ItemAdapter(interestList));
        }
    }


    private class ViewHolder extends RecyclerView.ViewHolder {

        final TextView text;
        final ImageView interestImg;

        ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            // TODO: Customize the item layout
            super(inflater.inflate(R.layout.fragment_item_list_dialog_item, parent, false));
            text = itemView.findViewById(R.id.interest_type);
            interestImg = itemView.findViewById(R.id.interest_img);

        }

    }

    private class ItemAdapter extends RecyclerView.Adapter<ViewHolder> {

        private List<Interest> interests = new ArrayList<>();

        ItemAdapter(List<Interest> interests) {
            this.interests = new ArrayList<>(interests);
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            final Interest interest = interests.get(position);
            holder.text.setText(interest.getType());
            AppImageLoader.getInstance().loadImage(getContext(), interest.getUrl(), holder.interestImg);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!interest.isSelceted()) {
                        interest.setSelceted(true);
                        holder.itemView.setBackgroundColor(Color.GRAY);
                        selectedInterestList.add(interest);
                        interestListViewModel.setSelectedInterestList(selectedInterestList);
                    } else {
                        interest.setSelceted(false);
                        holder.itemView.setBackgroundColor(Color.WHITE);
                        selectedInterestList.remove(interest);
                        interestListViewModel.setSelectedInterestList(selectedInterestList);
                    }


                }
            });
        }

        @Override
        public int getItemCount() {
            return interests.size();
        }

    }

}
