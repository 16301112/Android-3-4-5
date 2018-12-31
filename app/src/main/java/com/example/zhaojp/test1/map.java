package com.example.zhaojp.test1;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.RouteLine;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.route.BikingRouteLine;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRouteLine;
import com.baidu.mapapi.search.route.DrivingRoutePlanOption;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.IndoorRouteResult;
import com.baidu.mapapi.search.route.MassTransitRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteLine;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteLine;
import com.baidu.mapapi.search.route.WalkingRoutePlanOption;
import com.baidu.mapapi.search.route.WalkingRouteResult;

import static com.example.zhaojp.test1.R.layout.activity_map;

public class map extends AppCompatActivity {

    private MapView mMapView;
    private BaiduMap mBaiduMap;
    public LocationClient mLocationClient;
    public BDLocationListener myListener = new MyLocationListener();
    private LatLng latLng;


    //private AlertDialog.Builder builder=new AlertDialog.Builder(map.this);

    //路线规划
    private RoutePlanSearch mSearch;
    private double myLongitude; 	//经度
    private double myLatitude; 	//纬度
    RouteLine route = null;
    private String loaclcity = null;
    Button mBtnPre,mBtnNext;
    int nodeIndex = -1; // 节点索引,供浏览节点时使用
    private TextView popupText = null, driver_city; // 泡泡view
    private EditText start_edit, end_edit;
    boolean isFirstLoc = true; // 是否首次定位
    OverlayManager routeOverlay = null;
    boolean useDefaultIcon = false;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        SDKInitializer.initialize(getApplicationContext());
        setContentView( activity_map );


        mMapView = findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();

