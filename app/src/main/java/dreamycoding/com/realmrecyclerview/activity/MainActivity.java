package dreamycoding.com.realmrecyclerview.activity;

import android.app.Dialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import dreamycoding.com.realmrecyclerview.R;
import dreamycoding.com.realmrecyclerview.adapter.ContactAdapter;
import dreamycoding.com.realmrecyclerview.model.ContactModel;
import dreamycoding.com.realmrecyclerview.realm.RealmHelper;
import io.realm.Realm;
import io.realm.RealmChangeListener;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton actionButton;

    private Realm realm;
    private List<ContactModel> contactModelList;
    private RecyclerView recyclerView;
    private ContactAdapter adapter;

    private RealmChangeListener realmChangeListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);


        actionButton.setOnClickListener(actionButtonClickListener);

        //Setup RecyclerView
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Realm Configuration
        realm = Realm.getDefaultInstance();
        final RealmHelper realmHelper = new RealmHelper(realm);

        //Retrive contact
        realmHelper.retriveContact();

        //setup adapter
        adapter = new ContactAdapter(this, realmHelper.retriveContactList());
        recyclerView.setAdapter(adapter);

        //Data change event and refresh
        realmChangeListener = new RealmChangeListener() {
            @Override
            public void onChange() {
                adapter = new ContactAdapter(MainActivity.this, realmHelper.retriveContactList());
                recyclerView.setAdapter(adapter);
            }
        };

        //Add realmChangeListener to realm
        realm.addChangeListener(realmChangeListener);
    }

    View.OnClickListener actionButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            inputContactDialog();
        }
    };

    private void inputContactDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.setTitle("Add Contact");
        dialog.setContentView(R.layout.dialog);

        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogTheme;

        final EditText etName = (EditText) dialog.findViewById(R.id.etName);
        final EditText etEmail = (EditText) dialog.findViewById(R.id.etEmail);
        final EditText etPhone = (EditText) dialog.findViewById(R.id.etPhone);
        final EditText etAge = (EditText) dialog.findViewById(R.id.etAge);
        Button btnAdd = (Button) dialog.findViewById(R.id.btnAdd);
        Button btnCancel = (Button) dialog.findViewById(R.id.btnCancel);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = etName.getText().toString();
                String email = etEmail.getText().toString();
                String phone = etPhone.getText().toString();
                String age = etAge.getText().toString();

                ContactModel model = new ContactModel(name, email, phone, age);

                if (name != null && name.length() >= 0) {
                    RealmHelper realmHelper = new RealmHelper(realm);
                    //Add contact to realm database
                    if (realmHelper.addContact(model)) {
                        Toast.makeText(MainActivity.this, "Contact successfully added!!", Toast.LENGTH_SHORT).show();
                        etName.setText("");
                        etEmail.setText("");
                        etPhone.setText("");
                        etAge.setText("");
                    } else {
                        Toast.makeText(MainActivity.this, "Invalid input!!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Name can't be empty!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Refresh Realm
        realm.removeChangeListener(realmChangeListener);
        realm.close();
    }
}
