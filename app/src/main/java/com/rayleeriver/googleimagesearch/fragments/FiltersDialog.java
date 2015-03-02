package com.rayleeriver.googleimagesearch.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.rayleeriver.googleimagesearch.R;
import com.rayleeriver.googleimagesearch.activities.MainActivity;

/**
 * Created by ray on 3/1/15.
 */
public class FiltersDialog extends DialogFragment {

    public FiltersDialog() {}

    public static FiltersDialog newInstance(String title) {
        FiltersDialog filtersDialog = new FiltersDialog();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        filtersDialog.setArguments(bundle);
        return filtersDialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_filter, container);

        final Spinner sizeSpinner = (Spinner) view.findViewById(R.id.sizeSpinner);
        final Spinner colorSpinner = (Spinner) view.findViewById(R.id.colorSpinner);
        final Spinner typeSpinner = (Spinner) view.findViewById(R.id.typeSpinner);
        final EditText etSite = (EditText) view.findViewById(R.id.etSite);

        Button btnSave = (Button) view.findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity activity = (MainActivity) getActivity();
                activity.getFiltersMap().put("imgsz", sizeSpinner.getSelectedItem().toString());
                activity.getFiltersMap().put("imgcolor", colorSpinner.getSelectedItem().toString());
                activity.getFiltersMap().put("imgType", typeSpinner.getSelectedItem().toString());
                activity.getFiltersMap().put("as_sitesearch", etSite.getText().toString());
                dismiss();
            }
        });

        Button btnCancel = (Button) view.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return view;
    }
}
