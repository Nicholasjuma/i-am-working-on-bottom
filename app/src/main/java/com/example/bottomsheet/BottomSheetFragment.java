package com.example.bottomsheet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

public class BottomSheetFragment extends BottomSheetDialogFragment {

    private EditText editTextInput;
    private RecyclerView recyclerView;
    private ArrayList<String> itemsList;
    private ItemsAdapter itemsAdapter;
    private BottomSheetListener listener;

    public interface BottomSheetListener {
        void onDataReceived(String data);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_layout, container, false);

        editTextInput = view.findViewById(R.id.editTextInput);
        recyclerView = view.findViewById(R.id.recyclerView);
        Button buttonSubmit = view.findViewById(R.id.buttonSubmit);
        Button buttonCancel = view.findViewById(R.id.buttonCancel);

        itemsList = new ArrayList<>();
        itemsAdapter = new ItemsAdapter(itemsList, getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(itemsAdapter);

        buttonSubmit.setOnClickListener(v -> {
            String inputText = editTextInput.getText().toString().trim(); // Trim input text
            if (!inputText.isEmpty()) {
                itemsList.add(inputText);
                itemsAdapter.notifyDataSetChanged();
                editTextInput.setText("");

                listener.onDataReceived(inputText);
                Toast.makeText(getContext(), "Item added: " + inputText, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Please enter some text", Toast.LENGTH_SHORT).show();
            }
        });

        buttonCancel.setOnClickListener(v -> dismiss());

        return view;
    }

    @Override
    public void onAttach(@NonNull android.content.Context context) {
        super.onAttach(context);
        try {
            listener = (BottomSheetListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement BottomSheetListener");
        }
    }
}
