<template>
	<view class="page">
		<login></login>
		<cu-custom v-show="isBack" bgColor="bg-blue" :isBack="isBack">
			<block slot="content" style="width: calc(100% - 100px);">{{activity && activity.bt}}</block>
		</cu-custom>

		<view class="vote-wrapper padding">
			<view class="vote-tt title" style="font-size: 18px;">
				{{activity && activity.bt}}
			</view>
			<view class="vote-tt" style="margin-top: 40upx;">
				考评对象：<text class="vote-subtt">{{obj.dx}}</text>
			</view>
			<view class="vote-tt">
				活动状态：<text class="vote-subtt">{{activity.sffb== '0' ? '尚未发布' : (activity.sffb== '1' ? '已发布' : '活动已结束')}}</text>
			</view>
			<view class="vote-tt">
				活动期限：<text class="vote-subtt">{{activity.kssj && activity.kssj.substring(0,11)}} 至 {{activity.kssj && activity.kssj.substring(0,11)}}</text>
			</view>
			<view class="vote-tt">
				活动说明：<text class="vote-subtt">{{activity.nr}}</text>
			</view>
			

			<view class="padding" style="margin-top: 40upx;">
				<view class="mark-item">
					<text class="mark-name">考评内容</text>
					<text class="mark-status">打分区间</text>
					<text class="mark-input">
						打分
					</text>
				</view>
				<view class="mark-item" v-for="(item, index) in activity.scoreoblist" :key="index">
					<text class="mark-name">{{item.mc}}</text>
					<view class="mark-status">
						<text class="mark-num">{{item.zdf}}</text>
						<view class="mark-range">
							<Range :min='rangeValues[index][0]' :width="180" :max='rangeValues[index][1]' v-model='item.val' :data-index="index"
							 @rangechange='onRangeChange'></Range>
						</view>
						<text class="mark-num">{{item.zgf}}</text>
					</view>
					<text class="mark-input">
						{{scores[index]}}
					</text>
				</view>

			</view>
			<view v-show="activity.sffb=='1' && curTime > strTime && curTime < endTime" style="margin-top: 50upx;text-align: center;">
				<button class="cu-btn bg-blue shadow-blur" @tap="saveRes">保存打分结果</button>
			</view>

		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				hdid: null,
				obid: null,
				isBack: false,
				rangeValues: [], //当前区间值
				slideMin: 30, //slider最小值
				slideMax: 60, //slider最大值
				scores: [],
				activity:{},
				obj: {},
				curTime: '',
				strTime: '',
				endTime: '',
				data: [{
						name: '专业能力专业能力专业能力',
						min: 0,
						max: 100,
					},
					{
						name: '协调能力',
						min: 10,
						max: 100
					},
					{
						name: '管理能力',
						min: 40,
						max: 100
					},
					{
						name: '应变能力',
						min: 60,
						max: 100
					}
				]
			};
		},
		mounted() {

		},
		onLoad(params) {
			this.hdid = params.hdid;
			this.obid = params.obid;
			this.user = this.getGlobalUser() || {};
			if(this.hdid && this.obid) {
				this.getData();
			}
			if (getCurrentPages().length > 1) {
				this.isBack = true;
			}
		},
		onShow() {
			this.user = this.getGlobalUser() || {};
			if(this.hdid && this.obid) {
				this.getData();
			}
		},
		methods: {
			getData() {
				this.$api.post('/theme/findAllInfoById', {
					id: this.hdid
				}).then(res => {
					if (res.resCode == 200) {
						this.activity = res.data;
						this.curTime = new Date().getTime(); 
						this.strTime = new Date(Date.parse(this.activity.kssj.replace(/-/g, "/"))).getTime(); // 时间戳
						this.endTime = new Date(Date.parse(this.activity.jssj.replace(/-/g, "/"))).getTime(); // 时间戳
						this.activity.scorelist.forEach(item => {
							if(item.id == this.obid) {
								this.obj = item
								return
							}
						})
						this.data = this.activity.scoreoblist;
						this.rangeValues = [];
						this.scores = [];
						this.data.forEach(item => {
							this.rangeValues.push([item.zdf, item.zgf]);
							item.val = item.zdf;
							this.scores.push(item.zgf)
						})
					}
				})
			},
			saveRes() {
				console.log(this.data)
				let res = this.data.map(item => {
					return {
						df: item.val,
						oid: item.id,
						qid: this.obid,
						pid: this.user.id
					}
				})
				this.$api.post('/score/score', JSON.stringify(res)).then(res => {
					if(res.resCode == 200) {
						uni.showToast({
							title: '保存成功！',
							icon: 'none'
						})
						uni.navigateBack()
					}else {
						uni.showToast({
							title: '保存失败！',
							icon: 'none'
						})
					}
				})
				console.log(res)
			},
			onRangeChange: function(e) {
				this.scores = this.data.map(item => {
					return item.val;
				})
			},
			gotoMarkTitle() {
				uni.navigateTo({
					url: '../../pages/markTitle/markTitle'
				})
			}

		}
	}
</script>

<style lang="scss">
	.vote-wrapper {
		padding: 20px 30upx;

		.vote-tt {
			font-size: 14px;
			color: #333;
			line-height: 2;

			.vote-subtt {
				color: #666;
			}
		}
	}

	.mark-item {
		display: flex;
		flex-wrap: nowrap;
		justify-content: space-around;
		margin-bottom: 20upx;
		align-items: center;

		.mark-name,
		.mark-input,
		.mark-status {
			text-align: center;
			// flex: 1;
		}

		.mark-name {
			width: 230upx;
		}

		.mark-status {
			width: 320upx;
			display: flex;
			justify-content: space-around;
			align-items: center;

			.mark-num {
				width: 50upx;
			}

			.mark-range {
				width: 180upx;
			}
		}

		.mark-input {
			width: 130upx;
		}
	}
</style>
