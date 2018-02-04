package com.babytt.activity;

import com.babytt.model.Account;
import com.babytt.service.Service;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;


public class ModifyActivity extends ActionBarActivity {

    private EditText name=null;
    private EditText money=null;
    private EditText remark=null;

    private Service service=null;
    private Account contact=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);

        contact = new Account();
        service = new Service(this);
        init();  //init

        // show the detail of the contact
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", -1);
        if(id == -1){
            finish();
        }else{
            contact = service.getById(id);
            name.setText(contact.getName());
            money.setText(contact.getMoney());
        }

        // 标题栏添加“返回”菜单
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    // init
    private void init(){
        name = (EditText)findViewById(R.id.name);
        money = (EditText)findViewById(R.id.money);
        remark = (EditText)findViewById(R.id.remark);
    }


    // get Input text
    private Account getContent(){
        Account a = new Account();
        a.setId(contact.getId());
        a.setName(name.getText().toString());
        a.setMoney(money.getText().toString());
        a.setRemark(remark.getText().toString());
        return a;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_modify, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_save) {
            if(name.getText().toString().equals(""))
                Toast.makeText(this, "姓名不能为空", Toast.LENGTH_LONG).show();
            else if(money.getText().toString().equals(""))
                Toast.makeText(this, "金额不能为空", Toast.LENGTH_LONG).show();
            else {
                boolean flag = service.update(getContent());
                if(flag)
                    Toast.makeText(ModifyActivity.this, "修改成功", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(ModifyActivity.this, "修改失败", Toast.LENGTH_LONG).show();
            }
            return true;
        }
        if (id == android.R.id.home)  // 返回
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    // *************** internal classes as listeners ******************
//    class GroupListener implements OnCheckedChangeListener{
//        @Override
//    public void onCheckedChanged(RadioGroup group, int checkedId) {
//        if(group.getCheckedRadioButtonId() == R.id.male)
//            image.setImageResource(R.drawable.icon_boy);
//        else
//            image.setImageResource(R.drawable.icon_girl);
//    }
//}
}