        //普通地图 general map
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);



        // 地图点击事件处理
        //mBaiduMap.setOnMapClickListener();

        /*Button navigation=(Button)findViewById( R.id.navigation );
        //监听button事件
        navigation.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(map.this);
                builder.setTitle( "Navigation information" );
                /*builder.setTitle( "起始地：" );
                builder.setView( new EditText( map.this ) );
                builder.setTitle( "目的地：" );
                builder.setView( new EditText( map.this ) );
                final EditText editText1=new EditText(map.this);
                editText1.setHint("origin");
                final EditText editText2=new EditText(map.this);
                editText2.setHint("destination");
                LinearLayout layout=new LinearLayout(map.this);
                layout.setOrientation(LinearLayout.VERTICAL);
                layout.addView(editText1);
                layout.addView(editText2);
                builder.setView(layout);

                builder.setPositiveButton( "确定",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id){

                        // 初始化搜索模块，注册事件监听
                        //mSearch = RoutePlanSearch.newInstance();
                        //mSearch.setOnGetRoutePlanResultListener( listener );
                        //searchButtonProcess();


                    }
                });
                builder.setNegativeButton("取消", null);
                builder.show();
            }
        });*/

    }


    /*@Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }*/




    /*开始菜单栏 https://blog.csdn.net/weixin_41835113/article/details/79893700*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_map, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.generalMap:
                mBaiduMap.setBaiduHeatMapEnabled(false);
                mBaiduMap.setTrafficEnabled(false);
                mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
                break;
            case R.id.GDPMap:
                mBaiduMap.setBaiduHeatMapEnabled(false);
                mBaiduMap.setTrafficEnabled(false);
                mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
                break;
            case R.id.trafficMap:
                mBaiduMap.setBaiduHeatMapEnabled(false);
                mBaiduMap.setTrafficEnabled(true);
                break;
            case R.id.heatMap:
                mBaiduMap.setTrafficEnabled(false);
                mBaiduMap.setBaiduHeatMapEnabled(true);
                break;
            case R.id.location:
                // 开启定位图层
                mBaiduMap.setMyLocationEnabled(true);
                mLocationClient = new LocationClient(getApplicationContext());     //声明LocationClient类
                //配置定位SDK参数
                initLocation();
                mLocationClient.registerLocationListener(myListener);    //注册监听函数
                //开启定位
                mLocationClient.start();
                //图片点击事件，回到定位点
                mLocationClient.requestLocation();
                break;
            case R.id.cancelLocation:
                mLocationClient.stop();
                mBaiduMap.setMyLocationEnabled(false);
                break;
            case R.id.navigation:
                AlertDialog.Builder builder=new AlertDialog.Builder(map.this);
                builder.setTitle( "起始地：" );
                builder.setView( new EditText( map.this ) );
                builder.setTitle( "目的地：" );
                builder.setView( new EditText( map.this ) );
                builder.setPositiveButton( "确定",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id){
                        //mSearch.drivingSearch((new DrivingRoutePlanOption()).from(stNode).to(enNode));
                        mSearch = RoutePlanSearch.newInstance();
                        OnGetRoutePlanResultListener listener = new OnGetRoutePlanResultListener() {

                            public void onGetWalkingRouteResult(WalkingRouteResult result) {
                                //获取步行线路规划结果

                            }

                            @Override
                            public void onGetTransitRouteResult(TransitRouteResult transitRouteResult) {

                            }

                            @Override
                            public void onGetMassTransitRouteResult(MassTransitRouteResult massTransitRouteResult) {

                            }

                            @Override
                            public void onGetDrivingRouteResult(DrivingRouteResult drivingRouteResult) {
                                //获取驾车线路规划结果

                                if (drivingRouteResult.error == SearchResult.ERRORNO.NO_ERROR) {
                                    // mroute = result.getRouteLines().get(0);
                                    //mBaiduMap.clear();
                                    DrivingRouteOverlay overlay = new DrivingRouteOverlay(mBaiduMap);
                                    overlay.setData(drivingRouteResult.getRouteLines().get(0));
                                    overlay.addToMap();
                                    overlay.zoomToSpan();
                                }
                            }

                            @Override
                            public void onGetIndoorRouteResult(IndoorRouteResult indoorRouteResult) {

                            }

                            @Override
                            public void onGetBikingRouteResult(BikingRouteResult bikingRouteResult) {

                            }

                        };
                        mSearch.setOnGetRoutePlanResultListener(listener);
                        PlanNode stNode = PlanNode.withCityNameAndPlaceName("北京", "西二旗地铁站");

                        PlanNode enNode = PlanNode.withCityNameAndPlaceName("北京", "百度科技园");
                        mSearch.drivingSearch((new DrivingRoutePlanOption())

                                .from(stNode)
                                .to(enNode));

                    }
                });
                builder.show();
                break;
        }
        return true;
    }
    /*结束菜单栏*/


    /*开始定位 https://blog.csdn.net/weiye666/article/details/78930986*/

    //配置定位SDK参数
    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        int span = 1000;
        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation
        // .getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);
        option.setOpenGps(true); // 打开gps

        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤GPS仿真结果，默认需要
        mLocationClient.setLocOption(option);
    }


    //实现BDLocationListener接口,BDLocationListener为结果监听接口，异步获取定位结果
    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            latLng = new LatLng(location.getLatitude(), location.getLongitude());
            // 构造定位数据
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(100).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            // 设置定位数据
            mBaiduMap.setMyLocationData(locData);
            // 当不需要定位图层时关闭定位图层
            //mBaiduMap.setMyLocationEnabled(false);
            if (isFirstLoc) {
                isFirstLoc = false;
                LatLng ll = new LatLng(location.getLatitude(),
                        location.getLongitude());
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(ll).zoom(18.0f);
                mBaiduMap.animateMapStatus( MapStatusUpdateFactory.newMapStatus(builder.build()));

                if (location.getLocType() == BDLocation.TypeGpsLocation) {
                    // GPS定位结果
                    Toast.makeText(map.this, location.getAddrStr(), Toast.LENGTH_SHORT).show();
                } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {
                    // 网络定位结果
                    Toast.makeText(map.this, location.getAddrStr(), Toast.LENGTH_SHORT).show();

                } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {
                    // 离线定位结果
                    Toast.makeText(map.this, location.getAddrStr(), Toast.LENGTH_SHORT).show();

                } else if (location.getLocType() == BDLocation.TypeServerError) {
                    Toast.makeText(map.this, "服务器错误，请检查", Toast.LENGTH_SHORT).show();
                } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                    Toast.makeText(map.this, "网络错误，请检查", Toast.LENGTH_SHORT).show();
                } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                    Toast.makeText(map.this, "手机模式错误，请检查是否飞行", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    /*结束定位*/


    /*导航开始*/

    public void searchButtonProcess() {
        // 重置浏览节点的路线数据
        route = null;
        mBtnPre = (Button) findViewById(R.id.driver_pre);
        mBtnNext = (Button) findViewById(R.id.driver_next);
        mBtnPre.setVisibility(View.INVISIBLE);
        mBtnNext.setVisibility(View.INVISIBLE);


        mBaiduMap.clear();
        // 设置起终点信息，对于tranist search 来说，城市名无意义
        PlanNode stNode = PlanNode.withCityNameAndPlaceName(/*loaclcity*/"北京",/* start_edit.getText().toString()*/"西二旗地铁站");
        PlanNode enNode = PlanNode.withCityNameAndPlaceName(/*loaclcity*/"北京",/*, end_edit.getText().toString()*/"百度科技园");

        // 实际使用中请对起点终点城市进行正确的设定
        //if (v.getId() == R.id.driver_go) {
            //mSearch.drivingSearch((new DrivingRoutePlanOption()).from(stNode).to(enNode));
        mSearch.walkingSearch((new WalkingRoutePlanOption()).from(stNode).to(enNode));
        MyToast( "没问题" );
        /*} else if (v.getId() == R.id.transit) {
            mSearch.transitSearch((new TransitRoutePlanOption()).from(stNode).city(loaclcity).to(enNode));
        } else if (v.getId() == R.id.walk) {
            mSearch.walkingSearch((new WalkingRoutePlanOption()).from(stNode).to(enNode));
        } else if (v.getId() == R.id.bike) {
            mSearch.bikingSearch((new BikingRoutePlanOption()).from(stNode).to(enNode));
        }*/
    }

    /**
     * 节点浏览示例
     *
     * @param v
     */
    public void nodeClick(View v) {
        if (route == null || route.getAllStep() == null) {
            return;
        }
        if (nodeIndex == -1 && v.getId() == R.id.driver_pre) {
            return;
        }
        // 设置节点索引
        if (v.getId() == R.id.driver_next) {
            if (nodeIndex < route.getAllStep().size() - 1) {
                nodeIndex++;
            } else {
                return;
            }
        } else if (v.getId() == R.id.driver_pre) {
            if (nodeIndex > 0) {
                nodeIndex--;
            } else {
                return;
            }
        }
        // 获取节结果信息
        LatLng nodeLocation = null;
        String nodeTitle = null;
        Object step = route.getAllStep().get(nodeIndex);
        if (step instanceof DrivingRouteLine.DrivingStep) {
            nodeLocation = ((DrivingRouteLine.DrivingStep) step).getEntrance().getLocation();
            nodeTitle = ((DrivingRouteLine.DrivingStep) step).getInstructions();
        } else if (step instanceof WalkingRouteLine.WalkingStep) {
            nodeLocation = ((WalkingRouteLine.WalkingStep) step).getEntrance().getLocation();
            nodeTitle = ((WalkingRouteLine.WalkingStep) step).getInstructions();
        } else if (step instanceof TransitRouteLine.TransitStep) {
            nodeLocation = ((TransitRouteLine.TransitStep) step).getEntrance().getLocation();
            nodeTitle = ((TransitRouteLine.TransitStep) step).getInstructions();
        } else if (step instanceof BikingRouteLine.BikingStep) {
            nodeLocation = ((BikingRouteLine.BikingStep) step).getEntrance().getLocation();
            nodeTitle = ((BikingRouteLine.BikingStep) step).getInstructions();
        }

        if (nodeLocation == null || nodeTitle == null) {
            return;
        }
        // 移动节点至中心
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(nodeLocation));
        // show popup
        popupText = new TextView(map.this);
        popupText.setBackgroundResource(R.drawable.background1);
        popupText.setTextColor(0xFF000000);
        popupText.setText(nodeTitle);
        mBaiduMap.showInfoWindow(new InfoWindow(popupText, nodeLocation, 0));

    }

    /**
     * 切换路线图标，刷新地图使其生效 注意： 起终点图标使用中心对齐.
     */

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    OnGetRoutePlanResultListener listener = new OnGetRoutePlanResultListener() {
        public void onGetWalkingRouteResult(WalkingRouteResult result) {
            if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                MyToast( "抱歉，未找到结果" );
            }
            if (result.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
                // 起终点或途经点地址有岐义，通过以下接口获取建议查询信息
                // result.getSuggestAddrInfo()

                return;
            }
            if (result.error == SearchResult.ERRORNO.NO_ERROR) {

                nodeIndex = -1;
                mBtnPre.setVisibility( View.VISIBLE );
                mBtnNext.setVisibility( View.VISIBLE );
                route = result.getRouteLines().get( 0 );
                WalkingRouteOverlay overlay = new MyWalkingRouteOverlay( mBaiduMap );
                mBaiduMap.setOnMarkerClickListener( overlay );
                routeOverlay = overlay;
                overlay.setData( result.getRouteLines().get( 0 ) );
                overlay.addToMap();
                overlay.zoomToSpan();
            }

        }

        public void onGetTransitRouteResult(TransitRouteResult result) {

            if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                MyToast( "抱歉，未找到结果" );
            }
            if (result.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
                // 起终点或途经点地址有岐义，通过以下接口获取建议查询信息
                // result.getSuggestAddrInfo()
                return;
            }
            if (result.error == SearchResult.ERRORNO.NO_ERROR) {
                nodeIndex = -1;
                mBtnPre.setVisibility( View.VISIBLE );
                mBtnNext.setVisibility( View.VISIBLE );
                route = result.getRouteLines().get( 0 );
                TransitRouteOverlay overlay = new MyTransitRouteOverlay( mBaiduMap );
                mBaiduMap.setOnMarkerClickListener( overlay );
                routeOverlay = overlay;
                overlay.setData( result.getRouteLines().get( 0 ) );
                overlay.addToMap();
                overlay.zoomToSpan();
            }
        }

        @Override
        public void onGetMassTransitRouteResult(MassTransitRouteResult massTransitRouteResult) {

        }


        public void onGetDrivingRouteResult(DrivingRouteResult result) {
            if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                MyToast( "抱歉，未找到结果" );
            }
            if (result.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
                // 起终点或途经点地址有岐义，通过以下接口获取建议查询信息
                // result.getSuggestAddrInfo()
                return;
            }
            if (result.error == SearchResult.ERRORNO.NO_ERROR) {
                nodeIndex = -1;
                mBtnPre.setVisibility( View.VISIBLE );
                mBtnNext.setVisibility( View.VISIBLE );
                route = result.getRouteLines().get( 0 );
                DrivingRouteOverlay overlay = new MyDrivingRouteOverlay( mBaiduMap );
                routeOverlay = overlay;
                mBaiduMap.setOnMarkerClickListener( overlay );
                overlay.setData( result.getRouteLines().get( 0 ) );
                overlay.addToMap();
                overlay.zoomToSpan();
            }
        }

        @Override
        public void onGetIndoorRouteResult(IndoorRouteResult indoorRouteResult) {

        }


        public void onGetBikingRouteResult(BikingRouteResult bikingRouteResult) {
            if (bikingRouteResult == null || bikingRouteResult.error != SearchResult.ERRORNO.NO_ERROR) {
                MyToast( "抱歉，未找到结果" );
            }
            if (bikingRouteResult.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
                // 起终点或途经点地址有岐义，通过以下接口获取建议查询信息
                // result.getSuggestAddrInfo()
                return;
            }
            if (bikingRouteResult.error == SearchResult.ERRORNO.NO_ERROR) {
                nodeIndex = -1;
                mBtnPre.setVisibility( View.VISIBLE );
                mBtnNext.setVisibility( View.VISIBLE );
                route = bikingRouteResult.getRouteLines().get( 0 );
                BikingRouteOverlay overlay = new MyBikingRouteOverlay( mBaiduMap );
                routeOverlay = overlay;
                mBaiduMap.setOnMarkerClickListener( overlay );
                overlay.setData( bikingRouteResult.getRouteLines().get( 0 ) );
                overlay.addToMap();
                overlay.zoomToSpan();
            }
        }
    };

    // 定制RouteOverly
    private class MyDrivingRouteOverlay extends DrivingRouteOverlay {

        public MyDrivingRouteOverlay(BaiduMap baiduMap) {
            super(baiduMap);
        }

        @Override
        public BitmapDescriptor getStartMarker() {
            if (useDefaultIcon) {
                return BitmapDescriptorFactory.fromResource(R.drawable.background1);
            }
            return null;
        }

        @Override
        public BitmapDescriptor getTerminalMarker() {
            if (useDefaultIcon) {
                return BitmapDescriptorFactory.fromResource(R.drawable.background2);
            }
            return null;
        }
    }

    private class MyWalkingRouteOverlay extends WalkingRouteOverlay {

        public MyWalkingRouteOverlay(BaiduMap baiduMap) {
            super(baiduMap);
        }

        @Override
        public BitmapDescriptor getStartMarker() {
            if (useDefaultIcon) {
                return BitmapDescriptorFactory.fromResource(R.drawable.background1);
            }
            return null;
        }

        @Override
        public BitmapDescriptor getTerminalMarker() {
            if (useDefaultIcon) {
                return BitmapDescriptorFactory.fromResource(R.drawable.background2);
            }
            return null;
        }
    }

    private class MyTransitRouteOverlay extends TransitRouteOverlay {

        public MyTransitRouteOverlay(BaiduMap baiduMap) {
            super(baiduMap);
        }

        @Override
        public BitmapDescriptor getStartMarker() {
            if (useDefaultIcon) {
                return BitmapDescriptorFactory.fromResource(R.drawable.background1);
            }
            return null;
        }

        @Override
        public BitmapDescriptor getTerminalMarker() {
            if (useDefaultIcon) {
                return BitmapDescriptorFactory.fromResource(R.drawable.background2);
            }
            return null;
        }
    }

    private class MyBikingRouteOverlay extends BikingRouteOverlay {
        public MyBikingRouteOverlay(BaiduMap baiduMap) {
            super(baiduMap);
        }

        @Override
        public BitmapDescriptor getStartMarker() {
            if (useDefaultIcon) {
                return BitmapDescriptorFactory.fromResource(R.drawable.background1);
            }
            return null;
        }

        @Override
        public BitmapDescriptor getTerminalMarker() {
            if (useDefaultIcon) {
                return BitmapDescriptorFactory.fromResource(R.drawable.background2);
            }
            return null;
        }

    }

    @Override
    protected void onPause() {
        mMapView.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        mMapView.onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        /*mSearch.destroy();
        mMapView.onDestroy();
        super.onDestroy();*/
        super.onDestroy();
        try {
            mBaiduMap.setMyLocationEnabled(false);
//			mMapView.onDestroy();
        } catch (Exception e) {

        }

    }


    public void onMapClick(LatLng point) {
        mBaiduMap.hideInfoWindow();
    }


    public boolean onMapPoiClick(MapPoi poi) {
        return false;
    }

    public void MyToast(String s) {
        Toast.makeText(map.this, s, Toast.LENGTH_SHORT).show();
    }


    public void onReceivePoi(BDLocation poiLocation) {
    }



    /*导航结束*/
}
