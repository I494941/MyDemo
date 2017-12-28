package com.example.a94941.mydemo.activitys.fourLevelLinkage;

import android.view.View;
import android.widget.ListView;

import com.example.a94941.mydemo.R;
import com.example.a94941.mydemo.base.BaseToolbarActivity;
import com.example.a94941.mydemo.utils.ToastUtils;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;

/**
 * Created by 94941 on 2017/12/27.
 */

public class FourLevelLinkageActivity extends BaseToolbarActivity {

    @BindView(R.id.lv_continent)
    ListView mLvContinent;//洲
    @BindView(R.id.lv_country)
    ListView mLvCountry;//国
    @BindView(R.id.lv_province)
    ListView mLvProvince;//省
    @BindView(R.id.lv_city)
    ListView mLvCity;//市

    private DBhelper bhelper;

    private ArrayList<Level> continentValues = new ArrayList<Level>();
    private ArrayList<Level> countryValues   = new ArrayList<Level>();
    private ArrayList<Level> provinceValues  = new ArrayList<Level>();
    private ArrayList<Level> cityValues;

    private LevelListViewAdapter continentAdapter;
    private LevelListViewAdapter countryAdapter;
    private LevelListViewAdapter provinceAdapter;
    private LevelListViewAdapter cityAdapter;

    private int continentPosition = 0;
    private int countryPosition   = 0;
    private int provincePosition  = 0;
    private int cityPosition      = 0;

    private int countryNumber  = -1;
    private int provinceNumber = -1;

