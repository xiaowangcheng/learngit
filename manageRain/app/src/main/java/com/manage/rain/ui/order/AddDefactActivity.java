package com.manage.rain.ui.order;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.LubanOptions;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.model.TakePhotoOptions;
import com.lzy.okgo.model.HttpParams;
import com.manage.rain.R;
import com.manage.rain.adapter.DialogListAdapter;
import com.manage.rain.config.Urlx;
import com.manage.rain.model.UpdateState;
import com.manage.rain.net.Netapi;
import com.manage.rain.ui.TakePhotoActivity;
import com.manage.rain.until.BottomView;
import com.manage.rain.until.Common;
import com.manage.rain.until.JSON2Class;
import com.manage.rain.until.StringUtil;
import com.manage.rain.until.ToastUtils;
import com.manage.rain.until.ViewUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;


/**
 * Created by wangcheng on 2018/2/9.
 */

public class AddDefactActivity extends TakePhotoActivity {
    ImageView iv_img = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_defact);
        setBack();
        setTitle("异常信息上传");
        fileList = new ArrayList<File>();
        iv_img = (ImageView) findViewById(R.id.iv_img);
        iv_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> list  = new ArrayList<String>();
                list.add("拍照");
                list.add("从相册选择");
                final BottomView bottomView  = new BottomView(AddDefactActivity.this, R.style.BottomViewTheme_Dialog, R.layout.bottom_dialog);
                ViewUtils.showCameraDialog(bottomView,AddDefactActivity.this, list, new DialogListAdapter.OnItemClickLister() {
                    @Override
                    public void onItemClick(View view, int position) {
                        File file=new File(Environment.getExternalStorageDirectory(), "/temp/"+System.currentTimeMillis() + ".jpg");
                        if (!file.getParentFile().exists())file.getParentFile().mkdirs();
                        Uri imageUri = Uri.fromFile(file);
                        TakePhoto takePhoto = getTakePhoto();
                        configCompress(takePhoto);
                        configTakePhotoOption(takePhoto);
                        switch (position){
                            case 0 :
                                //是否裁切  false  否   true  是
                                if(false){
                                   // takePhoto.onPickFromCaptureWithCrop(imageUri,getCropOptions());
                                }else {
                                    takePhoto.onPickFromCapture(imageUri);
                                }
                                bottomView.dismissBottomView();
                                break;
                            case 1 :
                                takePhoto.onPickFromGallery();
                                bottomView.dismissBottomView();
                                break;
                        }
                    }
                    @Override
                    public void onItemLongClick(View view, int position) {

                    }
                });
            }
        });

        Button btn_submit = (Button) findViewById(R.id.btn_submit);
        final EditText et_beizhu = (EditText) findViewById(R.id.et_beizhu);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fileList.size()<1){
                    ToastUtils.makeText(AddDefactActivity.this,"请上传照片！");
                } else if(StringUtil.isBlank(et_beizhu.getText().toString())){
                    ToastUtils.makeText(AddDefactActivity.this,"请输入瑕疵说明");
                } else  {
                    request(et_beizhu.getText().toString());
                }
            }
        });
    }

    private void request(String tag){
        HttpParams httpParams = new HttpParams();
        httpParams.put("introduce",tag);
        httpParams.put("indentId",getIntent().getStringExtra("indentId"));
        Netapi.requestFile(Urlx.addAbnormal,fileList,httpParams).doOnSubscribe(new Action0() {
            @Override
            public void call() {

            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Common.log(s);
                UpdateState updateState =  JSON2Class.fromJson(s, UpdateState.class);
                if (updateState.getSuccess()){
                    ToastUtils.makeText(AddDefactActivity.this,"添加异常成功");
                    setResult(11);
                    AddDefactActivity.this.finish();
                } else{
                    ToastUtils.makeText(AddDefactActivity.this,"添加异常失败");
                }

            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }

    private List<File> fileList =null;

    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        ArrayList<TImage>  images =result.getImages();
        File file = new File(images.get(images.size() - 1).getCompressPath());

        fileList.add(file);
        Glide.with(this).load(file).into(iv_img);
    }

    private void configCompress(TakePhoto takePhoto){

        int maxSize= Integer.parseInt("102400");
        int width= Integer.parseInt("800");
        int height= Integer.parseInt("800");
        boolean showProgressBar= true;//是否压缩进度条
        boolean enableRawFile =  false;//拍照压缩后是否保存原图
        CompressConfig config;
        if(true){
            //自带压缩工具
            config=new CompressConfig.Builder()
                    .setMaxSize(maxSize)
                    .setMaxPixel(width>=height? width:height)
                    .enableReserveRaw(enableRawFile)
                    .create();
        }else {
            //Luban
            LubanOptions option=new LubanOptions.Builder()
                    .setMaxHeight(height)
                    .setMaxWidth(width)
                    .setMaxSize(maxSize)
                    .create();
            config=CompressConfig.ofLuban(option);
            config.enableReserveRaw(enableRawFile);
        }
        takePhoto.onEnableCompress(config,showProgressBar);


    }

    private void configTakePhotoOption(TakePhoto takePhoto){
        TakePhotoOptions.Builder builder=new TakePhotoOptions.Builder();
        if(true){
            //使用TakePhoto自带相册
            builder.setWithOwnGallery(true);
        }
        if(true){
            //纠正拍照的照片旋转角度
            builder.setCorrectImage(true);
        }
        takePhoto.setTakePhotoOptions(builder.create());
    }
}
