package com.example.mycalculate;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.mycalculate.databinding.FragmentQueryBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QueryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QueryFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FragmentQueryBinding binding;
    private MyViewModel myViewModel;

    public QueryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment QueryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QueryFragment newInstance(String param1, String param2) {
        QueryFragment fragment = new QueryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_query, container, false);

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_query,container,false);
        myViewModel = new ViewModelProvider(requireActivity(),new SavedStateViewModelFactory(getActivity().getApplication(), getActivity())).get(MyViewModel.class);
        myViewModel.generator();
        myViewModel.getCurScore().setValue(0);
        binding.setData(myViewModel);
        binding.setLifecycleOwner(requireActivity());

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        StringBuilder builder = new StringBuilder();

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (v.getId()){
                    case R.id.button00:
                        builder.append("0");
                         break;
                    case R.id.button01:
                        builder.append("1");
                        break;
                    case R.id.button02:
                        builder.append("2");
                        break;
                    case R.id.button03:
                        builder.append("3");
                        break;
                    case R.id.button04:
                        builder.append("4");
                        break;
                    case R.id.button05:
                        builder.append("5");
                        break;
                    case R.id.button06:
                        builder.append("6");
                        break;
                    case R.id.button07:
                        builder.append("7");
                        break;
                    case R.id.button08:
                        builder.append("8");
                        break;
                    case R.id.button09:
                        builder.append("9");
                        break;
                    case R.id.buttonc:
                        builder.setLength(0);
                        break;

                }
                if(builder.length() == 0){
                  binding.textView9.setText(getString(R.string.input));
                }else {
                    binding.textView9.setText(builder.toString());
                }

            }
        };

        binding.button00.setOnClickListener(listener);
        binding.button01.setOnClickListener(listener);
        binding.button02.setOnClickListener(listener);
        binding.button03.setOnClickListener(listener);
        binding.button04.setOnClickListener(listener);
        binding.button05.setOnClickListener(listener);
        binding.button06.setOnClickListener(listener);
        binding.button07.setOnClickListener(listener);
        binding.button08.setOnClickListener(listener);
        binding.button09.setOnClickListener(listener);
        binding.buttonc.setOnClickListener(listener);
        binding.buttonok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(builder.length() == 0){
                    Log.d("TAG", "onClick: null");
                  return;
                }

                if(Integer.valueOf(builder.toString()).intValue() == myViewModel.getAnswer().getValue()){
                    myViewModel.answerCorrect();
                    builder.setLength(0);
                   // builder.append(getString(R.string.answer_correct_message));
                    binding.textView9.setText(R.string.answer_correct_message);
                }else{
                    NavController navController = Navigation.findNavController(v);
                    if(myViewModel.win_flag){
                        navController.navigate(R.id.action_queryFragment_to_winFragment);
                        myViewModel.win_flag = false;
                        myViewModel.save();
                    }else{
                        navController.navigate(R.id.action_queryFragment_to_loseFragment);

                    }
                }

            }
        });


    }
}