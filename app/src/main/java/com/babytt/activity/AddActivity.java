package com.babytt.activity;

import com.babytt.model.Account;
import com.babytt.service.Service;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class AddActivity extends ActionBarActivity {

    private EditText number=null;    // declare EditText
    private EditText name=null;
    private EditText money=null;
    private EditText remark=null;
    private ImageView image=null;
    private Service service=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        service = new Service(this);
        init();  // init

        // 标题栏添加“返回”菜单
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    // init - get views by Id
    private void init(){
        name = (EditText)findViewById(R.id.account_name);
        money = (EditText)findViewById(R.id.account_money);
        remark = (EditText)findViewById(R.id.account_remark);
    }


    // get Input text
    private Account getContent(){
        Account account = new Account();
        account.setName(name.getText().toString());
        account.setMoney(money.getText().toString());
        account.setRemark(remark.getText().toString());
        return account;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_save) {  // 保存
            if(name.getText().toString().equals(""))
                Toast.makeText(this, "姓名不能为空", Toast.LENGTH_LONG).show();
            else if(money.getText().toString().equals(""))
                Toast.makeText(this, "金额不能为空", Toast.LENGTH_LONG).show();
            else {
                boolean flag = service.save(getContent());
                if(flag)
                    Toast.makeText(this, "账目添加成功", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(this, "账目添加失败", Toast.LENGTH_LONG).show();
            }
            return true;
        }
        if (id == android.R.id.home)  // 返回
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    //**************** internal classes as Listener ********************
//    class GroupListener implements OnCheckedChangeListener{
//        @Override
//        public void onCheckedChanged(RadioGroup group, int checkedId) {
//            if(group.getCheckedRadioButtonId() == R.id.male)
//                image.setImageResource(R.drawable.icon_boy);
//            else
//                image.setImageResource(R.drawable.icon_girl);
//        }
//    }
}
