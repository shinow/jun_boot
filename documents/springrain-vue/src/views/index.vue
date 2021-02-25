<template>
	<div class="dashboard-editor-container">

		<panel-group @handleSetLineChartData="handleSetLineChartData" />

		<el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
			<line-chart :chart-data="lineChartData" />
		</el-row>

		<el-row :gutter="32">
			<el-col :xs="24" :sm="24" :lg="8">
				<div class="chart-wrapper">
					<raddar-chart />
				</div>
			</el-col>
			<el-col :xs="24" :sm="24" :lg="8">
				<div class="chart-wrapper">
					<pie-chart />
				</div>
			</el-col>
			<el-col :xs="24" :sm="24" :lg="8">
				<div class="chart-wrapper">
					<bar-chart />
				</div>
			</el-col>
		</el-row>

		<!-- 地图 -->
		<el-row>
			<el-col :xs="24" :sm="24" :lg="24">
				<div class="mod-config">
					<el-form :inline="true">
						<el-row :gutter="24">
							<el-col :span="8">
								<el-card shadow="hover" style="color: #409EFF;font-size: 14px;font-weight: bold;">
									车辆总数：<span style="font-size: 16px;">{{devNum}}</span>
								</el-card>
							</el-col>
							<el-col :span="8">
								<el-card shadow="hover" style="color: green;font-size: 14px;font-weight: bold;">
									在线车辆： <span style="font-size: 16px;">{{devOnline}}</span>
								</el-card>
							</el-col>
							<el-col :span="8">
								<el-card shadow="hover" style="color: red;font-size: 14px;font-weight: bold;">
									离线车辆： <span style="font-size: 16px;">{{devUnline}}</span>
								</el-card>
							</el-col>
						</el-row>
					</el-form>

					<div class="amap-wrapper">
						<div id="container" class="map"></div>
					</div>
				</div>
			</el-col>
		</el-row>

    <!-- 地图 -->
    <el-row>
      <el-col :xs="24" :sm="24" :lg="24">
        <div class="mod-config">
          <div class="amap-wrapper">
            <div id="container1" class="map"></div>
          </div>
        </div>
      </el-col>
    </el-row>

	</div>
</template>