    private long countryTime  = 0;
    private long provinceTime = 0;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_four_level_linkage;
    }

    @Override
    protected void initViewsAndEvents() {
        mTvTitle.setText("FourLevelLinkage");
        mIvRight.setVisibility(View.VISIBLE);

        bhelper = new DBhelper(this);
        setContinent();
        setCountry();
        setProvince();
        setCity();
    }

    @Override
    protected String getContent() {
        return "一二三四级联动，ListView联动，城市选择联动，SQLite轻量级数据库，城市数据库！！！\n" +
                "基于项目本地的资源文件，SQLite轻量级数据库实现的城市选择器，数据库中城市数据基本齐全。\n" +
                "本Demo是使用的ListView实现的联动，当然也可使用Fragment，在这里我只给出了一种方式。\n" +
                "思路是融汇贯通的，我看网上大多都是三级联动，所以在这里给出一个很久之前写的四级联动，希望能够帮助到大家。";
    }

    /**
     * 设置市
     */
    private void setCity() {
        cityValues = getCity(provinceValues.get(provincePosition).getPlaceid());
        if (!(cityValues.isEmpty())) {
            cityAdapter = new LevelListViewAdapter(this, cityValues);
            cityAdapter.setSelectedPositionNoNotify(cityPosition, cityValues);
            mLvCity.setAdapter(cityAdapter);
            cityAdapter.setOnItemClickListener(new LevelListViewAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    ToastUtils.showToast(cityValues.get(position).getPlacename());
                }
            });
        }
    }

    /**
     * 设置省
     */
    private void setProvince() {
        provinceValues = getProvince(countryValues.get(countryPosition).getPlaceid());
        if (!(provinceValues.isEmpty())) {
            provinceAdapter = new LevelListViewAdapter(this, provinceValues);
            provinceAdapter.setSelectedPositionNoNotify(provincePosition, provinceValues);
            mLvProvince.setAdapter(provinceAdapter);
            provinceAdapter.setOnItemClickListener(new LevelListViewAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    if (provinceNumber != position) {//记录不是当前点击的
                        provinceNumber = position;//就记录当前条目
                        provinceTime = System.currentTimeMillis();//并记录第一次时间戳
                        Timer timer = new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                provinceNumber = -1;
                                provinceTime = 0;
                            }
                        }, 300);
                    } else {//记录的是当前点击的
                        long num = System.currentTimeMillis() - provinceTime;//判断时间差，是不是双击
                        if (num <= 300) {//时间差200毫秒内
                            ToastUtils.showToast(provinceValues.get(position).getPlacename());
                        }
                        provinceNumber = -1;//重置过的记录
                        provinceTime = 0;//重置时间的记录
                    }
                    cityValues.clear();
                    if (!(provinceValues.isEmpty())) {
                        cityValues = getCity(provinceValues.get(position).getPlaceid());
                        cityAdapter.notifyDataSetChanged();
                        cityAdapter.setSelectedPositionNoNotify(0, cityValues);
                        mLvCity.smoothScrollToPosition(0);
                    } else {
                        cityAdapter.notifyDataSetChanged();
                    }
                }
            });
        }
    }

    /**
     * 设置国
     */
    private void setCountry() {
        countryValues = getCountry(continentValues.get(continentPosition).getPlaceid());
        if (!(countryValues.isEmpty())) {
            countryAdapter = new LevelListViewAdapter(this, countryValues);
            countryAdapter.setSelectedPositionNoNotify(countryPosition, countryValues);
            mLvCountry.setAdapter(countryAdapter);
            countryAdapter.setOnItemClickListener(new LevelListViewAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    if (countryNumber != position) {//记录不是当前点击的
                        countryNumber = position;//就记录当前条目
                        countryTime = System.currentTimeMillis();//并记录第一次时间戳
                        Timer timer = new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                countryNumber = -1;
                                countryTime = 0;
                            }
                        }, 300);
                    } else {//记录的是当前点击的
                        long num = System.currentTimeMillis() - countryTime;//判断时间差，是不是双击
                        if (num <= 300) {//时间差200毫秒内
                            ToastUtils.showToast(countryValues.get(position).getPlacename());
                        }
                        countryNumber = -1;//重置过的记录
                        countryTime = 0;//重置时间的记录
                    }
                    provinceValues.clear();
                    if (!(countryValues.isEmpty())) {
                        provinceValues = getProvince(countryValues.get(position).getPlaceid());
                        provinceAdapter.notifyDataSetChanged();
                        provinceAdapter.setSelectedPositionNoNotify(0, provinceValues);
                        mLvProvince.smoothScrollToPosition(0);
                    } else {
                        provinceAdapter.notifyDataSetChanged();
                    }

                    cityValues.clear();
                    if (!(provinceValues.isEmpty())) {
                        cityValues = getCity(provinceValues.get(0).getPlaceid());
                        cityAdapter.notifyDataSetChanged();
                        cityAdapter.setSelectedPositionNoNotify(0, cityValues);
                        mLvCity.smoothScrollToPosition(0);
                    } else {
                        cityAdapter.notifyDataSetChanged();
                    }
                }
            });
        }
    }

    /**
     * 设置洲
     */
    private void setContinent() {
        continentValues = bhelper.getContinent();
        if (!(continentValues.isEmpty())) {
            continentAdapter = new LevelListViewAdapter(this, continentValues);
            continentAdapter.setSelectedPositionNoNotify(continentPosition, continentValues);
            mLvContinent.setAdapter(continentAdapter);
            continentAdapter.setOnItemClickListener(new LevelListViewAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    countryValues.clear();
                    if (!(continentValues.isEmpty())) {
                        countryValues = getCountry(continentValues.get(position).getPlaceid());
                        countryAdapter.notifyDataSetChanged();
                        countryAdapter.setSelectedPositionNoNotify(0, countryValues);
                        mLvCountry.smoothScrollToPosition(0);
                    } else {
                        countryAdapter.notifyDataSetChanged();
                    }

                    provinceValues.clear();
                    if (!(countryValues.isEmpty())) {
                        provinceValues = getProvince(countryValues.get(0).getPlaceid());
                        provinceAdapter.notifyDataSetChanged();
                        provinceAdapter.setSelectedPositionNoNotify(0, provinceValues);
                        mLvProvince.smoothScrollToPosition(0);
                    } else {
                        provinceAdapter.notifyDataSetChanged();
                    }

                    cityValues.clear();
                    if (!(provinceValues.isEmpty())) {
                        cityValues = getCity(provinceValues.get(0).getPlaceid());
                        cityAdapter.notifyDataSetChanged();
                        cityAdapter.setSelectedPositionNoNotify(0, cityValues);
                        mLvCity.smoothScrollToPosition(0);
                    } else {
                        cityAdapter.notifyDataSetChanged();
                    }
                }
            });
        }
    }

    /**
     * 获取国
     *
     * @param placetoid
     * @return
     */
    public ArrayList<Level> getCountry(String placetoid) {
        return bhelper.getCountry(placetoid);
    }

    /**
     * 获取省
     *
     * @param placetoid
     * @return
     */
    public ArrayList<Level> getProvince(String placetoid) {
        return bhelper.getProvince(placetoid);
    }

    /**
     * 获取市
     *
     * @param placetoid
     * @return
     */
    public ArrayList<Level> getCity(String placetoid) {
        return bhelper.getCity(placetoid);
    }
}
