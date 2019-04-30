<template>
	<view class="page">
		<login :loginFormShow="loginFormShow" @hide="loginHide"></login>
		<cu-custom v-show="isBack" bgColor="bg-blue" :isBack="isBack">
			<block slot="content" style="width: calc(100% - 100px);">{{activity && activity.bt}}</block>
		</cu-custom>

		<view class="vote-wrapper padding">
			<view class="vote-tt title" style="font-size: 18px;">
				{{activity && activity.bt}}<view v-show="activity.tpzt == '1'" class='cu-tag line-orange radius' style="margin-left: 20upx;">已完成投票</view>
			</view>
			<view class="vote-tt" style="margin-top: 40upx;">
				活动状态：<text class="vote-subtt">{{activity.sffb== '0' ? '尚未发布' : (activity.sffb== '1' ? '已发布' : '活动已结束')}}</text>
			</view>
			<view class="vote-tt">
				活动期限：<text class="vote-subtt">{{activity.kssj && activity.kssj.substring(0,11)}} 至 {{activity.jssj && activity.jssj.substring(0,11)}}</text>
			</view>
			<view class="vote-tt">
				活动说明：<text class="vote-subtt">{{activity.nr}}</text>
			</view>
			<view v-show="activity.sffb != '2'" class="padding" style="margin-top: 40upx;">
				<view class="mark-item">
					<text class="mark-name">参评对象</text>
					<text class="mark-status">打分状态</text>
					<text class="mark-opt">操作</text>
				</view>
				<view class="mark-item" v-for="(obj, index) in activity.scorelist">
					<text class="mark-name">{{obj.dx}}</text>
					<text class="mark-status">{{obj.zt ? '已完成' : '未完成'}}</text>
					<text class="mark-opt">
						<text v-show="!obj.zt" @tap="gotoMarkTitle(index)" class='cu-tag radius light bg-blue'>打分</text>
					</text>
				</view>
				<view v-show="user.js == '1' && activity.sffb == '0'" class="padding-sm" style="text-align: center;">
					<button class="cu-btn bg-blue" @tap="createCode" style="margin-top: 60upx;">发布本次活动并生成二维码</button>
				</view>

				<view v-show="user.js == '1' && activity.sffb == '1' && curTime > strTime && curTime < endTime" class="padding-sm" style="text-align: center;">
					<button class="cu-btn bg-blue" @tap="updateFbzt(2)">提前结束活动</button>
				</view>
				<view v-show="user.js == '1' && activity.sffb == '1'" class="padding-sm" style="text-align: center;">
					<button class="cu-btn bg-blue" @tap="gotoCodePage">查看活动二维码</button>
				</view>
			</view>

			<view v-show="activity.sffb == '2'" class="padding">
				活动已结束
				<image src="../../static/end.png" mode="" style="width: 150upx; height: 150upx;"></image>
				<view class="padding-sm" style="text-align: center;">
					<button class="cu-btn bg-blue" @tap="gotoCountPage">查看活动统计结果</button>
				</view>
			</view>

		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				loginFormShow: false,
				isBack: false,
				hdid: null,
				user: {},
				activity: {},
				curTime: '',
				endTime: '',
				strTime: ''
			};
		},
		mounted() {

		},
// 		computed: {
// 			logined() {
// 				return this.$store.state.logined
// 			}
// 		},
// 		watch: {
// 			logined: {
// 				handler(newVal, oldVal) {
// 					this.user = this.getGlobalUser() || {};
// 					if (newVal) {
// 						this.getData();
// 					}
// 				},
// 				immediate: true
// 			}
// 		},
		onShow() {
			if(this.getLogined() || this.hdid) {
				this.getData();
			}
		},
		onLoad(params) {
			this.hdid = params.hdid;
			this.user = this.getGlobalUser() || {};
			if (getCurrentPages().length > 1) {
				this.isBack = true;
			}
			if(!this.getLogined()) {
				this.loginFormShow = true;
			}
		},
		methods: {
			loginHide() {
				this.loginFormShow = false;
				if (this.getLogined() || this.hdid) {
					this.getData();
				}
			},
			gotoCountPage() {
				uni.navigateTo({
					url: '../../pages/markCount/markCount?hdid=' + this.hdid
				})
			},
			getData() {
				uni.showLoading({
					title: '加载中'
				});
				this.$api.post('/theme/findAllInfoById', {
					id: this.hdid
				}).then(res => {
					uni.hideLoading()
					if (res.resCode == 200) {
						this.activity = res.data;
						this.curTime = new Date().getTime(); 
						this.strTime = new Date(Date.parse(this.activity.kssj.replace(/-/g, "/"))).getTime(); // 时间戳
						this.endTime = new Date(Date.parse(this.activity.jssj.replace(/-/g, "/"))).getTime(); // 时间戳
					}else if(res.resCode == 100) {
						this.loginFormShow = true;
					}
				})
			},
			createCode() {
				this.$api.post('/theme/updateFbzt', {id: this.hdid, fbzt: 1}).then(res => {
					if(res.resCode == 200) {
						uni.showToast({
							title: '发布成功！',
							icon: 'none'
						})
						this.gotoCodePage();
					}else if(res.resCode == 100) {
						this.loginFormShow = true;
					}
				})
			},
			updateFbzt(zt) {
				this.$api.post('/theme/updateFbzt', {id: this.hdid, fbzt: zt}).then(res => {
					if(res.resCode == 200) {
						uni.showToast({
							title: '操作成功！',
							icon: 'none'
						})
					}else if(res.resCode == 100) {
						this.loginFormShow = true;
					}
				})
			},
			gotoCodePage() {
				uni.navigateTo({
					url:'../../pages/code/code?lx=mark&id=' + this.hdid + '&title=' + this.activity.bt
						+ '&kssj=' + this.activity.kssj.substring(0, 11) + '&jssj=' + this.activity.jssj.substring(0, 11)
				})
			},
			gotoMarkTitle(index) {
				uni.navigateTo({
					url: '../../pages/markTitle/markTitle?hdid=' + this.hdid + '&obid=' + this.activity.scorelist[index].id
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
		align-items: center;
		margin-bottom: 20upx;

		.mark-name,
		.mark-status,
		.mark-opt {
			width: 200upx;
			text-align: center;
			flex: 1;
		}
	}
</style>