<script>
	// 组件初始化
	import Vue from 'vue'
	import VueAMap from 'vue-amap'
	Vue.use(VueAMap);
	VueAMap.initAMapApiLoader({
		key: '8ab5e8a5a6e366ea860f5ccffce298cf',
		v: '1.4.4',
	})

  import { lazyAMapApiLoaderInstance } from 'vue-amap';

	import PanelGroup from './dashboard/PanelGroup'
	import LineChart from './dashboard/LineChart'
	import RaddarChart from './dashboard/RaddarChart'
	import PieChart from './dashboard/PieChart'
	import BarChart from './dashboard/BarChart'

	const lineChartData = {
		newVisitis: {
			expectedData: [100, 120, 161, 134, 105, 160, 165],
			actualData: [120, 82, 91, 154, 162, 140, 145]
		},
		messages: {
			expectedData: [200, 192, 120, 144, 160, 130, 140],
			actualData: [180, 160, 151, 106, 145, 150, 130]
		},
		purchases: {
			expectedData: [80, 100, 121, 104, 105, 90, 100],
			actualData: [120, 90, 100, 138, 142, 130, 130]
		},
		shoppings: {
			expectedData: [130, 140, 141, 142, 145, 150, 160],
			actualData: [120, 82, 91, 154, 162, 140, 130]
		}
	}

  var that;
	export default {
		name: 'Index',
		components: {
			PanelGroup,
			LineChart,
			RaddarChart,
			PieChart,
			BarChart
		},
		data() {
			return {
				lineChartData: lineChartData.newVisitis,

        map: null,
        zoom: 6,
        lng: 0,
        lat: 0,
        center: [],
        markers: [],
        cluster: [],
        points: [],
        gridSize: 80,
        infoWindow: null,
        devNum: 0,
        devOnline: 0,
        devUnline: 0,

        map1: null,
        zoom1: 6,
        lng1: 116.478935,
        lat1: 39.997761,
        passedPolyline: null,
        polyline: null,
        marker: null,
        lineArr: [],
			}
		},
    created() {
      that = this;
      lazyAMapApiLoaderInstance.load().then(() => {
        that._getMap();
        that._getMap1();
        var point = []
        let index = 0;
        let basePosition = [121.59996, 31.197646];
        while (++index <= 30) {
          point.push({
            lnglat: [basePosition[0] + 0.01 * index, basePosition[1]],
          });
        }
        that.points = point;
        that.getMarkers();
        that.addCluster(0);
      });
    },
		methods: {

      getLineArr: function() {
        that.lineArr = [[116.478935,39.997761],[116.478939,39.997825],[116.478912,39.998549],[116.478912,39.998549],[116.478998,39.998555],[116.478998,39.998555],[116.479282,39.99856],[116.479658,39.998528],[116.480151,39.998453],[116.480784,39.998302],[116.480784,39.998302],[116.481149,39.998184],[116.481573,39.997997],[116.481863,39.997846],[116.482072,39.997718],[116.482362,39.997718],[116.483633,39.998935],[116.48367,39.998968],[116.484648,39.999861]];
        that.handleData();
      },

      handleData: function() {
        that.marker = new AMap.Marker({
          map: that.map1,
          position: [116.484648,39.999861],
          icon: "https://webapi.amap.com/images/car.png",
          offset: new AMap.Pixel(-26, -13),
          autoRotation: true,
          angle:-90,
        });

        // 绘制轨迹
        that.polyline = new AMap.Polyline({
          map: that.map1,
          path: that.lineArr,
          showDir:true,
          strokeColor: "#28F",  //线颜色
          // strokeOpacity: 1,     //线透明度
          strokeWeight: 6,      //线宽
          // strokeStyle: "solid"  //线样式
        });

        that.passedPolyline = new AMap.Polyline({
          map: that.map1,
          // path: lineArr,
          strokeColor: "#AF5",  //线颜色
          // strokeOpacity: 1,     //线透明度
          strokeWeight: 6,      //线宽
          // strokeStyle: "solid"  //线样式
        });

        that.marker.on('moving', function (e) {
          that.passedPolyline.setPath(e.passedPath);
        });

        that.map1.setFitView();
      },

      _getMap1: function() {
        that.map1 = new AMap.Map("container1", {
          resizeEnable: true,
          center: [that.lng1, that.lat1],
          zoom: that.zoom1,
          mapStyle: 'amap://styles/macaron',
        });
        AMap.plugin(['AMap.ToolBar', 'AMap.Scale', 'AMap.MapType', 'AMap.Geolocation', 'AMap.MarkerClusterer'], function() { //异步加载插件
          that.map1.addControl(new AMap.ToolBar());
          that.map1.addControl(new AMap.Scale());
          that.map1.addControl(new AMap.MapType());
          var geolocation = new AMap.Geolocation({
            enableHighAccuracy: true, //是否使用高精度定位，默认:true
            timeout: 10000, //超过10秒后停止定位，默认：5s
            buttonPosition: 'RB', //定位按钮的停靠位置
            buttonOffset: new AMap.Pixel(10, 20), //定位按钮与设置的停靠位置的偏移量，默认：Pixel(10, 20)
          });
          that.map1.addControl(geolocation);
          that.getLineArr();
        });
      },

		  /**
       * 地图位置
       */
      getMarkers: function() {
        for (var i = 0; i < that.points.length; i += 1) {
          var marker = new AMap.Marker({
            position: that.points[i]['lnglat'],
            // content: '<div style="background-color: hsla(180, 100%, 50%, 0.7); height: 24px; width: 24px; border: 1px solid hsl(180, 100%, 40%); border-radius: 12px; box-shadow: hsl(180, 100%, 50%) 0px 0px 1px;"></div>',
            offset: new AMap.Pixel(-15, -15)
          });
          //构建信息窗体中显示的内容
          var info = [];
          info.push("<div class='content-window-card'>");
          info.push("<div>sn：" + that.points[i]['sn'] + "</div>");
          info.push("<div class='input-item'>lon：" + that.points[i]['lon'] + "</div>");
          info.push("<div class='input-item'>lat：" + that.points[i]['lat'] + "</div>");
          info.push("<div class='input-item'>alt：" + that.points[i]['alt'] + "</div>");
          info.push("<div class='input-item'>speed：" + that.points[i]['speed'] + "</div>");
          info.push("</div>")
          marker.content = info.join("");
          marker.on('click', that.markerClick);

          var status = that.points[i]['status'];
          if (status == 1) {
            marker.setAnimation('AMAP_ANIMATION_BOUNCE');
          }
          that.markers.push(marker)
        }
      },

      markerClick: function(e) {
        that.infoWindow.setContent(e.target.content);
        that.infoWindow.open(that.map, e.target.getPosition());
      },

      _renderClusterMarker: function(context) {
        var count = that.markers.length;
        var factor = Math.pow(context.count / count, 1 / 18);
        var div = document.createElement('div');
        var Hue = 180 - factor * 180;
        var bgColor = 'hsla(' + Hue + ',100%,50%,0.7)';
        var fontColor = 'hsla(' + Hue + ',100%,20%,1)';
        var borderColor = 'hsla(' + Hue + ',100%,40%,1)';
        var shadowColor = 'hsla(' + Hue + ',100%,50%,1)';
        div.style.backgroundColor = bgColor;
        var size = Math.round(30 + Math.pow(context.count / count, 1 / 5) * 20);
        div.style.width = div.style.height = size + 'px';
        div.style.border = 'solid 1px ' + borderColor;
        div.style.borderRadius = size / 2 + 'px';
        div.style.boxShadow = '0 0 1px ' + shadowColor;
        div.innerHTML = context.count;
        div.style.lineHeight = size + 'px';
        div.style.color = fontColor;
        div.style.fontSize = '14px';
        div.style.textAlign = 'center';
        context.marker.setOffset(new AMap.Pixel(-size / 2, -size / 2));
        context.marker.setContent(div)
      },

      addCluster: function(tag) {
        if (tag == 2) { //完全自定义
          that.cluster = new AMap.MarkerClusterer(that.map, that.markers, {
            gridSize: that.gridSize,
            renderClusterMarker: that._renderClusterMarker
          });
        } else if (tag == 1) { //自定义图标
          var sts = [{
            url: "https://a.amap.com/jsapi_demos/static/images/blue.png",
            size: new AMap.Size(32, 32),
            offset: new AMap.Pixel(-16, -16)
          }, {
            url: "https://a.amap.com/jsapi_demos/static/images/green.png",
            size: new AMap.Size(32, 32),
            offset: new AMap.Pixel(-16, -16)
          }, {
            url: "https://a.amap.com/jsapi_demos/static/images/orange.png",
            size: new AMap.Size(36, 36),
            offset: new AMap.Pixel(-18, -18)
          }, {
            url: "https://a.amap.com/jsapi_demos/static/images/red.png",
            size: new AMap.Size(48, 48),
            offset: new AMap.Pixel(-24, -24)
          }, {
            url: "https://a.amap.com/jsapi_demos/static/images/darkRed.png",
            size: new AMap.Size(48, 48),
            offset: new AMap.Pixel(-24, -24)
          }];
          that.cluster = new AMap.MarkerClusterer(that.map, that.markers, {
            styles: sts,
            gridSize: that.gridSize
          });
        } else { //默认样式
          that.cluster = new AMap.MarkerClusterer(that.map, that.markers, {
            gridSize: that.gridSize
          });
        }
      },

      _getMap: function() {
        that.map = new AMap.Map("container", {
          resizeEnable: true,
          center: [that.lng, that.lat],
          zoom: that.zoom,
          mapStyle: 'amap://styles/macaron',
        });
        that.infoWindow = new AMap.InfoWindow({
          offset: new AMap.Pixel(0, -20)
        });
        AMap.plugin(['AMap.ToolBar', 'AMap.Scale', 'AMap.MapType', 'AMap.Geolocation', 'AMap.MarkerClusterer'], function() { //异步加载插件
          that.map.addControl(new AMap.ToolBar());
          that.map.addControl(new AMap.Scale());
          that.map.addControl(new AMap.MapType());
          var geolocation = new AMap.Geolocation({
            enableHighAccuracy: true, //是否使用高精度定位，默认:true
            timeout: 10000, //超过10秒后停止定位，默认：5s
            buttonPosition: 'RB', //定位按钮的停靠位置
            buttonOffset: new AMap.Pixel(10, 20), //定位按钮与设置的停靠位置的偏移量，默认：Pixel(10, 20)
          });
          that.map.addControl(geolocation);
          geolocation.getCurrentPosition(function(status, result) {
            if (status == 'complete') {
              if (result && result.position) {
                // 设置经度
                that.lng = result.position.lng
                // 设置维度
                that.lat = result.position.lat
                // 设置坐标
                that.center = [that.lng, that.lat]
                // 页面渲染好后
                that.$nextTick()
              }
            } else {
              console.log(result);
            }
          });
        });
      },

			handleSetLineChartData(type) {
				this.lineChartData = lineChartData[type]
			}
		},
	}
</script>

<style lang="scss" scoped>
	.dashboard-editor-container {
		padding: 32px;
		background-color: rgb(240, 242, 245);
		position: relative;

		.chart-wrapper {
			background: #fff;
			padding: 16px 16px 0;
			margin-bottom: 32px;
		}
	}

	@media (max-width:1024px) {
		.chart-wrapper {
			padding: 8px;
		}
	}

	.map {
		height: 500px;
		width: 100%;
		margin-top: 10px;
	}

	.content-window-card {
		margin: 10px 10px 0px 10px;
		font-size: 14px;
		font-weight: 500;
	}
</style>
