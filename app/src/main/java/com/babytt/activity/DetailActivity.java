package com.babytt.activity;

import com.babytt.model.Account;
import com.babytt.service.Service;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.net.Uri;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;


public class DetailActivity extends ActionBarActivity {

    private EditText number=null;    // declare all EditText views
    private EditText name=null;
    private EditText money=null;
    private EditText remark=null;
    private ImageView image=null;

    private Account account=null;
    private Service service=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        account = new Account();
        init();
        // get the Intent to receive the Id of the account, return -1 while Id doesn't exist
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", -1);
        if(id == -1){
            finish();
        }else{
            service = new Service(this);
            account = service.getById(id);
            name.setText(account.getName());
            money.setText(account.getMoney());
            remark.setText(account.getRemark());
        }

        // 标题栏添加“返回”菜单
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    public void init(){
        name = (EditText)findViewById(R.id.account_name);
        money = (EditText)findViewById(R.id.account_money);
        remark = (EditText)findViewById(R.id.account_remark);
    }


    // create hint dialog
    private void dialog(){
        AlertDialog.Builder builder = new Builder(DetailActivity.this);
        builder.setMessage("确定删除吗?");
        builder.setTitle("提示");
        builder.setPositiveButton("确定", new OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                service.delete(account.getId());
                finish();
            }
        });
        builder.setNegativeButton("取消", new OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_modify) {
            Intent intent = new Intent(DetailActivity.this, ModifyActivity.class);
            intent.putExtra("id", account.getId());
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_delete) {
            dialog();
            return true;
        }
        if (id == android.R.id.home)  // 返回
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onRestart() {
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", -1);
        if(id == -1){
            finish();
        }else{
            service = new Service(this);
            account = service.getById(id);
            name.setText(account.getName());
            remark.setText(account.getRemark());
        }
        super.onRestart();
    }


    //**************** internal classes as Listener ********************
    class ButtonCallListener implements android.view.View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:"+account.getMoney()));
            intent.addCategory("android.intent.category.DEFAULT");
            startActivity(intent);
        }
    }
    class ButtonSmsListener implements android.view.View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("smsto:"+account.getMoney()));
            intent.addCategory("android.intent.category.DEFAULT");
            startActivity(intent);
        }
    }
}
