package io.github.ecom.gowarkop;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfilFragment extends Fragment {


    @BindView(R.id.tv_nama_profil)
    TextView tvNama;
    @BindView(R.id.tv_email_profil)
    TextView tvEmail;
    @BindView(R.id.tv_hp_profil)
    TextView tvHp;

    SessionManager session;

    public ProfilFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profil, container, false);
        ButterKnife.bind(this, v);
        session = new SessionManager(getActivity());
        tvNama.setText(session.getNamaPref());
        tvEmail.setText(session.getEmailPref());
        tvHp.setText(session.getHpPref());
        return v;
    }

}
